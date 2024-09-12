package AdressBooks;

import java.util.ArrayList;
import java.util.Scanner;

public class Contact{
	Scanner sc=new Scanner(System.in);
	String firstName,lastName,adress,city,state,email;
	long phoneNumber;
	int zip;
	Contact contact;


	public  void showOptionForContact() {
		char charecter;

		while(true) {
			System.out.println("Enter the below option for: \n");
			System.out.println("A : Add contact in  Adress Book System\n");
			System.out.println("B : Modification of Contact in  Adress Book System\n");
			System.out.println("C : Remove contact from  the  Adress Book Sytem\n");
			System.out.println("D : Exit from the  Adress Book System\n");
			String str=sc.next();
			charecter=str.charAt(0); 
			if(charecter=='D') {
				break;
			}else if(charecter=='A') {
				System.out.println("enter the person name\n");
				String inputFirstName=sc.next();
				boolean b=true;
				for(Contact personName:AdressBook.getContactArrayList()) {
					if(personName.firstName.equals(inputFirstName)) {
						System.out.println("Person Name Already Exist");
						b=false;
					}
				}if(b) {
					contact=new Contact();
					contact.firstName=inputFirstName;
					createContact();
				}

			} else if(charecter=='B') {
				modifyContact();
			}else if(charecter=='C') {
				removeContact();
			}else {
				System.out.println("Enter the correct option");
			}

		}
	}
	public void createContact() {






		System.out.println("enter the last name\n");
		contact.lastName=sc.next();
		System.out.println("enter the phone no\n");
		contact.phoneNumber=sc.nextLong();
		System.out.println("enter the Adress details\n");
		sc.next();
		contact.adress=sc.nextLine();
		System.out.println("enter the zip no\n");
		contact.zip=sc.nextInt();
		System.out.println("enter the mail adress\n");
		contact.email=sc.next();
		System.out.println("enter the state name\n");
		contact.state=sc.next();

		System.out.println("Your contact is added to the Adress Book System\n");
		AdressBook.getContactArrayList().add(contact);
	}


	public void modifyContact() {
		System.out.println("enter the person name");
		String personName=sc.next();

		for(Contact contacts:AdressBook.getContactArrayList()) {
			if(contacts.firstName.equals(personName)) {
				System.out.println("enter the new phone number");
				contacts.phoneNumber=sc.nextLong();
				System.out.println("enter the new email adress");
				contacts.email=sc.next();
				System.out.println("enter the new city name");
				contacts.city=sc.next();
				System.out.println("Your contact is Modified in the Adress Book System");

			}	
		}
	}
	public void removeContact() {
		System.out.println("Enter the Adresss Book System Name");
		String adressBookName=sc.next();
		System.out.println("enter the person name want to delete ");
		String personName=sc.next();
		for(String inputAdressBookName:AdressBook.getHashMap().keySet()) {
			if(inputAdressBookName.equals(personName)) {
				AdressBook.getHashMap().remove(firstName);
				System.out.println("the contact is deleted from the Adress Book System");
			}
		}


	}
}