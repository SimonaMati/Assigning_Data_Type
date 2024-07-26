import java.util.Scanner;

public class DataTypes {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		// Input number of tests
		System.out.print("Enter a number of tests >> ");
		int tests = input.nextInt();
		input.nextLine();

		// Loop every test
		for (int i = 0; i < tests; i++) {
			System.out.print("Enter a number >> ");
			String number = input.next();
			try {
				if (number.contains(".")) {
					// Handle floating-point numbers
					double num = Double.parseDouble(number);
					System.out.println(number + " can be fitted in:");
					if (num >= Float.MIN_VALUE && num <= Float.MAX_VALUE) {
						System.out.println("* float");
					}
					if (num >= Double.MIN_VALUE && num <= Double.MAX_VALUE) {
						System.out.println("* double");
					}
				} else {

					// Handle integer numbers
					int stringLength = String.valueOf(number).length();
					long difference = 0;
					long literal = 223372036854775807L;
					String substring = "";

					// If number is 19 digit number, check if the input is equal to the border of
					// the long

					if (stringLength == 19 && number.substring(0, 1).equals("9")) {
						substring = number.substring(1);
						difference = Long.parseLong(substring) - literal;
					} else if (stringLength == 20 && number.substring(1, 2).equals("9")) {
						substring = number.substring(2);
						difference = literal - Long.parseLong(substring);
						stringLength = 19;
					}

					// If number is bigger in length than the range of long OR the difference is not
					// equal to 0
					if (stringLength > 19 || difference > 0) {
						System.out.println(number + " can't be fitted anywhere.");
					} else {
						System.out.println(number + " can be fitted in:");
						for (int j = 7; j < 64; j = (j * 2) + 1) {
							long num = Long.parseLong(number);
							if (num >= Math.pow(-2, j) && num <= (Math.pow(2, j) - 1)) {
								// Print the outcome
								printDataType(j);
							}
						}
					}
				}

			} catch (NumberFormatException e) {
				System.out.println(number + " can't be fitted anywhere.");

			}
		}
		input.close();
	}

	public static void printDataType(int num) {
		switch (num) {
		case 7:
			System.out.println("* byte");
			break;
		case 15:
			System.out.println("* short");
			break;
		case 31:
			System.out.println("* int");
			break;
		case 63:
			System.out.println("* long");
			break;
		}
	}
}
