package presentation;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bll.CarBLL;
import bll.UserBLL;

import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class StaffGUI extends JFrame {

	private JPanel contentPane;
	private JTable table;


	/**
	 * Create the frame.
	 */
	public StaffGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 659, 516);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnAddBook = new JButton("Add Car");
		btnAddBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddCarGUI a = new AddCarGUI();
			    a.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		btnAddBook.setBounds(24, 38, 184, 63);
		contentPane.add(btnAddBook);
		
		
		JButton btnViewAllBooks = new JButton("View All Cars");
		btnViewAllBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CarBLL b= new CarBLL();
				ArrayList<Object> list= new ArrayList<Object>();
				list.addAll(b.selectAllCars());
				
				table=MakeTable.createTable(list);
				table.setBounds(221, 28, 430, 262);
				contentPane.add(table);
				table.repaint(); 
			}
		});
		btnViewAllBooks.setBounds(307, 331, 193, 57);
		contentPane.add(btnViewAllBooks);
		
		JButton btnViewUsers = new JButton("View Users");
		btnViewUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserBLL b= new UserBLL();
				ArrayList<Object> list= new ArrayList<Object>();
				list.addAll(b.selectUsers());
				
				table=MakeTable.createTable(list);
				table.setBounds(221, 28, 390, 262);
				contentPane.add(table);
				table.repaint(); 
			}
		});
		btnViewUsers.setBounds(107, 331, 193, 57);
		contentPane.add(btnViewUsers);
		
		JButton btnDeleteBook = new JButton("Delete Car");
		btnDeleteBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("car deletion");
				table.addMouseListener(new MouseAdapter() {
				    public void mouseClicked(final MouseEvent e) {
				        if (e.getClickCount() == 1) {	
				            final JTable jTable= (JTable)e.getSource();
				            final int row = jTable.getSelectedRow();
				            final int column = jTable.getSelectedColumn();
				            final int valueInCell = (int)jTable.getValueAt(row, column);
				            System.out.println(valueInCell);
				            CarBLL.deleteCar(valueInCell);
				        }
				    }
				});
			}
		});
		btnDeleteBook.setBounds(24, 120, 184, 63);
		contentPane.add(btnDeleteBook);
		
		JButton btnDeleteUser = new JButton("Delete User");
		btnDeleteUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("user deletion");
				table.addMouseListener(new MouseAdapter() {
				    public void mouseClicked(final MouseEvent e) {
				        if (e.getClickCount() == 1) {	
				            final JTable jTable= (JTable)e.getSource();
				            final int row = jTable.getSelectedRow();
				            final int column = jTable.getSelectedColumn();
				            final int valueInCell = (int)jTable.getValueAt(row, column);
				            System.out.println(valueInCell);
				            UserBLL.deleteUser(valueInCell);
				        }
				    }
				});
			}
		});
		btnDeleteUser.setBounds(24, 210, 184, 63);
		contentPane.add(btnDeleteUser);
	}

}
