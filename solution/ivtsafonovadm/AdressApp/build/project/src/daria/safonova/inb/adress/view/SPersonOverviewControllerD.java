package daria.safonova.inb.adress.view;

import org.controlsfx.dialog.Dialogs;

import daria.safonova.inb.adress.SMainAppD;
import daria.safonova.inb.adress.model.SPersonD;
import daria.safonova.inb.adress.util.SDateUtilD;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class SPersonOverviewControllerD {
	@FXML
	private TableView<SPersonD> personTable;
	@FXML
	private TableColumn<SPersonD, String> firstNameColumn;
	@FXML
	private TableColumn<SPersonD, String> lastNameColumn;

	@FXML
	private Label firstNameLabel;
	@FXML
	private Label lastNameLabel;
	@FXML
	private Label streetLabel;
	@FXML
	private Label postalCodeLabel;
	@FXML
	private Label cityLabel;
	@FXML
	private Label birthdayLabel;

	// Reference to the main application.
	private SMainAppD smainAppd;

	public SPersonOverviewControllerD() {
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */

	@FXML
	private void initialize() {
		// Initialize the person table with the two columns.
		firstNameColumn.setCellValueFactory(cellData -> cellData.getValue()
				.firstNameProperty());
		lastNameColumn.setCellValueFactory(cellData -> cellData.getValue()
				.lastNameProperty());

		sshowPersonDetailsD(null);

		personTable
				.getSelectionModel()
				.selectedItemProperty()
				.addListener(
						(observable, oldValue, newValue) -> sshowPersonDetailsD(newValue));

	}

	public void setMainApp(SMainAppD mainApp) {
		this.smainAppd = mainApp;

		// Add observable list data to the table
		personTable.setItems(mainApp.getPersonData());
	}

	private void sshowPersonDetailsD(SPersonD person) {
		if (person != null) {
			firstNameLabel.setText(person.getFirstName());
			lastNameLabel.setText(person.getLastName());
			streetLabel.setText(person.getStreet());
			postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
			cityLabel.setText(person.getCity());
			birthdayLabel.setText(SDateUtilD.sformatD(person.getBirthday()));
		} else {
			firstNameLabel.setText("");
			lastNameLabel.setText("");
			streetLabel.setText("");
			postalCodeLabel.setText("");
			cityLabel.setText("");
			birthdayLabel.setText("");
		}
	}

	@FXML
	private void shandleDeletePerson() {
		int sselectedIndexd = personTable.getSelectionModel()
				.getSelectedIndex();
		if (sselectedIndexd >= 0) {
			personTable.getItems().remove(sselectedIndexd);
		} else {
			Dialogs.create().title("No selection")
					.masthead("No Person Selected")
					.message("Please select a person in the table.")
					.showWarning();
		}
	}

	@FXML
	private void handleNewPerson() {
		SPersonD tempPerson = new SPersonD();
		boolean okClicked = smainAppd.showPersonEditDialog(tempPerson);
		if (okClicked) {
			smainAppd.getPersonData().add(tempPerson);
		}
	}

	@FXML
	private void handleEditPerson() {
		SPersonD selectedPerson = personTable.getSelectionModel()
				.getSelectedItem();
		if (selectedPerson != null) {
			boolean okClicked = smainAppd.showPersonEditDialog(selectedPerson);
			if (okClicked) {
				sshowPersonDetailsD(selectedPerson);
			}

		} else {
			// Nothing selected.
			Dialogs.create().title("No Selection")
					.masthead("No Person Selected")
					.message("Please select a person in the table.")
					.showWarning();
		}
	}

}
