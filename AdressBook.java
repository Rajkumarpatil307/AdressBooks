package AdressBooks;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

public class AdressBook {

	Scanner sa=new Scanner(System.in);;
	Contact obj=new Contact();
	static ArrayList<Contact> contactArrayList;
	static String tableName;


	static ArrayList adressBookArrayList=new ArrayList();
	static HashMap<String,ArrayList> mapForAdressName=new HashMap<String,ArrayList>();

	public  void createAdressBook() {
		System.out.println("Enter the Adress Book System name :\n");
		String name=sa.next();
		tableName=name;
		try {
			Connection conAdressBook = DriverManager.getConnection("jdbc:mysql://localhost:3306/adressbookmanagementsystem", "root", "Rajupatil@1234");
			String createTableSQL = "CREATE TABLE IF NOT EXISTS " + name + " ("
					+ "ID INT  AUTO_INCREMENT PRIMARY KEY, "
					+ "firstName VARCHAR(100) , "
					+"lastName varchar(100),"
					+ "address VARCHAR(255), "
					+"city varchar(50),"
					+"zip int , "
					+"phoneNum bigint , "
					+ "email VARCHAR(100) ,"
					+ "CONSTRAINT constraint_firstName UNIQUE (firstName) "
					+ ")";

			Statement stmt=conAdressBook.createStatement();

			stmt.execute(createTableSQL);
			String query = "INSERT INTO adressbookmanagementsystem (tableName) VALUES (?)";
			PreparedStatement stmt1 = conAdressBook.prepareStatement(query);
			stmt1.setString(1, name);
			stmt1.executeUpdate();
			obj.showOptionForContact(name);



		}catch(Exception e) {
			System.out.println("the Adress Book System "+name+" is already exist !\n");		}

	}
	public static String getTableName() {
		return tableName;
	}
	public static  ArrayList<Contact> getContactArrayList() {
		return contactArrayList;
	}

	public void modifyAdressBook() {
	    System.out.println("Enter the Address Book System: ");
	    String inputAdressBookName = sa.next();

	    String sql = "SELECT * FROM adressbookmanagementsystem WHERE tableName = ?";

	    try {
	        Connection conAdressBook = DriverManager.getConnection("jdbc:mysql://localhost:3306/adressbookmanagementsystem", "root", "Rajupatil@1234");
	        PreparedStatement pstmt = conAdressBook.prepareStatement(sql);
	        pstmt.setString(1, inputAdressBookName); // Set the parameter

	        ResultSet rs = pstmt.executeQuery();

	        if (!rs.next()) { // Check if the result set is empty
	            System.out.println("The Address Book System does not exist!");
	            return;
	        }

	        while (true) {
	            System.out.println("Enter the below option for: \n");
	            System.out.println("A: Add contact in Address Book System\n");
	            System.out.println("B: Modification of Contact in Address Book System\n");
	            System.out.println("C: Remove contact from the Address Book System\n");
	            System.out.println("D: Exit from the Address Book System\n");
	            System.out.println("S: Show contacts from the Address Book System\n");

	            String str = sa.next();
	            char charForMod = str.charAt(0);

	            if (charForMod == 'A') {
	                obj.f1().insertContact(inputAdressBookName);
	            } else if (charForMod == 'C') {
	                obj.removeContact(inputAdressBookName);
	            } else if (charForMod == 'S') {
	                obj.showContacts(inputAdressBookName);
	            } else if (charForMod == 'D') {
	                break;
	            } else {
	                System.out.println("Enter the correct Options!\n");
	            }
	        }

	    } catch (Exception e) {
	        System.out.println("The Address Book System does not exist!\n");
	        e.printStackTrace();
	    }
	}

	public static ArrayList<ArrayList> getAdressArrayList() {
		return adressBookArrayList;
	}
	public static HashMap<String,ArrayList> getHashMap() {
		return mapForAdressName;


	}

}
