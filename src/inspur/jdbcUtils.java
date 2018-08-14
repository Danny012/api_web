package inspur;

import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class jdbcUtils {
  public Connection getconnection() throws Exception {
	String url;
	String driver;
	String username;
	String pass;
	Properties properties=new Properties();
   InputStream is=jdbcUtils.class.getResourceAsStream("jdbc.properties");
  // System.out.println(jdbcUtils.class.getResourceAsStream("jdbc.properties"));
	properties.load(is);
	driver=properties.getProperty("driverClassName");
	url=properties.getProperty("url");
	username=properties.getProperty("username");
	pass=properties.getProperty("password");
	
	Class.forName(driver); 
	Connection connect = (Connection) DriverManager.getConnection( url,username,pass);
	return connect;
}
  public static void main(String[] args) throws Exception {
	  
       jdbcUtils ju=new jdbcUtils();
	    Statement stmt = (Statement)ju.getconnection().createStatement();
	      ResultSet rs = stmt.executeQuery("select * from pub_users");
	                                                              //user 为你表的名称
	while (rs.next()) {
	        System.out.println(rs.getString("USER_ID"));
	  }
}
}
