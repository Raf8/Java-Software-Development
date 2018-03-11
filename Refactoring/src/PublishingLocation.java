
public class PublishingLocation {
	private String city;
	private String state;
	private String postCode;
	
	PublishingLocation(String city, String state, String postCode){
		this.city = city;
		this.state = state;
		this.postCode = postCode;
	}
	public String getCity() {
		return this.city;
	}

	public String getState() {
		return this.state;
	}

	public String getPostCode() {
		return this.postCode;
	}
	
}
