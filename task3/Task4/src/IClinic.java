
public interface IClinic {
	public void addDoctor(IDoctor doctor);

	public void addPatient(IPatient patient);

	public void makeAppointment(IDoctor doctor, IPatient patient);

	public void cancelAppointment(IDoctor doctor, IPatient patient);

	public void showAllDoctors();

	public void showAllPatients();

	public int getPatientsByDoctor(IDoctor doctor);
}
