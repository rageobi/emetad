package com.emetads.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emetads.repo.DBRepository;

/**
 * Servlet implementation class selectController
 */
@WebServlet("/selectController")
public class selectController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBRepository userRepository;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public selectController() {
        super();userRepository = new DBRepository();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userID= request.getParameter("testid");
		ResultSet result=userID!=null?userRepository.findByUserName(userID):null;
		String htmlRespone = "<html>";
		 if(userID!=null) {
		        try {
					while (result.next()) {
						try {
							htmlRespone += "<h2>" + result.getString("FIRST_NAME") + "</h2><br/>";
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}}
		        else {htmlRespone += "<h2>NO ID BOSS</h2><br/>";}
		        htmlRespone += "</html>";
        PrintWriter writer = response.getWriter();
        writer.println(htmlRespone);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
