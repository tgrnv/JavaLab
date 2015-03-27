package daria.safonova.inb.adress;

import daria.safonova.inb.adress.model.PersonListWrapper;
import daria.safonova.inb.adress.model.SPersonD;
import daria.safonova.inb.adress.view.BirthdayStatisticsController;
import daria.safonova.inb.adress.view.RootLayoutController;
import daria.safonova.inb.adress.view.SPersonEditDialogControllerD;
import daria.safonova.inb.adress.view.SPersonOverviewControllerD;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.controlsfx.dialog.Dialogs;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SMainAppD extends Application {

	private Stage sprimaryStaged;
	private BorderPane srootLayoutd;
	private ObservableList<SPersonD> spersonDatad = FXCollections
			.observableArrayList();

	public SMainAppD() {
		// Add some sample data
		spersonDatad.add(new SPersonD("Hans", "Muster"));
		spersonDatad.add(new SPersonD("Ruth", "Mueller"));
		spersonDatad.add(new SPersonD("Heinz", "Kurz"));
		spersonDatad.add(new SPersonD("Cornelia", "Meier"));
		spersonDatad.add(new SPersonD("Werner", "Meyer"));
		spersonDatad.add(new SPersonD("Lydia", "Kunz"));
		spersonDatad.add(new SPersonD("Anna", "Best"));
		spersonDatad.add(new SPersonD("Stefan", "Meier"));
		spersonDatad.add(new SPersonD("Martin", "Mueller"));
	}

	public ObservableList<SPersonD> getPersonData() {
		return spersonDatad;
	}

	@Override
	public void start(Stage primaryStage) {
		this.sprimaryStaged = primaryStage;
		this.sprimaryStaged.setTitle("AdressApp");
		this.sprimaryStaged.getIcons().add(
				new Image("file:resources/images/1425923986_Address_Book.png"));

		sinitRootLayoutD();

		sshowPersonOverviewD();

	}

	private void sinitRootLayoutD() {

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(SMainAppD.class
					.getResource("view/RootLayout.fxml"));
			srootLayoutd = (BorderPane) loader.load();

			Scene scene = new Scene(srootLayoutd);
			sprimaryStaged.setScene(scene);

			RootLayoutController controller = loader.getController();
			controller.setMainApp(this);

			sprimaryStaged.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

		File file = sgetPersonFilePathD();
		if (file != null) {
			sloadPersonDataFromFileD(file);
		}
	}

	private void sshowPersonOverviewD() {
		try {

			// Load person overview.
			FXMLLoader sloaderd = new FXMLLoader();
			sloaderd.setLocation(SMainAppD.class
					.getResource("view/PersonOverview.fxml"));
			AnchorPane spersonOverviewd = (AnchorPane) sloaderd.load();

			// Set person overview into the center of root layout.
			srootLayoutd.setCenter(spersonOverviewd);

			SPersonOverviewControllerD controller = sloaderd.getController();
			controller.setMainApp(this);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public Stage sgetPrimaryStageD() {
		return sprimaryStaged;
	}

	public static void main(String[] args) {
		launch(args);
	}

	public boolean showPersonEditDialog(SPersonD person) {
		try {

			FXMLLoader sloaderd = new FXMLLoader();
			sloaderd.setLocation(SMainAppD.class
					.getResource("view/PersonEditDialog.fxml"));
			AnchorPane spaged = (AnchorPane) sloaderd.load();

			Stage sdialogStaged = new Stage();
			sdialogStaged.setTitle("Edit Person");
			sdialogStaged.initModality(Modality.WINDOW_MODAL);
			sdialogStaged.initOwner(sprimaryStaged);
			Scene sscened = new Scene(spaged);
			sdialogStaged.setScene(sscened);

			SPersonEditDialogControllerD controller = sloaderd.getController();
			controller.setDialogStage(sdialogStaged);
			controller.setPerson(person);

			sdialogStaged.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public File sgetPersonFilePathD() {
		Preferences sprefsd = Preferences.userNodeForPackage(SMainAppD.class);
		String sfilePathd = sprefsd.get("filePath", null);
		if (sfilePathd != null) {
			return new File(sfilePathd);
		} else {
			return null;
		}
	}

	public void ssetPersonFilePathD(File file) {
		Preferences sprefsd = Preferences.userNodeForPackage(SMainAppD.class);
		if (file != null) {
			sprefsd.put("filePath", file.getPath());

			sprimaryStaged.setTitle("AdressApp - " + file.getName());
		} else {
			sprefsd.remove("filePath");
			sprimaryStaged.setTitle("AdressApp");
		}
	}

	public void sloadPersonDataFromFileD(File file) {
		try {
			JAXBContext scontextd = JAXBContext
					.newInstance(PersonListWrapper.class);
			Unmarshaller sumd = scontextd.createUnmarshaller();
			PersonListWrapper swrapperd = (PersonListWrapper) sumd
					.unmarshal(file);

			spersonDatad.clear();
			spersonDatad.addAll(swrapperd.getPersons());

			// Save the file path to the registry.
			ssetPersonFilePathD(file);

		} catch (Exception e) { // catches ANY exception
			Dialogs.create()
					.title("Error")
					.masthead(
							"Could not load data from file:\n" + file.getPath())
					.showException(e);
		}
	}

	public void savePersonDataToFile(File file) {
		try {
			JAXBContext context = JAXBContext
					.newInstance(PersonListWrapper.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			// Wrapping our person data.
			PersonListWrapper wrapper = new PersonListWrapper();
			wrapper.setPersons(spersonDatad);

			// Marshalling and saving XML to the file.
			m.marshal(wrapper, file);

			// Save the file path to the registry.
			ssetPersonFilePathD(file);
		} catch (Exception e) { // catches ANY exception
			Dialogs.create()
					.title("Error")
					.masthead("Could not save data to file:\n" + file.getPath())
					.showException(e);
		}
	}

	public void sshowBirthdayStatisticsD() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(SMainAppD.class
					.getResource("view/BirthdayStatistics.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Birthday Statistics");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(sprimaryStaged);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			BirthdayStatisticsController controller = loader.getController();
			controller.ssetPersonDataD(spersonDatad);

			dialogStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
