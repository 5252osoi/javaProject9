package academyManager;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

@SuppressWarnings("serial")
public class AcademyAdmin extends JFrame {

	private JPanel contentPane,tabCur,tabStu,tabTea;
	private JTabbedPane tabbedPane;
	private JTextField txtCur,txtStu,txtTea;
	private JButton btnCurSearch,btnCurInsert,btnExit1;
	private JButton btnStuSearch,btnStuInsert,btnExit2;
	private JButton btnTeaSearch,btnTeaInsert,btnExit3;
	
	@SuppressWarnings("rawtypes")
	Vector titleCur,vDataCur,titleStu,vDataStu,titleTea,vDataTea;
	DefaultTableModel dtmCur,dtmStu,dtmTea;
	
	AcademyManagerDAO dao = new AcademyManagerDAO();
	AcademyManagerService svc= new AcademyManagerService();
	private JTable tblCur;
	private JTable tblStu;
	private JTable tblTea;
	private JButton btnCurRefresh;
	private JLabel lblNewLabel;
	private JComboBox cbCur,cbStu,cbTea;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AcademyAdmin frame = new AcademyAdmin();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public AcademyAdmin() {
		setTitle("학원관리자");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(2, 5, 980, 550);
		tabbedPane.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 14));
		contentPane.add(tabbedPane);
		
		/*------------------------수업관리탭------------------------------------*/
		tabCur = new JPanel();
		tabbedPane.addTab("수업관리", null, tabCur, null);
		tabCur.setLayout(null);
		
		JPanel curPn1 = new JPanel();
		curPn1.setBounds(0, 0, 975, 40);
		tabCur.add(curPn1);
		curPn1.setLayout(null);
		
		cbCur = new JComboBox();
		cbCur.setModel(new DefaultComboBoxModel(new String[] {"수업명", "담당강사"}));
		cbCur.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		cbCur.setBounds(5, 7, 100, 25);
		curPn1.add(cbCur);
		
		txtCur = new JTextField();
		txtCur.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		txtCur.setBounds(107, 7, 150, 25);
		curPn1.add(txtCur);
		txtCur.setColumns(10);
		
		btnCurSearch = new JButton("검색");
		btnCurSearch.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		btnCurSearch.setBounds(259, 7, 60, 25);
		curPn1.add(btnCurSearch);
		
		//////////////////////JTable설계 (수업탭)////////////////////////
		JPanel curPn2 = new JPanel();
		curPn2.setBounds(0, 42, 975, 430);
		tabCur.add(curPn2);
		curPn2.setLayout(null);
		
		titleCur = new Vector<>();
		titleCur.add("번호");
		titleCur.add("수업명");
		titleCur.add("수업시작일");
		titleCur.add("수업종료일");
		titleCur.add("담당강사");
		titleCur.add("수강인원");
		titleCur.add("수업료/인");
		
		//Vector로 db에서 데이터 가져옴
		vDataCur=dao.getCurList();
		//db에서 가져온 자료를 DTM 테이블모델 생성으로 담아줌
		dtmCur =new DefaultTableModel(vDataCur,titleCur);
		//자료와 타이틀로 JTable생성
		tblCur= new JTable(dtmCur);
		
		JScrollPane curSP = new JScrollPane(tblCur);
		curSP.setBounds(2, 0, 970, 430);
		curPn2.add(curSP);
		
		tblCur.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		curSP.setViewportView(tblCur);
		
		tableCellAlign(tblCur);
		tblCur.getColumnModel().getColumn(0).setMaxWidth(50);
		
		//
		
		
		////////////////////////////////////////
		
		
		JPanel curPn3 = new JPanel();
		curPn3.setBounds(0, 474, 975, 40);
		tabCur.add(curPn3);
		curPn3.setLayout(null);
		
		btnCurInsert = new JButton("수업 추가");
		btnCurInsert.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		btnCurInsert.setBounds(721, 5, 120, 30);
		curPn3.add(btnCurInsert);
		
		btnExit1 = new JButton("작업 종료");
		btnExit1.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		btnExit1.setBounds(843, 5, 120, 30);
		curPn3.add(btnExit1);
		
		btnCurRefresh = new JButton("새로 고침");
		btnCurRefresh.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		btnCurRefresh.setBounds(599, 5, 120, 30);
		curPn3.add(btnCurRefresh);
		
		lblNewLabel = new JLabel("//테이블을 클릭해서 수정,삭제");
		lblNewLabel.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		lblNewLabel.setBounds(12, 5, 389, 30);
		curPn3.add(lblNewLabel);
		
		
		/*---------------------------------학생관리탭---------------------------------------*/
		
		
		tabStu = new JPanel();
		tabbedPane.addTab("학생관리", null, tabStu, null);
		tabStu.setLayout(null);
		
		JPanel stuPn1 = new JPanel();
		stuPn1.setLayout(null);
		stuPn1.setBounds(0, 0, 975, 40);
		tabStu.add(stuPn1);
		
		cbStu = new JComboBox();
		cbStu.setModel(new DefaultComboBoxModel(new String[] {"이름", "수업명", "학교", "나이"}));
		cbStu.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		cbStu.setBounds(5, 7, 100, 25);
		stuPn1.add(cbStu);
		
		txtStu = new JTextField();
		txtStu.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		txtStu.setColumns(10);
		txtStu.setBounds(107, 7, 150, 25);
		stuPn1.add(txtStu);
		
		btnStuSearch = new JButton("검색");
		btnStuSearch.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		btnStuSearch.setBounds(259, 7, 60, 25);
		stuPn1.add(btnStuSearch);
		
		////////////////////////////학생관리탭 테이블 ///////////////////////////////

		JPanel stuPn2 = new JPanel();
		stuPn2.setBounds(0, 42, 975, 430);
		tabStu.add(stuPn2);
		stuPn2.setLayout(null);
		
		titleStu = new Vector<>();
		titleStu.add("번호");
		titleStu.add("이름");
		titleStu.add("나이");
		titleStu.add("연락처");
		titleStu.add("보호자연락처");
		titleStu.add("주소");
		titleStu.add("학교");
		titleStu.add("수강과목");
		titleStu.add("수업료");
		/*번호/이름/나이/연락처/보호자연락처/주소/학교/수강과목/수업료*/
		vDataStu=dao.getStuList();
		
		dtmStu = new DefaultTableModel(vDataStu, titleStu);
		
		tblStu = new JTable(dtmStu);
		
		JScrollPane stuSP = new JScrollPane(tblStu);
		stuSP.setBounds(2, 0, 970, 430);
		stuPn2.add(stuSP);
		tblStu.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		stuSP.setViewportView(tblStu);
		
		tableCellAlign(tblStu);
		tblStu.getColumnModel().getColumn(0).setMaxWidth(50);
		
		
		
		
		JPanel stuPn3 = new JPanel();
		stuPn3.setLayout(null);
		stuPn3.setBounds(0, 474, 975, 40);
		tabStu.add(stuPn3);
		
		btnStuInsert = new JButton("학생 정보 입력");
		btnStuInsert.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		btnStuInsert.setBounds(721, 5, 120, 30);
		stuPn3.add(btnStuInsert);
		
		btnExit2 = new JButton("작업 종료");
		btnExit2.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		btnExit2.setBounds(843, 5, 120, 30);
		stuPn3.add(btnExit2);
		
		JButton btnStuRefresh = new JButton("새로 고침");
		btnStuRefresh.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		btnStuRefresh.setBounds(599, 5, 120, 30);
		stuPn3.add(btnStuRefresh);
		
		/*--------------------------------강사관리----------------------------------------*/
		tabTea = new JPanel();
		tabbedPane.addTab("강사관리", null, tabTea, null);
		tabTea.setLayout(null);
		
		JPanel teaPn1 = new JPanel();
		teaPn1.setLayout(null);
		teaPn1.setBounds(0, 0, 975, 40);
		tabTea.add(teaPn1);
		
		cbTea = new JComboBox();
		cbTea.setModel(new DefaultComboBoxModel(new String[] {"이름", "나이"}));
		cbTea.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		cbTea.setBounds(5, 7, 100, 25);
		teaPn1.add(cbTea);
		
		txtTea = new JTextField();
		txtTea.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		txtTea.setColumns(10);
		txtTea.setBounds(107, 7, 150, 25);
		teaPn1.add(txtTea);
		
		btnTeaSearch = new JButton("검색");
		btnTeaSearch.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		btnTeaSearch.setBounds(259, 7, 60, 25);
		teaPn1.add(btnTeaSearch);
		
		
		/*////////////// 강사관리탭 테이블 만들기 /////////*/
		JPanel teaPn2 = new JPanel();
		teaPn2.setLayout(null);
		teaPn2.setBounds(0, 42, 975, 430);
		tabTea.add(teaPn2);
		
		titleTea = new Vector<>();
		titleTea.add("번호");
		titleTea.add("이름");
		titleTea.add("나이");
		titleTea.add("연락처");
		titleTea.add("주소");
		titleTea.add("담당수업");
		titleTea.add("월급");
		/*번호/이름/나이/연락처/주소/담당수업/월급*/
		vDataTea=dao.getTeaList();
		dtmTea=new DefaultTableModel(vDataTea,titleTea);
		tblTea=new JTable(dtmTea);
		
		JScrollPane teaSP = new JScrollPane(tblTea);
		teaSP.setBounds(2, 0, 970, 430);
		teaPn2.add(teaSP);
		
		tblTea.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		teaSP.setViewportView(tblTea);
		
		tableCellAlign(tblTea);
		tblTea.getColumnModel().getColumn(0).setMaxWidth(50);
		//////////////
		
		JPanel teaPn3 = new JPanel();
		teaPn3.setLayout(null);
		teaPn3.setBounds(0, 474, 975, 40);
		tabTea.add(teaPn3);
		
		btnTeaInsert = new JButton("강사 정보 추가");
		btnTeaInsert.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		btnTeaInsert.setBounds(721, 5, 120, 30);
		teaPn3.add(btnTeaInsert);
		
		btnExit3 = new JButton("작업 종료");
		btnExit3.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		btnExit3.setBounds(843, 5, 120, 30);
		teaPn3.add(btnExit3);
		
		JButton btnTeaRefresh = new JButton("새로 고침");
		btnTeaRefresh.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		btnTeaRefresh.setBounds(599, 5, 120, 30);
		teaPn3.add(btnTeaRefresh);
		
