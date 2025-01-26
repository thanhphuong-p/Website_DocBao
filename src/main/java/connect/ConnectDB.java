package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
	public static Connection conn ;
	public static Connection getConnection() {
		String url = "jdbc:sqlserver://localhost:1433;databaseName=ASM_JAVA3;encrypt=false";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			return DriverManager.getConnection(url,"sa","123456");
		} catch (SQLException|ClassNotFoundException e) {
			return null;
		}
	}
	
	static {
		conn = getConnection();
	}
	
	public static void main(String[] args) {
		if (conn!=null) {
			System.out.println("Connected !");
		}
	}
}
