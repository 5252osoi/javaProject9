package academyManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class AcademyManagerDAO {

	Connection conn =null;
	PreparedStatement pstmt =null;
	PreparedStatement pstmt2 =null;
	PreparedStatement pstmt3 =null;
	ResultSet rs =null;
	ResultSet rs2 =null;
	ResultSet rs3 =null;
	
	String sql ="";
	String sql2 ="";
	String sql3 ="";
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
			if(pstmt2!=null)pstmt2.close();
			if(pstmt3!=null)pstmt3.close();
		} catch (Exception e) {}
	}
	
	public void rsClose() {
		try {
			if(rs!=null)rs.close();
			if(rs2!=null)rs2.close();
			if(rs3!=null)rs3.close();
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
			//담당강사가 없고 수강인원도 없는 수업 
			//담당강사가 있고 수강인원도 있는 수업  
			//담당강사가 없고 수강인원이 있는 수업
			//담당강사가 있고 수강인원이 없는 수업
			sql="SELECT * FROM curriculum ";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			
			
			while(rs.next()) {
				Vector vo = new Vector<>();
				int cIdx=rs.getInt("idx");
				/* 번호/수업명/수업시작일/수업종료일/담당강사/수강인원/수업료 */
				vo.add(rs.getInt("idx"));					//번호
				vo.add(rs.getString("name"));				//수업명
				//수업시작,수업끝날짜 출력
				String curStart,curEnd;						
				curStart=rs.getString("curriculumStart");	
				vo.add(curStart.substring(0, 10));			//시작일
				curEnd=rs.getString("curriculumEnd");		
				vo.add(curEnd.substring(0, 10));			//종료일
				
				//해당 수업 (cIdx)을 수업하는 강사의 이름을 테이블에 넣기
				sql2="SELECT t.name as tName FROM teacher t where t.curri1="+cIdx;
				pstmt2=conn.prepareStatement(sql2);
				rs2=pstmt2.executeQuery();
				if(rs2.next()) {
					vo.add(rs2.getString("tName"));			//담당강사
				} else {
					vo.add("담당강사없음");						//담당강사가 없으면 없다고 표시
				}
				// 해당 수업을 수강하는 학생의 수 count로 몇명인지 테이블에 넣기
				sql3="SELECT count(s.name) as numOfStu FROM student s where s.curri1="+cIdx;
				pstmt3=conn.prepareStatement(sql3);
				rs3=pstmt3.executeQuery();
				if(rs3.next()) {
					vo.add(rs3.getInt("numOfStu"));			//수강인원
				} else {
					vo.add(0);								//수강인원이 없으면 0명으로 표시
				}
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
	
	//학생정보 기입시 콤보박스에 수업명 출력용
	public List<CurriculumVO> getStuCbbox() {
		List<CurriculumVO> vos = new ArrayList<CurriculumVO>();
		try {
			sql="SELECT * FROM Curriculum";
			pstmt = conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				CurriculumVO vo = new CurriculumVO();
				//이름이랑 IDX만 가져가면 됨!! 
				vo.setIdx(rs.getInt("idx"));
				vo.setName(rs.getString("Name"));
				vos.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("SQL오류 : "+e.getMessage());
			e.printStackTrace();
		} finally {
			rsClose();
		}		
		return vos;
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
	//수업 신규입력처리
	public int setCurInput(CurriculumVO cVO) {
		int res=0;
		try {
			sql="insert into curriculum values (default,?,?,?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, cVO.getName());
			pstmt.setString(2, cVO.getCurriculumStart());
			pstmt.setString(3, cVO.getCurriculumEnd());
			pstmt.setInt(4, cVO.getFee());
			res=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}

	public int setCurUpdate(CurriculumVO cVO) {
		int res=0;
			try {
				sql="update curriculum set name=?, curriculumStart=?,curriculumEnd=?,fee=? where idx=?";
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, cVO.getNewName());
				pstmt.setString(2, cVO.getCurriculumStart());
				pstmt.setString(3, cVO.getCurriculumEnd());
				pstmt.setInt(4, cVO.getFee());
				pstmt.setInt(5, cVO.getIdx());
				res=pstmt.executeUpdate();
				
			} catch (SQLException e) {
				System.out.println("SQL 오류 : " + e.getMessage());
			} finally {
				pstmtClose();
			}
				
		return res;
	}

	public int CurDelete(CurriculumVO cVO) {
		int res=0;
		try {
			sql="delete from curriculum where idx=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, cVO.getIdx());
			res=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		
		return res;
	}
//	수업명,담당강사 둘중 하나로 조건검색하기
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector getCurConditionSearch(String fieldName, String txtCurCondi) {
		Vector vData = new Vector<>();
		try {
//			//커리큘럼 idx를 가진 선생의 이름, 학생이름의 갯수(수업듣는학생수)를 join으로 가져옴
//			번호/수업명/수업시작일/수업종료일/담당강사/수강인원/수업료
//			SELECT c.*, t.name as tName, count(s.name) as numOfStu FROM curriculum c, student s, teacher t  where t.curri1=c.idx and c.name like '고등부%' order by name;
//			sql2="SELECT * FROM curriculum ";
			
			//수업명으로 검색할때
			if (fieldName.equals("c.name")) {
				//수업명으로 검색 fieldName=수업명 (c.name)
				sql="SELECT c.* from curriculum c where "+fieldName+" like ? order by name";
				//학생수/강사이름 찾아서 있는지 없는지 검색
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%"+txtCurCondi+"%");
				rs=pstmt.executeQuery();
				//텍스트필드에 입력한값으로 검색해서 나온값//
				while(rs.next()) {
					//해당값으로 돌려받은 idx값을 변수로
					int cIdx=rs.getInt("idx");
					Vector vo = new Vector<>();
	/*번호*/			vo.add(rs.getInt("idx"));
	/*수업명*/		vo.add(rs.getString("name"));

					String curStart,curEnd;		
					curStart=rs.getString("curriculumStart");	
					curEnd=rs.getString("curriculumEnd");		
	/*시작일*/		vo.add(curStart.substring(0, 10));	
	/*종료일*/		vo.add(curEnd.substring(0, 10));
					//해당 수업의 idx로 담당 강사 이름 찾기
					sql2="select t.name as tName FROM teacher t where t.curri1="+cIdx;
					pstmt2=conn.prepareStatement(sql2);
					rs2=pstmt2.executeQuery();
					if(rs2.next()) {
	/*강사명*/			vo.add(rs2.getString("tName"));
					} else {
						vo.add("담당강사없음");
					}
					
					//해당 수업의 idx로 수업듣는 학생 수 찾기
					sql3="SELECT count(s.name) as numOfStu FROM student s where s.curri1="+cIdx;
					pstmt3=conn.prepareStatement(sql3);
					rs3=pstmt3.executeQuery();
					if(rs3.next()) {
	/*인원*/				vo.add(rs3.getInt("numOfStu"));							
					} else {
						vo.add(0);
					}
	/*수업료*/		vo.add(rs.getInt("fee"));
					vData.add(vo);
				}
			
			} else {   //강사이름으로 검색할때
				//강사이름으로 검색결과가 나온다 = 담당강사가 있다는뜻
				sql2="SELECT c.*, t.name as tName FROM curriculum c, teacher t  where t.curri1=c.idx and "+fieldName+" like ? order by name";
				pstmt2=conn.prepareStatement(sql2);
				pstmt2.setString(1, "%"+txtCurCondi+"%");
				rs2=pstmt2.executeQuery();
				
				while(rs2.next()) {
					int cIdx=rs2.getInt("idx");
					Vector vo = new Vector<>();
	/*번호*/			vo.add(rs2.getInt("idx"));
	/*수업명*/		vo.add(rs2.getString("name"));

					String curStart,curEnd;		
					curStart=rs2.getString("curriculumStart");	
					curEnd=rs2.getString("curriculumEnd");		
	/*시작일*/		vo.add(curStart.substring(0, 10));	
	/*종료일*/		vo.add(curEnd.substring(0, 10));
	/*강사명*/		vo.add(rs2.getString("tName"));
	
					//수업 idx로 학생 수 찾기
					sql3="SELECT count(s.name) as numOfStu FROM student s where s.curri1="+cIdx;
					pstmt3=conn.prepareStatement(sql3);
					rs3=pstmt3.executeQuery();
					if(rs3.next()) {
	/*인원*/				vo.add(rs3.getInt("numOfStu"));	
					} else {
						vo.add(0);
					}
	/*수업료*/		vo.add(rs2.getInt("fee"));
					vData.add(vo);
				}
				
			}
			
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return vData;
	}
	
	//학생 신규등록버튼
	public int setStuInput(StudentVO sVO) {
		int res=0;
		try {
			sql="insert into student values(default,?,?,?,?,?,?,?,null,null,0)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, sVO.getName());
			pstmt.setInt(2, sVO.getAge());
			pstmt.setString(3, sVO.getPhone());
			pstmt.setString(4, sVO.getFamphone());
			pstmt.setString(5, sVO.getAddress());
			pstmt.setString(6, sVO.getSchool());
			pstmt.setInt(7, sVO.getCurri1());
			res=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		
		return res;
	}
	//학생 정보 삭제
	public int stuDelete(StudentVO sVO) {
		int res=0;
		try {
			sql="delete from student where idx=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, sVO.getIdx());
			res=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		
		return res;
	}
	
	//학생 정보 수정
	public int setStuUpdate(StudentVO sVO) {
		int res=0;
		try {
			sql="update Student set name=?, age=?, phone=?,famphone=?,address=?,school=?,curri1=? where idx=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, sVO.getName());
			pstmt.setInt(2, sVO.getAge());
			pstmt.setString(3, sVO.getPhone());
			pstmt.setString(4, sVO.getFamphone());
			pstmt.setString(5, sVO.getAddress());
			pstmt.setString(6, sVO.getSchool());
			pstmt.setInt(7, sVO.getCurri1());
			pstmt.setInt(8, sVO.getIdx());
			res=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
			
	return res;
	}
	//학생탭에서 조건걸고 검색하기
	@SuppressWarnings("unchecked")
	public Vector getStuConditionSearch(String fieldName, String txtStuCondi) {
		Vector vData = new Vector<>();
		
		try {
			//수업명으로 검색 (c.name)
			if (fieldName.equals("c.name")) {
				sql="select c.idx as idx, c.name as cName, c.fee as cfee from curriculum c where "+fieldName+" like ?";
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%"+txtStuCondi+"%");
				rs=pstmt.executeQuery();
				//수업명으로 검색한 뒤 수업의 idx값을 가져와서 학생의 curri1에 있는 값을 검색
				int cIdx=0;
				while(rs.next()) {
					Vector vo = new Vector<>();
					cIdx=rs.getInt("idx");
					
					sql2="select s.* from student s where curri1="+cIdx;
					pstmt2=conn.prepareStatement(sql2);
					rs2=pstmt2.executeQuery();
					if(rs2.next()) {
						vo.add(rs2.getInt("idx"));
						vo.add(rs2.getString("name"));
						vo.add(rs2.getInt("age"));	
						String phone,famPhone;
						phone=rs2.getString("phone");			//연락처
						vo.add(phone.substring(0,3)+"-"+phone.substring(3,7)+"-"+phone.substring(7,11));
						famPhone=rs2.getString("famPhone");		//보호자연락처
						vo.add(famPhone.substring(0,3)+"-"+famPhone.substring(3,7)+"-"+famPhone.substring(7,11));
						vo.add(rs2.getString("address"));		//주소
						vo.add(rs2.getString("school"));			//학교
						vo.add(rs.getString("cName"));			//수강과목
						vo.add(rs.getInt("cFee"));				//수업료
						vData.add(vo);
					}
				}
				
			} else {
				//이름으로 검색 fieldName = 학생이름(s.name)
				sql="SELECT s.* from Student s where "+fieldName+" like ?";
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%"+txtStuCondi+"%");
				rs=pstmt.executeQuery();
				
				//필드네임으로 검색해서 필요한idx값을 찾기 (curri1에 있는 해당 수업의 IDX를 찾아서 수업명,수업료를 출력) 
				while(rs.next()) {
					Vector vo = new Vector<>();
					
					int cIdx = rs.getInt("curri1");
					
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
					
					sql2="select c.name as cName, c.fee as cFee From curriculum c where c.idx="+cIdx;
					pstmt2=conn.prepareStatement(sql2);
					rs2=pstmt2.executeQuery();
					if(rs2.next()) {
						vo.add(rs2.getString("cName"));			//수강과목
						vo.add(rs2.getInt("cFee"));				//수업료
					} else {
						vo.add("수강과목없음");
						vo.add(0);
					}
					vData.add(vo);
				}
			}
			
			
		} catch (SQLException e) {
//			System.out.println("SQL 오류 : " + e.getMessage());
			e.printStackTrace();
		} finally {
			rsClose();
		}
		return vData;
	}
	
	
	//강사정보등록
	public int setTeaInput(TeacherVO tVO) {
		int res=0;
		try {
			sql="insert into teacher values(default,?,?,?,?,?,null,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, tVO.getName());
			pstmt.setInt(2, tVO.getAge());
			pstmt.setString(3, tVO.getPhone());
			pstmt.setString(4, tVO.getAddress());
			pstmt.setInt(5, tVO.getCurri1());
			pstmt.setInt(6, tVO.getPay());
			res=pstmt.executeUpdate();
			
		} catch (SQLException e) {
//			System.out.println("SQL 오류 : " + e.getMessage());
			e.printStackTrace();
		} finally {
			pstmtClose();
		}
		
		return res;
	}
	
	//강사정보삭제
	public int teaDelete(TeacherVO tVO) {
		int res=0;
		try {
			sql="delete from teacher where idx=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, tVO.getIdx());
			res=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		
		return res;
		
	}
		//강사정보 수정
	public int setTeaUpdate(TeacherVO tVO) {
		int res=0;
		try {
			sql="update teacher set name=?, age=?, phone=?,address=?,curri1=?,pay=? where idx=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, tVO.getName());
			pstmt.setInt(2, tVO.getAge());
			pstmt.setString(3, tVO.getPhone());
			pstmt.setString(4, tVO.getAddress());
			pstmt.setInt(5, tVO.getCurri1());
			pstmt.setInt(6, tVO.getPay());
			pstmt.setInt(7, tVO.getIdx());
			res=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
			
	return res;
	}
	//강사탭에서 조건으로 검색
	@SuppressWarnings("unchecked")
	public Vector getTeaConditionSearch(String fieldName, String txtTeaCondi) {
		Vector vData = new Vector<>();
		
		try {
			//강사이름으로 검색 (t.name)
//			if (fieldName.equals("t.name")) {
				sql="select * from teacher t where "+fieldName+" like ?";
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%"+txtTeaCondi+"%");
				rs=pstmt.executeQuery();
				//강사의 curri1에 있는 값으로 수업의 idx와 비교
				while(rs.next()) {
					Vector vo = new Vector<>();
					int cIdx=rs.getInt("curri1");
					vo.add(rs.getInt("idx"));
					vo.add(rs.getString("name"));
					vo.add(rs.getInt("age"));
					String phone=rs.getString("phone");			//연락처
					vo.add(phone.substring(0,3)+"-"+phone.substring(3,7)+"-"+phone.substring(7,11));
					vo.add(rs.getString("address"));
					sql2="select c.name as cName from curriculum c where idx="+cIdx;
					pstmt2=conn.prepareStatement(sql2);
					rs2=pstmt2.executeQuery();
					if(rs2.next()) {
						vo.add(rs2.getString("cName"));
					} 
					vo.add(rs.getInt("pay"));
					vData.add(vo);
				}
//			}
		} catch (SQLException e) {
//			System.out.println("SQL 오류 : " + e.getMessage());
			e.printStackTrace();
		} finally {
			rsClose();
		}
		
		return vData;
	}
	
	



}
