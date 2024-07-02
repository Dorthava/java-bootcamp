import java.util.Scanner;

public class Program {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String word = new String();
		word = scanner.nextLine();
		scanner.close();

		char characters[] = new char[65535];
		int amount_characters = 0;

		int numbers[] = new int[65535];
		int amount_numbers = 0;

		for (int i = 0; i != word.length(); ++i) {
			boolean analysis = true;
			for (int j = 0; j != amount_characters; ++j) {
				if (word.charAt(i) == characters[j]) {
					analysis = false;
					break;
				}
			}
			if (analysis) {
				characters[amount_characters++] = word.charAt(i);
				for (int k = i; k != word.length(); ++k) {
					if (numbers[amount_numbers] != 999 && word.charAt(i) == word.charAt(k)) {
						numbers[amount_numbers] += 1;
					}
				}
				++amount_numbers;
			}
		}
		word = "";
		int order_numbers[] = new int[amount_numbers];
		for (int i = 0; i != amount_numbers && i != 10; ++i) {
			int k = 0;
			for (int j = 0; j != amount_numbers; ++j) {
				if (numbers[j] > numbers[k] || (numbers[k] == numbers[j] && characters[j] < characters[k])) {
					k = j;
				}
			}
			word += characters[k];
			order_numbers[i] = numbers[k];
			numbers[k] = 0;
		}
		char order_characters[] = word.toCharArray();
		float step = (float) order_numbers[0] / 10;

		int interval = 1;

		if (order_numbers[0] > 99)
			interval = 3;
		else if (order_numbers[0] > 9)
			interval = 2;

		for (float i = (float) order_numbers[0]; i > -1; i -= step) {
			for (int j = 0; j != word.length(); ++j) {
				if (order_numbers[j] < (float) i)
					continue;
				if ((float) order_numbers[j] - step >= (float) i) {
					for (int k = interval; k != 1; --k)
						System.out.print(" ");
					System.out.print("# ");
				} else {
					if (String.valueOf(order_numbers[j]).length() < interval)
						for (int k = interval - String.valueOf(order_numbers[j]).length(); k != 0; --k)
							System.out.print(" ");
					System.out.print(order_numbers[j] + " ");
				}
			}
			System.out.println();
		}
		for (int i = 0; i != word.length(); ++i) {
			for (int k = interval; k != 1; --k)
				System.out.print(" ");
			System.out.print(order_characters[i] + " ");
		}
	}
}
