import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * SD2x Homework #8
 * This class represents the Data Tier in the three-tier architecture.
 * Implement the appropriate methods for this tier below.
 */

public class DataTier {
	
	private String fileName; // the name of the file to read
	
	public DataTier(String inputSource) {
		fileName = inputSource;
	}
	

/*read the data file containing information about the books, 
  create Book objects for each, and then return the Book objects.
            [title][tab][author][tab][year]                    */
	
	public List<Book> getAllBooks (){
		
		// read file
		// get each line
		// split using '/t'
		// call Book constructor
		if(fileName==null) {
			return null;
		}
	    List<Book> bookList = new ArrayList<>(); 
	    BufferedReader in = null;
	    
	    try {
	    	in = new BufferedReader(new FileReader(fileName));
	    	String read = null;
	    	while ((read = in.readLine()) != null) {
	            String[] splited = read.split("\t");
	            int age = Integer.parseInt(splited[2]);
	    		Book newWord = new Book(splited[0], splited[1], age);
	    		bookList.add(newWord);
	    			
	        }
	    }catch(IOException ioe) {
	    	ioe.printStackTrace();
	    }finally {
	    	try{
	    		if(in != null)
	    			in.close();
	    	}catch(IOException ioe) {
	    		System.out.println("Error in closing the Buffered Reader");
	    	}
	    }
	   return bookList;
	}
}
