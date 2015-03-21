package nikita.frolkin.ist.work1.model;

import javafx.collections.FXCollections;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author tervaskanto on 01.03.15
 */
@XmlRootElement(name = "persons")
@XmlAccessorType(XmlAccessType.NONE)
public class FPersonListWrapperN {
    @XmlElement(name = "person")
    public List<FPersonN> fPersonsn;

    public List<FPersonN> fGetPersonsN() {
        if (fPersonsn == null) fPersonsn = FXCollections.observableArrayList();
        return fPersonsn;
    }

    public void fSetPersonsN(List<FPersonN> persons) {
        this.fPersonsn = persons;
    }
}
