<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page
	import="com.emetads.controller.User,java.util.ArrayList,java.util.List, java.io.ByteArrayOutputStream,java.util.Base64"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Emetad</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<style type="text/css">
body {
	background: rgb(237, 131, 131);
	background: linear-gradient(90deg, rgba(237, 131, 131, 1) 0%,
		rgba(178, 199, 255, 1) 95%);
}

.card:hover {
	box-shadow: -1px 9px 40px -12px rgba(0, 0, 0, 0.75);
}
</style>
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
					<li class="nav-item active"><a class="nav-link" href="main">Home
							<span class="sr-only">(current)</span>
					</a></li>
					<li class="nav-item"><a class="nav-link" href="profile.jsp">Profile</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="#">About Us</a>
					</li>
				</ul>
			</div>
			<form class="form-inline" action="search" method="post" id="searchForm">
				<input class="form-control mr-sm-2" type="search"
					placeholder="Search" id="searchInput" name="searchInput" aria-label="Search">
				<button id="searchBtn" class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
			</form>
		</nav>
	</header>
	<div class="container">
		<div class="modal" id="emailModal" tabindex="-1" role="dialog"
			aria-labelledby="emailModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="emailModalLabel">Yay! That's a
							match. Send an email to them.</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<form action="EmailSendingServlet" method="post">
							<div class="form-group">
								<label for="content" class="col-form-label">Message:</label>
								<textarea class="form-control" id="content"></textarea>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Close</button>
						<button type="submit" class="btn btn-primary btn-smessage">Send
							email</button>
					</div>
				</div>
			</div>
		</div>

		<div id="emailSuccess" class="modal bd-example-modal-sm" tabindex="-1"
			role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-body">Email sent successfully.</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Awesome, okay</button>
					</div>
				</div>
			</div>
		</div>
		<%
			if (session.getAttribute("user") == null) {
			response.sendRedirect("login.html");
			return;
		}
		%>
		<div class="row">
			<%
				User user = (User) session.getAttribute("user");
			%>
			<input type="text" style="display: none" id="intUserVal"> <input
				type="text" style="display: none" id="userVal"> <input type="text" style="display: none" value=<%=user.getEmail()%>
				class="form-control" id="emailU">
				<input
				type="text" style="display: none" value=<%=user.getGender()%>
				class="form-control" id="gender">
				<input
				type="text" style="display: none" value=<%=user.getIntrestedIn()%>
				class="form-control" id="genderIn">
			<%
				ArrayList<User> userInts = (ArrayList<User>) session.getAttribute("userInts");
			for (User userInt : userInts) {
				String base64Image = userInt.getBase64Image(userInt.getdisplayPicture());
				System.out.println(base64Image + "\n");
				session.setAttribute("intUserDP", base64Image);
			%>
			<div class="col-sm-6 col-md-6 col-lg-3 mt-2 mb-2">
				<div class="card-group">
					<div class="card" style="width: 18rem;">
						<img src="data:image/jpg;base64,${intUserDP}" width=200 height=200
							class="card-img-top" alt="">
						<div class="card-body">
							<h5 class="card-title"><%=userInt.getFirstname()%>&nbsp;<%=userInt.getLastname()%></h5>
						</div>
						<ul class="list-group list-group-flush">
							<li class="list-group-item"><b><i>About:&nbsp;</i></b><%=userInt.getDescription()%></li>
							<li class="list-group-item"><b><i>Date of
										birth:&nbsp;</i></b><%=userInt.getDob()%></li>
							<li class="list-group-item"><b><i>State:&nbsp;</i></b><%=userInt.getState()%></li>
							<li class="list-group-item"><b><i>City:&nbsp;</i></b><%=userInt.getCity()%></li>
						</ul>
						<input type="text" style="display: none"
							value=<%=userInt.getEmail()%> class="form-control"
							id="emailI-<%=userInt.getUserID()%>">
						<div class="card-footer" id="card-footer-<%=userInt.getUserID()%>"
							align="center">
							<%
								String queryString = "likeController?liker=" + user.getUserID() + "&likee=" + userInt.getUserID();
							%>
							<a href=<%=queryString%> class="card-link"><i
								class="fa fa-thumbs-up"></i>Like user</a>
						</div>
					</div>
				</div>
			</div>
			<%
				}
			%>
		</div>
	</div>

	<footer>

		
	</footer>
</body>
</html>