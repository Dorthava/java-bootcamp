import java.util.Scanner;

public class Program {
	public static void main(String[] args) {

		int students_count = 0;
		String students[] = new String[11];
		Scanner scanner = new Scanner(System.in);
		while (students_count != 10) {
			students[students_count] = scanner.nextLine();
			if (students[students_count].length() > 10) {
				System.out.println("Количество символов в имени студента не может превышать 10-ти символов.");
				scanner.close();
				System.exit(-1);
			}
			if (students[students_count].equals("."))
				break;
			++students_count;
		}
		if (students_count == 10) {
			System.out.println("Превышено максимальное количество студентов.");
			scanner.close();
			System.exit(-1);
		}
		int classes_count = 0;
		int classes[][] = new int[7][6];
		String week = new String("MO TU WE TH FR SA SU");
		String[] days_in_week = week.split(" ");
		week = null;

		while (classes_count != 11) {
			String word = scanner.nextLine();
			if (word.equals("."))
				break;

			int digit = word.charAt(0) - '0';
			word = word.substring(2);
			for (int i = 0; i != 7; ++i) {
				if (word.equals(days_in_week[i])) {
					classes[i][digit - 1] += digit;
					classes[i][5] += 1;
					break;
				}
			}
			++classes_count;
		}
		if (classes_count == 11) {
			System.out.println("Превышено максимальное количество занятий за неделю");
			scanner.close();
			System.exit(-1);
		}

		String attendance = new String();
		int attendance_count = 0;
		for (String attendance_line = scanner.nextLine(); !attendance_line.equals("."); attendance_line = scanner
				.nextLine()) {
			attendance += attendance_line + ' ';
			attendance_count += 1;
		}
		scanner.close();
		attendance_count *= 4;
		int attendance_array[] = new int[attendance_count * 4];
		for (int i = 0, j = 0; i != attendance_count; ++i) {
			if (attendance.charAt(j) == ' ')
				++j;
			String temp = new String();
			while (attendance.charAt(j) != ' ') {
				temp += attendance.charAt(j++);
			}
			switch (i % 4) {
			case 0:
				for (int k = 0; k != students_count; ++k) {
					if (temp.equals(students[k])) {
						attendance_array[i] = k;
						break;
					}
				}
				break;
			case 1:
			case 2:
				if (temp.length() == 1)
					attendance_array[i] = temp.charAt(0) - '0';
				else
					attendance_array[i] = (temp.charAt(0) - '0') * 10 + (temp.charAt(1) - '0');
				break;
			default:
				attendance_array[i] = temp.equals("HERE") ? 1 : -1;
				break;
			}
			temp = null;
		}
		attendance = null;
		System.out.print("          ");
		for (int i = 1; i != 31; ++i) {
			if (classes[i % 7][5] == 0)
				continue;
			for (int j = 0; j != 5; ++j) {
				if (classes[i % 7][j] == 0)
					continue;
				System.out.print(classes[i % 7][j] + ":00" + " " + days_in_week[i % 7]);
				if (i > 9)
					System.out.print(" ");
				else
					System.out.print("  ");
				System.out.print(i + "|");
			}
		}
		System.out.println();

		for (int i = 0; i != students_count; ++i) {
			for (int count = 10 - students[i].length(); count != 0; --count)
				System.out.print(' ');
			System.out.print(students[i]);
			for (int j = 1; j != 31; ++j) {
				if (classes[j % 7][5] == 0)
					continue;
				System.out.print("        ");
				for (int k = 0; k != 5; ++k) {
					if (classes[j % 7][k] == 0)
						continue;
					boolean find = false;
					for (int p = 0; p != attendance_count; ++p) {
						int copy_p = p;
						if (p % 4 == 0 && attendance_array[p] == i && classes[j % 7][k] == attendance_array[++p]
								&& j == attendance_array[++p]) {
							attendance_array[copy_p] = -1;
							if (attendance_array[++p] == 1)
								System.out.print(" 1|");
							else
								System.out.print("-1|");
							find = true;
							break;
						}
					}
					if (!find)
						System.out.print("  |");
				}
			}
			System.out.println();
		}
	}
}
