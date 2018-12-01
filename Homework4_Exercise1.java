package mainPKG;

import java.util.Scanner;

public class Homework4 {
	private static void currency() {
		Scanner scanner = null;
		try {
			scanner = new Scanner(System.in);
			while (true) {
				
				System.out.println("Please enter the price for one dollar in Japanese yen, enter 'exit' to quit:");
				String rate = scanner.next();
				if (rate.equals("change")) {
					currency();
				}
				else if(rate.equals("0")) {
					System.out.println("conversion rate cannot be zero, please enter again");
					currency();
				}
				else if(rate.equals("exit")) {
					System.exit(0);;
				}
				double rate1 = Double.valueOf(rate).doubleValue();
	
				//System.out.println(rate1);
			
				System.out.println("Please enter the amount to be converted, enter 'exit' to quit:");
				String first = scanner.next();
				if (rate.equals("change")) {
					currency();
				}
				else if(rate.equals("exit")) {
					System.exit(0);
				}
				double first1 = Double.valueOf(first).doubleValue();
				
				System.out.println("Please choose the original currency: dollar or yen, enter 'exit' to quit");
				String choice = scanner.next();
				if (choice.equals("change")) {
					currency();
				}
				else if (choice.equals("dollar")) {
					double second = first1 * rate1;
					System.out.println("The amount in yen is: " + second);
				}
				else if (choice.equals("yen")) {
					double second = first1 / rate1;
					System.out.println("The amount in dollar is: " + second);
				}
				else if(choice.equals("exit")) {
					System.exit(0);
				}
				else {
					System.out.println("Please choose between dollar and yen");
					currency();
				}
			}
		} 
		catch (NumberFormatException e) {
					System.out.println("error,please enter a number");
					currency();
				}

	    finally {
			if (scanner != null) {
				scanner.close();
			}
	}
			}

	public static void main(String[] args) {
			currency();
  }
}
