package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mysql.cj.xdevapi.Statement;

import classes.DatabaseConnection;

@WebServlet("/delete-all-books")
public class DeleteAllBooksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DatabaseConnection.getInstance().getConnection();
			String sql = "CALL deleteAllBooks()";
			statement = connection.prepareStatement(sql);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                request.setAttribute("message", "All books deleted successfully");
            }
		} catch (SQLException e) {
			 request.setAttribute("message", "Error: " + e.getMessage());
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		response.sendRedirect("/UnifunProject/");
	}

}
