import java.util.Scanner;

public class Program {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		if (!scanner.hasNextInt()) {
			scanner.close();
			System.exit(-1);
		}
		int number = scanner.nextInt();
		if (number <= 1) {
			scanner.close();
			System.out.println("Illegal Argument");
			System.exit(-1);
		}
		scanner.close();
		int root = (int) Math.sqrt(number) + 1;
		int count = 1;
		boolean result = true;
		for (int divisor = 2; divisor != root; ++divisor, ++count) {
			if (number % divisor == 0) {
				result = false;
				break;
			}
		}
		System.out.println(result + " " + count);

	}
}
