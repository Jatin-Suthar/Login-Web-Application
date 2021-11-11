package com.simplewebapplication.servlet;

import java.io.IOException;

import com.simplewebapplication.beans.Products;
import com.simplewebapplicaton.utils.DBUtils;
import com.simplewebapplicaton.utils.MyUtils;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import java.sql.*;

/**
 * Servlet implementation class CreateProductServlet
 */
@WebServlet(urlPatterns = { "/createProduct" })
public class CreateProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/createProductView.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = MyUtils.getStoredConnection(request);
		String code = (String) request.getParameter("code");
		String name = (String) request.getParameter("name");
		String priceStr = (String) request.getParameter("price");
		
		float price = 0;
		try {
			price = Float.parseFloat(priceStr);
		}catch(Exception e) {}
		
		Products product = new Products(code, name, price);
		String errorString = null;
		
		String regax = "\\w+";
		
		if(code==null || !code.matches(regax)) {
			errorString = "Product Code Invalid!";
		}
		
		if(errorString == null) {
			try {
				DBUtils.insertProduct(conn, product);
			}catch(Exception e) {
				e.printStackTrace();
				errorString = e.getMessage();
			}
		}
		
		request.setAttribute("errorString", errorString);
		request.setAttribute("product", product);
		
		if(errorString == null) {
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/createProductView.jsp");
			dispatcher.forward(request, response);
		}else {
			response.sendRedirect(request.getContextPath() + "/productList");
		}
	}

}
