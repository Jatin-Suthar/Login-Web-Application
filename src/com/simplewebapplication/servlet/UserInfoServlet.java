package com.simplewebapplication.servlet;

import jakarta.annotation.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import jakarta.servlet.jsp.*;

import java.io.IOException;

import com.simplewebapplication.beans.UserAccounts;
import com.simplewebapplicaton.utils.MyUtils;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class UserInfoServlet
 */
@WebServlet(urlPatterns = { "/userInfo" })
public class UserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();

		UserAccounts loginedUser = MyUtils.getLoginedUser(session);

		if (loginedUser == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		
		request.setAttribute("user", loginedUser);

		RequestDispatcher dispatcher //
				= this.getServletContext().getRequestDispatcher("/WEB-INF/views/userInfoView.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
