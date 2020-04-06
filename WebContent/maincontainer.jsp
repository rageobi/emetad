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