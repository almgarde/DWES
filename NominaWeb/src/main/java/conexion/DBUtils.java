package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {

	public static Connection getConnection() throws SQLException {
		Connection conn = null;
		String url = "jdbc:oracle:thin:@192.168.1.135:1521/XE";
		String user = "dwes";
		String pass = "dwes";

		conn = DriverManager.getConnection(url, user, pass);
		return conn;

	}

	public static void close(Connection conn) throws SQLException {
		if (conn != null)
			conn.close();
	}

	public static void close(Statement st) throws SQLException {
		if (st != null)
			st.close();
	}

	public static void close(ResultSet rs) throws SQLException {
		if (rs != null)
			rs.close();
	}

	
}
