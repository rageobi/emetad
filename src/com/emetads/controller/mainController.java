package com.emetads.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.emetads.repo.DBRepository;

/**
 * Servlet implementation class mainController
 */
@WebServlet({ "/mainController", "/main" })
public class mainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBRepository userRepository;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public mainController() {
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
		HttpSession session = request.getSession();
		User user = null;
		//ResultSet userDetailsResults = userRepository.fetchUserDetails(email);
		ArrayList<User> userIntList = new ArrayList<User>();
		user = (User)session.getAttribute("user");
		userIntList = (ArrayList<User>) userRepository.getUsersInList(user.getGender(), user.getIntrestedIn(),
				user.getUserID());
		session.setAttribute("userInts", userIntList);
		session.setMaxInactiveInterval(30 * 60);
		String encodedURL = null;
		if (session.getAttribute("page") == "profile") {
			request.setAttribute("page", "");
			encodedURL = response.encodeRedirectURL("profile.jsp");
		} else {
			encodedURL = response.encodeRedirectURL("main.jsp");
		}
		response.sendRedirect(encodedURL);
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
