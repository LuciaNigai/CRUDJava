<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>addBook</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/script.js" defer></script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/style.css" />
</head>
<body>

<form class="newBookForm" action="<%=request.getContextPath()%>/add-book" method="post">
<label for="fbook">Book name</label>
<input type="text" id="fbook" name="fbook" value="${sessionScope.fbook}" required><br>
<label for="fauthor">Author name</label>
<input type="text" id="fauthor" name="fauthor" value="${sessionScope.fauthor}" required><br>
<label for="fpublisher">Publisher</label>
<input type="text" id="fpublisher" name="fpublisher" value="${sessionScope.fpublisher}" required><br>
<label for="fyear">Pubication year</label>
<input type="number" min="1900" max="2024" step="1" value="${sessionScope.fyear}" id="fyear" name="fyear"> <br>
<label for="fgenre">Genre</label>
<input type="text" id="fgenre" name="fgenre" value="${sessionScope.fgenre}" required><br>
<label for="fisbn">ISBN</label>
<input type="text" id="fisbn" name="fisbn" minlength="13" maxlength="13" value="${sessionScope.fisbn}"><br>
<input type="submit" value="Submit" name="submitbutton">
</form>

</body>
</html>