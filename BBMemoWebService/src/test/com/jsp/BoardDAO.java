package test.com.jsp;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class BoardDAO {

	ArrayList<BoardDTO> list;

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@192.168.0.21:1521:XE";
	String userid = "kosmop";
	String passwd = "1234";

	public BoardDAO() {

		try {
			list = new ArrayList<BoardDTO>();
			Class.forName(driver);

		} catch (ClassNotFoundException e) {

			e.printStackTrace();

		}// end try~catch
	}// end 占쎄문占쎄쉐占쎌쁽

	public ArrayList<BoardDTO> select() {

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(url, userid, passwd);

			String query = "SELECT b_num , b_name, b_content FROM board ";

			stmt = con.prepareStatement(query);

			rs = stmt.executeQuery();

			System.out.println("select실행완료");
			while (rs.next()) {

				int b_num = rs.getInt("b_num");
				String b_name = rs.getString("b_name");
				String b_content = rs.getString("b_content");
				list.add(new BoardDTO(b_num, b_name, b_content));
			}// end while

		} catch (Exception e) {
			e.printStackTrace();
		} // end try~catch

		finally {

			// 8. 占쎌쁽占쎌뜚 獄쏆꼶沅�
			try {
				rs.close();
				stmt.close();
				con.close();

			} catch (SQLException se) {
				se.printStackTrace();
			}

		}

		// db 占쎈염占쎈짗 占쎄국
		return list;
	}
}// end select()

