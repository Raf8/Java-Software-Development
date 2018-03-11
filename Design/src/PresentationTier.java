import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/*
 * SD2x Homework #8
 * This class represents the Presentation Tier in the three-tier architecture.
 * Implement the appropriate methods for this tier below. 
 * Also implement the start method as described in the assignment description.
 */

public class PresentationTier {
	
	private LogicTier logicTier; // link to the Logic Tier
	
	public PresentationTier(LogicTier logicTier) {
		this.logicTier = logicTier;
	}
	/*
	 *  PresentationTier.start so that it asks the user which feature they would like to use 
	 *  and invokes the appropriate methods in the appropriate classes. 
	 *  Once the output has been displayed, the program should terminate. 
	 *  As above, you can handle error cases in any way that you deem appropriate.*/
	public void start() {
		Scanner scan= new Scanner(System.in);
		System.out.println("Do you want to display number of 1. titles or  2. number of books in a year");
		String var = scan.nextLine();
		if(var.equals("1")) {showBookTitlesByAuthor();}
		else if(var.equals("2")) {showNumberOfBooksInYear();}
		else {System.out.println("Invalid Input");}
	}
	
	/*
	showBookTitlesByAuthor: using the command-line (i.e., reading from System.in), 
	ask the user to enter part or all of an author’s name, 
	then display (using System.out) the titles of those books whose author name includes the input name.
	*/
	public void showBookTitlesByAuthor() {
		String name;
		List<String> titleList;
		Scanner scan= new Scanner(System.in);
		System.out.println("Enter the Author's name");
		name = scan.nextLine();
		if(name==null) {
			System.out.println("Invalid string name");
			return;
		}
		else {
			titleList = new ArrayList<String>();
			titleList = logicTier.findBookTitlesByAuthor(name);
		}
		for (String elem: titleList) {
			System.out.println(elem);
		}
		
	}
	
	/*
	 * : using the command-line (i.e., reading from System.in), ask the user to enter a year, 
	 * then display (using System.out) the number of books published in that year
	 * */
	
	public void showNumberOfBooksInYear() {
		System.out.print("Which year: ");
		Scanner scan= new Scanner(System.in);
		int year = scan.nextInt();		
		int numberOfBooks = logicTier.findNumberOfBooksInYear(year);
		
		System.out.println("Number of books in " + year + " is " + numberOfBooks);
		
	}
	
	



}
