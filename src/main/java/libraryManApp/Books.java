package libraryManApp;

public class Books {
	private int ID;
	private String title;
	private String publisher;
	private String category;
	private String price;

	//constructors
	public Books() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Books(int iD, String title, String publisher, String category, String price) {
		super();
		ID = iD;
		this.title = title;
		this.publisher = publisher;
		this.category = category;
		this.price = price;
	}

	//setter & getter
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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Books [ID=" + ID + ", title=" + title + ", publisher=" + publisher + ", category=" + category
				+ ", price=" + price + "]";
	}
	
	
	

}

