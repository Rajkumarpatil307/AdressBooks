package AdressBooks;

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
			System.out.println("X : Exit from main  Adress Book System\n");
			String str=s.next();
			characterOFmain=str.charAt(0); 
			if(characterOFmain=='X') {
				break;
			}
			if(characterOFmain=='M') {
				obj1.createAdressBook();
			}else if(characterOFmain=='N') {
				obj1.modifyAdressBook();
			}else{
				System.out.println("Enter the correct option");
			}

		}
	}
	public static void main(String[] args) {


		System.out.println("Welcome to Adress Book System\n");
		showOptionForCreateAdressBook();





	}

}