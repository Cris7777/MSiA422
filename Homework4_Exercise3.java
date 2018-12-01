package data_analysis;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Homework4_Exercise3 {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String pathname = "/Users/chensi/Desktop/Python & Java/Homework/HW 4/employees.txt";
		File file = new File(pathname);
		List<String> names = new ArrayList<>();
		List<String> births = new ArrayList<>();
		List<String> salaries = new ArrayList<>();
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
			String line; 
			while ((line = bufferedReader.readLine()) != null) {
				names.add(line.split(",")[0]);
				births.add(line.split(",")[1]);
				salaries.add(line.split(",")[2]);
			}
		}
		System.out.println("The number of employees in this file is: " + names.size());
		
		// select who has the highest salary
		int max_salary = 0;
		int i = 0;
		int j = 0;
		for (String salary : salaries) {
			int each = Integer.valueOf(salary).intValue();
			if (each > max_salary) {
				max_salary = each;
				j = i;
			}
			i++;
		}
		int count = 0;
		String highsalary_employee = "";		
		for (String name : names) {
			if (count == j) {
				highsalary_employee = name;
				//System.out.println(highsalary_employee);
			}
			count++;
		}
		System.out.println("The max salary is: " + max_salary);
		System.out.println("The employee that has the max salary is: " + highsalary_employee);
		
		// calculate average salary
		double sum = 0;
		for (String salary : salaries) {
			double each = Double.valueOf(salary).doubleValue();
			sum = sum + each;
		}
		double avg_salary = sum / salaries.size();
		System.out.println("The average salary is: " + avg_salary);
		
		//calculate number of employees above average salary
		int above_avg = 0;
		for (String salary : salaries) {
			double each = Double.valueOf(salary).doubleValue();
			if (each > avg_salary) {
				above_avg += 1;
			}
		}
		System.out.println("The number of employees that make above the average salary is: " + above_avg);
		
		double age = 0;
		for (String birth : births) {
			double year = Integer.valueOf(birth.substring(birth.length() - 4)).intValue();
			age = age + (2018 - year);
		}
		double avg_age = age / births.size();
		System.out.println("The average age of employees is: " + avg_age);
		
		// sorting salaries
		int p = 0;
		int q = 0;
		int [] sorted_salaries = new int[salaries.size()];
		for (int m = 0; m < salaries.size(); m++) {
			sorted_salaries[m] = Integer.valueOf(salaries.get(m)).intValue();
		}
		
		for (p = 0; p < sorted_salaries.length - 1; p++) {
			for (q = 0; q < sorted_salaries.length - 1 - p; q++) {
				if (sorted_salaries[q] > sorted_salaries[q+1]) {
					int temp = sorted_salaries[q];
					sorted_salaries[q] = sorted_salaries[q+1];
					sorted_salaries[q+1] = temp;
				}
			}
		}
		
		//listing names according to sorted salaries
		List<String> sorted_names = new ArrayList<>();
		for (int a = 0; a < salaries.size(); a++) {
			for (int b = 0; b < names.size(); b++) {
				if (sorted_salaries[a] == Integer.valueOf(salaries.get(b)).intValue()) {
					sorted_names.add(names.get(b));
				}
			}
		}
		
		for (int x = 0; x < sorted_names.size(); x++) {
			for (int y = 0; y < sorted_names.size(); y++) {
				if (sorted_names.get(x).equals(sorted_names.get(y)) && x !=y ) {
					sorted_names.remove(y);
				}
			}
		}
		
		
		//writing the output file
		String header = "NAME,SALARY";
		
		String[] writeLines = new String[sorted_names.size()];
		for (int k = 0; k < sorted_names.size(); k++) {
			writeLines[k] = sorted_names.get(k) + "," + sorted_salaries[k];
		}
		
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(pathname.replace("txt", "csv")));
		bufferedWriter.write(header+"\n");
		for (String line : writeLines) {
			bufferedWriter.write(line+"\n");
		}
		bufferedWriter.close();
	}
}
