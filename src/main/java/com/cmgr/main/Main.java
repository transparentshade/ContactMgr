package com.cmgr.main;

import java.util.Scanner;
import java.util.Set;

import com.cmgr.bean.Contact;
import com.cmgr.service.ContactFacade;
import com.cmgr.validator.Validator;

public class Main {
	public static void main(String[] args) {
		//Entry point for all contact related operation like add, search, etc.
		ContactFacade cf = new ContactFacade();
		
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("Enter 1) Add contact 2) Search 3) Exit.\n");

			Integer choice = getInt(sc);
			if(choice == null) {
				sc.nextLine();
				continue;
			}
			sc.nextLine();

			switch (choice) {
				/**
				 * TO handle contact add
				 */
				case 1:
					System.out.println("Enter name: ");
					String line = sc.nextLine();
					line = line.trim();
					String[] names = line.split(" ");
					//only fname and/or lname is allowed.
					if (names == null || names.length < 1 ||  names.length > 2) {
						System.err.println("Invalid name string! Please enter valid name.\n");
						break;
					}
					Contact c = new Contact(names[0], names.length > 1 ? names[1] : "");
					cf.createContact(c);
					break;
				/**
				 * To handle search query on contact in memory datbase
				 */
				case 2:
					System.out.println("Enter name: ");
					String query = sc.next();
					sc.nextLine();
					Set<Contact> res = cf.searchContact(query);
				
					//Exception occurred and handled at facade level
					if(res == null) continue;
					
					if(res.isEmpty()){
						System.out.println("No contacts found!.\n");
						continue;
					}
					for(Contact cont : res){
						System.out.println(cont);
					}
					break;
				/**
				 * To handle exit from the application.
				 */
				case 3:
					System.out.println("Happy Searching.\n");
					System.exit(0);
				/** 
				 * To handle all unwanted input case.
				 */
				default :
					System.err.println("Please enter number between 1 to 3.\n");
					break;
			}
		}
	}
	public static Integer getInt(Scanner sc){
		int t;
		try{
			t = sc.nextInt();
			return t;
		}
		catch(Exception e){
			System.err.println("Please enter valid Integer");
			return null;
		}
	}
}
