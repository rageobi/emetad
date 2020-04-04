<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page
	import="com.emetads.controller.User,java.util.ArrayList,java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Profile</title>
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
<script src="js/profile.js" type="text/javascript"></script>
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
</head>
<body>
	<header>
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<a class="navbar-brand" href="#">Emetad.</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNavDropdown">
				<ul class="navbar-nav">
					<li class="nav-item active"><a class="nav-link"
						href="main">Home </a></li>
					<li class="nav-item active"><a class="nav-link"
						href="#	">Profile <span class="sr-only">(current)</span></a>
					</li>
					<li class="nav-item"><a class="nav-link" href="#">About Us</a>
					</li>
				</ul>
			</div>
			<form class="form-inline">
				<input class="form-control mr-sm-2" type="search"
					placeholder="Search" aria-label="Search">
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
			</form>
		</nav>
	</header>
	<div class="container">
		<%
			if (session.getAttribute("user") == null) {
			response.sendRedirect("login.html");
			return;
		}
		%>
		<br>
		<link
			href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css"
			rel="stylesheet">
		<div class="container">
			<div class="card">
				<div class="card-body">
					<div class="emetadProfile">
						<div class="row">
							<div class="col-12 col-sm-auto mb-3">
								<div class="mx-auto" style="width: 150px;">
									<img src="data:image/jpg;base64,${DisplayPic}" width=150
										height=150 class="card-img-top" alt="">
									<div id="image-holder"></div>
								</div>
							</div>
							<div class="col d-flex mb-3">
								<div class="text-center text-sm-left mb-2 mb-sm-0">
									<h4 class="pt-sm-2 pb-1 mb-0 text-nowrap">${user.firstname}&nbsp;${user.lastname}</h4>
									<div class="mt-2">
										<button class="btn btn-primary" id="btnChangePhoto"
											value="btnChangePhoto" type="button">
											<i class="fa fa-fw fa-camera"></i> <span>Change Photo</span>
										</button>
									</div>
								</div>
							</div>
						</div>
						<ul class="nav nav-tabs">
							<li class="nav-item"><a href="" class="active nav-link">Settings</a></li>
						</ul>
						<div class="tab-content pt-3">
							<div class="tab-pane active">
								<form name="profileForm" id="profileForm" method="post"
									action="profileController" onSubmit="return validate();"
									enctype='multipart/form-data'>
									<input type="file" class="form-control-file form-control-sm"
										name="dpInput" style="display:none" id="dpInput" onChange="validateFileType()"
										accept="image/x-png,image/jpeg" />
									<div class="form-row">
										<div class="form-group col-md-6">
											<label for="fnameInput">Firstname</label> <input type="text"
												class="form-control" id="fnameInput" name="fnameInput"
												value=${user.firstname}>
										</div>
										<div class="form-group col-md-6">
											<label for="lnameInput">Lastname</label> <input type="text"
												class="form-control" id="lnameInput" name="lnameInput"
												value=${user.lastname}>
										</div>
									</div>
									<div class="form-row">

										<div class="form-group col-md-6">
											<label for="emailInput">Email</label> <input type="text"
												class="form-control" id="emailInput" name="emailInput"
												value=${user.email}>
										</div>
										<div class="form-group col-md-6">
											<label for="genderInput">Gender</label> <select
												class="form-control" id="genderInput" name="genderInput">
												<option value="M">Male</option>
												<option value="F">Female</option>
												<option value="O">Other</option>
											</select><input type="hidden" id="fgenderInput" name="fgenderInput"
												value="${user.gender}">
												<input type="hidden" id="userID" name="userID"
												value="${user.userID}">
										</div>
									</div>
									<div class="form-row">
										<div class="form-group col-md-6">
											<label for="stateInput">State</label> <input type="text"
												class="form-control" id="stateInput" name="stateInput"
												value=${user.state}>
										</div>
										<div class="form-group col-md-6">
											<label for="cityInput">City</label> <input type="text"
												class="form-control" id="cityInput" name="cityInput"
												value=${user.city}>
										</div>
									</div>
									<div class="form-row">
										<div class="form-group col-md-6">
											<label for="phoneInput">Phone</label> <input type="text"
												class="form-control form-control-sm" name="phoneInput"
												value=${user.phone}>
										</div>
										<div class="form-group col-md-6">
											<label for="phoneInput">Date of Birth</label><input
												type="date" class="form-control form-control-sm"
												id="dobInput" name="dobInput" value=${user.dob}>
										</div>
									</div>

									<div class="form-row">
										<div class="form-group col-md-6">
											<label for="description">Description</label>
											<textarea class="form-control" name="description"
												id="description" rows="2">${user.description}</textarea>
										</div>
										<div class="form-group col-md-6	">
											<label for="intSelect">Interested Gender?</label> <select
												class="form-control form-control-sm mb-2" id="intSelect">
												<option value="M">Male</option>
												<option value="F">Female</option>
												<option value="O">Others</option>
											</select> <input type="hidden" id="iGenderInput" name="iGenderInput"
												value="M">
										</div>
									</div>
									<div class="alert alert-danger collapse" id="signupAlert"
										role="alert"></div>
									<div class="text-center">
										<input type="submit" class="btn btn-primary mt-3" value="Submit">
									</div>
								</form>

							</div>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>

	<footer>

		<script>
			$("ul li").on("click", function() {
				$("li").removeClass("active");
				$(this).addClass("active");
			});

			$(btnChangePhoto).on("click", function() {
				$(dpInput).click();
			});
			$(document).ready(function() {
				$("#genderInput").val("${user.gender}");
				$("#intSelect").val("${user.intrestedIn}");
			});
		</script>
	</footer>
</body>
</html>