///////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////여기서부터 버튼액션////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////
		//////////////////////////////////수업관리탭////////////////////////////
		//검색버튼 눌렀을때의 처리
		btnCurSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getCurConditionProcess();
				
			}
		});
		//콤보박스 선택시 커서를 입력 텍스트필드로
		cbCur.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				txtCur.requestFocus();
			}
		});
		
		//수업탭에서 새로고침버튼
		btnCurRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dtmCur = new DefaultTableModel(vDataCur,titleCur);
				dtmCur.setNumRows(0);
				//새로고침 하기위해 테이블 초기화
				
				//바뀐 리스트 받아오기
				vDataCur=dao.getCurList();
				//테이블 새로 만들기
				dtmCur = new DefaultTableModel(vDataCur,titleCur);
				tblCur.setModel(dtmCur);
				//정렬
				tableCellAlign(tblCur);
				tblCur.getColumnModel().getColumn(0).setMaxWidth(50);
				
			}
		});
		
		//수업추가버튼
		btnCurInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CurInput();
			}
		});
		/////////////수업탭 테이블 선택하면 수정창 뜨게끔 만들것임!!!//////////////
		tblCur.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {
				CurriculumVO cVO=new CurriculumVO();
				//선택한 셀의 행 번호
				int row = tblCur.getSelectedRow();
				//테이블 모델객체 가져오기
				TableModel data = tblCur.getModel();
				
//				titleCur.add("번호");
//				titleCur.add("수업명");
//				titleCur.add("수업시작일");
//				titleCur.add("수업종료일");
//				titleCur.add("담당강사");
//				titleCur.add("수강인원");
//				titleCur.add("수업료/인");
				int idx=(int)data.getValueAt(row, 0);
				String name = (String)data.getValueAt(row,1);
				int fee = (int)data.getValueAt(row, 6);
				cVO.setIdx(idx);
				cVO.setName(name);
				cVO.setFee(fee);
				new CurUpdate(cVO);
			}
		});
