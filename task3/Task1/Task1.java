
public class Task1 {

	public static void main(String[] args) {

		String[] numbers = { "1", "2", "3" };

		System.out.println(getStringSum(numbers));
	}

	private static String getStringSum(String[] numbers) {
		int sum = 0;
		for (int i = 0; i <= numbers.length - 1; i++) {
			sum += Integer.valueOf(numbers[i]);

		}

		String stringSum = Integer.toString(sum);
		return stringSum;
	}
}
