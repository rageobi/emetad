package com.emetads.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.emetads.repo.DBRepository;

/**
 * Servlet implementation class loginController
 */
@WebServlet({ "/loginController", "/login" })
public class loginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBRepository userRepository;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public loginController() {
		super();
		// TODO Auto-generated constructor stub
		userRepository = new DBRepository();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("emailInput");
		String password = request.getParameter("pwdInput");

		if (userRepository.validateUserCredentials(email, password)) {
			HttpSession session = request.getSession(true);
			User user = null;
			ResultSet result = userRepository.fetchUserDetails(email);
			try {
				user = new User(result);
				user.getdisplayPicture();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[4096];
			int bytesRead = -1;

			while ((bytesRead = user.getdisplayPicture().read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}

			byte[] imageBytes = outputStream.toByteArray();
			String base64Image = Base64.getEncoder().encodeToString(imageBytes);

			session.setAttribute("user", user);
			session.setAttribute("DisplayPic", base64Image);

			session.setMaxInactiveInterval(30 * 60);
			String encodedURL = response.encodeRedirectURL("main.jsp");
			response.sendRedirect(encodedURL);
		} else if (userRepository.checkUserPresence(email)) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
			PrintWriter out = response.getWriter();
			out.println("<div class=\"text-center\"><font color=red>Wrong Password entered</font></div>");
			rd.include(request, response);
		} else {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/signup.html");
			PrintWriter out = response.getWriter();
			out.println(
					"<div class=\"text-center\"><font color=red>Looks like you don't have an account! Please go ahead and signup below</font></div>");
			rd.include(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
