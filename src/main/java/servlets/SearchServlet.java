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
		List<Book> bookList = (List<Book>) request.getSession().getAttribute("books");
		String search = request.getParameter("searchFor");
		search=search.trim();
		search=search.toLowerCase();
		String in = request.getParameter("search_parameter");
		List<Book> searchBooks = new ArrayList<>();
		
		switch(in) {
		case "book" : 
			for(Book b :  bookList) {
				if(b.getBookName().toLowerCase().contains(search))
					searchBooks.add(b);
			}
		break;
		case "author":
			for(Book b : bookList) {
				if(b.getAuthorName().toLowerCase().contains(search))
					searchBooks.add(b);
			}
		break;
		case "publisher":
			for(Book b : bookList) {
				if(b.getPublisher().toLowerCase().contains(search))
					searchBooks.add(b);
			}
		break;
		case "year" :
			for(Book b : bookList) {
				if(b.getYear().equals(search))
					searchBooks.add(b);
			}
		break;
		case "genre" :
			for(Book b : bookList) {
				String[] array = b.getGenre().split(",");
				for(int i=0; i< array.length; i++) {
					array[i]=array[i].trim();
					if(array[i].equals(search))
						searchBooks.add(b);
				}
				
			}
		break;
		case "isbn" :
			for(Book b : bookList) {
				if(b.getISBN().equals(search))
					searchBooks.add(b);
			}
		break;
		}
		
		for (int i = 0; i < searchBooks.size(); i++) {
		    System.out.println(searchBooks.get(i).getAuthorName());
		}

		
		return searchBooks;
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("/UnifunProject/");
	}




}
