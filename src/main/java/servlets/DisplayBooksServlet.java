package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import classes.Book;
import classes.DatabaseConnection;

@WebServlet(value = "", loadOnStartup = 1)
public class DisplayBooksServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Book> books = fetchDataFromDatabase(req,resp);
		req.setAttribute("books", books);
//		req.setAttribute("books", new Gson().toJson(books));
		RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
		dispatcher.forward(req, resp);
	}
	
	private List<Book> fetchDataFromDatabase(HttpServletRequest req, HttpServletResponse res){
	    List<Book> books = new ArrayList<Book>();
	    try {
	        Connection  connection = DatabaseConnection.getInstance().getConnection();
	        Statement stmt = connection.createStatement();
	        ResultSet rs = stmt.executeQuery("CALL getBooks();");
	        
	        while(rs.next()) {
	            String name = rs.getString("book_name");
	            String author = rs.getString("author");
	            String publisher = rs.getString("publisher");
	            String year = rs.getString("year");
	            String genres = rs.getString("genres");
	            String isbn = rs.getString("isbn");
	            
	            Book book = new Book(name, author,publisher,year,genres,isbn);
	            books.add(book);
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return books;
        
	}

}
