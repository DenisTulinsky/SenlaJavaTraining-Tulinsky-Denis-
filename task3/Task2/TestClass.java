package Task2;
import java.util.Random;

public class TestClass {

	public static void main(String[] args) {
		
		Random randNum = new Random();
		
		int n = randNum.nextInt(900)+99;
		System.out.println("Случайное число: " +n);
		
		BiggestDigit bigdigit = new BiggestDigit();
		bigdigit.getBigDigit(n);
	}

}
