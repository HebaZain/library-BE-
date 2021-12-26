package libraryManApp;

public class Books {
	private int ID;
	private String title;
	private String publisher;
	private String category;
	private int year;
	
	public Books() {}
	
	public String getTitle() {
		return title;
	}
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
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
		return "Books [ID=" + ID + ", title=" + title + ", publisher=" + publisher + ", category=" + category
				+ ", year=" + year + "]";
	}

	
	
	
	

}

