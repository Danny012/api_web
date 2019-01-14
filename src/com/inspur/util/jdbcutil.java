package com.inspur.util;

import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class jdbcutil {
	private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();
	  public Connection getconnection() throws Exception {
			String url;
			String driver;
			String username;
			String pass;
			Properties properties=new Properties();
		   InputStream is=jdbcutil.class.getResourceAsStream("jdbc.properties");
			properties.load(is);
			driver=properties.getProperty("driverClassName");
			url=properties.getProperty("url");
			username=properties.getProperty("username");
			pass=properties.getProperty("password");			
			Class.forName(driver); 
			Connection connect = (Connection) DriverManager.getConnection( url,username,pass);
			return connect;
		}
		public static void closeConnection() {
			try {
				Connection conn = threadLocal.get();
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				threadLocal.set(null);
			}
		}
	  
	  public static void main(String[] args) throws  Exception {
		    jdbcutil ju=new jdbcutil();
		    Statement stmt = (Statement)ju.getconnection().createStatement();
		      ResultSet rs = stmt.executeQuery("select * from hbase_api");		                                                          
		while (rs.next()) {
		        System.out.println(rs.getString("id"));
		  }
	}
}
