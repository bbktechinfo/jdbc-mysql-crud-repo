package bbktech.info;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class JDBCMySQLCreateRecord {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		//Step-1: Load the MySQL Driver.
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		//Step-2: Get the MySql Database Connection.
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/scient_db", "root", "root");

		//Step-3: Create the PreparedStatement by passing SQL Query as INPUT
		PreparedStatement pstmt = connection.prepareStatement(
				"INSERT INTO student_tble (full_name, roll_number, branch, mobile) VALUES (?, ?, ?, ?)");

		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter your FullName : ");
		String fullName = scanner.nextLine();
		System.out.println("Enter your Roll Number : ");
		String rollNumber = scanner.nextLine();
		System.out.println("Which branch are you pursuing? : ");
		String branch = scanner.nextLine();
		System.out.println("Enter your Contact Number : ");
		String mobile = scanner.nextLine();

		pstmt.setString(1, fullName);
		pstmt.setString(2, rollNumber);
		pstmt.setString(3, branch);
		pstmt.setString(4, mobile);

		// Step-4:Execute the query
		int updateRowCount = pstmt.executeUpdate();
		if (updateRowCount > 0) {
			System.out.println("Record Inserted successfully!!\t" + updateRowCount);
		}

		//Step-5: Close the Connection/Resources
		pstmt.close();
		connection.close();
		scanner.close();
	}
}