///////////////////////////////////////////////////////학생관리 탭 버튼액션///////////////////////////////////////////////////////
		
		//학생관리탭에서 검색버튼
		btnStuSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getStuConditionProcess();
				
			}
		});
		
		//학생관리탭에서 콤보박스 선택시 커서이동
		cbStu.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				txtStu.requestFocus();
			}
		});
		
		//학생탭 테이블 클릭시 수정/삭제로이동
		tblStu.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {
				StudentVO sVO=new StudentVO();
				//선택한 셀의 행 번호
				int row = tblStu.getSelectedRow();
				//테이블 모델객체 가져오기
				TableModel data = tblStu.getModel();
				
				int idx=(int)data.getValueAt(row, 0);
				String name = (String)data.getValueAt(row,1);
				int age = (int)data.getValueAt(row, 2);
				String phone = (String)data.getValueAt(row, 3);
				String famPhone = (String)data.getValueAt(row, 4);
				String address=(String)data.getValueAt(row, 5);
				String school=(String)data.getValueAt(row, 6);
				
				sVO.setIdx(idx);
				sVO.setName(name);
				sVO.setAge(age); //012-4567-9012
				sVO.setPhone(phone.substring(0,3)+phone.substring(4,8)+phone.substring(9,13));
				sVO.setFamphone(famPhone.substring(0,3)+phone.substring(4,8)+phone.substring(9,13));
				sVO.setAddress(address);
				sVO.setSchool(school);
				new StuUpdate(sVO);
			}
		});
		
		//학생관리 탭 새로고침버튼
		btnStuRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dtmStu = new DefaultTableModel(vDataStu,titleStu);
				dtmStu.setNumRows(0);
				//새로고침 하기위해 테이블 초기화
				
				//바뀐 리스트 받아오기
				vDataStu=dao.getStuList();
				//테이블 새로 만들기
				dtmStu = new DefaultTableModel(vDataStu,titleStu);
				tblStu.setModel(dtmStu);
				//정렬
				tableCellAlign(tblStu);
				tblStu.getColumnModel().getColumn(0).setMaxWidth(50);
			}
		});
		//학생 정보 추가버튼
		btnStuInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new StuInput();
				
			}
		});
