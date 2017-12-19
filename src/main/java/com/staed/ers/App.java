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
		/*System.out.print("All: ");
		System.out.println(app.getEmployeeDAO().getAllEmployee());
		
		System.out.print("\nSingle: ");
		System.out.println(app.getEmployeeDAO().getEmployee(2));
		
		System.out.print("\nAll Reimbursement: ");
		System.out.println(app.getReimbursementDAO().getAllReimbursement());
		
		System.out.print("\nSingle: ");
		System.out.println(app.getReimbursementDAO().getReimbursement(1));*/
		
		App app = new App();
		
		String buffer;
		app.printWelcomeText();
		
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
				case "quit":
					return;
				default:
					break;
			}
			System.out.println("\nWhat is your next command?");
		} while(!buffer.equals("quit"));
	}
	
	public void printWelcomeText() {
		System.out.println("Welcome to the Employee Reimbursement System");
		System.out.println("What would you like to do?");
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
		//service.login("doge2018", "#notmydog");
	}

}
