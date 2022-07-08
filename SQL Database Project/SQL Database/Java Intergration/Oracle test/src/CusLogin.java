import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowStateListener;
import java.awt.event.WindowEvent;
import javax.swing.JTable;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

public class CusLogin extends JFrame {

	private JPanel contentPane;
	private final JMenuBar menuBar = new JMenuBar();
	private final JMenuItem mntmHome = new JMenuItem("Home");
	private final JMenuItem mntmOrders = new JMenuItem("Orders");
	private final JMenuItem mntmLogOut = new JMenuItem("LogOut");
	private final JTextField textEmail = new JTextField();
	jdbc_test hello = new jdbc_test();
	private final JTable table = new JTable();
	private final JLabel lblNewLabel_1 = new JLabel("Email:");
	private final JLabel lblNewLabel_1_1 = new JLabel("Password:");
	private final JLabel lblNewLabel_1_2 = new JLabel("First Name:");
	private final JLabel lblNewLabel_1_2_1 = new JLabel("Last Name:");
	private final JTextField FName = new JTextField();
	private final JTextField LName = new JTextField();
	private final JTextField Email = new JTextField();
	private final JTextField Password = new JTextField();
	private final JLabel lblNewLabel_2 = new JLabel("Basic Information");
	private final JLabel Country = new JLabel("Country:");
	private final JLabel s = new JLabel("State:");
	private final JLabel AddressLine1 = new JLabel("Address Line 1:");
	private final JLabel lblNewLabel_1_2_1_1 = new JLabel("City:");
	private final JTextField Address = new JTextField();
	private final JTextField City = new JTextField();
	private final JTextField C_Country = new JTextField();
	private final JTextField State = new JTextField();
	private final JLabel lblNewLabel_2_1 = new JLabel("Shipping Information");
	private final JLabel p = new JLabel("Postcode/ ZIP:");
	private final JTextField PostalCode = new JTextField();
	private final JLabel lblNewLabel_3 = new JLabel("Middle Initial:");
	private final JTextField MiddleInitial = new JTextField();
	private final JLabel lblNewLabel = new JLabel("User ID:");
	private final JTextField UserID = new JTextField();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CusLogin frame = new CusLogin();
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
	public CusLogin() {
		textEmail.setBounds(10, 61, 155, 20);
		textEmail.setColumns(10);
		initGUI();
	}
	private void initGUI() {
		ResultSet rs =hello.get_user_info(Startup.Email, Startup.Password);
		try {
			rs.next();
			textEmail.setText(rs.getString("email"));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		List<String> columns = new ArrayList<String>();
		columns.add("Package_id");
		columns.add("Last_distro_id");
		columns.add("Shipping_adress");
		columns.add("Shipping_City");
		columns.add("Shipping_Zip");
		columns.add("Shipping_country");
		columns.add("Out_for_del");
		columns.add("status");
		
		
		List<String[]> values = new ArrayList<String[]>();

		 for (int i = 0; i < 100; i++) {
	            values.add(new String[] {"val"+i+" col1","val"+i+" col2","val"+i+" col3"});
	        }
	      TableModel tableModel = new DefaultTableModel(values.toArray(new Object[][] {}), columns.toArray());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 519, 343);
		
		setJMenuBar(menuBar);
		mntmHome.setBackground(Color.WHITE);
		mntmHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				mntmHome.setBackground(Color.LIGHT_GRAY);
			}
			public void mouseExited(MouseEvent e) {
				mntmHome.setBackground(Color.white);
			}
		});
		
		menuBar.add(mntmHome);
		mntmOrders.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				mntmOrders.setBackground(Color.LIGHT_GRAY);
			}
			public void mouseExited(MouseEvent e) {
				mntmOrders.setBackground(Color.white);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				int account_id = new jdbc_test().get_acc_id(Startup.Email, Startup.Password);
				new Orders().setVisible(true);
				
			}
		});
		mntmOrders.setBackground(Color.WHITE);
		
		menuBar.add(mntmOrders);
		mntmLogOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				mntmLogOut.setBackground(Color.LIGHT_GRAY);
			}
			public void mouseExited(MouseEvent e) {
				mntmLogOut.setBackground(Color.white);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				Startup startup1 = new Startup();
				startup1.setVisible(true);
				dispose();
			}
		});
		mntmLogOut.setBackground(Color.WHITE);
		
		menuBar.add(mntmLogOut);
		contentPane = new JPanel();
		contentPane.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				int account_id = new jdbc_test().get_acc_id(Startup.Email, Startup.Password);
				System.out.println(account_id);
			}
		});
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(11, 152, 45, 21);
		
		contentPane.add(lblNewLabel_1);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_1.setBounds(11, 184, 68, 21);
		
		contentPane.add(lblNewLabel_1_1);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_2.setBounds(11, 61, 68, 21);
		
		contentPane.add(lblNewLabel_1_2);
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_2_1.setBounds(11, 125, 68, 21);
		
		contentPane.add(lblNewLabel_1_2_1);
		FName.setColumns(10);
		FName.setBounds(86, 62, 142, 20);
		
		contentPane.add(FName);
		LName.setColumns(10);
		LName.setBounds(86, 126, 142, 20);
		
		contentPane.add(LName);
		Email.setColumns(10);
		Email.setBounds(52, 153, 142, 20);
		
		contentPane.add(Email);
		Password.setColumns(10);
		Password.setBounds(86, 185, 142, 20);
		
		contentPane.add(Password);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(62, 36, 122, 14);
		
		contentPane.add(lblNewLabel_2);
		Country.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Country.setBounds(279, 120, 57, 21);
		
		contentPane.add(Country);
		s.setFont(new Font("Tahoma", Font.PLAIN, 13));
		s.setBounds(279, 152, 39, 21);
		
		contentPane.add(s);
		AddressLine1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		AddressLine1.setBounds(279, 61, 89, 21);
		
		contentPane.add(AddressLine1);
		lblNewLabel_1_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_2_1_1.setBounds(280, 93, 38, 21);
		
		contentPane.add(lblNewLabel_1_2_1_1);
		Address.setColumns(10);
		Address.setBounds(378, 62, 142, 20);
		
		contentPane.add(Address);
		City.setColumns(10);
		City.setBounds(313, 94, 142, 20);
		
		contentPane.add(City);
		C_Country.setColumns(10);
		C_Country.setBounds(337, 121, 142, 20);
		
		contentPane.add(C_Country);
		State.setColumns(10);
		State.setBounds(323, 153, 142, 20);
		
		contentPane.add(State);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_1.setBounds(352, 36, 154, 14);
		
		contentPane.add(lblNewLabel_2_1);
		p.setFont(new Font("Tahoma", Font.PLAIN, 13));
		p.setBounds(279, 184, 89, 21);
		
		contentPane.add(p);
		PostalCode.setColumns(10);
		PostalCode.setBounds(378, 185, 142, 20);
		
		contentPane.add(PostalCode);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(10, 93, 89, 21);
		
		contentPane.add(lblNewLabel_3);
		MiddleInitial.setColumns(10);
		MiddleInitial.setBounds(96, 93, 25, 20);
		
		contentPane.add(MiddleInitial);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(11, 216, 62, 29);
		
		contentPane.add(lblNewLabel);
		
		contentPane.add(UserID);
		
		
		contentPane.add(textEmail);
	}
}
