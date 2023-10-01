package academyManager;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TeaInput extends JFrame {

	private JPanel contentPane;
	private JTextField txtName,txtPhone,txtAddress,txtAge;
	private JComboBox cbCur;
	
	
	
	AcademyManagerDAO dao = new AcademyManagerDAO();
	TeacherVO tVO = new TeacherVO();
	CurriculumVO cVO = new CurriculumVO();
	int res=0;
	private JTextField txtPay;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TeaInput frame = new TeaInput();
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
	public TeaInput() {
		setTitle("수업정보추가");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(300, 450);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(2, 61, 280, 350);
		contentPane.add(panel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(5, 8, 270, 270);
		panel.add(panel_2);
		
		txtName = new JTextField();
		txtName.setHorizontalAlignment(SwingConstants.LEFT);
		txtName.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		txtName.setColumns(10);
		txtName.setBounds(82, 12, 180, 30);
		panel_2.add(txtName);
		
		JLabel lblCurName = new JLabel("이름");
		lblCurName.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurName.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		lblCurName.setBounds(0, 12, 80, 30);
		panel_2.add(lblCurName);
		
		JLabel lblCurStart = new JLabel("담당수업");
		lblCurStart.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurStart.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		lblCurStart.setBounds(0, 180, 80, 30);
		panel_2.add(lblCurStart);
		
		JLabel lblCurName_1 = new JLabel("나이");
		lblCurName_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurName_1.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		lblCurName_1.setBounds(0, 54, 80, 30);
		panel_2.add(lblCurName_1);
		
		JLabel lblCurFee_1 = new JLabel("연락처");
		lblCurFee_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurFee_1.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		lblCurFee_1.setBounds(0, 96, 80, 30);
		panel_2.add(lblCurFee_1);
		
		txtPhone = new JTextField();
		txtPhone.setHorizontalAlignment(SwingConstants.LEFT);
		txtPhone.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		txtPhone.setColumns(10);
		txtPhone.setBounds(82, 96, 180, 30);
		panel_2.add(txtPhone);
		
		txtAddress = new JTextField();
		txtAddress.setHorizontalAlignment(SwingConstants.LEFT);
		txtAddress.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		txtAddress.setColumns(10);
		txtAddress.setBounds(82, 138, 180, 30);
		panel_2.add(txtAddress);
		
		JLabel lblCurFee_2 = new JLabel("주소");
		lblCurFee_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurFee_2.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		lblCurFee_2.setBounds(0, 138, 80, 30);
		panel_2.add(lblCurFee_2);
		
		txtAge = new JTextField();
		txtAge.setHorizontalAlignment(SwingConstants.LEFT);
		txtAge.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		txtAge.setColumns(10);
		txtAge.setBounds(82, 54, 180, 30);
		panel_2.add(txtAge);
		
		JButton btnInsert = new JButton("등  록");
		btnInsert.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		btnInsert.setBounds(33, 288, 90, 30);
		panel.add(btnInsert);
		
		JButton btnExit = new JButton("취  소");
		btnExit.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		btnExit.setBounds(156, 288, 90, 30);
		panel.add(btnExit);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(2, 0, 280, 60);
		contentPane.add(panel_1);
		
		JLabel lblInsert = new JLabel("강사 정보 입력");
		lblInsert.setHorizontalAlignment(SwingConstants.CENTER);
		lblInsert.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 20));
		lblInsert.setBounds(65, 5, 150, 50);
		panel_1.add(lblInsert);
		
		
		//콤보박스 내용출력 (수업명 출력)
		List<CurriculumVO> vos = dao.getStuCbbox();
		String[] curName= new String[vos.size()];
		String[][] curFindIdx= new String[vos.size()][2];
		
		for(int i=0; i<vos.size(); i++) {
			cVO=vos.get(i);
			curName[i]=cVO.getName();			//리스트 출력용
				
			curFindIdx[i][0]=cVO.getName();			//비교해서 IDX 가져가기용
			curFindIdx[i][1]=(cVO.getIdx()+""); 	//String 으로 가져간다음 나중에 찾을것임
		}
		
		cbCur = new JComboBox(curName);
		cbCur.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		cbCur.setBounds(82, 180, 180, 30);
		panel_2.add(cbCur);
		
		txtPay = new JTextField();
		txtPay.setHorizontalAlignment(SwingConstants.LEFT);
		txtPay.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		txtPay.setColumns(10);
		txtPay.setBounds(82, 222, 180, 30);
		panel_2.add(txtPay);
		
		JLabel lblCurFee_2_1 = new JLabel("급여");
		lblCurFee_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurFee_2_1.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		lblCurFee_2_1.setBounds(0, 222, 80, 30);
		panel_2.add(lblCurFee_2_1);
		
		
		/*--------------------------버튼액션-------------------------------*/
		//신규강사정보등록
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name =txtName.getText();
				String age = txtAge.getText();
				String phone = txtPhone.getText();
				String address = txtAddress.getText();
				String choiceCur=cbCur.getSelectedItem()+"";
				String pay = txtPay.getText();
				
				if(name.trim().equals("")) {
					JOptionPane.showMessageDialog(null, "성명을 입력하세요");
					txtName.requestFocus();
				} else if(!Pattern.matches("^[0-9]*$", age)) {
					JOptionPane.showMessageDialog(null, "나이는 숫자로 입력하세요");
					txtAge.requestFocus();
				} else if(!Pattern.matches("^[0-9]*$", phone)) {
					JOptionPane.showMessageDialog(null, "번호는 숫자로 입력하세요(-제외 11자리)");
					txtPhone.requestFocus();
				} else {
					tVO.setName(name);
					tVO.setAge(Integer.parseInt(age));
					tVO.setPhone(phone);
					tVO.setAddress(address);
					for(int i=0; i<vos.size(); i++) {
						if(choiceCur.equals(curFindIdx[i][0])) {
							tVO.setCurri1(Integer.parseInt(curFindIdx[i][1]));
						}
					}
					tVO.setPay(Integer.parseInt(pay));
					res=dao.setTeaInput(tVO);
					if(res==0) {
						JOptionPane.showMessageDialog(null, "정보 등록 실패.");
						txtName.requestFocus();
					} else {
						JOptionPane.showMessageDialog(null, "등록을 완료했습니다.");
						dispose();
					}
				}
			}
		});
		//종료
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
}
