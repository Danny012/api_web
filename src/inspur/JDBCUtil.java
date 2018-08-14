package inspur;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSourceFactory;

import com.mysql.jdbc.Connection;

public class JDBCUtil {
	private static DataSource ds;
	private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();
	static {
		Properties properties = new Properties();
		InputStream is = JDBCUtil.class.getResourceAsStream("jdbc.properties");
		try {
			properties.load(is);
			ds = BasicDataSourceFactory.createDataSource(properties);
		} catch (Exception e) {
			System.out.println("加载资源文件错误！");
			e.printStackTrace();
		}
	}
	public static Connection getConnection() throws Exception {
		// 先从本地线程类中取连接对象
		Connection conn = threadLocal.get();
		// 判断 连接是否为空，或连接是否关闭。
		if (conn == null || conn.isClosed()) {
			conn = (Connection) ds.getConnection();
			threadLocal.set(conn);
		}
		System.out.println("sssssssss");
		return conn;
	}
	public static void closeConnection() {
		try {
			Connection conn = threadLocal.get();
			if (conn != null && !conn.isClosed()) {
				conn.close();// 释放连接
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			threadLocal.set(null);// 清空
		}
	}

}
