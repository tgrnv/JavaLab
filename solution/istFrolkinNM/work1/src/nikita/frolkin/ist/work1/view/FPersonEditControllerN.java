package nikita.frolkin.ist.work1.view;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import nikita.frolkin.ist.work1.model.FPersonN;
import nikita.frolkin.ist.work1.util.FDateUtilN;
import org.controlsfx.dialog.Dialogs;

/**
 * @author tervaskanto on 01.03.15
 */
public class FPersonEditControllerN {
    @FXML
    private TextField ffirstNameFieldn;
    @FXML
    private TextField flastNameFieldn;
    @FXML
    private TextField fstreetFieldn;
    @FXML
    private TextField fpostalCodeFieldn;
    @FXML
    private TextField fcityFieldn;
    @FXML
    private DatePicker fbirthdayFieldn;
    private Stage fdialogStagen;
    private FPersonN fpersonn;
    private boolean fOkClickedn = false;

    @FXML
    private void initialize() {}

    public void fSetDialogStageN(Stage dialogStage) {
        this.fdialogStagen = dialogStage;
    }

    public void fSetPersonN(FPersonN fpersonn) {
        this.fpersonn = fpersonn;
        ffirstNameFieldn.setText(fpersonn.getFirstName());
        flastNameFieldn.setText(fpersonn.getLastName());
        fstreetFieldn.setText(fpersonn.getStreet());
        fpostalCodeFieldn.setText(Integer.toString(fpersonn.getPostalCode()));
        fcityFieldn.setText(fpersonn.getCity());
    }

    public boolean fIsOkClickedN() {
        return fOkClickedn;
    }

    @FXML
    private void fHandleOkN() {
        if (fIsInputValidN()) {
            fpersonn.setFirstName(ffirstNameFieldn.getText());
            fpersonn.setLastName(flastNameFieldn.getText());
            fpersonn.setStreet(fstreetFieldn.getText());
            fpersonn.setPostalCode(Integer.parseInt(fpostalCodeFieldn.getText()));
            fpersonn.setCity(fcityFieldn.getText());
            fpersonn.setBirthday(fbirthdayFieldn.getValue());
            fOkClickedn = true;
            fdialogStagen.close();
        }
    }

    @FXML
    private void fHandleCancelN() {
        fdialogStagen.close();
    }

    private String fCheckEmptyTextFieldN(String pattern, String needToValidate, String info) {
        if (needToValidate == null || !needToValidate.matches(pattern))
            return info;
        else
            return "";
    }

    private boolean fIsInputValidN() {
        StringBuilder errorMessage = new StringBuilder();
        errorMessage.append(fCheckEmptyTextFieldN("^[A-Za-z- ]+$", ffirstNameFieldn.getText(), "No valid first name!\n"));
        errorMessage.append(fCheckEmptyTextFieldN("^[A-Za-z- ]+$", flastNameFieldn.getText(), "No valid last name!\n"));
        errorMessage.append(fCheckEmptyTextFieldN("^[A-Za-z- ,]+$", fstreetFieldn.getText(), "No valid street!\\n"));
        errorMessage.append(fCheckEmptyTextFieldN("^[A-Za-z- ]+$", fcityFieldn.getText(), "No valid city!\n"));
        errorMessage.append(fCheckEmptyTextFieldN("^[0-9]{4,6}$", fpostalCodeFieldn.getText(), "No valid postal code!\n"));
        if (errorMessage.length() == 0)
            return true;
        else {
            Dialogs.create()
                .title("Invalid fields")
                .masthead("Please correct invalid fields")
                .message(errorMessage.toString())
                .showError();
            return false;
        }
    }
}
