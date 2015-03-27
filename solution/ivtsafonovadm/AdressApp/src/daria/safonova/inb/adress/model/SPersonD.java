package daria.safonova.inb.adress.model;

import java.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import daria.safonova.inb.adress.util.LocalDateAdapter;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SPersonD {

	private final StringProperty sfirstNamed;
	private final StringProperty slastNamed;
	private final StringProperty sstreetd;
	private final IntegerProperty spostalCoded;
	private final StringProperty scityd;
	private final ObjectProperty<LocalDate> sbirthdayd;

	public SPersonD() {
		this(null, null);
	}

	public SPersonD(String firstName, String lastName) {
		this.sfirstNamed = new SimpleStringProperty(firstName);
		this.slastNamed = new SimpleStringProperty(lastName);

		this.sstreetd = new SimpleStringProperty("some street");
		this.spostalCoded = new SimpleIntegerProperty(1234);
		this.scityd = new SimpleStringProperty("some city");
		this.sbirthdayd = new SimpleObjectProperty<LocalDate>(LocalDate.of(
				1999, 2, 21));
	}

	public String getFirstName() {
		return sfirstNamed.get();
	}

	public void setFirstName(String firstName) {
		this.sfirstNamed.set(firstName);
	}

	public StringProperty firstNameProperty() {
		return sfirstNamed;
	}

	public String getLastName() {
		return slastNamed.get();
	}

	public void setLastName(String lastName) {
		this.slastNamed.set(lastName);
	}

	public StringProperty lastNameProperty() {
		return slastNamed;
	}

	public String getStreet() {
		return sstreetd.get();
	}

	public void setStreet(String street) {
		this.sstreetd.set(street);
	}

	public StringProperty streetProperty() {
		return sstreetd;
	}

	public int getPostalCode() {
		return spostalCoded.get();
	}

	public void setPostalCode(int postalCode) {
		this.spostalCoded.set(postalCode);
	}

	public IntegerProperty postalCodeProperty() {
		return spostalCoded;
	}

	public String getCity() {
		return scityd.get();
	}

	public void setCity(String city) {
		this.scityd.set(city);
	}

	public StringProperty cityProperty() {
		return scityd;
	}

	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	public LocalDate getBirthday() {
		return sbirthdayd.get();
	}

	public void setBirthday(LocalDate birthday) {
		this.sbirthdayd.set(birthday);
	}

	public ObjectProperty<LocalDate> birthdayProperty() {
		return sbirthdayd;
	}
}
