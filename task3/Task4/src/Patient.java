
public class Patient implements IPatient {
	private String name;
	private String surname;

	public Patient(String name, String surname) {
		this.name = name;
		this.surname = surname;
	}

	@Override
	public void setName(String newName) {
		name = newName;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setSurname(String newSurname) {
		name = newSurname;
	}

	@Override
	public String getSurname() {
		return surname;
	}
}