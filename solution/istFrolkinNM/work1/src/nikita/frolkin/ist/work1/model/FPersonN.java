package nikita.frolkin.ist.work1.model;

import javafx.beans.property.*;

import java.time.LocalDate;

/**
 * @author tervaskanto on 24.02.15
 */
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

    public FPersonN(String ffirstNamen, String flastNamen) {
        this.ffirstNamen = new SimpleStringProperty(ffirstNamen);
        this.flastNamen = new SimpleStringProperty(flastNamen);
        this.fstreetn = new SimpleStringProperty("some street");
        this.fpostalCoden = new SimpleIntegerProperty(1234);
        this.fcityn = new SimpleStringProperty("some sity");
        this.fbirthdayn = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
    }

    public String fGetFirstNameN() {
        return ffirstNamen.get();
    }

    public void fSetFirstNameN(String ffirstNamen) {
        this.ffirstNamen.set(ffirstNamen);
    }

    public StringProperty fFirstNamePropertyN() {
        return ffirstNamen;
    }

    public String fGetLastNameN() {
        return flastNamen.get();
    }

    public void fSetLastNameN(String flastNamen) {
        this.flastNamen.set(flastNamen);
    }

    public StringProperty fLastNamePropertyN() {
        return flastNamen;
    }

    public String fGetStreetN() {
        return fstreetn.get();
    }

    public void fSetStreetN(String fstreetn) {
        this.fstreetn.set(fstreetn);
    }

    public StringProperty fStreetPropertyN() {
        return fstreetn;
    }

    public int fGetPostalCodeN() {
        return fpostalCoden.get();
    }

    public void fSetPostalCodeN(int fpostalCoden) {
        this.fpostalCoden.set(fpostalCoden);
    }

    public IntegerProperty fPostalCodePropertyN() {
        return fpostalCoden;
    }

    public String fGetCityN() {
        return fcityn.get();
    }

    public void fSetCityN(String fcityn) {
        this.fcityn.set(fcityn);
    }

    public StringProperty fCityPropertyN() {
        return fcityn;
    }

    public LocalDate fGetBirthdayN() {
        return fbirthdayn.get();
    }

    public void fSetBirthdayN(LocalDate fbirthdayn) {
        this.fbirthdayn.set(fbirthdayn);
    }

    public ObjectProperty<LocalDate> fBirthdayPropertyN() {
        return fbirthdayn;
    }
}
