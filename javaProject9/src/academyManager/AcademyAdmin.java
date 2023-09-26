package academyManager;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JMenuBar;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class AcademyAdmin extends JFrame {

	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JPanel tabCur;
	private JPanel tabStu;
	private JPanel tabTea;
	private JPanel panel;

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
		
		tabCur = new JPanel();
		tabbedPane.addTab("수업관리", null, tabCur, null);
		tabCur.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 975, 68);
		tabCur.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 78, 975, 368);
		tabCur.add(panel_1);
		
		tabStu = new JPanel();
		tabbedPane.addTab("학생관리", null, tabStu, null);
		
		tabTea = new JPanel();
		tabbedPane.addTab("강사관리", null, tabTea, null);
	}
}
