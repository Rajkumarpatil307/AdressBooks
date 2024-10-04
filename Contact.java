package AdressBooks;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Contact{
	Scanner sc=new Scanner(System.in);
	String firstName,lastName,adress,city,email;
	String state;
	long phoneNumber;
	int zip;
	Contact contact;
    // Regex patterns
    private static final String NAME_PATTERN = "^[A-Za-z]{2,}$";  // Only letters, at least 2 characters
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";  // Basic email validation
    private static final String PHONE_PATTERN = "^[0-9]{10}$";  // 10-digit phone number
    private static final String ZIP_PATTERN = "^[0-9]{6}$"; 
    
    private boolean validateInput(String input, String pattern) {
        return Pattern.matches(pattern, input);
    }

	public Scanner getSc() {
		return sc;
	}
	public void setSc(Scanner sc) {
		this.sc = sc;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public int getZip() {
		return zip;
	}
	public void setZip(int zip) {
		this.zip = zip;
	}
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	public  void showOptionForContact(String tableName) {
		char charecter;

		while(true) {
			System.out.println("Enter the below option for: \n");
			System.out.println("A : Add contact in  Adress Book System\n");
			
			
			System.out.println("D : Exit from the  Adress Book System\n");
			System.out.println("S : Show  contacts from  the  Adress Book System\n");

			String str=sc.next();
			charecter=str.charAt(0); 
			
			if(charecter=='D')
			{
				break;
			}else if(charecter=='A') {
			//	insertContact(tableName);
				
				f1().insertContact(AdressBook.getTableName());
				
				
				
				


			}
			else if(charecter=='S') {
				showContacts(tableName);
			}else {
				System.out.println("Enter the correct option");
			}

		}

	}



	public boolean modifyContact(String tableName) {
		System.out.println("enter the person Name");
		String firstName=sc.next();
		if (!validateInput(firstName, NAME_PATTERN)) {
            System.out.println("Invalid first name! Only letters, at least 2 characters\n.");
            return false;
        }
		
		try {
			
			Connection cons=DriverManager.getConnection("jdbc:mysql://localhost:3306/adressbookmanagementsystem", "root", "Rajupatil@1234");
			String sql = "update "+ tableName +"  set phoneNum=?,email=?,city=? where firstName= ?";
			PreparedStatement stmt = cons.prepareStatement(sql);
	
			System.out.println("enter the new phone number\n");
			
			String phoneNum = sc.next();
            if (!validateInput(phoneNum, PHONE_PATTERN)) {
                System.out.println("Invalid phone number! It should be exactly 10 digits.\n");
                return false;
            }
            Long newPhone = Long.parseLong(phoneNum);
			
			System.out.println("enter the new email adress\n");
			String emailInput = sc.next();
            if (!validateInput(emailInput, EMAIL_PATTERN)) {
                System.out.println("Invalid email format!");
                return false;
            }
            String newEmail = emailInput;
			
			System.out.println("enter the new city name\n");
			 String city=sc.next();
			stmt.setLong(1,newPhone);
			stmt.setString(2, newEmail);
			stmt.setString(3, city);
			stmt.setString(4, firstName);
			 int rowsInserted = stmt.executeUpdate();
             if (rowsInserted > 0) {
            	 System.out.println("Your contact is Modified in the Adress Book System");  
            	 return true;
            	 }
		}catch(Exception e) {
			e.printStackTrace();
		};
		return false;

	}
	
	//public boolean insertContact(String tableName) { 
	AddContact conInterface=(tableName)->{
		try {
			Contact contact=new Contact();
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/adressbookmanagementsystem", "root", "Rajupatil@1234");
			
			
			String sql = "INSERT INTO "+tableName+" (firstName, lastName, address, city, zip, phoneNum, email)\r\n"
					+ " VALUES (?, ?, ?, ?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			System.out.println("enter the first name\n");
			String firstName=sc.next();
			if (!validateInput(firstName, NAME_PATTERN)) {
                System.out.println("Invalid first name! Only letters, at least 2 characters\n.");
                return false;
            }
			contact.firstName=firstName;
			System.out.println("enter the last name\n");
			 String lastNameInput = sc.next();
	            if (!validateInput(lastNameInput, NAME_PATTERN)) {
	                System.out.println("Invalid last name! Only letters, at least 2 characters.\n");
	                return false;
	            }
	            contact.lastName = lastNameInput;;
			System.out.println("enter the phone no\n");
			String phoneInput = sc.next();
            if (!validateInput(phoneInput, PHONE_PATTERN)) {
                System.out.println("Invalid phone number! It should be exactly 10 digits.\n");
                return false;
            }
            contact.phoneNumber = Long.parseLong(phoneInput);
			System.out.println("enter the Adress details\n");
			sc.nextLine();
			contact.adress=sc.nextLine();
			System.out.println("enter the zip no\n");
			String zipInput = sc.next();
            if (!validateInput(zipInput, ZIP_PATTERN)) {
                System.out.println("Invalid zip code! It should be exactly 5 digits.\n");
                return false;
            }
            contact.zip = Integer.parseInt(zipInput);
			System.out.println("enter the mail adress\n");
			String emailInput = sc.next();
            if (!validateInput(emailInput, EMAIL_PATTERN)) {
                System.out.println("Invalid email format!");
                return false;
            }
            contact.email = emailInput;
			System.out.println("enter the city name\n");
			contact.city=sc.next();

			  stmt.setString(1, contact.firstName);
              stmt.setString(2, contact.lastName);
              stmt.setString(3, contact.adress);
              stmt.setString(4,contact.city );
              stmt.setInt(5,contact.zip);
              stmt.setLong(6,contact.phoneNumber );
              stmt.setString(7,contact.email );
              int rowsInserted = stmt.executeUpdate();
              if (rowsInserted > 0) {
            	  System.out.println("A new entry was added successfully!\n");
            	  return true;
                 
              }
             
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;

	
	}; 
	public AddContact f1() {
		//System.out.println(conInterface);
		return conInterface;
	}
	public boolean showContacts(String adressBookName) {
		System.out.println("The contacts from the Adress Book System");
		try {
			Connection conr=DriverManager.getConnection("jdbc:mysql://localhost:3306/adressbookmanagementsystem", "root", "Rajupatil@1234");
			String sql="select * from "+adressBookName;
			Statement st=conr.createStatement();
		ResultSet res=	st.executeQuery(sql);
		int showrow=0;
		while(res.next()) {
			showrow++;
			System.out.println(res.getInt(1)+" \t"+res.getString(2)+"\t "+res.getString(3)+"\t "+res.getString(4)+" \t"+res.getString(5)+" \t"+res.getInt(6)+" \t"+res.getLong(7)+"\t"+res.getString(8));
		}
        if(showrow>0) {
			return true;
		 }
		}catch(Exception e) { 
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean removeContact(String tableName) {
		
		System.out.println("enter the person name want to delete ");
		String firstName=sc.next();
		if (!validateInput(firstName, NAME_PATTERN)) {
            System.out.println("Invalid first name! Only letters, at least 2 characters\n.");
            return false;
        }
		try {
			Connection cons=DriverManager.getConnection("jdbc:mysql://localhost:3306/adressbookmanagementsystem", "root", "Rajupatil@1234");
			String sql="delete from "+tableName+" where firstName= ? ";
			PreparedStatement stmt = cons.prepareStatement(sql);
            stmt.setString(1, firstName);
            int rs=stmt.executeUpdate();
            if (rs > 0) {
           	 System.out.println("Your contact is removed  from the Adress Book System !");   
         	return true;

           	 }
		
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("The Person name is not exist in the Adress Book System : "+tableName);
		};
		
		
		
		return false;

	}
}