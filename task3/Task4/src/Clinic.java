
public class Clinic implements IClinic {

	private IDoctor[] doctors;
	private IPatient[] patients;
	private IAppointment[] appointments;

	public Clinic(int countDoctor, int countPatients, int countAppointments) {
		doctors = new IDoctor[countDoctor];
		patients = new IPatient[countDoctor];
		appointments = new IAppointment[countAppointments];
		System.out.println("Создана клиника");
	}

	@Override
	public void addDoctor(IDoctor doctor) {
		for (int j = 0; j < doctors.length; j++) {
			if (doctors[j] == null) {
				doctors[j] = doctor;
				System.out.println("Added " + doctors[j].getName()+ doctors[j].getSurname());

				break;
			}
		}
	}

	@Override
	public void addPatient(IPatient patient) {
		for (int j = 0; j < patients.length; j++) {
			if (patients[j] == null) {
				patients[j] = patient;
				System.out.println("Added " + patients[j].getName() + patients[j].getSurname());

				break;
			}
		}
	}

	@Override
	public void makeAppointment(IDoctor doctor, IPatient patient) {
		IAppointment appointment = new Appointment(doctor, patient);
		for (int j = 0; j < appointments.length; j++) {
			if (appointments[j] == null) {
				appointments[j] = appointment;
				 System.out.println("Создана запись: Доктор: " +
				 appointments[j].getDoctor().getSurname() + " Пациент: " + appointments[j].getPatient().getSurname()) ;

				break;
			}
		}

	}

	@Override
	public void cancelAppointment(IDoctor doctor, IPatient patient) {
		for (int j = 0; j < appointments.length; j++) {
			if (appointments[j] != null && appointments[j].getDoctor().equals(doctor)
					&& appointments[j].getPatient().equals(patient)) {
				System.out.println("Удалена запись: Доктор: " +
						 appointments[j].getDoctor().getSurname() + " Пациент: " + appointments[j].getPatient().getSurname());
				appointments[j] = null;
				

				break;
			}
		}

	}

	@Override
	public void showAllDoctors() {
		for (int j = 0; j < doctors.length; j++) {
			if (doctors[j] != null) {
				
				System.out.println("Doctor " + doctors[j].getName()+ " "+ doctors[j].getSurname());

			}
			}

	}

	@Override
	public void showAllPatients() {
		for (int j = 0; j < patients.length; j++) {
			if (patients[j] != null) {
				
				System.out.println("Patient " + patients[j].getName()+ " "+ patients[j].getSurname());

			}
			}

	}

	@Override
	public int getPatientsByDoctor(IDoctor doctor) {
		Integer sum = 0;
			for (int j = 0; j < appointments.length; j++) {
				if (appointments[j] != null && appointments[j].getDoctor().equals(doctor)){
						 sum += 1;
				}
			}
					
		return sum;
	}
	
	
}
