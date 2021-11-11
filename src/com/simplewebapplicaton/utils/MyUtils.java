package com.simplewebapplicaton.utils;

import com.simplewebapplication.beans.UserAccounts;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
//import jakarta.servlet.annotation.*;
import java.sql.*;

public class MyUtils {
	public static final String ATT_NAME_CONNECTION = "ATTRIBUTE_FOR_CONNECTION";
	private static final String ATT_NAME_USER_NAME = "ATTRIBUTE_FOR_STORE_USER_NAME_IN_COOKIE";
	public static void storeConnection(ServletRequest request, Connection conn) {
		request.setAttribute(ATT_NAME_CONNECTION, conn);
	}
	public static Connection getStoredConnection(ServletRequest request) {
		Connection conn = (Connection) request.getAttribute(ATT_NAME_CONNECTION);
		return conn;
	}
	public static void storeLoginedUser(HttpSession session, UserAccounts LoginedUser) {
		session.setAttribute("loginedUser", LoginedUser);
	}
	public static UserAccounts getLoginedUser(HttpSession session) {
		UserAccounts loginedUser = (UserAccounts) session.getAttribute("loginedUser");
		return loginedUser;
	}
	public static void storeUserCookie(HttpServletResponse response, UserAccounts user) {
		System.out.println("Store user cookie");
		Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, user.getUsername());
		cookieUserName.setMaxAge(24*60*60);
		response.addCookie(cookieUserName);
	}
	public static String getUserNameInCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if(ATT_NAME_USER_NAME.equals(cookie.getName())) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}
	public static void deleteUserCookie(HttpServletResponse response) {
		Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, null);
		cookieUserName.setMaxAge(0);
		response.addCookie(cookieUserName);
	}
}
