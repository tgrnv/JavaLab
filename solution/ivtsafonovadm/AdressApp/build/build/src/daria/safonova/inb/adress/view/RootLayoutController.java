package daria.safonova.inb.adress.view;

import java.io.File;

import javafx.fxml.FXML;
import javafx.stage.FileChooser;

import org.controlsfx.dialog.Dialogs;

import daria.safonova.inb.adress.SMainAppD;

public class RootLayoutController {
	private SMainAppD smainAppd;

	public void setMainApp(SMainAppD mainApp) {
		this.smainAppd = mainApp;
	}

	@FXML
	private void shandleNewD() {
		smainAppd.getPersonData().clear();
		smainAppd.ssetPersonFilePathD(null);
	}

	@FXML
	private void shandleOpenD() {
		FileChooser fileChooser = new FileChooser();

		// Set extension filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
				"XML files (*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);

		File file = fileChooser.showOpenDialog(smainAppd.sgetPrimaryStageD());

		if (file != null) {
			smainAppd.sloadPersonDataFromFileD(file);
		}
	}

	@FXML
	private void handleSave() {
		File personFile = smainAppd.sgetPersonFilePathD();
		if (personFile != null) {
			smainAppd.savePersonDataToFile(personFile);
		} else {
			handleSaveAs();
		}
	}

	@FXML
	private void handleSaveAs() {
		FileChooser fileChooser = new FileChooser();

		// Set extension filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
				"XML files (*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);

		// Show save file dialog
		File file = fileChooser.showSaveDialog(smainAppd.sgetPrimaryStageD());

		if (file != null) {
			if (!file.getPath().endsWith(".xml")) {
				file = new File(file.getPath() + ".xml");
			}
			smainAppd.savePersonDataToFile(file);
		}
	}

	@FXML
	private void handleAbout() {
		Dialogs.create().title("AddressApp").masthead("About")
				.message("Author: Safonova Daria").showInformation();
	}

	@FXML
	private void handleExit() {
		System.exit(0);
	}

	@FXML
	private void handleShowBirthdayStatistics() {
		smainAppd.sshowBirthdayStatisticsD();
	}
}
