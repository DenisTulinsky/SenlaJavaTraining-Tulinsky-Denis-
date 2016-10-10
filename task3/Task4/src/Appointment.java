
public class Appointment implements IAppointment {
	
	private IDoctor doctor;
	private IPatient patient;
	
	public Appointment(IDoctor doctor, IPatient patient) {
		this.doctor = doctor;
		this.patient = patient;
		
	}

	public IDoctor getDoctor() {
		return doctor;
	}

	public void setDoctor(IDoctor doctor) {
		this.doctor = doctor;
	}

	public IPatient getPatient() {
		return patient;
	}

	public void setPatient(IPatient patient) {
		this.patient = patient;
	}

}
