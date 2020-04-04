package com.emetads.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emetads.repo.DBRepository;

/**
 * Servlet implementation class likeController
 */
@WebServlet("/likeController")
public class likeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBRepository userRepository;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public likeController() {
        super();
        // TODO Auto-generated constructor stub
		userRepository = new DBRepository();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String liker = request.getParameter("liker");
		String likee = request.getParameter("likee");
		int result=0;
		int likeStatus=0;
	
		if(!userRepository.checkLikeExists(liker, likee)){
			result=userRepository.storeLikes(liker, likee);
			if(result==1) {
				likeStatus=1;
				if(userRepository.checkLikeExists(likee,liker)){
					likeStatus=2;
				}
			}
		}
		response.getWriter().write(Integer.toString(likeStatus));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
