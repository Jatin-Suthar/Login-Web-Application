package com.simplewebapplication.servlet;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.sql.*;
import java.util.*;

import com.simplewebapplication.beans.Products;
import com.simplewebapplicaton.utils.DBUtils;
import com.simplewebapplicaton.utils.MyUtils;

/**
 * Servlet implementation class EditProductServlet
 */
@WebServlet(urlPatterns = { "/editProduct" })
public class EditProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProductServlet() {
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

		Products product = null;

		String errorString = null;

		try {
			product = DBUtils.findProduct(conn, code);
		} catch (Exception e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}
		if (errorString != null && product == null) {
			response.sendRedirect(request.getServletPath() + "/productList");
			return;
		}

		request.setAttribute("errorString", errorString);
		request.setAttribute("product", product);

		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/editProductView.jsp");
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
		} catch (Exception e) {
		}
		Products product = new Products(code, name, price);

		String errorString = null;

		try {
			DBUtils.updateProduct(conn, product);
		} catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}
		request.setAttribute("errorString", errorString);
		request.setAttribute("product", product);

		if (errorString != null) {
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/editProductView.jsp");
			dispatcher.forward(request, response);
		}
		else {
			response.sendRedirect(request.getContextPath() + "/productList");
		}
	}

}
