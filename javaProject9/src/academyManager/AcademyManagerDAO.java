package academyManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AcademyManagerDAO {

	Connection conn =null;
	PreparedStatement pstmt =null;
	ResultSet rs =null;
	
	String sql ="";
	CurriculumVO cVO =null;
	TeacherVO tVO =null;
	StudentVO sVO =null;
	IdentifyVO iVO =null;
	
	public AcademyManagerDAO() {
		String url ="jdbc:mysql://localhost:3306/javaProject9";
		String user = "root";
		String password="1234";
		try {
			conn=DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.out.println("DB 연결 실패"+e.getMessage());
//			e.printStackTrace();
		}
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 검색 실패"+e.getMessage());
//			e.printStackTrace();
		}
	}

	public void connClose() {
		try {
			conn.close();
		} catch (Exception e) {}
	}
	
	public void pstmtClose() {
		try {
			if(pstmt!=null)pstmt.close();
		} catch (Exception e) {}
	}
	
	public void rsClose() {
		try {
			if(rs!=null)rs.close();
		} catch (Exception e) {}
		pstmtClose();
	}
	
	///////////////!로그인 시 관리자 아이디 체크!////////////////
	public IdentifyVO getAdminCheck(String id) {
		iVO=new IdentifyVO();
		try {
			sql="select * from identify where id=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				iVO.setId(rs.getString("id"));
				iVO.setPassword(rs.getNString("password"));
			}
			
		} catch (SQLException e) {
			System.out.println("SQL 오류 : "+e.getMessage());
			e.printStackTrace();
		}finally {
			rsClose();
		}
		return iVO;
	}



}
