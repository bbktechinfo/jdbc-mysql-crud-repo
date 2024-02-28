package bbktech.info;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bbktech.info.util.MySQLDBQueriesUtil;
import bbktech.info.util.MySqlDBConnection;

public class JavaMySqlCrudApplication {

	public static void main(String[] args) {
		Connection mySqlConnection = null;
		try {
			//Step-1:Get the MySQL Database Connection
			mySqlConnection = MySqlDBConnection.getMySqlConnection();

			//Step-2:Create PreparedStatement passing SQL Query
			PreparedStatement pstmt1 = mySqlConnection.prepareStatement(MySQLDBQueriesUtil.INSERT_STDNT_QRY);
			pstmt1.setString(1, "ANAD");
			pstmt1.setString(2, "11D41F0031");
			pstmt1.setString(3, "CSE");

			//Step-3:Execute the query
			int updateRowCount = pstmt1.executeUpdate();
			if (updateRowCount > 0) {
				System.out.println("Record Inserted successfully!!\t" + updateRowCount);
			}

			PreparedStatement pstmt2 = mySqlConnection.prepareStatement(MySQLDBQueriesUtil.SELECT_ALL_STDNT_QRY);

			ResultSet result = pstmt2.executeQuery();
			System.out.println("Student Detarils Retrived\t - ");
			while (result.next()) {
				String full_name = result.getString("full_name");
				String roll_number = result.getString("roll_number");
				String branch = result.getString("branch");
				System.out.println("FULL NAME : " + full_name);
				System.out.println("ROLLNUMBER : " + roll_number);
				System.out.println("BRANCH : " + branch);
			}

			PreparedStatement pstmt3 = mySqlConnection.prepareStatement(MySQLDBQueriesUtil.UPDATE_STDNT_ROLLNUM_QRY);
			pstmt3.setString(1, "11D41F0001");
			pstmt3.setInt(2, 1);
			pstmt3.executeUpdate();
			System.out.println("Record updated.");

			PreparedStatement pstmt4 = mySqlConnection
					.prepareStatement(MySQLDBQueriesUtil.DELETE_STDNT_BY_STDNT_ID_QRY);
			pstmt4.setInt(1, 1);
			pstmt4.executeUpdate();
			System.out.println("Record deleted.");
		} catch (Exception e) {
			System.err.println("Error while performing the CRUD operations !! " + e);
			e.printStackTrace();
		} finally {
			if(null != mySqlConnection) {
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