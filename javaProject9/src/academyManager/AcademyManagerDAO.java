package academyManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

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
	
	//커리큘럼 리스트 출력 (VO에 담기)
	/////////////////////* 번호/수업명/수업시작일/수업종료일/담당강사/수강인원/수업료 *////////////////////////
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Vector getCurList() {
		Vector vData = new Vector<>();
		try {
//			sql="select * from curriculum order by idx desc";
			sql="SELECT c.*, t.name as tName, count(s.name) as numOfStu FROM curriculum c, student s, teacher t  where t.curri1=c.idx";
			//커리큘럼 idx를 가진 선생의 이름, 학생이름의 갯수(수업듣는학생수)를 join으로 가져옴
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				Vector vo = new Vector<>();
				/* 번호/수업명/수업시작일/수업종료일/담당강사/수강인원/수업료 */
				vo.add(rs.getInt("idx"));					//번호
				vo.add(rs.getString("name"));				//수업명
				//수업시작,수업끝날짜 출력
				String curStart,curEnd;						
				curStart=rs.getString("curriculumStart");	
				vo.add(curStart.substring(0, 10));			//시작일
				curEnd=rs.getString("curriculumEnd");		
				vo.add(curEnd.substring(0, 10));			//종료일
				//
				vo.add(rs.getString("tName"));				//담당강사
				vo.add(rs.getInt("numOfStu"));				//수강인원
				vo.add(rs.getInt("fee"));					//수업료
				vData.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("SQL오류 : "+e.getMessage());
			e.printStackTrace();
		} finally {
			rsClose();
		}
		
		return vData;
	}

	
	//학생 리스트 출력 (VO에 담기)
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector getStuList() {
		Vector vData = new Vector<>();
		try {
			sql="select s.*, c.name as cName, c.fee as cFee From student s, curriculum c where s.curri1=c.idx";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				Vector vo = new Vector<>();
				/*번호/이름/나이/연락처/보호자연락처/주소/학교/수강과목/수업료*/
				vo.add(rs.getInt("idx"));				//번호
				vo.add(rs.getString("name"));			//이름
				vo.add(rs.getInt("age"));				//나이
				//전화번호 형식으로 집어넣기//
				String phone,famPhone;
				phone=rs.getString("phone");			//연락처
				vo.add(phone.substring(0,3)+"-"+phone.substring(3,7)+"-"+phone.substring(7,11));
				famPhone=rs.getString("famPhone");		//보호자연락처
				vo.add(famPhone.substring(0,3)+"-"+famPhone.substring(3,7)+"-"+famPhone.substring(7,11));
				//////////////////////////////////
				vo.add(rs.getString("address"));		//주소
				vo.add(rs.getString("school"));			//학교
				vo.add(rs.getString("cName"));			//수강과목
				vo.add(rs.getInt("cFee"));				//수업료
				vData.add(vo);
			}
			
			
		} catch (SQLException e) {
			System.out.println("SQL오류 : "+e.getMessage());
			e.printStackTrace();
		} finally {
			rsClose();
		}
		
		return vData;
	}
	
	//강사 리스트 출력 (VO에 담기)
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Vector getTeaList() {
		Vector vData = new Vector<>();		
		try {
			sql="select t.*, c.name as cName from teacher t, curriculum c where t.curri1=c.idx";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				Vector vo = new Vector<>();
				/*번호/이름/나이/연락처/주소/담당수업/월급*/
				vo.add(rs.getInt("idx"));		//번호
				vo.add(rs.getString("name"));	//이름
				vo.add(rs.getInt("age"));		//나이
				//전화번호 형식으로 집어넣기//
				String phone;
				phone=rs.getString("phone");	//연락처
				vo.add(phone.substring(0,3)+"-"+phone.substring(3,7)+"-"+phone.substring(7,11));
				//////////////////////////////////
				vo.add(rs.getString("address"));//주소
				vo.add(rs.getString("cName"));	//담당수업
				vo.add(rs.getInt("pay"));		//월급
				vData.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("SQL오류 : "+e.getMessage());
			e.printStackTrace();
		} finally {
			rsClose();
		}
		
		return vData;
	}



}
