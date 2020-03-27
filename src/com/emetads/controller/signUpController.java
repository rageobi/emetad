package com.emetads.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emetads.repo.DBRepository;

/**
 * Servlet implementation class signUpController
 */
@WebServlet("/signUpController")
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
		String gender= "M";//request.getParameter("genderInput");
		String password= request.getParameter("pwdInput");
		String state= request.getParameter("stateInput");
		String city= request.getParameter("cityInput");
		String mobileNumber= request.getParameter("phoneInput");
		String dob= request.getParameter("dobInput");
		String iGender= "F";//request.getParameter("iGenderInput");
		
		Date dobD=Date.valueOf(dob);
		//String fName,String lName, String Email, String gender, String password, String state, String city, String mobileNumber, Date dob, String iGender
		int result = userRepository.signupUser(firstName,lastName,email,gender,password,state,city,mobileNumber,dobD,iGender);
		if (result==0) {
			PrintWriter out = response.getWriter();
			out.println("<html><body><table>...your code...</table></body></html>");
		}
		else {
			String path= "/login.html";

			RequestDispatcher dispatcher =getServletContext().getRequestDispatcher(path);

			dispatcher.forward(request,response);
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
