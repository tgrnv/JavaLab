package nikita.frolkin.ist.work1.model;

import javafx.beans.property.*;
import nikita.frolkin.ist.work1.util.FLocalDateAdapterN;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

/**
 * @author tervaskanto on 24.02.15
 */
@XmlAccessorType(XmlAccessType.NONE)
public class FPersonN {
    private final StringProperty ffirstNamen;
    private final StringProperty flastNamen;
    private final StringProperty fstreetn;
    private final IntegerProperty fpostalCoden;
    private final StringProperty fcityn;
    private final ObjectProperty<LocalDate> fbirthdayn;

    public FPersonN() {
        this(null, null);
    }

    public FPersonN(String firstName, String lastName) {
        this.ffirstNamen = new SimpleStringProperty(firstName);
        this.flastNamen = new SimpleStringProperty(lastName);
        this.fstreetn = new SimpleStringProperty();
        this.fpostalCoden = new SimpleIntegerProperty();
        this.fcityn = new SimpleStringProperty();
        this.fbirthdayn = new SimpleObjectProperty<>();
    }

    @XmlElement(name = "firstName")
    public String getFirstName() {
        return ffirstNamen.get();
    }

    public void setFirstName(String ffirstNamen) {
        this.ffirstNamen.set(ffirstNamen);
    }

    public StringProperty fFirstNamePropertyN() {
        return ffirstNamen;
    }

    @XmlElement(name = "lastName")
    public String getLastName() {
        return flastNamen.get();
    }

    public void setLastName(String flastNamen) {
        this.flastNamen.set(flastNamen);
    }

    public StringProperty fLastNamePropertyN() {
        return flastNamen;
    }

    @XmlElement(name = "street")
    public String getStreet() {
        return fstreetn.get();
    }

    public void setStreet(String fstreetn) {
        this.fstreetn.set(fstreetn);
    }

    public StringProperty fStreetPropertyN() {
        return fstreetn;
    }

    @XmlElement(name = "postalCode")
    public int getPostalCode() {
        return fpostalCoden.get();
    }

    public void setPostalCode(int fpostalCoden) {
        this.fpostalCoden.set(fpostalCoden);
    }

    public IntegerProperty fPostalCodePropertyN() {
        return fpostalCoden;
    }

    @XmlElement(name = "city")
    public String getCity() {
        return fcityn.get();
    }

    public void setCity(String fcityn) {
        this.fcityn.set(fcityn);
    }

    public StringProperty fCityPropertyN() {
        return fcityn;
    }

    @XmlJavaTypeAdapter(FLocalDateAdapterN.class)
    public LocalDate getBirthday() {
        return fbirthdayn.get();
    }

    public void setBirthday(LocalDate fbirthdayn) {
        this.fbirthdayn.set(fbirthdayn);
    }

    public ObjectProperty<LocalDate> fBirthdayPropertyN() {
        return fbirthdayn;
    }
}
