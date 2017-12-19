package com.staed.ers;

import java.util.Scanner;

import com.staed.ers.dao.EmployeeDAO;
import com.staed.ers.dao.ReimbursementDAO;

public class App {
	private Service service;
	private Scanner sc;
	
	public App() {
		service = new Service(new EmployeeDAO(), new ReimbursementDAO());
		sc = new Scanner(System.in);
	}

	public static void main(String[] args) {
		App app = new App();
		
		String buffer;
		System.out.println(app.welcomeText());
		
		do {
			buffer = app.getNextLine();
			switch(buffer.toLowerCase()) {
				case "login":
					app.getLogin();
					break;
				case "viewall":
					app.getService().viewAll();
					break;
				case "logout":
					app.getService().logout();
					break;
				case "submit":
					app.request();
					break;
				case "quit":
					System.out.println("Goodbye.");
					return;
				default:
					System.out.print("\nCommand was not recognized.");
					break;
			}
			System.out.println("\nWhat is your next command?");
		} while(!buffer.equals("quit"));
	}
	
	public String welcomeText() {
		return "Welcome to the Employee Reimbursement System\nWhat would you like to do?";
	}
	
	public String getNextLine() {
		return sc.nextLine().trim();
	}
	
	public Service getService() {
		return service;
	}
	
	public void getLogin() {
		System.out.println("Enter your username");
		String user = getNextLine();
		System.out.println("Enter your password");
		String pass = getNextLine();
		
		service.login(user, pass);
	}
	
	public void request() {
		if (!service.loggedIn()) {
			System.out.println("You need to be logged in to submit a reimbursement request.");
		} else {
			String dateString = null;
			System.out.println("Enter the date of the event you want reimbursement for.");
			do {
				System.out.println("Use the yyyy-MM-dd format.");
				dateString = getNextLine();
			} while (!dateString.matches("\\d\\d\\d\\d-[01]\\d-(([012]\\d)|(3[01]))"));
			
			System.out.println("Enter a description for the request.");
			String desc = getNextLine();
			
			System.out.println("Enter the amount to be reimbursed.");
			String amtString = getNextLine();
			while (!amtString.matches("\\d+(\\.\\d\\d?)?")) {
				System.out.println("Please use the XXX or X.XX style formats.");
				amtString = getNextLine();
			}
			float amount = Float.parseFloat(amtString);
			service.request(dateString, desc, amount);
		}
	}

}
