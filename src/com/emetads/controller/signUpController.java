package com.emetads.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.emetads.repo.DBRepository;

/**
 * Servlet implementation class signUpController
 */
@WebServlet("/signUpController")
@MultipartConfig(maxFileSize = 16177215)  
public class signUpController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBRepository userRepository;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public signUpController() {
        super();
        userRepository = new DBRepository();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String firstName= request.getParameter("fnameInput");
		String lastName= request.getParameter("lnameInput");
		String email= request.getParameter("emailInput");
		String gender= request.getParameter("genderInput");
		String password= request.getParameter("pwdInput");
		String state= request.getParameter("stateInput");
		String city= request.getParameter("cityInput");
		String mobileNumber= request.getParameter("phoneInput");
		String dob= request.getParameter("dobInput");
		String iGender= request.getParameter("iGenderInput");
		
		InputStream inputStream = null;
        Part filePart = request.getPart("dpInput");
        inputStream = filePart.getInputStream();

		Date dobD=Date.valueOf(dob);
		if(userRepository.checkUserPresence(email)) {
			//String additionalMessage = null;
			//request.getSession().setAttribute(additionalMessage, "Not successfully signedup. Please try again");
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
			PrintWriter out= response.getWriter();
			out.println("<div class=\"text-center\"><font color=red> Email Id exists. Please login</font></div>");
			rd.include(request, response);}
		else {
		int result = userRepository.signupUser(firstName,lastName,email,gender,password,state,city,mobileNumber,dobD,iGender,inputStream);
		if (result==0 && !(userRepository.checkUserPresence(email))) {
			//String additionalMessage = null;
			//request.getSession().setAttribute(additionalMessage, "Not successfully signedup. Please try again");
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/signup.html");
			PrintWriter out= response.getWriter();
			out.println("<div class=\"text-center\"><font color=red> Not successfully signedup. Please try again</font></div>");
			rd.include(request, response);
		}
		else {
			response.sendRedirect(request.getContextPath() + "/login.html");
		}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
