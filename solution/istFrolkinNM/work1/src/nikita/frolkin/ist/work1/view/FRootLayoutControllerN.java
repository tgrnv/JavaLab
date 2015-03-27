package nikita.frolkin.ist.work1.view;

import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import nikita.frolkin.ist.work1.FMainN;

import java.io.File;

/**
 * @author tervaskanto on 01.03.15
 */
public class FRootLayoutControllerN {
    private FileChooser.ExtensionFilter fXmlFiltern = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");


    @FXML
    private void fHandleNewN() {
        FMainN.fGetInstanceN().fGetPersonDataN().clear();
        FMainN.fGetInstanceN().fSetPersonFilePathN(null);
    }

    @FXML
    private void fHandleOpenN() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(fXmlFiltern);
        File file = fileChooser.showOpenDialog(FMainN.fGetInstanceN().fGetPrimaryStageN());
        if (file != null)
            FMainN.fGetInstanceN().fLoadDataFromFileN(file);
    }

    @FXML
    private void fHandleSaveN() {
        File personFile = FMainN.fGetInstanceN().fGetPersonFilePathN();
        if (personFile != null)
            FMainN.fGetInstanceN().fSaveDataToFileN(personFile);
        else
            fHandleSaveAsN();
    }

    @FXML
    private void fHandleSaveAsN() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(fXmlFiltern);
        File file = fileChooser.showSaveDialog(FMainN.fGetInstanceN().fGetPrimaryStageN());
        if (file != null) {
            if (!file.getPath().endsWith(".xml"))
                file = new File(file.getPath() + ".xml");
            FMainN.fGetInstanceN().fSaveDataToFileN(file);
        }
    }

    @FXML
    private void fHandleExitN() {
        System.exit(0);
    }
}
