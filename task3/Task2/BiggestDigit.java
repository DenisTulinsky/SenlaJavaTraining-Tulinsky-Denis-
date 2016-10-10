package Task2;

public class BiggestDigit {

	public void getBigDigit(int n) {
		int big = 0;
		for (int i = 0; i <= 3; i++) {

			int d = n % 10;
			n = (n - d) / 10;

			if (d > big) {

				big = d;

			}

			else if (n == 0) {
				System.out.println("Наибольшая цифра: " + big);
				break;
			}
		}
	}

}