/////////////////////////////////////////////////강사탭 버튼액션/////////////////////////////////////////////////////////////////
		
		//검색버튼 눌렀을때의 처리
		
		btnTeaSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getTeaConditionProcess();
				
			}
		});
		//콤보박스 선택시 커서를 입력 텍스트필드로
		cbTea.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				txtTea.requestFocus();
			}
		});
		
		//강사탭 테이블 클릭시 수정/삭제탭
		tblTea.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {
				TeacherVO tVO=new TeacherVO();
				//선택한 셀의 행 번호
				int row = tblTea.getSelectedRow();
				//테이블 모델객체 가져오기
				TableModel data = tblTea.getModel();
				
				int idx=(int)data.getValueAt(row, 0);
				String name = (String)data.getValueAt(row,1);
				int age = (int)data.getValueAt(row, 2);
				String phone = (String)data.getValueAt(row, 3);
				String address=(String)data.getValueAt(row, 4);
				int pay=(int)data.getValueAt(row, 6);
				
				tVO.setIdx(idx);
				tVO.setName(name);
				tVO.setAge(age); //012-4567-9012
				tVO.setPhone(phone.substring(0,3)+phone.substring(4,8)+phone.substring(9,13));
				tVO.setAddress(address);
				new TeaUpdate(tVO);
			}
		});
		
		//신규 강사 추가 버튼
		btnTeaInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TeaInput();
			}
		});
		
		//새로고침
		btnTeaRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dtmTea = new DefaultTableModel(vDataTea,titleTea);
				dtmTea.setNumRows(0);
				//새로고침 하기위해 테이블 초기화
				
				//바뀐 리스트 받아오기
				vDataTea=dao.getTeaList();
				//테이블 새로 만들기
				dtmTea = new DefaultTableModel(vDataTea,titleTea);
				tblTea.setModel(dtmTea);
				//정렬
				tableCellAlign(tblTea);
				tblTea.getColumnModel().getColumn(0).setMaxWidth(50);
			}
		});
		
