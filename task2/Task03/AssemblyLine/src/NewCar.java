
public class NewCar implements IProduct {

	private Body body;
	private Chassis chassis;
	private Engine engine;
	
	@Override
	public void installFirstPart(IProductPart part1) {
		this.body = (Body) part1;
		
	}

	@Override
	public void installSecondPart(IProductPart part2) {
		this.chassis = (Chassis) part2;
		
	}

	@Override
	public void installThirdPart(IProductPart part3) {
		this.engine = (Engine) part3;
		
	}
	
	
	
	
}
