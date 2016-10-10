
public class TestClass {

	public static void main(String[] args) {
	
		
		Flower fl1 = new Rose();
		Flower fl2 = new Daisy();
		Flower fl3 = new Carnation();
		IBunchOfFlowers bunch = new BunchOfFlowers(3);
		bunch.addFlower(fl1);
		bunch.addFlower(fl2);
		bunch.addFlower(fl3);
		System.out.println("Стоимость букета: " + bunch.getSum());
	}

}
