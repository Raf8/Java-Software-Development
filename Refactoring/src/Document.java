import java.util.Date;

public abstract class Document {
/*
 *  title, author, date, and publishing location
 * */
	private String title;
	private String author;
	private Date date;
	
	PublishingLocation pubLoc;
	
	Document(String title, String author,Date date, String city, String state, String postCode){
		pubLoc = new PublishingLocation(city, state, postCode);
		this.title = title;
		this.author = author;
		this.date = date;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getAuthor() {
		return this.author;
	}
	
	public Date getDate() {
		return this.date;
	}
	
	public String getCity() {
		return pubLoc.getCity();
	}

	public String getState() {
		return pubLoc.getState();
	}

	public String getPostCode() {
		return pubLoc.getPostCode();
	}
	
	public boolean sameAuthor(Document doc){
		return this.author.equals(doc.getAuthor());
	}
	public int compareDates(Document doc){
		return this.date.compareTo(doc.getDate());
	}
	public int compareWithGeneralDate(Date date){
		return this.date.compareTo(date);
	}
}
