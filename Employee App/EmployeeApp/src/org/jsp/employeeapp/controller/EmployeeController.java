package org.jsp.employeeapp.controller;

import java.util.List;
import java.util.Scanner;

import org.jsp.employeeapp.dao.EmployeeDao;
import org.jsp.employeeapp.dto.Employee;

public class EmployeeController {
	public static void main(String[] args) {
		EmployeeDao dao = new EmployeeDao();
		Scanner sc = new Scanner (System.in);
		System.out.println("Enter your choice");
		boolean flag = true;
		while(flag) {
			System.out.println("1. Save Employee");
			System.out.println("2. Update Employee");
			System.out.println("3. Find Employee By Id");
			System.out.println("4. Verify Employee By Phone and password");
			System.out.println("5. Verify Employee By Email and password");
			System.out.println("6. Find Employee By Designation");
			System.out.println("7. Filter Employee By Salary");
			System.out.println("8. Exit");
			switch(sc.nextInt()) {
			case 1:{
				System.out.println("Enter the employee id,name,designation,salary,email,phone and password to insert");
				int id = sc.nextInt();
				String name = sc.next();
				String designation = sc.next();
				double salary = sc.nextDouble();
				String email = sc.next();
				long phone = sc.nextLong();
				String password = sc.next();
				Employee emp =new Employee(id,name,designation,salary,email,phone,password);
				String message = dao.saveEmployee(emp);
				System.out.println(message);
				break;
			}
			case 2:{
				System.out.println("Enter the employee id,name,designation,salary,email,phone and password to update");
				int id =sc.nextInt();
				String name = sc.nextLine();
				sc.nextLine();
				String designation = sc.next();
				double salary = sc.nextDouble();
				String email = sc.next();
				long phone = sc.nextLong();
				String password = sc.next();
				Employee emp =new Employee(id,name,designation,salary,email,phone,password);
				String message = dao.updateEmployee(emp);
				System.out.println(message);
				break;
			}
			case 3: {
				System.out.println("Enter the Employee id to find the emp");
				int id = sc.nextInt();
				Employee emp = dao.findEmpById(id);
				if (emp != null) {
					System.out.println("Employee Found");
					System.out.println("Id:" + emp.getId());
					System.out.println("Name:" + emp.getName());
					System.out.println("Designation:" + emp.getDesignation());
					System.out.println("Salary:" + emp.getSalary());
					System.out.println("Email:" + emp.getEmail());
					System.out.println("Phone:" + emp.getPhone());
					System.out.println("Password:" + emp.getPassword());
				} else {
					System.err.println("Invalid Id");
				}
				break;
			}
			case 4: {
				System.out.println("Enter the phone Number and password to verify user");
				long phone = sc.nextLong();
				String password = sc.next();
				Employee emp= dao.verifyByPhone(phone, password);
				if (emp != null) {
					System.out.println("Employee Verified Succesfully");
					
				} else {
					System.err.println("Invalid Phone Number or Password");
				}
				break;
			}
			case 5: {
				System.out.println("Enter the Email Id and password to verify user");
				String email = sc.next();
				String password = sc.next();
				Employee emp= dao.verifyByEmail(email, password);
				if (emp != null) {
					System.out.println("Employee Verified Succesfully");
				} else {
					System.err.println("Invalid Email Id or Password");
				}
				break;
			}
			
			case 6: {
				System.out.println("Enter the designation to find the emp");
				String designation = sc.next();
				Employee emp = dao.findEmpByDesign(designation);
				if (emp != null) {
					System.out.println("Employee Found");
					System.out.println("Id:" + emp.getId());
					System.out.println("Name:" + emp.getName());
					System.out.println("Designation:" + emp.getDesignation());
					System.out.println("Salary:" + emp.getSalary());
					System.out.println("Email:" + emp.getEmail());
					System.out.println("Phone:" + emp.getPhone());
					System.out.println("Password:" + emp.getPassword());
				} else {
					System.err.println("Invalid Designation");
				}
				break;
			}
			case 7: {
				System.out.println("Enter the Lower Sal");
				double lowsal = sc.nextDouble();
				System.out.println("Enter the Higher Sal");
				double highsal = sc.nextDouble();		
				List<Employee> emp1 = dao.filterEmpBySalary(lowsal,highsal);
				if(emp1.size()>0) {
					for(Employee emp:emp1) {
						System.out.println("Employee Found");
						System.out.println("Id:" + emp.getId());
						System.out.println("Name:" + emp.getName());
						System.out.println("Designation:" + emp.getDesignation());
						System.out.println("Salary:" + emp.getSalary());
						System.out.println("Email:" + emp.getEmail());
						System.out.println("Phone:" + emp.getPhone());
						System.out.println("Password:" + emp.getPassword());
					}
				}
				break;
			}
			case 8:{
				flag = false;
				System.out.println(dao.exit());
				sc.close();
			}
			}
	}
	}
}
