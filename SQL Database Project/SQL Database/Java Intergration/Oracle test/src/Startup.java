import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class Startup extends JFrame {

	private JPanel contentPane;
	private final JTextField cUser = new JTextField();
	private final JButton btnCustLogin = new JButton("Login!");
	private final JTextField cPass = new JTextField();
	jdbc_test hello = new jdbc_test();
	private final JLabel lblNewLabel = new JLabel("Email:");
	private final JLabel lblNewLabel_1 = new JLabel("Password");
	private final JLabel lblNewLabel_2 = new JLabel("Customer Login");
	private final JLabel lblpassword = new JLabel("");
	//CusLogin c1 = new CusLogin();
	public static String Email = "";
	public static String Password = "";
	private JTextField eUser;
	private JTextField ePass;
	private final JLabel lblpassword_2 = new JLabel("");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Startup frame = new Startup();
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
	public Startup() {
		cPass.setBounds(67, 116, 153, 20);
		cPass.setColumns(10);
		cUser.setBounds(67, 78, 153, 20);
		cUser.setColumns(10);
		initGUI();
	}
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 479, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		contentPane.add(cUser);
		btnCustLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		
		btnCustLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				boolean res = hello.user_login(cUser.getText(),cPass.getText());
				if(res == false) {
					lblpassword.setText("Incorrect Password");
				} else {
					Email = cUser.getText();
					Password = cPass.getText();
					new CusLogin().setVisible(true);
					dispose();
					}
			}
		});
		
		
		btnCustLogin.setBounds(91, 174, 89, 23);
		
		contentPane.add(btnCustLogin);
		
		contentPane.add(cPass);
		lblNewLabel.setBounds(11, 81, 46, 14);
		
		contentPane.add(lblNewLabel);
		lblNewLabel_1.setBounds(11, 119, 46, 14);
		
		contentPane.add(lblNewLabel_1);
		
		
		lblNewLabel_2.setBounds(106, 11, 74, 14);
		
		contentPane.add(lblNewLabel_2);
		
		
		lblpassword.setForeground(Color.RED);
		lblpassword.setBounds(69, 147, 151, 14);
		
		contentPane.add(lblpassword);
		
	
		lblpassword_2.setForeground(Color.RED);
		lblpassword_2.setBounds(106, 11, 74, 14);
		
		contentPane.add(lblpassword_2);
		
		JLabel lblNewLabel_3 = new JLabel("Email:");
		lblNewLabel_3.setBounds(240, 81, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setBounds(240, 119, 46, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("Employee Login");
		lblNewLabel_2_1.setBounds(326, 11, 74, 14);
		contentPane.add(lblNewLabel_2_1);
		
		eUser = new JTextField();
		eUser.setColumns(10);
		eUser.setBounds(291, 78, 153, 20);
		contentPane.add(eUser);
		
		ePass = new JTextField();
		ePass.setColumns(10);
		ePass.setBounds(291, 116, 153, 20);
		contentPane.add(ePass);
		
		JButton btnEmpLogin_1 = new JButton("Login!");
		btnEmpLogin_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				boolean res = hello.user_login(eUser.getText(),ePass.getText());
				if(res == false) {
					lblpassword_2.setText("Incorrect Password");
				} else {
					Email = eUser.getText();
					Password = ePass.getText();
					new CusLogin().setVisible(true);
					dispose();
					}
			}
		});
		btnEmpLogin_1.setBounds(326, 174, 89, 23);
		contentPane.add(btnEmpLogin_1);
		lblpassword_2.setForeground(Color.RED);
		lblpassword_2.setBounds(291, 147, 151, 14);
		
		contentPane.add(lblpassword_2);
		
		JButton SignUp = new JButton("Sign Up");
		SignUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SignUp signUp1 = new SignUp();
				signUp1.setVisible(true);
				dispose();
			}
		});
		SignUp.setBounds(91, 227, 89, 23);
		contentPane.add(SignUp);
		
		JButton SignUp_2 = new JButton("Sign Up");
		SignUp_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SignUp_Emp signUp1 = new SignUp_Emp();
				signUp1.setVisible(true);
				dispose();
			}
		});
		SignUp_2.setBounds(326, 227, 89, 23);
		contentPane.add(SignUp_2);
	}
}
