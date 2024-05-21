<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>updateBook</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/script.js" defer></script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/style.css" />
</head>
<body>

<form class="newBookForm" method="post" action="<%=request.getContextPath()%>/perform-book-update" onsubmit="return confirmSubmit();">
<!-- old book data -->
<input type="hidden" id="oldBook" name="oldBook" value="${requestScope.bookName}" required>
<input type="hidden" id="oldAuthor" name="oldAuthor" value="${requestScope.authorName}" required>
<input type="hidden" id="oldPublisher" name="oldPublisher" value="${requestScope.publisher}" required>
<input type="hidden" value="${requestScope.year}" id="oldYear" name="oldYear"> <br>
<input type="hidden" id="oldGenre" name="oldGenre" value="${requestScope.genre}" required><br>
<input type="hidden" id="oldISBN" name="oldISBN" minlength="13" maxlength="13" value="${requestScope.isbn}"><br>
<!-- new book data -->
<label for="fbook">Book name</label>
<input type="text" id="fbook" name="fbook" value="${requestScope.bookName}" required><br>
<label for="fauthor">Author name</label>
<input type="text" id="fauthor" name="fauthor" value="${requestScope.authorName}" required><br>
<label for="fpublisher">Publisher</label>
<input type="text" id="fpublisher" name="fpublisher" value="${requestScope.publisher}" required><br>
<label for="fyear">Pubication year</label>
<input type="number" min="1900" max="2024" step="1" value="${requestScope.year}" id="fyear" name="fyear"> <br>
<label for="fgenre">Genre</label>
<input type="text" id="fgenre" name="fgenre" value="${requestScope.genre}" required><br>
<label for="fisbn">ISBN</label>
<input type="text" id="fisbn" name="fisbn" minlength="13" maxlength="13" value="${requestScope.isbn}"><br>
<input type="submit" value="Submit" name="submitbutton">
</form>

</body>
</html>