package nikita.frolkin.ist.work1.view;

import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import nikita.frolkin.ist.work1.FMainN;

import java.io.File;

/**
 * @author tervaskanto on 01.03.15
 */
public class FRootLayoutControllerN {
    private FMainN fMainn;

    public void fSetMainN(FMainN fMainn) {
        this.fMainn = fMainn;
    }

    @FXML
    private void fHandleNewN() {
        fMainn.fGetPersonDataN().clear();
        fMainn.fSetPersonFilePathN(null);
    }

    @FXML
    private void fHandleOpenN() {
        FileChooser ffileChoosern = new FileChooser();
        ffileChoosern.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml"));
        File fFilen = ffileChoosern.showOpenDialog(fMainn.fGetPrimaryStageN());
        if (fFilen != null)
            fMainn.fLoadDataFromFileN(fFilen);
    }

    @FXML
    private void fHandleSaveN() {
        File fPersonFilen = fMainn.fGetPersonFilePathN();
        if (fPersonFilen != null)
            fMainn.fSaveDataToFileN(fPersonFilen);
        else
            fHandleSaveAsN();
    }

    @FXML
    private void fHandleSaveAsN() {
        FileChooser fFileChoosern = new FileChooser();
        fFileChoosern.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml"));
        File fFilen = fFileChoosern.showSaveDialog(fMainn.fGetPrimaryStageN());
        if (fFilen != null) {
            if (!fFilen.getPath().endsWith(".xml"))
                fFilen = new File(fFilen.getPath() + ".xml");
            fMainn.fSaveDataToFileN(fFilen);
        }
    }

    @FXML
    private void fHandleExitN() {
        System.exit(0);
    }
}
