import java.util.Scanner;

public class Program {
	public static void main(String[] args) {
		int weeks_count = 18;
		Scanner scanner = new Scanner(System.in);
		String last_week = new String();
		String result = new String();
		while (weeks_count-- != 0) {
			String week = scanner.nextLine();
			if (week.equals("42"))
				break;
			if (week.compareTo(last_week) < 0) {
				System.out.println("IllegalArgument");
				System.exit(-1);
			}
			String values = new String();
			values = scanner.nextLine();
			int number = 57;
			for (int i = 0; i != values.length(); ++i) {
				char ch = values.charAt(i);
				if (Character.isDigit(ch) && (int) ch < number) {
					number = ch;
				}
			}
			result += number - '0';
			last_week = week;
		}
		for (int i = 0; i != result.length(); ++i) {
			int number = result.charAt(i) - '0';
			System.out.print("Week " + (i + 1) + " ");
			while (number-- != 0) {
				System.out.print("=");
			}
			System.out.println(">");
		}
		scanner.close();
	}
}
