package classes;

public class Book {
	private String bookName;
	private String author_Name;
	private String publisher;
	private String year;
	private String genre;
	private String ISBN;

	
	
	
	public Book(String bookName, String author_Name, String publisher, String year, String genre, String iSBN) {
		this.bookName = bookName;
		this.author_Name = author_Name;
		this.publisher = publisher;
		this.year = year;
		this.genre = genre;
		ISBN = iSBN;
	}

	public String getBookName() {
		return bookName;
	}

	public String getAuthorName() {
		return author_Name;
	}

	public String getPublisher() {
		return publisher;
	}

	public String getYear() {
		return year;
	}

	public String getGenre() {
		return genre;
	}

	public String getISBN() {
		return ISBN;
	}


	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public void setAuthorName(String author_Name) {
		this.author_Name = author_Name;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

}
