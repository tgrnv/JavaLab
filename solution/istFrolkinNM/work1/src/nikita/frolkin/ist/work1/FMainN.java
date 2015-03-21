package nikita.frolkin.ist.work1;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import nikita.frolkin.ist.work1.model.FPersonListWrapperN;
import nikita.frolkin.ist.work1.model.FPersonN;
import nikita.frolkin.ist.work1.view.FPersonEditControllerN;
import nikita.frolkin.ist.work1.view.FPersonOverviewControllerN;
import org.controlsfx.dialog.Dialogs;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.prefs.Preferences;

public class FMainN extends Application {
    public static final String fVIEW_SUBDIRn = "view";
    private Stage fprimaryStagen;
    private BorderPane frootLayoutn;
    private ObservableList<FPersonN> fpersonDatan = FXCollections.observableArrayList();
    private Preferences fPrefsn = Preferences.userNodeForPackage(FMainN.class);
    private JAXBContext fJaxbContextn;
    private FPersonListWrapperN fPersonsWrappern = new FPersonListWrapperN();
    private static FMainN fInstancen;

    public static FMainN fGetInstanceN() {
        return fInstancen;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.fprimaryStagen = primaryStage;
        fprimaryStagen.setTitle("Address Book");
        String imagePath = String.format(
                "%2$s%1$s%3$s%1$s%4$s",
                File.separator,
                "resources",
                "images",
                "address_book_icon.png"
        );
        this.fprimaryStagen.getIcons().add(new Image("file:" + imagePath));
        fJaxbContextn = JAXBContext.newInstance(FPersonListWrapperN.class);
        fInitRootN();
        fSetPersonOverviewN();
        fInstancen = this;
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void fInitRootN() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(fGetLocationFXMLN("RootLayout.fxml"));
            frootLayoutn = loader.load();
            fprimaryStagen.setScene(new Scene(frootLayoutn));
            fprimaryStagen.setResizable(false);
            fprimaryStagen.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        File file = fGetPersonFilePathN();
        if (file != null) {
            fLoadDataFromFileN(file);
        }
    }

    private void fSetPersonOverviewN() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(fGetLocationFXMLN("PersonOverview.fxml"));
            frootLayoutn.setCenter(loader.load());
            FPersonOverviewControllerN controller = loader.getController();
            controller.fSetItemsN(fGetPersonDataN());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean fEditDialogN(FPersonN person) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(fGetLocationFXMLN("PersonEditDialog.fxml"));
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(fprimaryStagen);
            dialogStage.setScene(new Scene(loader.load()));
            FPersonEditControllerN controller = loader.getController();
            controller.fSetDialogStageN(dialogStage);
            controller.fSetPersonN(person);
            dialogStage.showAndWait();
            return controller.fIsOkClickedN();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private URL fGetLocationFXMLN(String fxmlName) {
        return getClass().getResource(String.format(
                "%s%s%s",
                fVIEW_SUBDIRn,
                File.separator,
                fxmlName.endsWith(".fxml") ? fxmlName : fxmlName + ".fxml"
        ));
    }

    public ObservableList<FPersonN> fGetPersonDataN() {
        return fpersonDatan;
    }

    public File fGetPersonFilePathN() {
        String filePath = fPrefsn.get("filePath", null);
        return filePath != null ? new File(filePath) : null;
    }

    public void fSetPersonFilePathN(File file) {
        try {
            fPrefsn.put("filePath", file.getPath());
        } catch (NullPointerException e) {
            fPrefsn.remove("filePath");
        }
    }

    public void fLoadDataFromFileN(File file) {
        try {
            Unmarshaller unmarshaller = fJaxbContextn.createUnmarshaller();
            FPersonListWrapperN persons = (FPersonListWrapperN) unmarshaller.unmarshal(file);
            fPersonsWrappern.fSetPersonsN(persons.fGetPersonsN());
            fpersonDatan.clear();
            fpersonDatan.addAll(fPersonsWrappern.fGetPersonsN());
            fSetPersonFilePathN(file);
        } catch (Exception e) {
            Dialogs.create()
                    .title("Error")
                    .masthead("Could not load data from file:\n" + file.getPath())
                    .showException(e);
        }
    }

    public void fSaveDataToFileN(File file) {
        try {
            Marshaller marshaller = fJaxbContextn.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            fPersonsWrappern.fSetPersonsN(fpersonDatan);
            marshaller.marshal(fPersonsWrappern, file);
            fSetPersonFilePathN(file);
        } catch (Exception e) {
            Dialogs.create()
                    .title("Error")
                    .masthead("Could not save data to file:\n" + file.getPath())
                    .showException(e);
        }
    }

    public Stage fGetPrimaryStageN() {
        return fprimaryStagen;
    }
}
