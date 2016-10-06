
public class NewCar implements IProduct {
	
	private IProductPart body;
	
	public NewCar(){
		System.out.println("empty car created");
	}
	
	@Override
	public void installFirstPart(IProductPart body) {
		this.body = body;
		System.out.println("body Installed");
		
	}

	@Override
	public void installSecondPart(IProductPart part2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void installThirdPart(IProductPart part3) {
		// TODO Auto-generated method stub
		
	}

	
	
}
