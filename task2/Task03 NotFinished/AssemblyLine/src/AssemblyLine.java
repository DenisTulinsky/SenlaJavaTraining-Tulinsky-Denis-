
public class AssemblyLine implements IAssamblyLine {
	
	private ILineStep bodyline;
	
	public AssemblyLine(ILineStep bodyline) { 
		this.bodyline = bodyline;
		System.out.println("AssemblyLine created");
	}
	
	@Override
	public IProduct assembleProduct(IProduct product) {
		
		IProductPart body = bodyline.buildProductPart();
		
		product.installFirstPart(body);
				
		
		System.out.println("Car ready");
		return product;
	}

}
