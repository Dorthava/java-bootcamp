public class Program {
	public static void main(String[] args) {
		short result = 0;
		for (int value = 479598; value != 0; value = value / 10) {
			result += value % 10;
		}
		System.out.println(result);
	}
}