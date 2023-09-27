package academyManager;

import java.util.Vector;

public class AcademyManagerService {
	
	AcademyManagerDAO dao = new AcademyManagerDAO();
	CurriculumVO cVO = new CurriculumVO();
	StudentVO sVO = new StudentVO();
	TeacherVO tVO = new TeacherVO();
	
	
	//테이블 만들 데이터 DB에서 가져와서 벡터로 보내기
	public Vector getCurList() {
		Vector vDataCur = new Vector<>();
		Vector<CurriculumVO> vosC = dao.getCurList();
		Vector<StudentVO> vosS = dao.getStuList();
		Vector<TeacherVO> vosT = dao.getTeaList();
		
		/* 번호/수업명/수업시작일/수업종료일/담당강사/수강인원/수업료 */
		
		for(int i=0; i<vosC.size(); i++) {
			Vector vo = new Vector<>();
			
			
		}
		
		
		
		
		
		
		return vDataCur;
	}
	
	
	
}
