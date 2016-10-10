
public abstract class Flower {

	protected String name;
	protected Integer price;

	public Flower(String name, int price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
