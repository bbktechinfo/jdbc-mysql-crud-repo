package bbktech.info;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCMySQLRetriveRecord {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/scient_db", "root", "root");

		PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM student_tble");
		ResultSet result = pstmt.executeQuery();
		System.out.println("Student Detarils Retrived\t - ");
		while (result.next()) {
			String full_name = result.getString("full_name");
			String roll_number = result.getString("roll_number");
			String branchName = result.getString("branch");
			String mobileNumber = result.getString("mobile");
			System.out.println("FULL NAME : " + full_name);
			System.out.println("ROLLNUMBER : " + roll_number);
			System.out.println("BRANCH : " + branchName);
			System.out.println("MOBILE NUMBER : " + mobileNumber);
		}
		pstmt.close();
		connection.close();
	}
}