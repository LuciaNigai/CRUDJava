package classes;

/**
 * Class represents the book
 */
public class Book {
	
	private String bookName;
	private String author_Name;
	private String publisher;
	private String year;
	private String genre;
	private String ISBN;

	
	
	/**
	 * Constructor of book clss.
	 * @param bookName The name of the book.
	 * @param author_Name The author name.
	 * @param publisher The publisher name.
	 * @param year The year the book was published.
	 * @param genre The book genres.
	 * @param iSBN The book isbn code.
	 */
	public Book(String bookName, String author_Name, String publisher, String year, String genre, String iSBN) {
		this.bookName = bookName;
		this.author_Name = author_Name;
		this.publisher = publisher;
		this.year = year;
		this.genre = genre;
		ISBN = iSBN;
	}

	/**
	 * Getter method returns the book name.
	 * @return The book name.
	 */
	public String getBookName() {
		return bookName;
	}

	/**
	 * Getter method returns author name.
	 * @return The author name.
	 */
	public String getAuthorName() {
		return author_Name;
	}

	/**
	 * Getter method returns publisher.
	 * @return The book publisher.
	 */
	public String getPublisher() {
		return publisher;
	}

	public String getYear() {
		return year;
	}

	/**
	 * Getter method returns the year the book was published in.
	 * @return The book publication year.
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * Getter method returns book's isbn code.
	 * @return The book's isbn code.
	 */
	public String getISBN() {
		return ISBN;
	}

	/**
	 * Setter method for book name.
	 * @param bookName
	 */
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	/**
	 * Setter method for book author.
	 * @param author_Name
	 */
	public void setAuthorName(String author_Name) {
		this.author_Name = author_Name;
	}

	/**
	 * Setter method for book publisher.
	 * @param publisher
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	/**
	 * Setter method for book's publication year.
	 * @param year
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * Setter method for book's genres.
	 * @param genre
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}

	/**
	 * Setter method for book's isbn code.
	 * @param iSBN
	 */
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

}
