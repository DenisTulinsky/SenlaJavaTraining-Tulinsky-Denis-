
public class TestClassMain {

	public static void main(String[] args) {
		IClinic clinic = new Clinic(10, 10, 10);
		IDoctor doctor1 = new Doctor("Vasilij", "Zaizev");
		IDoctor doctor2 = new Doctor("Valentin", "Lisicyn");
		IPatient patient1 = new Patient("John", "Johnson");
		IPatient patient2 = new Patient("Tom", "Freeman");
		System.out.println("Добавлены доктора: ");
		clinic.addDoctor(doctor1);
		clinic.addDoctor(doctor2);
		System.out.println("Добавлены пациенты: ");
		clinic.addPatient(patient1);
		clinic.addPatient(patient2);
		System.out.println("Вывести всех докторов: ");
		clinic.showAllDoctors();
		System.out.println("Вывести всех пациентов: ");
		clinic.showAllPatients();
		System.out.println("Созданные записи: ");
		clinic.makeAppointment(doctor1, patient1);
		clinic.makeAppointment(doctor1, patient2);
		clinic.makeAppointment(doctor2, patient2);
		
		System.out.println("Пациентов у доктора " + doctor1.getSurname() +" "+ clinic.getPatientsByDoctor(doctor1));
		
		System.out.println("Пациентов у доктора " + doctor2.getSurname() +" "+ clinic.getPatientsByDoctor(doctor2));
	
		clinic.cancelAppointment(doctor1, patient2);
		System.out.println("Пациентов у доктора " + doctor1.getSurname() +" "+ clinic.getPatientsByDoctor(doctor1));
	}

}
