package com.emetads.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.emetads.repo.DBRepository;

/**
 * Servlet implementation class profileController
 */
@WebServlet({ "/profileController", "/profile" })

@MultipartConfig(maxFileSize = 16177215)  
public class profileController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    private DBRepository userRepository;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public profileController() {
        super();
        // TODO Auto-generated constructor stub
   	 userRepository = new DBRepository();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user = null;
		String firstName= request.getParameter("fnameInput");
		String lastName= request.getParameter("lnameInput");
		String email= request.getParameter("emailInput");
		String gender= request.getParameter("fgenderInput");
		String password= request.getParameter("pwdInput");
		String state= request.getParameter("stateInput");
		String city= request.getParameter("cityInput");
		String mobileNumber= request.getParameter("phoneInput");
		String dob= request.getParameter("dobInput");
		String iGender= request.getParameter("iGenderInput");
		String userID= request.getParameter("userID");
		String description= request.getParameter("description");
		description=description.replaceAll("\\W"," ");
		description=description.replaceAll("( )+", " ");
		InputStream inputStream = null;
		InputStream decInputStream = null;
        Part filePart = request.getPart("dpInput");
        if (filePart != null) {
        	inputStream = filePart.getInputStream();
        }
        else {
        user= userRepository.fetchUserDetailswithID(userID);
        inputStream= user.getdisplayPicture();
        }
		Date dobD=Date.valueOf(dob);
		int result = userRepository.updateUser(firstName,lastName,email,gender,state,city,mobileNumber,dobD,iGender,inputStream,description,userID,password);
		if (result==0) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/profile.jsp");
			PrintWriter out= response.getWriter();
			out.println("<div class=\"alert alert-danger\" id=\"sd\"role=\"alert\" align=\"center\">Not successfully updated. Please try again</div>");
			rd.include(request, response);
		}
		else {
			user = userRepository.fetchUserDetailswithID(userID);
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			request.setAttribute("email", email);
			request.setAttribute("page", "profile");
			RequestDispatcher reqDispatcher = request.getRequestDispatcher("mainController");
			reqDispatcher.forward(request, response);
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
