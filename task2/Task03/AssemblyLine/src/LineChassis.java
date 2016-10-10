
public class LineChassis implements ILineStep {

	@Override
	public IProductPart buildProductPart() {
		Chassis chassis = new Chassis();
		System.out.println("Chassis is built");
		return chassis;
	}

}
