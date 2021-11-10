package com.simplewebapplication.conn;

import java.sql.*;

public class ConnectionsUtil {
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		return MySQLConnectionUtils.getMySQLConnection();
	}
	
	public static void closeQuietly(Connection conn) {
		try {
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void rollbackQuietly(Connection conn) {
		try {
			conn.rollback();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
