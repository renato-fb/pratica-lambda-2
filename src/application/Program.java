package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		List<Employee> list = new ArrayList<>();
		System.out.print("Enter full file path: ");
		String filePath = sc.nextLine();

		try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
			String line = br.readLine();
			while(line != null) {
				String[] fields = line.split(",");
				list.add(new Employee(fields[0],fields[1],Double.parseDouble(fields[2])));
				line = br.readLine();
			}
			System.out.println("Enter salary: ");
			double salary = sc.nextDouble();
			Comparator<String> comp = (s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());
			
			List<String> names = list.stream()
					.filter(p -> p.getSalary() >= salary)
					.map(p -> p.getEmail()).sorted(comp)
					.collect(Collectors.toList());
			
			double sum = list.stream()
					.filter(p -> p.getName().charAt(0) == 'M')
					.map(p -> p.getSalary())
					.reduce(0.0,(x, y) -> x+y);
			
			System.out.println("Email of people whose salary is more than 2000.00: ");
			names.forEach(System.out::println);
			System.out.print("Sum of salary of people whose name starts with 'M':");
			System.out.print(String.format("%.2f", sum));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		sc.close();
	}

}
