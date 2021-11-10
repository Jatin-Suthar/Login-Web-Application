package com.simplewebapplication.conn;

import java.sql.*;

public class MySQLConnectionUtils {
	public static Connection getMySQLConnection() throws ClassNotFoundException, SQLException {
		String hostname = "localhost";
		String dbName = "simplewebapp";
		String username = "root";
		String password = "Jatin@300";
		return getMySQLConnection(hostname, dbName, username, password);
	}
	public static Connection getMySQLConnection(String host, String db, String name, String pass) throws ClassNotFoundException, SQLException {
		//register class
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://"+host+":3306/"+db;
		Connection conn = DriverManager.getConnection(url, name, pass);
		return conn;
	}
}
