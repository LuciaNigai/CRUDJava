/**
 * 
 */

var ctx ="http://localhost:8080/UnifunProject/";


function addBook() {
    // Perform the desired action here, such as navigating to a new page
    window.location.href = ctx+"resources/pages/addBook.jsp";
}

function deleteAllBooks(){
	var result = confirm("Are you sure you want to delete all books?");
    if (result) {
        // User pressed OK, redirect to the servlet
        window.location.href = ctx + "delete-all-books";
    }
}

// to check is the user left the page is yes send the post request
// to the servlet that will delete session data
window.onbeforeunload = function() {
    $.ajax({
        type: 'POST',
        url: '/UnifunProject/userLeftPage', 
        async: false
    });
};

// when user wants to change the page he will have to confirm that
// he is ok with changes before proceding
function confirmSubmit() {
        var r = confirm("Are you sure you want to submit the changes?");
        if (r == true) {
            return true;
        } else {
            return false;
        }
    }
function deleteBookSubmit() {
        var r = confirm("Are you sure you want to delete the book?");
        if (r == true) {
            return true;
        } else {
            return false;
        }
}  



