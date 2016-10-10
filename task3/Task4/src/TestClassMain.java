
public class TestClassMain {

	public static void main(String[] args) {
		IClinic clinic = new Clinic(10, 10, 10);
		IDoctor doctor1 = new Doctor("Vasilij", "Zaizev");
		IDoctor doctor2 = new Doctor("Valentin", "Lisicyn");
		IPatient patient1 = new Patient("John", "Johnson");
		IPatient patient2 = new Patient("Tom", "Freeman");
		System.out.println("��������� �������: ");
		clinic.addDoctor(doctor1);
		clinic.addDoctor(doctor2);
		System.out.println("��������� ��������: ");
		clinic.addPatient(patient1);
		clinic.addPatient(patient2);
		System.out.println("������� ���� ��������: ");
		clinic.showAllDoctors();
		System.out.println("������� ���� ���������: ");
		clinic.showAllPatients();
		System.out.println("��������� ������: ");
		clinic.makeAppointment(doctor1, patient1);
		clinic.makeAppointment(doctor1, patient2);
		clinic.makeAppointment(doctor2, patient2);
		
		System.out.println("��������� � ������� " + doctor1.getSurname() +" "+ clinic.getPatientsByDoctor(doctor1));
		
		System.out.println("��������� � ������� " + doctor2.getSurname() +" "+ clinic.getPatientsByDoctor(doctor2));
	
		clinic.cancelAppointment(doctor1, patient2);
		System.out.println("��������� � ������� " + doctor1.getSurname() +" "+ clinic.getPatientsByDoctor(doctor1));
	}

}
