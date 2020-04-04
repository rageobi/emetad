package com.emetads.controller;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emetads.repo.DBRepository;
import com.emetads.util.EmailUtility;
 
/**
 * A servlet that takes message details from user and send it as a new e-mail
 * through an SMTP server.
 *
 * @author www.codejava.net
 *
 */
@WebServlet("/EmailSendingServlet")
public class MailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBRepository userRepository;
	private String host;
    private String port;
    private String user;
    private String pass;
 
    public void init() {
        // reads SMTP server setting from web.xml file
        ServletContext context = getServletContext();
        userRepository = new DBRepository();
        host = context.getInitParameter("host");
        port = context.getInitParameter("port");
        user = context.getInitParameter("user");
        pass = context.getInitParameter("pass");
    }
 
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // reads form fields
    	String content = request.getParameter("content");
    	String likeeID=request.getParameter("likeeID");
    	String likerID=request.getParameter("likerID");
    	ResultSet likeeResult= userRepository.getUserNameAndEmail(likeeID);
    	ResultSet likerResult= userRepository.getUserNameAndEmail(likerID);
        String likeeEmail = null;
		try {
			likeeEmail = likeeResult.getString(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String likeeName =null;
        try {
			 likeeName = likeeResult.getString(2)+likeeResult.getString(3);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String likerEmail = null;
		try {
			likerEmail = likerResult.getString(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String likerName = null;
		try {
			likerName = likerResult.getString(2)+likerResult.getString(3);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        String subject = "Yay!"+likeeName+", it's a match with "+likerName;
        content+="<br><br><br><br><br><i>The orginial receiptent of mail is <a href=\"mailto:"+likerEmail+"\">"+likerName+"</a><i> " ;
        String resultMessage = "";
 
        try {
            EmailUtility.sendEmail(host, port, user, pass, likeeEmail, subject,content);
            resultMessage = "The e-mail was sent successfully";
            request.setAttribute("Message", resultMessage);
            getServletContext().getRequestDispatcher("/Result.jsp").forward(
                    request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
            resultMessage = "There were an error: " + ex.getMessage();
        } 
      
    }
}