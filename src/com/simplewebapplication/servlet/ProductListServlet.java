package com.simplewebapplication.servlet;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import com.simplewebapplication.beans.*;
import com.simplewebapplicaton.utils.DBUtils;
import com.simplewebapplicaton.utils.MyUtils;
import java.util.*;

import java.sql.*;

/**
 * Servlet implementation class ProductListServlet
 */
@WebServlet(urlPatterns = { "/productList" })
public class ProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = MyUtils.getStoredConnection(request);
		String errorString = null;
		List<Products> list = null;
		try {
			list = DBUtils.queryProduct(conn);
		}catch(Exception e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}
		
		request.setAttribute("errorString", errorString);
		request.setAttribute("productList", list);
		
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/productListView.jsp");
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
