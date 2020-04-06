<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page
	import="com.emetads.controller.User,java.util.ArrayList,java.util.List, java.io.ByteArrayOutputStream,java.util.Base64"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Search Results</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="css/main.css" rel="stylesheet">
<script>
			$("ul li").on("click", function() {
				$("li").removeClass("active");
				$(this).addClass("active");
			});
			
			$('document').ready(function(){
				$('.card-link').click(function(event) { 
				    event.preventDefault(); 
				    url=$(this).attr('href');
			        var likee = String(url).match(/likee=([^&]*)/);
			        var liker = String(url).match(/liker=([^&]*)/);
				    $.ajax({
				        url: $(this).attr('href'),
				        success: function(response) {

				        	$("#card-footer-"+likee[1]).css("background", "#9AF976");
				            if(response == 2){
					        	$("#userVal").val(liker[1]);
					        	$("#intUserVal").val(likee[1]);
					        	$("#emailModal").modal("toggle");
				            }
				        }
				    });
				});
				
				$('.btn-smessage').click(function(event) { 
				    event.preventDefault();
		        	$("#emailModal").modal("toggle");
				    var liker=$("#userVal").val();
				    var likee=$("#intUserVal").val();
				    //var likerEmail=$("#emailU-"+liker).val();
				    //var likeeEmail=$("#emailI-"+likee).val();
					var body=$('#content').val();
					var recipient=$('#recipient').val();
				    $.ajax({
				        url: "EmailSendingServlet",
				        type:"POST",
				        data:{	content:body,
				        		likeeID:likee,
				        	   	likerID:liker},
				        success: function(response) {
				        	$("#emailSuccess").modal("toggle");
				        }
				    	});
				});
				
				$('.searchForm').click(function(event) { 
					debugger;
				    event.preventDefault();
				    var userID=$("#userVal").val();
				    var gender=$("#gender").val();
				    var intGender=$("#genderIn").val();
				    var search=$("#searchInput").val();
				    $.ajax({
				        url: "search",
				        type:"POST",
				        data:{search:search},
				        success: function(response) {
				        	alert("success");
				        }
				    	});
				});
			});
		</script>

</head>
<body>
	<header>
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<a class="navbar-brand" href="main">Emetad.</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNavDropdown">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link" href="main">Home
							<span class="sr-only">(current)</span>
					</a></li>
					<li class="nav-item"><a class="nav-link" href="profile.jsp">Profile</a>
					</li>
					<li class="nav-item active"><a class="nav-link" href="#">Search Results</a>
					</li>
				</ul>
			</div>
			<form class="form-inline" action="search" method="post" id="searchForm">
				<input class="form-control mr-sm-2" type="search"
					placeholder="Search" id="searchInput" name="searchInput" aria-label="Search">
				<button id="searchBtn" class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
			</form>
			<form class="form-inline" id="logOut" action="logout" method="POST">
				<button id="logOutBtn" class="btn btn-outline-danger ml-4" type="submit">Logout</button>
			</form>
		</nav>
	</header>

	<%@ include file="maincontainer.jsp" %>
	<footer>
		<%@ include file="footer.html" %>
	</footer>
</body>
</html>