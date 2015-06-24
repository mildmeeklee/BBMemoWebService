package test.com.jsp;
import java.sql.*;
import java.util.*;


public class EmpDAO {
	
	ArrayList<EmpDTO> list;
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "chang";
	String passwd = "1234";
	
	
	 public EmpDAO() {
         
	        try {
	            list = new ArrayList<EmpDTO>();
	            Class.forName(driver);
	             
	        } catch (ClassNotFoundException e) {
	             
	            e.printStackTrace();
	             
	        }//end try~catch
	    }//end 생성자
	

	public ArrayList<EmpDTO> select() {
        
        
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
         
        try {
             
            //3.    Connection 맺기   
                con = DriverManager.getConnection(url, userid, passwd);
 
            //4.    sql 문 작성
                String query = 
            "SELECT deptno, dname, loc FROM dept ORDER BY deptno ";
                 
            //5.    Statement 생성
                stmt = con.prepareStatement(query);
                 
            //6.    sql 문 전송
                rs = stmt.executeQuery();
 
            //7.    Data 얻기
 
                // boolean b = rs.next();
                 
                while ( rs.next() ) {
 
                      int deptno = rs.getInt("deptno");
        String dname = rs.getString("dname");
                    String loc = rs.getString("loc");
                     
                    list.add(new EmpDTO(deptno,dname,loc));
                }//end while
                 
            } catch (Exception e) { e.printStackTrace();
} //end try~catch
             
            finally {
                 
                //8.    자원 반납
                try {
                    rs.close();
                    stmt.close();
                    con.close();
                     
                } catch (SQLException se) {
                    se.printStackTrace();
                }
                 
            }
        //db 연동 끝
        return list;
    }//end select()
	
	public void insert(String deptno, String dname, String loc) {
        
        Connection con = null;
        PreparedStatement stmt = null;
         
        try {
             
            //3.    Connection 맺기   
                con = DriverManager.getConnection(url, userid, passwd);
 
            //4.    sql 문 작성
                String insert =
          "insert into dept (deptno,dname,loc) values ( ?,?,? )";
                 
            //5.    Statement 생성
                stmt = con.prepareStatement(insert);
             
            //6.    내용 입력
                stmt.setInt(1, Integer.parseInt(deptno));
                stmt.setString(2,dname);
                stmt.setString(3,loc);
                 
            //7.    sql 문 전송
                int n = stmt.executeUpdate();
 
            } catch (Exception e) {e.printStackTrace();} //end try~catch
             
            finally {
                 
                //8.    자원 반납
                try {
                     
                    stmt.close();
                    con.close();
                     
                } catch (SQLException se) {
                    se.printStackTrace();
                }
                 
            }
        //db 연동 끝
    }//end insert()
			
	public EmpDTO retrieve(String deptno) {
		
		EmpDTO dto = new EmpDTO();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = DriverManager.getConnection(url,userid,passwd);
			String sql = "select * from dept where deptno = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(deptno));
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setDeptno(rs.getInt("deptno"));
				dto.setDname(rs.getString("dname"));
				dto.setLoc(rs.getString("loc"));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch (SQLException se) {
				// TODO Auto-generated catch block
				se.printStackTrace();
			}
			
		}
		
		return dto;
		
	}//end retrieve()
	
	public void delete(String deptno) {
		
		EmpDTO dto = new EmpDTO();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(url,userid,passwd);
			String sql = "delete from dept where deptno=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(deptno));
			int n = pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				con.close();
			} catch (SQLException se) {
				// TODO Auto-generated catch block
				se.printStackTrace();
			}
			
		}
		

	}//end delete
	
	public void update(String deptno, String dname, String loc) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = DriverManager.getConnection(url,userid,passwd);
			
			String sql = "update dept set dname = ?, loc = ? where deptno = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dname);
			pstmt.setString(2, loc);
			pstmt.setInt(3, Integer.parseInt(deptno));
			int n = pstmt.executeUpdate();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			try {
				pstmt.close();
				con.close();
			} catch (SQLException se) {

				se.printStackTrace();
				
			}
		}
		
		
	}//end update()
	
}
	
	
	
	