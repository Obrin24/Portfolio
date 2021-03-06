import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;

public class Orders extends JFrame {

	private JPanel contentPane;
	private final JScrollPane scrollPane = new JScrollPane();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Orders frame = new Orders();
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
	public Orders() {
		initGUI();
	}
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 952, 315);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		scrollPane.setBounds(0, 0, 928, 268);
		
		contentPane.add(scrollPane);
		
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

		System.out.println("Connect to DB");
	       try {
	//step1 load the driver class
	           Class.forName("oracle.jdbc.driver.OracleDriver");

	//step2 create  the connection object
	           Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/ORCL", "HR", "oracle");
	           	System.out.println("Database successfully connected");
	           
	//step3 create the statement object
	           
	           PreparedStatement st = conn.prepareStatement("select * from Packages where account_id = ?");
	           st.setInt(1,new jdbc_test().get_acc_id(new Startup().Email,new Startup().Password));
	           ResultSet rs = st.executeQuery();
	           
	           while(rs.next())
	           {
	        	   values.add(new String[] {rs.getString("package_id"), rs.getString("last_distro_id"), rs.getString("shipping_adress"),rs.getString("shipping_city"),rs.getString("Shipping_zip"),rs.getString("shipping_country"),rs.getString("out_for_del"),rs.getString("status")});
	        
	           }
	         //step5 close the connection object
	           st.close();
	       	conn.close();
	     
	       }catch(Exception e){System.out.println(e); }	
	       
	
	      TableModel tableModel = new DefaultTableModel(values.toArray(new Object[][] {}), columns.toArray());
	      JTable table = new JTable(tableModel);
	      table.setFillsViewportHeight(true);

		scrollPane.setViewportView(table);
		table.setBounds(169, 110, 560, 115);
	}
}
