import java.util.Scanner;

public class Program {
	public static void main(String[] args) {
		int end_result = 0;
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNextInt()) {
			int number = scanner.nextInt();
			if (number == 42)
				break;
			int value = 0;

			for (; number != 0; number = number / 10) {
				value += number % 10;
			}

			int root = (int) Math.sqrt(value) + 1;
			boolean result = true;
			for (int divisor = 2; divisor != root; ++divisor) {
				if (value % divisor == 0) {
					result = false;
					break;
				}
			}
			if (result == true)
				end_result += 1;
		}
		scanner.close();
		System.out.println("Count of coffee-request – " + end_result);
	}
}
