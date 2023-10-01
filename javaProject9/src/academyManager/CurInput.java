package academyManager;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class CurInput extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtCurFee;

	
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
//					CurInput frame = new CurInput();
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
	public CurInput() {
		setTitle("수업정보입력");
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
		panel.setBounds(2, 61, 280, 350);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(5, 8, 270, 270);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		txtName = new JTextField();
		txtName.setHorizontalAlignment(SwingConstants.LEFT);
		txtName.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		txtName.setBounds(70, 30, 194, 30);
		panel_2.add(txtName);
		txtName.setColumns(10);
		
		
		txtCurFee = new JTextField();
		txtCurFee.setHorizontalAlignment(SwingConstants.LEFT);
		txtCurFee.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		txtCurFee.setColumns(10);
		txtCurFee.setBounds(70, 210, 194, 30);
		panel_2.add(txtCurFee);
		
		String[] yy = new String[24];
		String[] mm = new String[12];
		String[] dd = new String[31];
		
		int imsi;
		for(int i=0; i<yy.length; i++) {
			imsi = i + 2023;
			yy[i] = imsi + "";
		}
		for(int i=0; i<mm.length; i++) {
			mm[i] = (i+1) + "";
		}
		for(int i=0; i<dd.length; i++) {
			dd[i] = (i+1) + "";
		}
		
		JComboBox cbCurStartYY = new JComboBox(yy);
		cbCurStartYY.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		cbCurStartYY.setBounds(70, 90, 70, 30);
		panel_2.add(cbCurStartYY);
		
		JComboBox cbCurStartMM = new JComboBox(mm);
		cbCurStartMM.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		cbCurStartMM.setBounds(142, 90, 60, 30);
		panel_2.add(cbCurStartMM);
		
		JComboBox cbCurStartDD = new JComboBox(dd);
		cbCurStartDD.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		cbCurStartDD.setBounds(204, 90, 60, 30);
		panel_2.add(cbCurStartDD);
		
		JComboBox cbCurEndYY = new JComboBox(yy);
		cbCurEndYY.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		cbCurEndYY.setBounds(70, 150, 70, 30);
		panel_2.add(cbCurEndYY);

		JComboBox cbCurEndMM = new JComboBox(mm);
		cbCurEndMM.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		cbCurEndMM.setBounds(142, 150, 60, 30);
		panel_2.add(cbCurEndMM);
		
		JComboBox cbCurEndDD = new JComboBox(dd);
		cbCurEndDD.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		cbCurEndDD.setBounds(204, 150, 60, 30);
		panel_2.add(cbCurEndDD);
		
		JLabel lblCurName = new JLabel("수업이름");
		lblCurName.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurName.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		lblCurName.setBounds(0, 30, 70, 30);
		panel_2.add(lblCurName);
		
		JLabel lblCurStart = new JLabel("수업 시작일");
		lblCurStart.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurStart.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		lblCurStart.setBounds(0, 90, 70, 30);
		panel_2.add(lblCurStart);
		
		JLabel lblCurEnd = new JLabel("수업 종료일");
		lblCurEnd.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurEnd.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		lblCurEnd.setBounds(0, 150, 70, 30);
		panel_2.add(lblCurEnd);
		
		JLabel lblCurFee = new JLabel("수업료");
		lblCurFee.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurFee.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		lblCurFee.setBounds(0, 210, 70, 30);
		panel_2.add(lblCurFee);
		
		JButton btnInsert = new JButton("등  록");
		btnInsert.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		btnInsert.setBounds(33, 288, 90, 30);
		panel.add(btnInsert);
		
		JButton btnExit = new JButton("취  소");
		btnExit.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		btnExit.setBounds(156, 288, 90, 30);
		panel.add(btnExit);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(2, 0, 280, 60);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblInsert = new JLabel("수업 정보 입력");
		lblInsert.setHorizontalAlignment(SwingConstants.CENTER);
		lblInsert.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 20));
		lblInsert.setBounds(65, 5, 150, 50);
		panel_1.add(lblInsert);
		
/*===================================버튼액션===========================================*/
		
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
//				String name = txtName.getText();
				String curStart= cbCurStartYY.getSelectedItem()+"-"+cbCurStartMM.getSelectedItem()+"-"+cbCurStartDD.getSelectedItem();
				String curEnd= cbCurEndYY.getSelectedItem()+"-"+cbCurEndMM.getSelectedItem()+"-"+cbCurEndDD.getSelectedItem();
				String fee = txtCurFee.getText();
				if(fee==null) fee="0";
				
				
				////////////////*!!!!날짜비교해야함!!!*/////////////////////////////
				int sYY,sMM,sDD,eYY,eMM,eDD;
				sYY=Integer.parseInt(cbCurStartYY.getSelectedItem()+"");
				sMM=Integer.parseInt(cbCurStartMM.getSelectedItem()+"");
				sDD=Integer.parseInt(cbCurStartDD.getSelectedItem()+"");
				eYY=Integer.parseInt(cbCurEndYY.getSelectedItem()+"");
				eMM=Integer.parseInt(cbCurEndMM.getSelectedItem()+"");
				eDD=Integer.parseInt(cbCurEndDD.getSelectedItem()+"");
				LocalDate date1 =LocalDate.of(sYY, sMM, sDD);			
				LocalDate date2 =LocalDate.of(eYY, eMM, eDD);
				//date2가 date1보다 빠른날짜면 -1,같은날짜면 0, 이후날짜면 1 
				int result = date1.compareTo(date2);
				///////////////////////////////////////////////////////////
				
				if(txtName.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "수업명을 입력하세요");
					txtName.requestFocus();
				} else if(result==1||result==0) {
					JOptionPane.showMessageDialog(null, "수업종료일은 수업시작일보다 이후의 날짜를 선택해주세요");
				} else if(!Pattern.matches("^[0-9]*$", fee)) {
					JOptionPane.showMessageDialog(null, "수업료는 숫자로 입력해주세요.");
					txtCurFee.requestFocus();
				} else {
					
					cVO.setName(txtName.getText());
					cVO.setCurriculumStart(curStart);
					cVO.setCurriculumEnd(curEnd);
					cVO.setFee(Integer.parseInt(fee));
					
					res=dao.setCurInput(cVO);
					
					if(res==0) {
						JOptionPane.showMessageDialog(null, "수업 정보 입력에 실패했습니다.");
					} else {
						JOptionPane.showMessageDialog(null, "수업 정보가 입력되었습니다.");
						dispose();
					}
				}
				
			}
		});
		
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		
	}
}
