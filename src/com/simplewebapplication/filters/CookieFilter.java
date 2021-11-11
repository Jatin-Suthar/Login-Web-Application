package com.simplewebapplication.filters;

import java.io.IOException;

import com.simplewebapplication.beans.UserAccounts;
import com.simplewebapplicaton.utils.DBUtils;
import com.simplewebapplicaton.utils.MyUtils;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.sql.*;

/**
 * Servlet Filter implementation class CookieFilter
 */
@WebFilter(filterName = "cookieFilter", urlPatterns = { "/*" })
public class CookieFilter implements Filter {

    /**
     * Default constructor. 
     */
    public CookieFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		UserAccounts userSession = MyUtils.getLoginedUser(session);
		
		if(userSession != null) {
			session.setAttribute("COOKIE_CHECKED", "CHECKED");
			chain.doFilter(request, response);
			return;
		}
		
		Connection conn = MyUtils.getStoredConnection(request);
		
		String checked = (String) session.getAttribute("COOKIE_CHECKED");
		if(checked == null && conn != null) {
			String userName = MyUtils.getUserNameInCookie(req);
			try {
				UserAccounts user = DBUtils.findUser(conn, userName);
				MyUtils.storeLoginedUser(session, user);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			session.setAttribute("COOKIE_CHECKED", "CHECKED");
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
