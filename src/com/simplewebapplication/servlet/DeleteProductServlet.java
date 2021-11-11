package com.simplewebapplication.servlet;

import java.io.IOException;

import com.simplewebapplicaton.utils.MyUtils;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import java.sql.*;
import com.simplewebapplication.beans.*;
import com.simplewebapplicaton.utils.*;
/**
 * Servlet implementation class DeleteProductServlet
 */
@WebServlet(urlPatterns = { "/deleteProduct" })
public class DeleteProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = MyUtils.getStoredConnection(request);
		
		String code = (String) request.getParameter("code");
		String errorString  = null;
		
		try {
			DBUtils.deleteProduct(conn, code);
		}catch(Exception e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}
		
		if(errorString != null) {
			request.setAttribute("errorString", errorString);
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/deleteProductErrorView.jsp");
			dispatcher.forward(request, response);
		}else {
			response.sendRedirect(request.getContextPath() + "/productList");
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
