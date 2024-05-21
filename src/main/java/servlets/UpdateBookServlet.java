package servlets;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/update-book")
public class UpdateBookServlet extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookName = req.getParameter("bookName");
        String authorName = req.getParameter("authorName");
        String publisher = req.getParameter("publisher");
        String year = req.getParameter("year");
        String genre = req.getParameter("genre");
        String isbn = req.getParameter("isbn");

        // Store the book details in the request attributes
        req.setAttribute("bookName", bookName);
        req.setAttribute("authorName", authorName);
        req.setAttribute("publisher", publisher);
        req.setAttribute("year", year);
        req.setAttribute("genre", genre);
        req.setAttribute("isbn", isbn);

        // Forward the request to the update page
        req.getRequestDispatcher("/resources/pages/updateBook.jsp").forward(req, resp);
	}
}
