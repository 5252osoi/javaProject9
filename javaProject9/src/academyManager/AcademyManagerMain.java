package academyManager;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class AcademyManagerMain extends JFrame {

	private JPanel contentPane;
	private JTextField txtID;
	private JPasswordField txtPwd;
	private JButton btnLogIn,btnExit;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AcademyManagerMain frame = new AcademyManagerMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AcademyManagerMain() {
		setTitle("학원관리자");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(250, 200);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(17, 8, 200, 100);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 70, 100);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblID = new JLabel("ID");
		lblID.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 14));
		lblID.setHorizontalAlignment(SwingConstants.CENTER);
		lblID.setBounds(0, 13, 70, 30);
		panel_1.add(lblID);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 14));
		lblPassword.setBounds(0, 56, 70, 30);
		panel_1.add(lblPassword);
		
		txtID = new JTextField();
		txtID.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 14));
		txtID.setBounds(70, 13, 120, 30);
		panel.add(txtID);
		txtID.setColumns(10);
		
		txtPwd = new JPasswordField();
		txtPwd.setBounds(70, 56, 120, 30);
		panel.add(txtPwd);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(17, 116, 200, 35);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		btnLogIn = new JButton("로그인");
		btnLogIn.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		btnLogIn.setBounds(0, 0, 90, 35);
		panel_2.add(btnLogIn);
		
		btnExit = new JButton("작업종료");
		btnExit.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		btnExit.setBounds(110, 0, 90, 35);
		panel_2.add(btnExit);
	/*-----------------------------------------------------------------------------------*/
		
		//로그인 버튼 누르고 관리자아이디인지 체크///(기본 admin,1234)
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id =txtID.getText();
				 @SuppressWarnings("deprecation")
				String pwd =txtPwd.getText();
				 AcademyManagerDAO dao=new AcademyManagerDAO();
				 IdentifyVO iVO = dao.getAdminCheck(id);
				 if(iVO.getId()==null) {
					 JOptionPane.showMessageDialog(null, "존재하지 않는 ID입니다.");
					 txtID.setText("");
					 txtPwd.setText("");
					 txtID.requestFocus();
				 } else if(!iVO.getPassword().equals(pwd)) {
					 JOptionPane.showMessageDialog(null, "틀린 비밀번호입니다.");
					 txtPwd.setText("");
					 txtPwd.requestFocus();
				 } else {
					 setVisible(false);
					 new AcademyAdmin();
				 }
			}
		});
		
		//작업종료 버튼 작동
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	
	}
}
