package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import classes.DatabaseConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/perform-book-update")
public class PerformBookUpdateServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection connection = null;
        PreparedStatement statement = null;
        String oldBookName = req.getParameter("oldBook");
        String oldAuthorName = req.getParameter("oldAuthor");
        String oldPublisherName = req.getParameter("oldPublisher");
        String oldYear = req.getParameter("oldYear");
        String oldGenre = req.getParameter("oldGenre");
        String oldISBN = req.getParameter("oldISBN");
        
        String bookName = req.getParameter("fbook");
        String authorName = req.getParameter("fauthor");
        String publisherName = req.getParameter("fpublisher");
        String year = req.getParameter("fyear");
        String genre = req.getParameter("fgenre");
        String isbn = req.getParameter("fisbn");
        
        String message = "";
        
        HttpSession session = req.getSession();
        
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            String sql = "CALL editBook(?,?,?,?,?,?,?,?,?,?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, oldBookName);
            statement.setString(2, oldAuthorName);
            statement.setString(3, oldPublisherName);
            statement.setString(4, oldYear);
            statement.setString(5, oldGenre);
            statement.setString(6, oldISBN);
            statement.setString(7, bookName);
            statement.setString(8, authorName);
            statement.setString(9, publisherName);
            statement.setString(10, year);
            statement.setString(11, genre);
            statement.setString(12, isbn);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                message = "The changes were applied";
            }

        } catch (SQLException e) {
        	message = "Error: " + e.getMessage();
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        session.setAttribute("message", message);
        String servletPath = req.getServletPath();
        resp.sendRedirect(req.getContextPath()+servletPath);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    HttpSession session = req.getSession();
	    String message = (String) session.getAttribute("message");
	    message = message.replace("'", "\\'"); // escape single quotes
	    session.removeAttribute("message"); // remove the attribute after using it

	    resp.setContentType("text/html");
	    PrintWriter out = resp.getWriter();
	    out.println("<html><body>");
	    out.println("<script type=\"text/javascript\">");
	    if (message!="The changes were applied")
	    	out.println("alert('" + message + "');");
	    out.println("window.location.href = '/UnifunProject/';");
	    out.println("</script>");
	    out.println("</body></html>");
	}


	

}
