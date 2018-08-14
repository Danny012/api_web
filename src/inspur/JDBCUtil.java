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
			System.out.println("������Դ�ļ�����");
			e.printStackTrace();
		}
	}
	public static Connection getConnection() throws Exception {
		// �ȴӱ����߳�����ȡ���Ӷ���
		Connection conn = threadLocal.get();
		// �ж� �����Ƿ�Ϊ�գ��������Ƿ�رա�
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
				conn.close();// �ͷ�����
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			threadLocal.set(null);// ���
		}
	}

}
