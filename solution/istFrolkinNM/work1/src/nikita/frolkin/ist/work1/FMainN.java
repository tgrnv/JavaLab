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
import nikita.frolkin.ist.work1.view.FRootLayoutControllerN;
import org.controlsfx.dialog.Dialogs;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

public class FMainN extends Application {
    public static final String fVIEW_SUBDIRn = "view";
    private Stage fprimaryStagen;
    private BorderPane frootLayoutn;
    private ObservableList<FPersonN> fpersonDatan = FXCollections.observableArrayList();
    private Preferences fPrefsn = Preferences.userNodeForPackage(FMainN.class);

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.fprimaryStagen = primaryStage;
        fprimaryStagen.setTitle("Address Book");
        String fImagePathn = String.format(
                "%2$s%1$s%3$s%1$s%4$s",
                File.separator,
                "resources",
                "images",
                "address_book_icon.png"
        );
        this.fprimaryStagen.getIcons().add(new Image("file:" + fImagePathn));
        fInitRootN();
        fSetPersonOverviewN();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void fInitRootN() {
        try {
            FXMLLoader floadern = new FXMLLoader();
            floadern.setLocation(getClass().getResource(String.format(
                    "%s%s%s",
                    fVIEW_SUBDIRn,
                    File.separator,
                    "RootLayout.fxml"
            )));
            frootLayoutn = floadern.load();
            fprimaryStagen.setScene(new Scene(frootLayoutn));
            fprimaryStagen.setResizable(false);
            ((FRootLayoutControllerN)floadern.getController()).fSetMainN(this);
            fprimaryStagen.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        File fFilen = fGetPersonFilePathN();
        if (fFilen != null) {
            fLoadDataFromFileN(fFilen);
        }
    }

    private void fSetPersonOverviewN() {
        try {
            FXMLLoader floadern = new FXMLLoader();
            floadern.setLocation(getClass().getResource(String.format(
                    "%s%s%s",
                    fVIEW_SUBDIRn,
                    File.separator,
                    "PersonOverview.fxml"
            )));
            frootLayoutn.setCenter(floadern.load());
            FPersonOverviewControllerN fcontrollern = floadern.getController();
            fcontrollern.fSetMainN(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fSetPersonDataN() {
        fpersonDatan.add(new FPersonN("Hans", "Muster"));
        fpersonDatan.add(new FPersonN("Ruth", "Mueller"));
        fpersonDatan.add(new FPersonN("Heinz", "Kurz"));
        fpersonDatan.add(new FPersonN("Cornelia", "Meier"));
        fpersonDatan.add(new FPersonN("Werner", "Meyer"));
        fpersonDatan.add(new FPersonN("Lydia", "Kunz"));
        fpersonDatan.add(new FPersonN("Anna", "Best"));
        fpersonDatan.add(new FPersonN("Stefan", "Meier"));
        fpersonDatan.add(new FPersonN("Martin", "Mueller"));
    }

    public boolean fEditDialogN(FPersonN fPersonn) {
        try {
            FXMLLoader floadern = new FXMLLoader();
            floadern.setLocation(getClass().getResource(String.format(
                    "%s%s%s",
                    fVIEW_SUBDIRn,
                    File.separator,
                    "PersonEditDialog.fxml"
            )));
            Stage fdialogStagen = new Stage();
            fdialogStagen.setTitle("Edit Person");
            fdialogStagen.initModality(Modality.WINDOW_MODAL);
            fdialogStagen.initOwner(fprimaryStagen);
            fdialogStagen.setScene(new Scene(floadern.load()));
            FPersonEditControllerN fcontrollern = floadern.getController();
            fcontrollern.fSetDialogStageN(fdialogStagen);
            fcontrollern.fSetPersonN(fPersonn);
            fdialogStagen.showAndWait();
            return fcontrollern.fIsOkClickedN();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ObservableList<FPersonN> fGetPersonDataN() {
        return fpersonDatan;
    }

    public File fGetPersonFilePathN() {
        String ffilePathn = fPrefsn.get("filePath", null);
        return ffilePathn != null ? new File(ffilePathn) : null;
    }

    public void fSetPersonFilePathN(File ffilen) {
        if (ffilen != null)
            fPrefsn.put("filePath", ffilen.getPath());
        else
            fPrefsn.remove("filePath");
    }

    public void fLoadDataFromFileN(File ffilen) {
        try {
            JAXBContext fContextn = JAXBContext.newInstance(FPersonListWrapperN.class);
            Unmarshaller fUmn = fContextn.createUnmarshaller();
            FPersonListWrapperN fWrappern = (FPersonListWrapperN) fUmn.unmarshal(ffilen);
            fpersonDatan.clear();
            fpersonDatan.addAll(fWrappern.fGetPersonsN());
            fSetPersonFilePathN(ffilen);
        } catch (Exception e) {
            Dialogs.create()
                    .title("Error")
                    .masthead("Could not load data from file:\n" + ffilen.getPath())
                    .showException(e);
        }
    }

    public void fSaveDataToFileN(File ffilen) {
        try {
            JAXBContext fContextn = JAXBContext.newInstance(FPersonListWrapperN.class);
            Marshaller fMn = fContextn.createMarshaller();
            fMn.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            FPersonListWrapperN fWrappern = new FPersonListWrapperN();
            fWrappern.fSetPersonsN(fpersonDatan);
            fMn.marshal(fWrappern, ffilen);
            fSetPersonFilePathN(ffilen);
        } catch (Exception e) {
            Dialogs.create()
                    .title("Error")
                    .masthead("Could not save data to file:\n" + ffilen.getPath())
                    .showException(e);
        }
    }

    public Stage fGetPrimaryStageN() {
        return fprimaryStagen;
    }
}
