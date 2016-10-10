
public class BunchOfFlowers implements IBunchOfFlowers {

	private Flower[] bunch;

	public BunchOfFlowers(int countFlowers) {
		bunch = new Flower[countFlowers];
	}

	@Override
	public void addFlower(Flower aflower) {

		for (int j = 0; j < bunch.length; j++) {
			if (bunch[j] == null) {
				bunch[j] = aflower;
				System.out.println("Добавлен цветок: " + bunch[j].getName());
				System.out.println("Цена: " +bunch[j].getPrice());
				break;
			}
		}

	}

	@Override
	public Integer getSum() {
		Integer sum = 0;

		for (int j = 0; j < bunch.length; j++) {

			if (bunch[j] != null) {
				sum += bunch[j].getPrice();
			}
		}
		return sum;

	}

}