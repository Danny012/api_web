package inspur;

import java.io.InputStream;
import java.util.Properties;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Driver;

public class util {
	private static String driver;
	private static String jdbcurl;
	private static String username;
	private static String password;
    public Connection getconnection() throws Exception{
    	InputStream is=util.class.getResourceAsStream("jdbc.properties");
    	Properties properties=new Properties();
    	properties.load(is);
    	driver=properties.getProperty("driverClassName");
    	jdbcurl=properties.getProperty("url");
    	username=properties.getProperty("username");
    	password=properties.getProperty("password");
    	
    	Driver dr=(Driver)Class.forName(driver).newInstance();
    	Properties p=new Properties();
    	p.put("username", username);
    	p.put("password", password);
    	Connection con=(Connection) dr.connect(jdbcurl, p);
    	return con;
    }
    public static void main(String[] args) throws Exception {
       //util u=new util();
    	InputStream is=util.class.getResourceAsStream("jdbc.properties");
    	Properties properties=new Properties();
    	properties.load(is);
    	driver=properties.getProperty("driverClassName");
    	jdbcurl=properties.getProperty("url");
    	username=properties.getProperty("username");
    	password=properties.getProperty("password");
		System.out.println(driver+"\n"+jdbcurl+"\n"+username+"\n"+password);
		//System.out.println(u.getconnection());
	}
}
