package bbktech.info.util;

public class MySQLDBQueriesUtil {
	public static final String INSERT_STDNT_QRY = "INSERT INTO student_tble (full_name, roll_number, branch, mobile) VALUES (?, ?, ?, ?)";
	public static final String SELECT_ALL_STDNT_QRY = "SELECT * FROM student_tble";
	public static final String UPDATE_STDNT_PHONENUM_BY_ROLLNUM_QRY = "UPDATE student_tble SET mobile = ? WHERE roll_number = ?";
	public static final String DELETE_STDNT_BY_ROLLNUM_QRY = "DELETE FROM student_tble WHERE roll_number = ?";
	
	public static final String SELECT_STDNT_BY_ROLLNUM_QRY = "SELECT * FROM student_tble WHERE roll_number = ?";
}
