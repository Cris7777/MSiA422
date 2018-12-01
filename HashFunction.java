package Hashing;

import java.io.*;
import java.util.*;

public class HashFunction {
	//calculate the hash function1 value for a string
	private static int hashfunction1(String str, int size) {
		char[] ascii1 = str.toCharArray();
		int count = 0;
	    for(char ch:ascii1){
	    	count = count + (int)ch;
	    }
	    int hash = count % size;
	    return hash;
	}
	
	private static int hashfunction2(String str, int size) {
		int hash = str.hashCode() % size;
		return Math.abs(hash);
	}
	
	private static int hashfunction3(String str, int size) {
		char[] ascii1 = str.toCharArray();
		int sum = 0;
		for(int i = 0; i < ascii1.length; i++) {
			sum = sum + (int)ascii1[i] * (i + 1);
		}
		int hash = sum % size;
		return hash;
	}
	
	//mapping each element based on its hash fuction1 value
	private static HashMap<Integer, String> ordering1(List<String> string, int size) {
		HashMap<Integer, String> map = new HashMap<>(); 
		for (String str : string) {
			int hash = hashfunction1(str, size);
			if (!map.containsKey(hash)) {
				map.put(hash, str);
			}
			else {
				map.replace(hash, map.get(hash) + ", " + str);
			}
		}
		return map;
	}
	
	private static HashMap<Integer, String> ordering2(List<String> string, int size) {
		HashMap<Integer, String> map = new HashMap<>(); 
		for (String str : string) {
			int hash = hashfunction2(str, size);
			if (!map.containsKey(hash)) {
				map.put(hash, str);
			}
			else {
				map.replace(hash, map.get(hash) + ", " + str);
			}
		}
		return map;
	}
	
	private static HashMap<Integer, String> ordering3(List<String> string, int size) {
		HashMap<Integer, String> map = new HashMap<>(); 
		for (String str : string) {
			int hash = hashfunction3(str, size);
			if (!map.containsKey(hash)) {
				map.put(hash, str);
			}
			else {
				map.replace(hash, map.get(hash) + ", " + str);
			}
		}
		return map;
	}
	
	private static double metric(HashMap<Integer,String> map, List<String> list) {
		//metric: calculating the deviation of # of words for each key value
		//the less this metric is, the better the function performs
		double avgword = list.size() / map.size();
		double sumd = 0;
		for (Integer name: map.keySet()){
            String value = map.get(name).toString(); 
            //System.out.println(value);
            double countword = 1;
            for (int i = 0; i < value.length(); i++) {
            	if (value.charAt(i) == ',') {
            		countword++;
            	}
            }
            sumd = sumd + (countword - avgword) * (countword - avgword);
		}
		double deviation = sumd / list.size();
		return deviation;
	}

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		Scanner scanner = null;
		scanner = new Scanner(System.in);
		String inputpathname = "/Users/chensi/Desktop/Python & Java/Homework/HW 6/input.txt";
		File inputfile = new File(inputpathname);
		Writer fileWriter = null;
		BufferedWriter bufferedWriter = null;
		List<String> input = new ArrayList<>();
		
