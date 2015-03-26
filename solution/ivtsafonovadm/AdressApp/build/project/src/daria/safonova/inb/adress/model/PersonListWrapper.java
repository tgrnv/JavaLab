package daria.safonova.inb.adress.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "persons")
public class PersonListWrapper {

	private List<SPersonD> spersonsd;

	@XmlElement(name = "person")
	public List<SPersonD> getPersons() {
		return spersonsd;
	}

	public void setPersons(List<SPersonD> persons) {
		this.spersonsd = persons;
	}
}
