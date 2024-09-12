package AdressBooks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class AdressBook {
	Scanner sa=new Scanner(System.in);;
	Contact obj=new Contact();
	//String AdressBookName;
	static ArrayList<Contact> contactArrayList;
	static ArrayList adressBookArrayList=new ArrayList();
	static HashMap<String,ArrayList> mapForAdressName=new HashMap<String,ArrayList>();

	public  void createAdressBook() {
		System.out.println("Enter the Adress Book System name\n");
		String name=sa.next();
		contactArrayList= new ArrayList<Contact>();
		if(mapForAdressName.keySet()!=null) {
			for(String str2:mapForAdressName.keySet()) {
				if(str2.equals(name)) {
					System.out.println("The Adress Book System is already exist");
					Main.showOptionForCreateAdressBook();

				}
			}
		}
		mapForAdressName.put(name, contactArrayList);


		obj.showOptionForContact();
	}
	public static  ArrayList<Contact> getContactArrayList() {
		return contactArrayList;
	}
	public  void modifyAdressBook() {
		System.out.println("Enter the Adress Book System");
		String inputAdressBookName=sa.next();

		for(String str2:mapForAdressName.keySet()) {
			if(str2.equals(inputAdressBookName)) {
				obj.showOptionForContact();
				adressBookArrayList.addAll(AdressBook.getContactArrayList());
			}
		}
	}

	public static ArrayList<ArrayList> getAdressArrayList() {
		return adressBookArrayList;
	}
	public static HashMap<String,ArrayList> getHashMap() {
		return mapForAdressName;
	}

}
