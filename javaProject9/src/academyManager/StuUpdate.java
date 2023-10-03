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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StuUpdate extends JFrame {

	private JPanel contentPane;
	private JTextField txtName,txtSchool,txtPhone,txtFamphone,txtAddress,txtAge;
	private JComboBox cbCur;
	
	AcademyManagerDAO dao = new AcademyManagerDAO();
	CurriculumVO cVO = new CurriculumVO();
	int res=0;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					StuUpdate frame = new StuUpdate();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 * @param sVO 
	 */
	public StuUpdate(StudentVO sVO) {
		setTitle("학생정보수정");
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
		txtName.setHorizontalAlignment(SwingConstants.LEFT);
		txtName.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		txtName.setColumns(10);
		txtName.setBounds(82, 7, 180, 30);
		panel_2.add(txtName);
		
		txtSchool = new JTextField();
		txtSchool.setHorizontalAlignment(SwingConstants.LEFT);
		txtSchool.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		txtSchool.setColumns(10);
		txtSchool.setBounds(82, 192, 180, 30);
		panel_2.add(txtSchool);
		
		JLabel lblCurName = new JLabel("이름");
		lblCurName.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurName.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		lblCurName.setBounds(0, 7, 80, 30);
		panel_2.add(lblCurName);
		
		JLabel lblCurStart = new JLabel("수강과목");
		lblCurStart.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurStart.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		lblCurStart.setBounds(0, 229, 80, 30);
		panel_2.add(lblCurStart);
		
		JLabel lblCurFee = new JLabel("학교");
		lblCurFee.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurFee.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		lblCurFee.setBounds(0, 192, 80, 30);
		panel_2.add(lblCurFee);
		
		JLabel lblCurName_1 = new JLabel("나이");
		lblCurName_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurName_1.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		lblCurName_1.setBounds(0, 44, 80, 30);
		panel_2.add(lblCurName_1);
		
		JLabel lblCurFee_1 = new JLabel("연락처");
		lblCurFee_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurFee_1.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		lblCurFee_1.setBounds(0, 81, 80, 30);
		panel_2.add(lblCurFee_1);
		
		txtPhone = new JTextField();
		txtPhone.setHorizontalAlignment(SwingConstants.LEFT);
		txtPhone.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		txtPhone.setColumns(10);
		txtPhone.setBounds(82, 81, 180, 30);
		panel_2.add(txtPhone);
		
		JLabel lbln = new JLabel("보호자 연락처");
		lbln.setHorizontalAlignment(SwingConstants.CENTER);
		lbln.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		lbln.setBounds(0, 118, 80, 30);
		panel_2.add(lbln);
		
		txtFamphone = new JTextField();
		txtFamphone.setHorizontalAlignment(SwingConstants.LEFT);
		txtFamphone.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		txtFamphone.setColumns(10);
		txtFamphone.setBounds(82, 118, 180, 30);
		panel_2.add(txtFamphone);
		
		txtAddress = new JTextField();
		txtAddress.setHorizontalAlignment(SwingConstants.LEFT);
		txtAddress.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		txtAddress.setColumns(10);
		txtAddress.setBounds(82, 155, 180, 30);
		panel_2.add(txtAddress);
		
		JLabel lblCurFee_2 = new JLabel("주소");
		lblCurFee_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurFee_2.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		lblCurFee_2.setBounds(0, 155, 80, 30);
		panel_2.add(lblCurFee_2);
		
		txtAge = new JTextField();
		txtAge.setHorizontalAlignment(SwingConstants.LEFT);
		txtAge.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		txtAge.setColumns(10);
		txtAge.setBounds(82, 44, 180, 30);
		panel_2.add(txtAge);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(2, 0, 280, 60);
		contentPane.add(panel_1);
		
		JLabel lblUpdate = new JLabel("학생 정보 수정/삭제");
		lblUpdate.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpdate.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 20));
		lblUpdate.setBounds(40, 5, 200, 50);
		panel_1.add(lblUpdate);
		
		
		//선택한 내용 미리 set해놓기
//		txtName,txtSchool,txtPhone,txtFamphone,txtAddress,txtAge;
		txtName.setText(sVO.getName());
		txtAge.setText(sVO.getAge()+"");
		txtPhone.setText(sVO.getPhone());
		txtFamphone.setText(sVO.getFamphone());
		txtAddress.setText(sVO.getAddress());
		txtSchool.setText(sVO.getSchool());
		
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
		cbCur.setBounds(82, 229, 180, 30);
		panel_2.add(cbCur);
		
		/*=-=-=-=-=-=-=-=-=-=-=-=-버튼액션-=-=-=-=-=-=-=-=-=-=-=-=*/
		

		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = txtName.getText();
				String age = txtAge.getText();
				String phone = txtPhone.getText();
				String famPhone = txtFamphone.getText();
				String address = txtAddress.getText();
				String school = txtSchool.getText();
				String choiceCur =cbCur.getSelectedItem()+"";
				
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
				} else if(!Pattern.matches("^[0-9]*$", famPhone)) {
					JOptionPane.showMessageDialog(null, "번호는 숫자로 입력하세요(-제외 11자리)");
					txtFamphone.requestFocus();
				} else {
//					StudentVO vo = new StudentVO();
					sVO.setName(name);
					sVO.setAge(Integer.parseInt(age));
					sVO.setPhone(phone);
					sVO.setFamphone(famPhone);
					sVO.setAddress(address);
					sVO.setSchool(school);
					//선택한 수업명과 비교해서 수업의 idx 찾기 
					for(int i=0; i<vos.size(); i++) {
						if(choiceCur.equals(curFindIdx[i][0])) {
							sVO.setCurri1(Integer.parseInt(curFindIdx[i][1]));
						}
					}
					res=dao.setStuUpdate(sVO);
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
				
				res=dao.stuDelete(sVO);
				if(res==0) {
					JOptionPane.showMessageDialog(null, "정보 삭제에 실패했습니다.");
					txtName.requestFocus();
				} else {
					JOptionPane.showMessageDialog(null, "정보 삭제를 완료했습니다");
					dispose();
				}
			}
		});
		
		
		//취소버튼
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			dispose();
			}
		});
		
	}

}
