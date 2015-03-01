package nikita.frolkin.ist.work1.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author tervaskanto on 01.03.15
 */
@XmlRootElement(name = "persons")
public class FPersonListWrapperN {
    private List<FPersonN> fPersonsn;

    public List<FPersonN> fGetPersonsN() {
        return fPersonsn;
    }

    public void fSetPersonsN(List<FPersonN> fPersonsn) {
        this.fPersonsn = fPersonsn;
    }
}
