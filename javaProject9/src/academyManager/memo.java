/*
			if (fieldName.equals("수업명")) {
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
	/*번호			vo.add(rs.getInt("idx"));
	/*수업명			vo.add(rs.getString("name"));

					String curStart,curEnd;		
					curStart=rs.getString("curriculumStart");	
					curEnd=rs.getString("curriculumEnd");		
	/*시작일			vo.add(curStart.substring(0, 10));	
	/*종료일			vo.add(curEnd.substring(0, 10));
					//해당 수업의 idx로 담당 강사 이름 찾기
					sql2="t.name as tName FROM teacher t where t.curri1="+cIdx;
					pstmt2=conn.prepareStatement(sql2);
					rs2=pstmt2.executeQuery();
					if(rs2.next()) {
	/*강사명			vo.add(rs2.getString("tName"));
					} else {
						vo.add("담당강사없음");
					}
					
					//해당 수업의 idx로 수업듣는 학생 수 찾기
					sql3="SELECT count(s.name) as numOfStu FROM student s where s.curri1="+cIdx;
					pstmt3=conn.prepareStatement(sql3);
					rs3=pstmt3.executeQuery();
					if(rs3.next()) {
	/*인원				vo.add(rs3.getInt("numOfStu"));							
					} else {
						vo.add(0);
					}
	/*수업료

import java.util.Vector;

vo.add(rs.getInt("fee"));
					vData.add(vo);
				}
*/