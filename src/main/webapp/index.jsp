<html>
<head>
<title>UnifunProject</title>
<link rel="stylesheet" type="text/css" href="resources/css/style.css" />
<script type="text/javascript" src="resources/js/script.js" defer></script>
<%@ page import="java.util.List" %>
<%@ page import="classes.Book" %>
</head>
<body>
<% String message = (String) request.getAttribute("message");
       if (message != null) { %>
        <script type="text/javascript">
            alert('<%= message %>');
        </script>
    <% } %>
	<div class="main_class">
		<h1>Books List</h1>
		<table>
			<thead>
				<tr>
					<th>Book name</th>
					<th>Author</th>
					<th>Publisher</th>
					<th>Year</th>
					<th>Genre</th>
					<th>ISBN</th>
					<th colspan="2">Operations</th>
				</tr>
			</thead>
			<tbody>
<% List<Book> books = (List<Book>) request.getAttribute("books");
   if (books != null) {
       for (Book book : books) { %>
           <tr>
               <td><%= book.getBookName() %></td>
               <td><%= book.getAuthorName() %></td>
               <td><%= book.getPublisher() %></td>
               <td><%= book.getYear() %></td>
               <td><%= book.getGenre() %></td>
               <td><%= book.getISBN() %></td>
               <td colspan="1">
                   <form action="update-book" method="POST">
                       <input type="hidden" name="bookName" value="<%= book.getBookName() %>">
                       <input type="hidden" name="authorName" value="<%= book.getAuthorName() %>">
                       <input type="hidden" name="publisher" value="<%= book.getPublisher() %>">
                       <input type="hidden" name="year" value="<%= book.getYear() %>">
                       <input type="hidden" name="genre" value="<%= book.getGenre() %>">
                       <input type="hidden" name="isbn" value="<%= book.getISBN() %>">
                       <input type="submit" value="Update">
                   </form>
               </td>
               <td colspan="1">
               		<form action="delete-book" method="POST" onsubmit="return deleteBookSubmit()">
                       <input type="hidden" name="bookName" value="<%= book.getBookName() %>">
                       <input type="hidden" name="authorName" value="<%= book.getAuthorName() %>">
                       <input type="submit" value="Delete">
                   </form>
               </td>
           </tr>
       <% }
   } %>
</tbody>
</table>


	</div>
		<button class="buttonInsert" onclick="addBook()">Insert new book</button>
		<button onclick="deleteAllBooks()">Delete all books</button>

	<div class="searchContainer">
		<form action="search" method="POST">
	          	<input class="search-bar" type="text" name="searchFor" placeholder="Search..."> <br>
	          	<input type="radio" id="book" name="search_parameter" value="book">
				<label for="book">Book name</label>
				<input type="radio" id="author" name="search_parameter" value="author">
				<label for="author">Author name</label>
				<input type="radio" id="publisher" name="search_parameter" value="publisher">
				<label for="publisher">Publisher</label> 
				<input type="radio" id="year" name="search_parameter" value="year">
				<label for="year">Published year</label> 
				<input type="radio" id="genre" name="search_parameter" value="genre">
				<label for="genre">Genre</label> 
				<input type="radio" id="isbn" name="search_parameter" value="isbn">
				<label for="isbn">ISBN</label><br> 
	            <input type="submit" value="Search">
		</form>
	</div>
	
<div class="main_class">
	<%
	List<Book> searchBooks = (List<Book>) session.getAttribute("searchBooks");
	
   if (searchBooks != null && !searchBooks.isEmpty()) { %>
    <table>
        <thead>
            <tr>
                <th>Book name</th>
                <th>Author</th>
                <th>Publisher</th>
                <th>Year</th>
                <th>Genre</th>
                <th>ISBN</th>
            </tr>
        </thead>
        <tbody>
            <% for (Book book : searchBooks) { %>
                <tr>
                    <td><%= book.getBookName() %></td>
                    <td><%= book.getAuthorName() %></td>
                    <td><%= book.getPublisher() %></td>
                    <td><%= book.getYear() %></td>
                    <td><%= book.getGenre() %></td>
                    <td><%= book.getISBN() %></td>
                </tr>
            <% } %>
        </tbody>
    </table>
<% } else { %>
    <h4>No books found.</h4>
<% }
	session.removeAttribute("searchBooks"); %>
</div>

	

</body>
</html>
