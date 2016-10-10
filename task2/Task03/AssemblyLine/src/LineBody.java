
public class LineBody implements ILineStep {
	public LineBody() {

	}
	
	
	@Override
	public IProductPart buildProductPart() {
		Body body = new Body();
		System.out.println("Body is built");
		return body;

	}

}
