package AppointmentBook;

import java.util.*;
import java.text.*;
import java.io.*;

public class TestAppointment {

	public static void main(String[] args) throws IOException {
		
		Scanner scanner = null;
		scanner = new Scanner(System.in);
		List<Appointment> book = new ArrayList<>();
		
		String pathname = "/Users/chensi/Desktop/Python & Java/Homework/HW5/Appointment.txt";
		File file = new File(pathname);
		Writer fileWriter = null;
		BufferedWriter bufferedWriter = null;
		
		//enter anything else for next step
		//entering months/days exceeding 12/29or30or31 will automatically be accounted to next year/month
		while(true) {
			System.out.println("Please enter the type of appointment:");
			String type = scanner.next();
			
			if (type.equals("Onetime")) {
				System.out.println("Please enter the description:");
				String description = scanner.next();
				System.out.println("Please enter the datesinfo, formatting mm/dd/yyyy:");
				String datesinfo = scanner.next();
				Appointment x = new Onetime(description, datesinfo);
				book.add(x);
				
				System.out.println("Do you want to add this appointment? Type Yes or No");
				String choice = scanner.next();
				if (choice.equals("Yes")) {
					fileWriter = new FileWriter(file);
					bufferedWriter = new BufferedWriter(fileWriter);
					for (Appointment apt : book) {
						bufferedWriter.write(apt+"\n");
					}
					bufferedWriter.close();
				}
				
				else if (choice.equals("No")) {
					System.out.println("Thank you");
				}
				
				else {
					System.out.println("Error");
				}
				
			}
			
			else if (type.equals("Daily")) {
				System.out.println("Please enter the description:");
				String description = scanner.next();
				System.out.println("Please enter the startdate, formatting mm/dd/yyyy:");
				String startdate = scanner.next();
				System.out.println("Please enter the enddate, formatting mm/dd/yyyy:");
				String enddate = scanner.next();
				Appointment x = new Daily(description, startdate, enddate);
				book.add(x);
				
				System.out.println("Do you want to add this appointment? Type Yes or No");
				String choice = scanner.next();
				if (choice.equals("Yes")) {
					fileWriter = new FileWriter(file);
					bufferedWriter = new BufferedWriter(fileWriter);
					for (Appointment apt : book) {
						bufferedWriter.write(apt+"\n");
					}
					bufferedWriter.close();
				}
				
				else if (choice.equals("No")) {
					System.out.println("Thank you");
				}
				
				else {
					System.out.println("Error");
				}
			}
			
			else if (type.equals("Monthly")) {
				System.out.println("Please enter the description:");
				String description = scanner.next();
				System.out.println("Please enter the day, formatting dd:");
				String day = scanner.next();
				System.out.println("Please enter the start, formatting mm/yyyy:");
				String start = scanner.next();
				System.out.println("Please enter the end, formatting mm/yyyy:");
				String end = scanner.next();
				Appointment x = new Monthly(description, day, start, end);
				book.add(x);
				
				System.out.println("Do you want to add this appointment? Type Yes or No");
				String choice = scanner.next();
				if (choice.equals("Yes")) {
					fileWriter = new FileWriter(file);
					bufferedWriter = new BufferedWriter(fileWriter);
					for (Appointment apt : book) {
						bufferedWriter.write(apt+"\n");
					}
					bufferedWriter.close();
				}
				
				else if (choice.equals("No")) {
					System.out.println("Thank you");
				}
				
				else {
					System.out.println("Error");
				}
			}
			
			else {
				break;
			}
		}
		
		List<Appointment> occur = new ArrayList<>();
		System.out.println("Please enter the year to be checked");
		int y = scanner.nextInt();
		System.out.println("Please enter the month to be checked");
		int m = scanner.nextInt();
		System.out.println("Please enter the day to be checked");
		int d = scanner.nextInt();
		
		for (Appointment apt : book) {
			if(apt.occursOn(y,m,d) == true) {
				occur.add(apt);
			}
		}
		
		if (occur.size()!= 0 ) {
			System.out.println("Appointments occur on this day are:");
			System.out.println(occur);
		}
		else {
			System.out.println("No appointments on this day");
		}
	}
}
