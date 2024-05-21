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
import java.sql.ResultSet;
import java.sql.SQLException;

import classes.DatabaseConnection;

/**
 * Servlet implementation class DeleteBookServlet
 */
@WebServlet("/delete-book")
public class DeleteBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PreparedStatement statement = null;
		
		String bookName = request.getParameter("bookName");
		String authorName = request.getParameter("authorName");
		System.out.println(bookName);
		System.out.println(authorName);
		String message = "";
		
		HttpSession session = request.getSession();
		
		Connection connection = DatabaseConnection.getInstance().getConnection();
		String sql = "CALL deleteBook(?,?)";
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, bookName);
			statement.setString(2, authorName);
			
			int rowsInserted = statement.executeUpdate();
			if(rowsInserted > 0) {
				message="Book deleted successfully";
			}
			
		} catch (SQLException e) {
			message="Error: "+e.getMessage();
		}
		finally {
			if(statement!=null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		session.setAttribute("message", message);
		String servletPath = request.getServletPath();
        response.sendRedirect(request.getContextPath()+servletPath);
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
        String message = (String) session.getAttribute("message");
        session.removeAttribute("message"); // remove the attribute after using it

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<script type=\"text/javascript\">");
        out.println("alert('" + message + "');");
        out.println("window.location.href = '/UnifunProject/';");

        out.println("</script>");
        out.println("</body></html>");
	}
}
