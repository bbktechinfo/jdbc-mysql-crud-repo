package bbktech.info;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import bbktech.info.util.MySQLDBQueriesUtil;
import bbktech.info.util.MySqlDBConnection;

public class JavaMySqlCrudApplication {

	public static void main(String[] args) {
		Connection mySqlConnection = null;
		PreparedStatement pstmt;
		try {
			// Step-1:Get the MySQL Database Connection
			mySqlConnection = MySqlDBConnection.getMySqlConnection();

			// Step-2:Create PreparedStatement passing SQL Query
			pstmt = mySqlConnection.prepareStatement(MySQLDBQueriesUtil.INSERT_STDNT_QRY);

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

			// Step-3:Execute the query
			int updateRowCount = pstmt.executeUpdate();
			if (updateRowCount > 0) {
				System.out.println("Record Inserted successfully!!\t" + updateRowCount);
			}

			pstmt = mySqlConnection.prepareStatement(MySQLDBQueriesUtil.SELECT_ALL_STDNT_QRY);

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

			System.out.println("Please enter your Roll Number to update Your Mobile Number : ");
			String rollNumber2 = scanner.nextLine();

			pstmt = mySqlConnection.prepareStatement(MySQLDBQueriesUtil.SELECT_STDNT_BY_ROLLNUM_QRY);
			pstmt.setString(1, rollNumber2);

			ResultSet result2 = pstmt.executeQuery();
			String roll_number = null;
			while (result2.next()) {
				roll_number = result2.getString("roll_number");
			}
			if (null != roll_number) {
				System.out.println("Enter Updated Phone number: ");
				String updatedPhoneNumber = scanner.nextLine();
				if (null != updatedPhoneNumber && !updatedPhoneNumber.isEmpty()) {
					pstmt = mySqlConnection.prepareStatement(MySQLDBQueriesUtil.UPDATE_STDNT_PHONENUM_BY_ROLLNUM_QRY);
					pstmt.setString(1, updatedPhoneNumber);
					pstmt.setString(2, roll_number);
					pstmt.executeUpdate();

					System.err.println("Updated.");
				}
			} else {
				System.err.println("Incorrect RollNumber.");
			}
			System.out.println(
					"Do you want to delete your details(Yes/No)? \tNote : Once deleeted, you can't Retrive. ");
			String deleteConfirm = scanner.nextLine();
			if (null != deleteConfirm && deleteConfirm.equalsIgnoreCase("Yes")) {
				System.out.println("Please Enter your Roll number");
				roll_number = scanner.nextLine();

				pstmt = mySqlConnection.prepareStatement(MySQLDBQueriesUtil.DELETE_STDNT_BY_ROLLNUM_QRY);
				pstmt.setString(1, roll_number);
				pstmt.executeUpdate();
				System.out.println("Your details are deleted successfully.");
			} else {
				System.out.println("Record not deleted!!!");
			}

			scanner.close();
		} catch (Exception e) {
			System.err.println("Error while performing the CRUD operations !! " + e);
			e.printStackTrace();
		} finally {
			if (null != mySqlConnection) {
				try {
					mySqlConnection.close();
				} catch (SQLException e) {
					System.err.println("Error while closing the connection!! " + e);
					e.printStackTrace();
				}
			}
		}
	}
}