
public class AssemblyLine implements IAssamblyLine {
	
	private ILineStep bodyLine;
	private ILineStep chassisLine;
	private ILineStep engineLine;
	
	public AssemblyLine(ILineStep bodyLine,ILineStep chassisLine, ILineStep engineLine) {
		this.bodyLine = bodyLine;
		this.chassisLine = chassisLine;
		this.engineLine = engineLine;
	}
	
	
	@Override
	public IProduct assembleProduct(IProduct product) {
		
		IProductPart part1 = bodyLine.buildProductPart();
		product.installFirstPart(part1);
		System.out.println("Body installed");
		
		IProductPart part2 = chassisLine.buildProductPart();
		product.installSecondPart(part2);
		System.out.println("Chassis installed");
		
		IProductPart part3 = engineLine.buildProductPart();
		product.installThirdPart(part3);
		System.out.println("Engine installed");
		
		System.out.println("Car ready");
		return product;
	}

}
