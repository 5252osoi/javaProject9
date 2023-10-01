package academyManager;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class TeaUpdate extends JFrame {

	private JPanel contentPane;
	private JTextField txtName,txtPhone,txtPay,txtAddress,txtAge;
	private JComboBox cbCur;

	
	AcademyManagerDAO dao = new AcademyManagerDAO();
	TeacherVO tVO = new TeacherVO();
	CurriculumVO cVO = new CurriculumVO();
	int res=0;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TeaUpdate frame = new TeaUpdate();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 * @param tVO 
	 */
	public TeaUpdate(TeacherVO tVO) {
		setTitle("강사정보수정");
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
		
		JButton btnUpdate = new JButton("수  정");
		btnUpdate.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		btnUpdate.setBounds(10, 288, 80, 30);
		panel.add(btnUpdate);
		
		JButton btnExit = new JButton("취  소");
		btnExit.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		btnExit.setBounds(190, 288, 80, 30);
		panel.add(btnExit);
		
		JButton btnDelete = new JButton("삭  제");
		btnDelete.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		btnDelete.setBounds(100, 288, 80, 30);
		panel.add(btnDelete);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(5, 8, 270, 270);
		panel.add(panel_2);
		
		txtName = new JTextField();
		txtName.setText((String) null);
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
		
		JLabel lblCurStart = new JLabel("수강과목");
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
		txtPhone.setText((String) null);
		txtPhone.setHorizontalAlignment(SwingConstants.LEFT);
		txtPhone.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		txtPhone.setColumns(10);
		txtPhone.setBounds(82, 96, 180, 30);
		panel_2.add(txtPhone);
		
		JLabel lbln = new JLabel("급여");
		lbln.setHorizontalAlignment(SwingConstants.CENTER);
		lbln.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		lbln.setBounds(0, 222, 80, 30);
		panel_2.add(lbln);
		
		txtPay = new JTextField();
		txtPay.setText((String) null);
		txtPay.setHorizontalAlignment(SwingConstants.LEFT);
		txtPay.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		txtPay.setColumns(10);
		txtPay.setBounds(82, 222, 180, 30);
		panel_2.add(txtPay);
		
		txtAddress = new JTextField();
		txtAddress.setText((String) null);
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
		txtAge.setText("0");
		txtAge.setHorizontalAlignment(SwingConstants.LEFT);
		txtAge.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		txtAge.setColumns(10);
		txtAge.setBounds(82, 54, 180, 30);
		panel_2.add(txtAge);
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(2, 0, 280, 60);
		contentPane.add(panel_1);
		
		JLabel lblUpdate = new JLabel("강사 정보 수정/삭제");
		lblUpdate.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpdate.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 20));
		lblUpdate.setBounds(40, 5, 200, 50);
		panel_1.add(lblUpdate);
		
		
		//선택한 내용 미리 set해놓기
//		txtName,txtPhone,txtPay,txtAddress,txtAge;
		txtName.setText(tVO.getName());
		txtAge.setText(tVO.getAge()+"");
		txtPhone.setText(tVO.getPhone());
		txtAddress.setText(tVO.getAddress());
		txtPay.setText(tVO.getPay()+"");
		
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
		
		
		
		/*-------------------------------------------------*/
		


		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = txtName.getText();
				String age = txtAge.getText();
				String phone = txtPhone.getText();
				String address = txtAddress.getText();
				String choiceCur =cbCur.getSelectedItem()+"";
				String pay = txtPay.getText();
				
				if(name.trim().equals("")) {
					JOptionPane.showMessageDialog(null, "성명을 입력하세요");
					txtName.requestFocus();
				} else if (age.trim().equals("")) {
					JOptionPane.showMessageDialog(null, "나이를 입력하세요");
					txtAge.requestFocus();
				} else if(!Pattern.matches("^[0-9]*$", age)) {
					JOptionPane.showMessageDialog(null, "나이는 숫자로 입력하세요");
					txtAge.requestFocus();
				} else if(!Pattern.matches("^[0-9]*$", phone)) {
					JOptionPane.showMessageDialog(null, "번호는 숫자로 입력하세요(-제외 11자리)");
					txtPhone.requestFocus();
				} else if(!Pattern.matches("^[0-9]*$", pay)) {
					JOptionPane.showMessageDialog(null, "급여는 숫자로 입력하세요");
					txtPay.requestFocus();
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
					res=dao.setTeaUpdate(tVO);
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
		

		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				res=dao.teaDelete(tVO);
				if(res==0) {
					JOptionPane.showMessageDialog(null, "정보 삭제에 실패했습니다.");
					txtName.requestFocus();
				} else {
					JOptionPane.showMessageDialog(null, "정보 삭제를 완료했습니다");
					dispose();
				}
			}
		});
		
		//종료버튼
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			dispose();
			}
		});
	}
}
