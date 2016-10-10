
public class LineEngine implements ILineStep {

	@Override
	public IProductPart buildProductPart() {
		Engine engine = new Engine();
		System.out.println("Engine is built");
		return engine;
	}

}
