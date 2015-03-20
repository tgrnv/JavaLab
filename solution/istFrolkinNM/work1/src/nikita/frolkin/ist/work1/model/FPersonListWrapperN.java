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
    private List<FPersonN> fPersonsn;

    @XmlElement(name = "person")
    public List<FPersonN> getPersons() {
        return fPersonsn != null ? fPersonsn : FXCollections.observableArrayList();
    }

    public void setPersons(List<FPersonN> persons) {
        this.fPersonsn = persons;
    }
}
