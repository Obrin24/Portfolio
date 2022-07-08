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
	private final JScrollPane scrollPane = new JScrollPane();
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
	      JTable table = new JTable(tableModel);
	      table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	      table.setFillsViewportHeight(true);
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
		
		
		contentPane.add(textEmail);
		scrollPane.setViewportBorder(UIManager.getBorder("DesktopIcon.border"));
		scrollPane.setBounds(175, 59, 320, 211);
		
		contentPane.add(scrollPane);
		table.setFillsViewportHeight(true);
		scrollPane.setRowHeaderView(table);
	}
}
