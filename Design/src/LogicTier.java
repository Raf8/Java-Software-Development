import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * SD2x Homework #8
 * This class represents the Logic Tier in the three-tier architecture.
 * Implement the appropriate methods for this tier below.
 */

public class LogicTier {
	
	private DataTier dataTier; // link to the Data Tier
	
	public LogicTier(DataTier dataTier) {
		this.dataTier = dataTier;
	}
	
	/*
	 * for a given name, search through all of the books and 
	 * return the titles of those books whose author name includes the input name.
	 * 
	 * */
	
	public List<String> findBookTitlesByAuthor(String title) {
		if(title == null) {
			return null;
		}
		List<Book> bookList = dataTier.getAllBooks();
		List<String> titleList = new ArrayList<String>();
		
		for(Book item: bookList) {
			if(item.getAuthor().toLowerCase().contains(title.toLowerCase())) {
				titleList.add(item.getTitle());
			}
		}
		
		return titleList;
	}
	/*
	 * or a given year, search through all of the books and 
	 * return the number of books published in that year
	 * 
	 * */
	public int findNumberOfBooksInYear(int year){
		List<Book> books = dataTier.getAllBooks();
		int count = 0;
		
		for (Book book : books) {
			if (book.getPublicationYear() == year)
				count++;
		}
		
		return count;
	}
	
	

}
