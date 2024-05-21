package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import classes.DatabaseConnection;

@WebServlet("/add-book")
public class AddBook extends HttpServlet {
    static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
        Connection connection = null;
        PreparedStatement statement = null;

        String bookName = req.getParameter("fbook");
        String authorName = req.getParameter("fauthor");
        String publisherName = req.getParameter("fpublisher");
        String year = req.getParameter("fyear");
        String genre = req.getParameter("fgenre");
        String isbn = req.getParameter("fisbn");
        String message = "";
        
// saving the book in http session if the exception will be thrown the user 
//  the page will reload with the same data that he entered earlier    

        HttpSession session = req.getSession();
        session.setAttribute("fbook", bookName);
        session.setAttribute("fauthor", authorName);
        session.setAttribute("fpublisher", publisherName);
        session.setAttribute("fyear", year);
        session.setAttribute("fgenre", genre);
        session.setAttribute("fisbn", isbn);
        
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            String sql = "CALL addBook(?,?,?,?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, bookName);
            statement.setString(2, authorName);
            statement.setString(3, publisherName);
            statement.setString(4, year);
            statement.setString(5, genre);
            statement.setString(6, isbn);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
//                req.setAttribute("message", "New book successfully added");
            	message = "New book successfully added";
            	session.removeAttribute("fbook");
                session.removeAttribute("fauthor");
                session.removeAttribute("fpublisher");
                session.removeAttribute("fyear");
                session.removeAttribute("fgenre");
                session.removeAttribute("fisbn");
            }

        } catch (SQLException e) {
//            req.setAttribute("message", "Error: " + e.getMessage());
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
//		resp.sendRedirect("/UnifunProject/");
		HttpSession session = req.getSession();
        String message = (String) session.getAttribute("message");
        session.removeAttribute("message"); // remove the attribute after using it

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        out.println("<script type=\"text/javascript\">");
        out.println("alert('" + message + "');");
        if(message.equals("New book successfully added")) {
            out.println("window.location.href = '/UnifunProject/';");
        }
        else {
            out.println("window.location.href = '/UnifunProject/resources/pages/addBook.jsp';");
        }
        out.println("</script>");
        out.println("</body></html>");
	}
}
