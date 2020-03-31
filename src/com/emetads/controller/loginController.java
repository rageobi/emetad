package com.emetads.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email= request.getParameter("emailInput");
		String password= request.getParameter("pwdInput");
		
		if(userRepository.validateUserCredentials(email, password)) {
			HttpSession session = request.getSession();
			session.setAttribute("user", "Pankaj");
			//setting session to expiry in 30 mins
			session.setMaxInactiveInterval(30*60);
			Cookie userName = new Cookie("email", email);
			userName.setMaxAge(30*60);
			response.addCookie(userName);
			//response.sendRedirect("signup.jsp");
			//Get the encoded URL string
			String encodedURL = response.encodeRedirectURL("signup.html");
			response.sendRedirect(encodedURL);
		}else if(userRepository.checkUserPresence(email)){
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
			PrintWriter out= response.getWriter();
			out.println("<div class=\"text-center\"><font color=red>Wrong Password entered</font></div>");
			rd.include(request, response);
		}
		else {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/signup.html");
			PrintWriter out= response.getWriter();
			out.println("<div class=\"text-center\"><font color=red>Looks like you don't have an account! Please go ahead and signup below</font></div>");
			rd.include(request, response);
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
