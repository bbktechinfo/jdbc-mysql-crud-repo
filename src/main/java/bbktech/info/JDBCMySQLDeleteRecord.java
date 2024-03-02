package bbktech.info;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCMySQLDeleteRecord {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/scient_db", "root", "root");

		PreparedStatement pstmt = connection.prepareStatement("DELETE FROM student_tble WHERE roll_number = ?");
		pstmt.setString(1, "R22500");
		pstmt.executeUpdate();
		System.out.println("Your details are deleted successfully.");

		pstmt.close();
		connection.close();
	}
}