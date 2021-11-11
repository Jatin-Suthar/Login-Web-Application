package com.simplewebapplicaton.utils;

import java.util.*;
import java.sql.*;
import com.simplewebapplication.beans.*;

public class DBUtils {
	public static UserAccounts findUser(Connection conn, String username, String password) throws SQLException, ClassNotFoundException {
		String query = "select user_name, user_password, user_gender from user_account " + "where user_name = ? and user_password = ?";
		
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, username);
		ps.setString(2, password);
		
		ResultSet res = ps.executeQuery();
		UserAccounts user = null;
		if(res.next()) {
//			String gender = res.getString("user_gender");
			user = new UserAccounts();
			user.setGender(res.getString("user_gender"));
			user.setUsername(username);
			user.setPassword(password);
		}
		closeDatabaseResources(res, ps, conn);
		return user;
	}
	
	public static UserAccounts findUser(Connection conn, String username) throws SQLException, ClassNotFoundException {
		String query = "select user_name, user_password, user_gender from user_account " + "where user_name = ?";
		
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, username);
		
		ResultSet res = ps.executeQuery();
		UserAccounts user = null;
		if(res.next()) {
			user = new UserAccounts();
			user.setGender(res.getString("user_gender"));
			user.setUsername(username);
			user.setPassword(res.getString("user_password"));
		}
		closeDatabaseResources(res, ps, conn);
		return user;
	}
	public static List<Products> queryProduct(Connection conn) throws SQLException, ClassNotFoundException {
		String query = "select product_code, product_name, product_price from product";
		
		PreparedStatement ps = conn.prepareStatement(query);
		
		ResultSet res = ps.executeQuery();
		List<Products> list = new ArrayList<Products>();
		
		while(res.next()) {
			Products p = new Products();
			p.setCode(res.getString("product_code"));
			p.setName(res.getString("product_name"));
			p.setPrice(res.getFloat("product_price"));
			list.add(p);
		}
		closeDatabaseResources(res, ps, conn);
		return list;
	}
	public static Products findProduct(Connection conn, String code) throws SQLException, ClassNotFoundException {
		String query = "select product_code, product_name, product_price from product " + "where product_code = ?";
		
		PreparedStatement ps = conn.prepareStatement(query);
		
		ps.setString(1, code);
		ResultSet res = ps.executeQuery();
		while(res.next()) {
			Products p = new Products(code, res.getString("product_name"), res.getFloat("product_price"));
			return p;
		}
		closeDatabaseResources(res, ps, conn);
		return null;
	}
	public static void updateProduct(Connection conn, Products p) throws SQLException {
		String query = "update product set product_name = ?, product_price = ? where product_code = ?";
		
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, p.getName());
		ps.setFloat(2, p.getPrice());
		ps.setString(3, p.getCode());
		
		ps.executeUpdate();
	}
	public static void insertProduct(Connection conn, Products p) throws SQLException {
		String query = "insert into product(product_code, product_name, product_price) values (?,?,?)";
		
		PreparedStatement ps = conn.prepareStatement(query);
		
		ps.setString(1, p.getCode());
		ps.setString(2, p.getName());
		ps.setFloat(3, p.getPrice());
		
		ps.executeUpdate();
	}
	public static void deleteProduct(Connection conn, String code) throws SQLException {
		String query = "delete from product where product_code = ?";
		
		PreparedStatement ps = conn.prepareStatement(query);
		
		ps.setString(1, code);
		
		ps.executeUpdate();
	} 
	public static void closeDatabaseResources(ResultSet rs, PreparedStatement ps, Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}


}
