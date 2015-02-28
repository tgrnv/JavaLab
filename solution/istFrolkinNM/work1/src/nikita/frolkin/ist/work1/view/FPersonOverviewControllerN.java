package nikita.frolkin.ist.work1.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import nikita.frolkin.ist.work1.FMainN;
import nikita.frolkin.ist.work1.model.FPersonN;
import org.controlsfx.dialog.Dialogs;

/**
 * @author tervaskanto on 24.02.15
 */
public class FPersonOverviewControllerN {
    @FXML
    private TableView<FPersonN> fpersonTablen;
    @FXML
    private TableColumn<FPersonN, String> ffirstNameColumnn;
    @FXML
    private TableColumn<FPersonN, String> flastNameColumnn;
    @FXML
    private Label ffirstNameLabeln;
    @FXML
    private Label flastNameLabeln;
    @FXML
    private Label fstreetLabeln;
    @FXML
    private Label fpostalCodeLabeln;
    @FXML
    private Label fcityLabeln;
    @FXML
    private Label fbirthdayLabeln;
    private FMainN fmainn;

    @FXML
    private void initialize() {
        ffirstNameColumnn.setCellValueFactory(fcellDatan -> fcellDatan.getValue().fFirstNamePropertyN());
        flastNameColumnn.setCellValueFactory(fcellDatan -> fcellDatan.getValue().fLastNamePropertyN());
        fpersonTablen.getSelectionModel().selectedItemProperty().addListener((fobservablen, foldValuen, fnewValuen) -> fShowPersonDetailsN(fnewValuen));
    }

    public void fSetMainN(FMainN fmainn) {
        this.fmainn = fmainn;
        fpersonTablen.setItems(fmainn.fGetPersonDataN());
    }

    private void fShowPersonDetailsN(FPersonN fpersonn) {
        if (fpersonn != null) {
            ffirstNameLabeln.setText(fpersonn.fGetFirstNameN());
            flastNameLabeln.setText(fpersonn.fGetLastNameN());
            fstreetLabeln.setText(fpersonn.fGetStreetN());
            fpostalCodeLabeln.setText(Integer.toString(fpersonn.fGetPostalCodeN()));
            fcityLabeln.setText(fpersonn.fGetCityN());
            fbirthdayLabeln.setText(fpersonn.fGetBirthdayN().toString());
        }
    }

    @FXML
    private void fHandleDeletePersonN() {
        int fselectedIndexn = fpersonTablen.getSelectionModel().getSelectedIndex();
        if (fselectedIndexn > -1) fpersonTablen.getItems().remove(fselectedIndexn);
    }

    @FXML
    private void fHandleNewPersonN() {
        FPersonN fTempPerson = new FPersonN();
        boolean fOkClickedn = fmainn.fEditDialogN(fTempPerson);
        if (fOkClickedn)
            fmainn.fGetPersonDataN().add(fTempPerson);
    }

    @FXML
    private void fHandleEditPerson() {
        FPersonN fselectedPersonn = fpersonTablen.getSelectionModel().getSelectedItem();
        if (fselectedPersonn != null) {
            boolean fOkClicked = fmainn.fEditDialogN(fselectedPersonn);
            if (fOkClicked)
                fShowPersonDetailsN(fselectedPersonn);
        } else {
            Dialogs.create()
                    .title("No Selection")
                    .masthead("No Person Selected")
                    .message("Please select a person in the table")
                    .showWarning();
        }
    }
}
