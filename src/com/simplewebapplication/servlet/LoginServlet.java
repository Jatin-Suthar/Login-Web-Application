package com.simplewebapplication.servlet;

import java.io.IOException;

import com.simplewebapplication.beans.UserAccounts;
import java.sql.*;
import com.simplewebapplicaton.utils.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.RequestDispatcher;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String rememberMeStr = request.getParameter("rememberMe");
		boolean remember = "Y".equals(rememberMeStr);
		
		UserAccounts user = null;
		boolean hasError = false;
		String errorString = null;
		
		if(userName == null || password == null || userName.length()==0 || password.length()==0) {
			hasError = true;
			errorString = "Required username and password";
		}else {
			Connection conn = MyUtils.getStoredConnection(request);
			try {
				user = DBUtils.findUser(conn, userName, password);
				if(user == null) {
					hasError = true;
					errorString = "User Name or password invalid";
				}
			}catch(Exception e) {
				e.printStackTrace();
				hasError = true;
				errorString = e.getMessage();
			}
		}
		if(hasError) {
			user = new UserAccounts();
			user.setUsername(userName);
			user.setPassword(password);
			
			request.setAttribute("errorString", errorString);
			request.setAttribute("user", user);
			
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
			dispatcher.forward(request, response);
		}else {
			HttpSession session = request.getSession();
			MyUtils.storeLoginedUser(session, user);
			
			if(remember) {
				MyUtils.storeUserCookie(response, user);
			}else {
				MyUtils.deleteUserCookie(response);
			}
			response.sendRedirect(request.getContextPath()+"/userInfo");
		}
	}

}