//////////////////////////////////////////////////////종료버튼//////////////////////////////////////////////////////////
		btnExit1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		
		
		
	}
	//조건검색 강사탭
	protected void getTeaConditionProcess() {
		String cbTeaCondi=cbTea.getSelectedItem().toString();
		String txtTeaCondi=txtTea.getText();
		if(txtTeaCondi.trim().equals("")) {
			JOptionPane.showMessageDialog(null, "검색 할 내용을 입력하세요.");
			txtTea.requestFocus();
			return;
		}
		if(cbTeaCondi.equals("이름")) vDataTea=dao.getTeaConditionSearch("t.name", txtTeaCondi);
		else if(cbTeaCondi.equals("나이")) vDataTea=dao.getTeaConditionSearch("t.age", txtTeaCondi);
		
		dtmTea.setDataVector(vDataTea, titleTea);
		tableCellAlign(tblTea);
		tblTea.getColumnModel().getColumn(0).setMaxWidth(50);
	}
	//조건검색 학생탭
	protected void getStuConditionProcess() {
		String cbStuCondi=cbStu.getSelectedItem().toString();
		String txtStuCondi=txtStu.getText();
		if(txtStuCondi.trim().equals("")) {
			JOptionPane.showMessageDialog(null, "검색 할 내용을 입력하세요.");
			txtStu.requestFocus();
			return;
		}
		if(cbStuCondi.equals("이름")) vDataStu=dao.getStuConditionSearch("s.name", txtStuCondi);
		else if(cbStuCondi.equals("수업명")) vDataStu=dao.getStuConditionSearch("c.name", txtStuCondi);
		else if(cbStuCondi.equals("학교")) vDataStu=dao.getStuConditionSearch("s.school", txtStuCondi);
		else if(cbStuCondi.equals("나이")) vDataStu=dao.getStuConditionSearch("s.age", txtStuCondi);
		
		dtmStu.setDataVector(vDataStu, titleStu);
		tableCellAlign(tblStu);
		tblStu.getColumnModel().getColumn(0).setMaxWidth(50);
	}

	//	수업명,담당강사 둘중 하나로 조건검색하기
	protected void getCurConditionProcess() {
		String cbCurCondi=cbCur.getSelectedItem().toString();
		String txtCurCondi=txtCur.getText(); //입력받은내용(검색내용
		if(txtCurCondi.trim().equals("")) {
			JOptionPane.showMessageDialog(null, "검색 할 내용을 입력하세요.");
			txtCur.requestFocus();
			return;
		}
		if(cbCurCondi.equals("수업명")) vDataCur=dao.getCurConditionSearch("c.name", txtCurCondi);
		else if(cbCurCondi.equals("담당강사")) vDataCur=dao.getCurConditionSearch("t.Name", txtCurCondi);
		
		dtmCur.setDataVector(vDataCur, titleCur);

		tableCellAlign(tblCur);
		tblCur.getColumnModel().getColumn(0).setMaxWidth(50);
		
	}
	//테이블 가운데 정렬
	private void tableCellAlign(JTable tbl) {
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = tbl.getColumnModel();
		for(int i=0; i<tcm.getColumnCount(); i++) {
			tcm.getColumn(i).setCellRenderer(dtcr);
		}
	}
}
