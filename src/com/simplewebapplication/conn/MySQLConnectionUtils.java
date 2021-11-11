package com.simplewebapplication.conn;

import java.sql.*;

public class MySQLConnectionUtils {
	public static Connection getMySQLConnection() throws SQLException, ClassNotFoundException {
		String hostname = "localhost";
		String dbName = "simplewebapp";
		String username = "root";
		String password = "Jatin@300";
		return getMySQLConnection(hostname, dbName, username, password);
	}
	public static Connection getMySQLConnection(String host, String db, String name, String pass) throws SQLException, ClassNotFoundException {
		//register class
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/simplewebapp";
		Connection conn = DriverManager.getConnection(url, name, pass);
		return conn;
	}
}
