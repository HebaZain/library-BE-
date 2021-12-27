package libraryManApp;

public class BookDetails {
	private int ID;
	private String title;
	private String publisher;
	private String category;
	private int year;
	private String hide;
	
	public BookDetails() {};
	public BookDetails(int iD, String title, String publisher, String category, int year, String hide) {
		ID = iD;
		this.title = title;
		this.publisher = publisher;
		this.category = category;
		this.year = year;
		this.hide = hide;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getHide() {
		return hide;
	}
	public void setHide(String hide) {
		this.hide = hide;
	}
	@Override
	public String toString() {
		return "BoookDetails [ID=" + ID + ", title=" + title + ", publisher=" + publisher + ", category=" + category
				+ ", year=" + year + ", hide=" + hide + "]";
	}
	
}
