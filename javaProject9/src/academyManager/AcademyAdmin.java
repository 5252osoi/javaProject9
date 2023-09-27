package academyManager;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.table.TableModel;

@SuppressWarnings("serial")
public class AcademyAdmin extends JFrame {

	private JPanel contentPane,tabCur,tabStu,tabTea;
	private JTabbedPane tabbedPane;
	private JTextField txtCur,txtStu,txtTea;
	private JButton btnCurSearch,btnCurInsert,btnCurUpdate,btnCurDelete,btnExit1;
	private JButton btnStuSearch,btnStuInsert,btnStuUpdate,btnStuDelete,btnExit2;
	private JButton btnTeaSearch,btnTeaInsert,btnTeaUpdate,btnTeaDelete,btnExit3;
	
	@SuppressWarnings("rawtypes")
	Vector titleCur,vDataCur,titleStu,vDataStu,titleTea,vDataTea;
	DefaultTableModel dtmCur,dtmStu,dtmTea;
	
	AcademyManagerDAO dao = new AcademyManagerDAO();
	AcademyManagerService svc= new AcademyManagerService();
	private JTable tblCur;
	private JTable tblStu;
	private JTable tblTea;
	
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
		
		JComboBox cbCur = new JComboBox();
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
		
		////////////////////////////////////////
		
		
		JPanel curPn3 = new JPanel();
		curPn3.setBounds(0, 474, 975, 40);
		tabCur.add(curPn3);
		curPn3.setLayout(null);
		
		btnCurInsert = new JButton("수업 추가");
		btnCurInsert.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		btnCurInsert.setBounds(489, 5, 120, 30);
		curPn3.add(btnCurInsert);
		
		btnCurUpdate = new JButton("수업 수정");
		btnCurUpdate.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		btnCurUpdate.setBounds(611, 5, 120, 30);
		curPn3.add(btnCurUpdate);
		
		btnCurDelete = new JButton("수업 삭제");
		btnCurDelete.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		btnCurDelete.setBounds(733, 5, 120, 30);
		curPn3.add(btnCurDelete);
		
		btnExit1 = new JButton("작업 종료");
		btnExit1.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		btnExit1.setBounds(855, 5, 120, 30);
		curPn3.add(btnExit1);
		
		
		/*---------------------------------학생관리탭---------------------------------------*/
		
		
		tabStu = new JPanel();
		tabbedPane.addTab("학생관리", null, tabStu, null);
		tabStu.setLayout(null);
		
		JPanel stuPn1 = new JPanel();
		stuPn1.setLayout(null);
		stuPn1.setBounds(0, 0, 975, 40);
		tabStu.add(stuPn1);
		
		JComboBox cbStu = new JComboBox();
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
		
		btnStuInsert = new JButton("학생 정보 추가");
		btnStuInsert.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		btnStuInsert.setBounds(489, 5, 120, 30);
		stuPn3.add(btnStuInsert);
		
		btnStuUpdate = new JButton("학생 정보 수정");
		btnStuUpdate.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		btnStuUpdate.setBounds(611, 5, 120, 30);
		stuPn3.add(btnStuUpdate);
		
		btnStuDelete = new JButton("학생 정보 삭제");
		btnStuDelete.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		btnStuDelete.setBounds(733, 5, 120, 30);
		stuPn3.add(btnStuDelete);
		
		btnExit2 = new JButton("작업 종료");
		btnExit2.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		btnExit2.setBounds(855, 5, 120, 30);
		stuPn3.add(btnExit2);
		
		/*--------------------------------강사관리----------------------------------------*/
		tabTea = new JPanel();
		tabbedPane.addTab("강사관리", null, tabTea, null);
		tabTea.setLayout(null);
		
		JPanel teaPn1 = new JPanel();
		teaPn1.setLayout(null);
		teaPn1.setBounds(0, 0, 975, 40);
		tabTea.add(teaPn1);
		
		JComboBox cbTea = new JComboBox();
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
		btnTeaInsert.setBounds(489, 5, 120, 30);
		teaPn3.add(btnTeaInsert);
		
		btnTeaUpdate = new JButton("강사 정보 수정");
		btnTeaUpdate.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		btnTeaUpdate.setBounds(611, 5, 120, 30);
		teaPn3.add(btnTeaUpdate);
		
		btnTeaDelete = new JButton("강사 정보 삭제");
		btnTeaDelete.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		btnTeaDelete.setBounds(733, 5, 120, 30);
		teaPn3.add(btnTeaDelete);
		
		btnExit3 = new JButton("작업 종료");
		btnExit3.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		btnExit3.setBounds(855, 5, 120, 30);
		teaPn3.add(btnExit3);
		
///////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////여기서부터 버튼액션////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		
		
		
		
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

	private void tableCellAlign(JTable tblCur2) {
		// TODO Auto-generated method stub
		
	}
}
