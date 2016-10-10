
public class Main {

	public static void main(String[] args) {

		ILineStep bodyLine = new LineBody();
		ILineStep chassisLine = new LineChassis();
		ILineStep engineLine = new LineEngine();

		IAssamblyLine asambliLine = new AssemblyLine(bodyLine, chassisLine, engineLine);

		IProduct car = new NewCar();
		asambliLine.assembleProduct(car);

		
	}

}
