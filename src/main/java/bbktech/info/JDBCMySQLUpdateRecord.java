package bbktech.info;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCMySQLUpdateRecord {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/scient_db", "root", "root");

		PreparedStatement pstmt = connection
				.prepareStatement("UPDATE student_tble SET mobile = ? WHERE roll_number = ?");
		pstmt.setString(1, "9999999999");
		pstmt.setString(2, "R22500");
		pstmt.executeUpdate();
		System.out.println("Your details are updated successfully.");

		pstmt.close();
		connection.close();
	}
}