		System.out.println("Please enter the size of the array: 100 or 200");
		try {
			String size = scanner.next();
			int s = Integer.valueOf(size).intValue();
			try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputfile))) {
				String line; 
				while ((line = bufferedReader.readLine()) != null) {
					input.add(line);
				}
			}
			if (s == 100) {				
				//ordering
				HashMap<Integer, String> map11 = ordering1(input, s); 
				//System.out.println(map11.size());
				
				//generating output file
				String outputpathname11 = "/Users/chensi/Desktop/Python & Java/Homework/HW 6/output11.txt";
				File outputfile11 = new File(outputpathname11);
				fileWriter = new FileWriter(outputfile11);
				bufferedWriter = new BufferedWriter(fileWriter);
				
				for (int i = 0; i < s; i++) {
					if (map11.containsKey(i)) {
						bufferedWriter.write(i+1 + " " + map11.get(i) + "\n" + "\n");
					}
					else {
						bufferedWriter.write(i+1 + " " + "EMPTY LINE" + "\n" + "\n");
					}
				}
				bufferedWriter.close();
				
				//metric
				double metric11 = metric(map11,input);
				//System.out.println(metric(map11,s100));
				
				//function2
				HashMap<Integer, String> map12 = ordering2(input,s);
				//System.out.println(map12);
				String outputpathname12 = "/Users/chensi/Desktop/Python & Java/Homework/HW 6/output12.txt";
				File outputfile12 = new File(outputpathname12);
				fileWriter = new FileWriter(outputfile12);
				bufferedWriter = new BufferedWriter(fileWriter);
				
				for (int i = 0; i < s; i++) {
					if (map12.containsKey(i)) {
						bufferedWriter.write(i+1 + " " + map12.get(i) + "\n" + "\n");
					}
					else {
						bufferedWriter.write(i+1 + " " + "EMPTY LINE" + "\n" + "\n");
					}
				}
				bufferedWriter.close();
				//System.out.println(metric(map12,s100));
				double metric12 = metric(map12,input);
				
				//function 3
				HashMap<Integer, String> map13 = ordering3(input,s);
				//System.out.println(map12);
				String outputpathname13 = "/Users/chensi/Desktop/Python & Java/Homework/HW 6/output13.txt";
				File outputfile13 = new File(outputpathname13);
				fileWriter = new FileWriter(outputfile13);
				bufferedWriter = new BufferedWriter(fileWriter);
				
				for (int i = 0; i < s; i++) {
					if (map13.containsKey(i)) {
						bufferedWriter.write(i+1 + " " + map13.get(i) + "\n" + "\n");
					}
					else {
						bufferedWriter.write(i+1 + " " + "EMPTY LINE" + "\n" + "\n");
					}
				}
				bufferedWriter.close();
				
				double metric13 = metric(map13,input);
				String [] function1 = {"Sum of String ASCII","Built-in Function","ASCII Multiply Order"};
				double [] metric1 = {metric11, metric12, metric13};
				
				String header1 = "Function,Metric";
				
				String[] writeLines = new String[metric1.length];
				for (int k = 0; k < 3; k++) {
					writeLines[k] = function1[k] + "," + metric1[k];
				}
				
				String outputmetric1 = "/Users/chensi/Desktop/Python & Java/Homework/HW 6/metric1.csv";
				File evaluation1 = new File(outputmetric1);
				fileWriter = new FileWriter(outputmetric1);
				bufferedWriter = new BufferedWriter(fileWriter);
				bufferedWriter.write(header1+"\n");
				for (String line : writeLines) {
					bufferedWriter.write(line+"\n");
				}
				bufferedWriter.close();
				
			}
			
			else if (s == 200) {
				
				//ordering
				HashMap<Integer, String> map21 = ordering1(input,s); 
				//System.out.println(map11);
				
				//generating output file
				String outputpathname21 = "/Users/chensi/Desktop/Python & Java/Homework/HW 6/output21.txt";
				File outputfile21 = new File(outputpathname21);
				fileWriter = new FileWriter(outputfile21);
				bufferedWriter = new BufferedWriter(fileWriter);
				
				for (int i = 0; i < s; i++) {
					if (map21.containsKey(i)) {
						bufferedWriter.write(i+1 + " " + map21.get(i) + "\n" + "\n");
					}
					else {
						bufferedWriter.write(i+1 + " " + "EMPTY LINE" + "\n" + "\n");
					}
				}
				bufferedWriter.close();
				double metric21 = metric(map21, input);
				//System.out.println(map21);
				
				//function2
				HashMap<Integer, String> map22 = ordering2(input,s);
				//System.out.println(map12);
				String outputpathname22 = "/Users/chensi/Desktop/Python & Java/Homework/HW 6/output22.txt";
				File outputfile22 = new File(outputpathname22);
				fileWriter = new FileWriter(outputfile22);
				bufferedWriter = new BufferedWriter(fileWriter);
				
				for (int i = 0; i < s; i++) {
					if (map22.containsKey(i)) {
						bufferedWriter.write(i+1 + " " + map22.get(i) + "\n" + "\n");
					}
					else {
						bufferedWriter.write(i+1 + " " + "EMPTY LINE" + "\n" + "\n");
					}
				}
				bufferedWriter.close();
				double metric22 = metric(map22, input);
				
				//System.out.println(metric(map22,s200));
				
				//function 3
				HashMap<Integer, String> map23 = ordering3(input,s);
				//System.out.println(map12);
				String outputpathname23 = "/Users/chensi/Desktop/Python & Java/Homework/HW 6/output23.txt";
				File outputfile23 = new File(outputpathname23);
				fileWriter = new FileWriter(outputfile23);
				bufferedWriter = new BufferedWriter(fileWriter);
				
				for (int i = 0; i < s; i++) {
					if (map23.containsKey(i)) {
						bufferedWriter.write(i+1 + " " + map23.get(i) + "\n" + "\n");
					}
					else {
						bufferedWriter.write(i+1 + " " + "EMPTY LINE" + "\n" + "\n");
					}
				}
				bufferedWriter.close();
				double metric23 = metric(map23, input);
				
				String [] function2 = {"Sum of String ASCII","Built-in Function","ASCII Multiply Order"};
				double [] metric2 = {metric21, metric22, metric23};
				
				String header2 = "Function,Metric";
				
				String[] writeLines = new String[metric2.length];
				for (int k = 0; k < 3; k++) {
					writeLines[k] = function2[k] + "," + metric2[k];
				}
				
				String outputmetric2 = "/Users/chensi/Desktop/Python & Java/Homework/HW 6/metric2.csv";
				File evaluation2 = new File(outputmetric2);
				fileWriter = new FileWriter(outputmetric2);
				bufferedWriter = new BufferedWriter(fileWriter);
				bufferedWriter.write(header2+"\n");
				for (String line : writeLines) {
					bufferedWriter.write(line+"\n");
				}
				bufferedWriter.close();
			}
			
			else {
				System.out.println("Please choose between only 100 and 200");
				System.exit(0);
			}
		}
		
		catch (NumberFormatException e) {
			System.out.println("Please enter an integer");
			System.exit(0);
		}
		
		finally {
			if (scanner != null) {
				scanner.close();
			}
		}

	}

}
