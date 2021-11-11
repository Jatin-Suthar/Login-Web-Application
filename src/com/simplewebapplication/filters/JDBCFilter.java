package com.simplewebapplication.filters;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import java.util.*;
import com.simplewebapplication.conn.*;
import com.simplewebapplicaton.utils.*;
import java.sql.*;

/**
 * Servlet Filter implementation class JDBCFilter
 */
@WebFilter(filterName = "jdbcFilter", urlPatterns = { "/*" })
public class JDBCFilter implements Filter {

    /**
     * Default constructor. 
     */
    public JDBCFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	private boolean needJDBC(HttpServletRequest req) {
		System.out.println("JDBC Filters");
		
		String servlet_path = req.getServletPath();
		String path_info = req.getPathInfo();
		String url_path = servlet_path;
		
		if(path_info != null) {
			url_path = servlet_path + "/*";
		}
		
		Map<String, ? extends ServletRegistration> servletRegistration = req.getServletContext().getServletRegistrations();
		
		//Collections of all servlet in your project
		Collection<? extends ServletRegistration> list = servletRegistration.values();
		for(ServletRegistration res : list) {
			Collection<String> mapAll = res.getMappings();
			if(mapAll.contains(url_path)) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) request;
		
		if(this.needJDBC(req)) {
			System.out.println("Open Connection for:" + req.getServletPath());
			Connection conn = null;
			try {
				conn = ConnectionsUtil.getConnection();
				conn.setAutoCommit(false);
				
				MyUtils.storeConnection(request, conn);

				chain.doFilter(request, response);

				conn.commit();
			}catch(Exception e) {
				e.printStackTrace();
				ConnectionsUtil.rollbackQuietly(conn);
//				throw new ServletException();
			}finally {
				ConnectionsUtil.closeQuietly(conn);
			}
		}else {
			chain.doFilter(req, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
