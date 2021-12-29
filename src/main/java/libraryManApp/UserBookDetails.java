package libraryManApp;

public class UserBookDetails implements Comparable<UserBookDetails> {
	private String title;
	private String publisher;
	private String category;
	private int year;
	
	
	
	public UserBookDetails() {
		// TODO Auto-generated constructor stub
	}

	public UserBookDetails(String title, String publisher, String category, int year) {
		super();
		this.title = title;
		this.publisher = publisher;
		this.category = category;
		this.year = year;
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

	@Override
	public String toString() {
		return "UserBookDetails [title=" + title + ", publisher=" + publisher + ", category=" + category + ", year="
				+ year + "]";
	}

	public int compareTo(UserBookDetails o) {
		return this.year -o.year;
	}
	

}
