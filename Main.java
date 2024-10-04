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

public class Main {
	static AdressBook obj1=new AdressBook();
	static Scanner s=new Scanner(System.in);
	public static void showOptionForCreateAdressBook() {
		while(true) {
			char characterOFmain;
			System.out.println("Enter the below option for: \n");
			System.out.println("M: Create new Adrees Book System\n");
			System.out.println("N : Modify the  Adress Book System\n");
			System.out.println("O : Show the  Adress Book Systems\n");
			System.out.println("P : Remove Adress book System from  the  Adress Book Sytem Management\n");
			System.out.println("X : Exit from main  Adress Book System\n");
			String str=s.next();
			characterOFmain=str.charAt(0); 
			if(characterOFmain=='O') {
				try {
					Connection conAdressBook = DriverManager.getConnection("jdbc:mysql://localhost:3306/adressbookmanagementsystem", "root", "Rajupatil@1234");
					String sql="select * from adressbookmanagementsystem";
					Statement st=conAdressBook.createStatement();
				    ResultSet res=	st.executeQuery(sql);
				    System.out.println("The Adress Book Systems are belows :\n");
				   while(res.next()) {
					System.out.println("* "+res.getString(2));
				   }
				   System.out.println();
				}catch(Exception e) {
				
			}
			}else if(characterOFmain=='X') {
				break;
			}else
			if(characterOFmain=='M') {
				obj1.createAdressBook();
                
			}else if(characterOFmain=='N') {
				obj1.modifyAdressBook();
			} else if(characterOFmain=='P'){
				System.out.println("Enter the Adress Book System want to delete !");
				String tablename=s.next();
				try {
					Connection conAdressBook = DriverManager.getConnection("jdbc:mysql://localhost:3306/adressbookmanagementsystem", "root", "Rajupatil@1234");
				     String sql="drop table "+tablename;
				      Statement stmt=conAdressBook.createStatement();
				  
				   stmt.execute(sql) ;
					     String  sql1="delete from adressbookmanagementsystem where tableName=?";
					      PreparedStatement pstmt=conAdressBook.prepareStatement(sql1);
					      pstmt.setString(1, tablename);
						   pstmt.executeUpdate();
					      
					  
					   System.out.println("the Adress Book Sytem : "+tablename+"  removed successfully !\n");
				   
				}catch(Exception e) {  
					System.out.println("The Adress Book System does not Exist !\n");
				}
				    
				}else {
				
				System.out.println("Enter the correct option");
			}

		}
	}
	public static void main(String[] args)   {


		System.out.println("Welcome to Adress Book System\n");
	
		


	showOptionForCreateAdressBook();


	}

}
