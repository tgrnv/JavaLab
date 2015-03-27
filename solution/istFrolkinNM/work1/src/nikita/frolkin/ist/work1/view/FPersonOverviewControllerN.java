package nikita.frolkin.ist.work1.view;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import nikita.frolkin.ist.work1.FMainN;
import nikita.frolkin.ist.work1.model.FPersonN;
import org.controlsfx.dialog.Dialogs;

import java.time.format.DateTimeFormatter;

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
    public static final String fDATE_PATTERNn = "dd.MM.yyyy";
    public static final DateTimeFormatter fformattern = DateTimeFormatter.ofPattern(fDATE_PATTERNn);

    @FXML
    private void initialize() {
        ffirstNameColumnn.setCellValueFactory(firstNameCellData -> firstNameCellData.getValue().fFirstNamePropertyN());
        flastNameColumnn.setCellValueFactory(lastNameCellData -> lastNameCellData.getValue().fLastNamePropertyN());
        fpersonTablen.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> fShowPersonDetailsN(newValue));
    }

    public void fSetItemsN(ObservableList<FPersonN> personsList) {
        fpersonTablen.setItems(personsList);
    }

    private void fShowPersonDetailsN(FPersonN fpersonn) {
        if (fpersonn != null) {
            ffirstNameLabeln.setText(fpersonn.getFirstName());
            flastNameLabeln.setText(fpersonn.getLastName());
            fstreetLabeln.setText(fpersonn.getStreet());
            fpostalCodeLabeln.setText(Integer.toString(fpersonn.getPostalCode()));
            fcityLabeln.setText(fpersonn.getCity());
            fbirthdayLabeln.setText(fpersonn.getBirthday().format(fformattern));
        } else {
            fClearN();
        }
    }

    private void fClearN() {
        ffirstNameLabeln.setText(null);
        flastNameLabeln.setText(null);
        fstreetLabeln.setText(null);
        fpostalCodeLabeln.setText(null);
        fcityLabeln.setText(null);
        fbirthdayLabeln.setText(null);
    }

    @FXML
    private void fHandleDeletePersonN() {
        int fselectedIndexn = fpersonTablen.getSelectionModel().getSelectedIndex();
        if (fselectedIndexn > -1) fpersonTablen.getItems().remove(fselectedIndexn);
    }

    @FXML
    private void fHandleNewPersonN() {
        FPersonN fTempPerson = new FPersonN();
        boolean fOkClickedn = FMainN.fGetInstanceN().fEditDialogN(fTempPerson);
        if (fOkClickedn)
            FMainN.fGetInstanceN().fGetPersonDataN().add(fTempPerson);
    }

    @FXML
    private void fHandleEditPerson() {
        FPersonN fselectedPersonn = fpersonTablen.getSelectionModel().getSelectedItem();
        if (fselectedPersonn != null) {
            boolean fOkClicked = FMainN.fGetInstanceN().fEditDialogN(fselectedPersonn);
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
