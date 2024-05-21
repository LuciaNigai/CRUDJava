package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import classes.Book;
import classes.DatabaseConnection;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/search")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Book> searchBooks = searchForBooks(request, response);
		request.getSession().setAttribute("searchBooks", searchBooks);
		String servletPath = request.getServletPath();
		response.sendRedirect(request.getContextPath()+servletPath);
	}
	
	private List<Book> searchForBooks(HttpServletRequest request, HttpServletResponse response){
		PreparedStatement statement=null;
		String search = request.getParameter("searchFor");
		String in = request.getParameter("search_parameter");
		List<Book> searchBooks = new ArrayList<>();
		
		Connection connection = DatabaseConnection.getInstance().getConnection();
		String sql="CALL search(?,?)";
		
		
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, search);
			statement.setString(2, in);
			ResultSet rs = statement.executeQuery(); // Execute the prepared statement, not the SQL string
			
			while(rs.next()) {
				String name = rs.getString("book_name");
				String author = rs.getString("author");
				String publisher = rs.getString("publisher");
				String year = rs.getString("year");
				String genres = rs.getString("genres");
				String isbn = rs.getString("isbn");
				
				Book book = new Book(name, author,publisher,year,genres,isbn);
				searchBooks.add(book);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return searchBooks;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("/UnifunProject/");
	}




}
