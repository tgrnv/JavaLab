package nikita.frolkin.ist.work1.view;

import javafx.fxml.FXML;
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
    private TextField fbirthdayFieldn;
    private Stage fdialogStagen;
    private FPersonN fpersonn;
    private boolean fOkClickedn = false;

    @FXML
    private void initialize() {}

    public void fSetDialogStageN(Stage fdialogStagen) {
        this.fdialogStagen = fdialogStagen;
    }

    public void fSetPersonN(FPersonN fpersonn) {
        this.fpersonn = fpersonn;
        ffirstNameFieldn.setText(fpersonn.fGetFirstNameN());
        flastNameFieldn.setText(fpersonn.fGetLastNameN());
        fstreetFieldn.setText(fpersonn.fGetStreetN());
        fpostalCodeFieldn.setText(Integer.toString(fpersonn.fGetPostalCodeN()));
        fcityFieldn.setText(fpersonn.fGetCityN());
        fbirthdayFieldn.setText(FDateUtilN.fFormatN(fpersonn.fGetBirthdayN()));
        fbirthdayFieldn.setPromptText(FDateUtilN.fDATE_PATTERNn);
    }

    public boolean fIsOkClickedN() {
        return fOkClickedn;
    }

    @FXML
    private void fHandleOkN() {
        if (fIsInputValidN()) {
            fpersonn.fSetFirstNameN(ffirstNameFieldn.getText());
            fpersonn.fSetLastNameN(flastNameFieldn.getText());
            fpersonn.fSetStreetN(fstreetFieldn.getText());
            fpersonn.fSetPostalCodeN(Integer.parseInt(fpostalCodeFieldn.getText()));
            fpersonn.fSetCityN(fcityFieldn.getText());
            fpersonn.fSetBirthdayN(FDateUtilN.fParseN(fbirthdayFieldn.getText()));
            fOkClickedn = true;
            fdialogStagen.close();
        }
    }

    @FXML
    private void fHandleCancelN() {
        fdialogStagen.close();
    }

    private boolean fIsInputValidN() {
        StringBuilder fErrorMessagen = new StringBuilder();
        if (ffirstNameFieldn.getText() == null || ffirstNameFieldn.getText().length() == 0)
            fErrorMessagen.append("No valid first name!\n");
        if (flastNameFieldn.getText() == null || flastNameFieldn.getText().length() == 0)
            fErrorMessagen.append("No valid last name!\n");
        if (fstreetFieldn.getText() == null || fstreetFieldn.getText().length() == 0)
            fErrorMessagen.append("No valid street!\n");
        if (fpostalCodeFieldn.getText() == null || fpostalCodeFieldn.getText().length() == 0)
            fErrorMessagen.append("No valid postal code!\n");
        else
            try {
                Integer.parseInt(fpostalCodeFieldn.getText());
            } catch (NumberFormatException e) {
                fErrorMessagen.append("No valid postal code!\n");
            }
        if (fcityFieldn.getText() == null || fcityFieldn.getText().length() == 0)
            fErrorMessagen.append("No valid city!\n");
        if (fbirthdayFieldn.getText() == null || fbirthdayFieldn.getText().length() == 0)
            fErrorMessagen.append("No valid birthday!\n");
        else
            if (!FDateUtilN.fValidDateN(fbirthdayFieldn.getText()))
                fErrorMessagen.append("No valid birthday. Must be: dd.mm.yyyy\n");
        if (fErrorMessagen.length() == 0)
            return true;
        else {
            Dialogs.create()
                .title("Invalid fields")
                .masthead("Please correct invalid fields")
                .message(fErrorMessagen.toString())
                .showError();
            return false;
        }
    }
}
