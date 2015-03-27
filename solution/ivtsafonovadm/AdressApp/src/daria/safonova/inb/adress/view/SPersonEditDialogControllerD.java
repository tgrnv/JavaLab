package daria.safonova.inb.adress.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.controlsfx.dialog.Dialogs;

import daria.safonova.inb.adress.model.SPersonD;
import daria.safonova.inb.adress.util.SDateUtilD;

public class SPersonEditDialogControllerD {

	@FXML
	private TextField firstNameField;
	@FXML
	private TextField lastNameField;
	@FXML
	private TextField streetField;
	@FXML
	private TextField postalCodeField;
	@FXML
	private TextField cityField;
	@FXML
	private TextField birthdayField;

	private Stage sdialogStaged;
	private SPersonD spersond;
	private boolean sokClickedd = false;

	@FXML
	private void initialize() {

	}

	public void setDialogStage(Stage dialogStage) {
		this.sdialogStaged = dialogStage;
	}

	public void setPerson(SPersonD person) {
		this.spersond = person;

		firstNameField.setText(person.getFirstName());
		lastNameField.setText(person.getLastName());
		streetField.setText(person.getStreet());
		postalCodeField.setText(Integer.toString(person.getPostalCode()));
		cityField.setText(person.getCity());
		birthdayField.setText(SDateUtilD.sformatD(person.getBirthday()));
		birthdayField.setPromptText("dd.mm.yyyy");
	}

	public boolean isOkClicked() {
		return sokClickedd;
	}

	@FXML
	private void handleOk() {
		if (sisInputValidD()) {
			spersond.setFirstName(firstNameField.getText());
			spersond.setLastName(lastNameField.getText());
			spersond.setStreet(streetField.getText());
			spersond.setPostalCode(Integer.parseInt(postalCodeField.getText()));
			spersond.setCity(cityField.getText());
			spersond.setBirthday(SDateUtilD.sparseD(birthdayField.getText()));

			sokClickedd = true;
			sdialogStaged.close();
		}
	}

	@FXML
	private void handleCancel() {
		sdialogStaged.close();
	}

	private boolean sisInputValidD() {
		String errorMessage = "";

		if (firstNameField.getText() == null
				|| firstNameField.getText().length() == 0) {
			errorMessage += "No valid first name!\n";
		}
		if (lastNameField.getText() == null
				|| lastNameField.getText().length() == 0) {
			errorMessage += "No valid last name!\n";
		}
		if (streetField.getText() == null
				|| streetField.getText().length() == 0) {
			errorMessage += "No valid street!\n";
		}

		if (postalCodeField.getText() == null
				|| postalCodeField.getText().length() == 0) {
			errorMessage += "No valid postal code!\n";
		} else {
			// try to parse the postal code into an int.
			try {
				Integer.parseInt(postalCodeField.getText());
			} catch (NumberFormatException e) {
				errorMessage += "No valid postal code (must be an integer)!\n";
			}
		}

		if (cityField.getText() == null || cityField.getText().length() == 0) {
			errorMessage += "No valid city!\n";
		}

		if (birthdayField.getText() == null
				|| birthdayField.getText().length() == 0) {
			errorMessage += "No valid birthday!\n";
		} else {
			if (!SDateUtilD.svalidDateD(birthdayField.getText())) {
				errorMessage += "No valid birthday. Use the format dd.mm.yyyy!\n";
			}
		}

		if (errorMessage.length() == 0) {
			return true;
		} else {
			Dialogs.create().title("Invalid Fields")
					.masthead("Please correct invalid fields")
					.message(errorMessage).showError();
			return false;
		}
	}
}