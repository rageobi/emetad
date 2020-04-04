package com.emetads.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.emetads.repo.DBRepository;

/**
 * Servlet implementation class searchController
 */
@WebServlet({ "/searchController", "/search" })
public class searchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBRepository userRepository;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public searchController() {
        super();
        // TODO Auto-generated constructor stub
        userRepository = new DBRepository();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String searchText= request.getParameter("searchInput");
		User userss= (User)session.getAttribute("user");
		List<User> users = userRepository.getUsersInListAfterSearch(userss.getGender(), userss.getIntrestedIn(),userss.getUserID(),searchText);
		
		session.setAttribute("userInts", users);
		response.sendRedirect(request.getContextPath() + "/search.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
