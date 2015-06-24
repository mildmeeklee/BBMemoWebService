package test.com.jsp;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class LogDAO {

//	ArrayList<LogDTO> list;
	LogDTO logDTO;

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@192.168.0.21:1521:XE";
	String userid = "kosmop";
	String passwd = "1234";

	public LogDAO() {

		try {
//			list = new ArrayList<LogDTO>();
			
			Class.forName(driver);

		} catch (ClassNotFoundException e) {

			e.printStackTrace();

		}// end try~catch
	}// end

	
	public int findId(String user_id){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int idCnt = 0;
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			
			String query = "SELECT count(*) from Users  where id = ?";

			pstmt = con.prepareStatement(query);
			pstmt.setString(1, user_id);

//			rs = stmt.executeQuery();
			rs = pstmt.executeQuery();

			System.out.println("findId실행완료");
			if(rs.next()){
				idCnt = rs.getInt(1);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} // end try~catch

		finally {
			try {
				rs.close();
				pstmt.close();
				con.close();

			} catch (SQLException se) {
				se.printStackTrace();
			}

		}
		return idCnt;
	}

	public LogDTO select(String user_id) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(url, userid, passwd);
			
			String query = "SELECT id , pw FROM Users where id = ?";

			pstmt = con.prepareStatement(query);
			pstmt.setString(1, user_id);

//			rs = stmt.executeQuery();
			rs = pstmt.executeQuery();

			System.out.println("select실행완료");
			while (rs.next()) {
				String id  = rs.getString("id");
				String pw  = rs.getString("pw");
				logDTO = new LogDTO(id, pw);
//				list.add(new LogDTO(id, pw));
			}// end while

		} catch (Exception e) {
			e.printStackTrace();
		} // end try~catch

		finally {

			// 8. 占쎌쁽占쎌뜚 獄쏆꼶沅�
			try {
				rs.close();
				pstmt.close();
				con.close();

			} catch (SQLException se) {
				se.printStackTrace();
			}

		}

		// db 占쎈염占쎈짗 占쎄국
		return logDTO;
	}
}// end select()

