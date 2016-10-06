public class EmployeeManager {

	public static void main(String[] args) {

		Employee nick = new Employee("Nick", "Dagger", "Worker");

		Salary sal = new Salary(99);
		nick.setSalary(sal);

		Room reroom = new Room(2);
		nick.setRoom(reroom);

		Department programmersDepartment = new Department("Department");
		nick.setDepartment(programmersDepartment);

	}
}

class Man {
	protected String name;
	protected String surname;

	public Man() {
		System.out.println("Man");
	}

	public void setName(String newName) {
		name = newName;
	}

	public String getName() {
		return name;
	}

	public void setSurname(String newSurname) {
		name = newSurname;
	}

	public String getSurname() {
		return surname;
	}
}

class Employee extends Man {
	private String position;
	private Salary salary;
	private Room room;
	private Department department;

	// конструктор
	public Employee(String n, String s, String p) {
		name = n;
		surname = s;
		position = p;
		System.out.println("Employee");
	}

	public void setPosition(String newProfession) {
		position = newProfession;
	}

	public String getPosition() {
		return position;
	}

	public void setSalary(Salary c) {
		salary = c;
	}

	public Salary getSalary() {
		return salary;
	}

	public void setRoom(Room newRoom) {
		room = newRoom;
	}

	public Room getRoom() {
		return room;

	}

	public void setDepartment(Department d) {
		department = d;
	}

	public Department getDepartment() {
		return department;
	}
}

class Salary {
	private int number;

	public Salary(int n) {
		number = n;
		System.out.println("Salary");
	}

	public void setNumber(int newNumber) {
		number = newNumber;
	}

	public int getNumber() {
		return number;
	}
}

class Room {
	private int number;

	public Room(int n) {
		number = n;
		System.out.println("Room");
	}

	public void setNumber(int newNumber) {
		number = newNumber;
	}

	public int getNumber() {
		return number;
	}
}

class Department {
	private String name;

	public Department(String n) {
		name = n;
		System.out.println("Department");
	}

	public void setName(String newName) {
		name = newName;
	}

	public String getName() {
		return name;
	}
}
