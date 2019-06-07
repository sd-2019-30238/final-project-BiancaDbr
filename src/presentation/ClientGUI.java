package presentation;



import javax.swing.JRadioButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import bll.CarBLL;
import bll.RentManBLL;
import bll.UserBLL;
import dao.CarDAO;
import model.Car;
import model.CarObserver;
import model.RentMan;
import model.User;

import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
import java.awt.event.ActionEvent;
import java.awt.Font;

@SuppressWarnings("serial")
public class ClientGUI extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private static JFrame new_frame = new JFrame();
	private static JFrame new_ = new JFrame();

	/**
	 * Create the frame.
	 */
	public ClientGUI(int i) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 609, 666);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnBorrowBook = new JButton("Rent Car");
		btnBorrowBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.addMouseListener(new MouseAdapter() {
				    public void mouseClicked(final MouseEvent e) {
				        if (e.getClickCount() == 1) {	
				            final JTable jTable= (JTable)e.getSource();
				            final int row = jTable.getSelectedRow();
				            final int column = jTable.getSelectedColumn();
				            final int valueInCell = (int)jTable.getValueAt(row, column);
				            Car b = CarBLL.findCarById(valueInCell);
							openRegisterFrame(b, i);
				        }
				    }
				});
			}
		});
		
		
		btnBorrowBook.setBounds(92, 524, 176, 41);
		contentPane.add(btnBorrowBook);

		
		JButton btnreturn = new JButton("Return Car");
		btnreturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.addMouseListener(new MouseAdapter() {
				    public void mouseClicked(final MouseEvent e) {
				        if (e.getClickCount() == 1) {	
				            final JTable jTable= (JTable)e.getSource();
				            final int row = jTable.getSelectedRow();
				            final int column = jTable.getSelectedColumn();
				            final int valueInCell = (int)jTable.getValueAt(row, column);
				           // RentMan b = RentManBLL.findByRentManId(valueInCell);
							//RentManBLL.deleteRent(b.getIdRentManagement());
				            returnFrame(valueInCell);
				        }
				    }
				});
			}
		});
		
		
		btnreturn.setBounds(348, 524, 176, 41);
		contentPane.add(btnreturn);

		
		JRadioButton chckbxBrand = new JRadioButton("Brand");
		chckbxBrand.setBounds(98, 24, 75, 41);
		contentPane.add(chckbxBrand);
		
		JRadioButton chckbxColor = new JRadioButton("Color");
		chckbxColor.setBounds(197, 24, 75, 41);
		contentPane.add(chckbxColor);
		
		textField = new JTextField();
		textField.setBounds(294, 32, 164, 25);
		contentPane.add(textField);
		textField.setColumns(10);
		
		
		JButton btnFilter = new JButton("Available Cars");
		btnFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		    	CarBLL b= new CarBLL();
				ArrayList<Object> list= new ArrayList<Object>();
				list.addAll(b.selectAvailability());
				table=MakeTable.createTable(list);
				table.setBounds(26, 321, 518, 175);
				contentPane.add(table);
				table.repaint();
			}
		});
		btnFilter.setBounds(192, 198, 206, 41);
		contentPane.add(btnFilter);
		
		
		JButton button = new JButton("View All Cars");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CarBLL b= new CarBLL();
				ArrayList<Object> list= new ArrayList<Object>();
				list.addAll(b.selectAllCars());

				table=MakeTable.createTable(list);
				table.setBounds(26, 321, 518, 175);
				contentPane.add(table);
				table.repaint();

			}
		});
		button.setBounds(313, 257, 206, 41);
		contentPane.add(button);
		
		JButton button_1 = new JButton("View Rented Cars");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RentManBLL b= new RentManBLL();
				ArrayList<Object> list= new ArrayList<Object>();
				list.addAll(b.selectRented(i));

				table=MakeTable.createTable(list);
				table.setBounds(26, 321, 518, 175);
				contentPane.add(table);
				table.repaint();

			}
		});
		button_1.setBounds(80, 257, 206, 41);
		contentPane.add(button_1);
		
		
		JButton btnNewButton = new JButton("Filter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { 
				
				String s = textField.getText();
                if(chckbxBrand.isSelected()) {
                	CarBLL b= new CarBLL();
    				ArrayList<Object> list= new ArrayList<Object>();
    				list.addAll(b.selectBrand(s));

    				table=MakeTable.createTable(list);
    				table.setBounds(26, 321, 518, 175);
    				contentPane.add(table);
    				table.repaint();
			    } else if(chckbxColor.isSelected()) {
			    	CarBLL b= new CarBLL();
    				ArrayList<Object> list= new ArrayList<Object>();
    				list.addAll(b.selectColor(s));

    				table=MakeTable.createTable(list);
    				table.setBounds(26, 321, 518, 175);
    				contentPane.add(table);
    				table.repaint();
			    }
			} 
	});
		btnNewButton.setBounds(482, 24, 97, 41);
		contentPane.add(btnNewButton);
		
		User u = UserBLL.findById(i);
		String ss = u.getStrike() + " strikes";
		
		JLabel lblViewRecommendedBooks = new JLabel(ss);
		lblViewRecommendedBooks.setFont(new Font("Tahoma", Font.PLAIN, 36));
		lblViewRecommendedBooks.setBounds(227, 111, 274, 36);
		contentPane.add(lblViewRecommendedBooks);
		
	}
	
	public void openRegisterFrame(Car b, int i)
	{
		new_frame.setDefaultCloseOperation(3);
	    new_frame.setTitle("Borrow");
	    new_frame.setSize(400, 300);
	    new_frame.setVisible(true);
	    new_frame.getContentPane().setLayout(null);
	    
	    JLabel lblgenre = new JLabel("Are you sure you want to rent the car ");
		lblgenre.setBounds(65, 52, 255, 33);
		new_frame.getContentPane().add(lblgenre);
		
		JLabel lblg = new JLabel(b.getBrand() + " in the color " + b.getColor() + "?");
		lblg.setBounds(65, 92, 355, 33);
		new_frame.getContentPane().add(lblg);
	    
	    JButton btnRegister = new JButton("Yes");
		btnRegister.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) { 
				 int idRM = ThreadLocalRandom.current().nextInt(1000, 100000 + 1);
		         RentMan o = RentManBLL.findByCarId(b.getIdCar());
	             Date date = Calendar.getInstance().getTime();  
	             DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");  
	             String strDate = dateFormat.format(date);
	             String[] parts = strDate.split("-");
	             
	             CarObserver obs = new CarObserver();
	     		 b.addObserver(obs);
	     		 
	             int rd_mm = Integer.parseInt(parts[1]);
	             if(rd_mm==12) {
	            	 rd_mm = 0;
	             } else rd_mm += 1;
	             System.out.println(rd_mm);
		         String bd = strDate;
		         String rd = parts[0] + "-" + Integer.toString(rd_mm) + "-" + parts[2];
		         if(o==null) {
		        	 new_frame.setVisible(false);
					 new_frame.dispose();
		        	 RentMan bb = new RentMan(idRM, b.getIdCar(), i, bd, rd);
		        	 RentManBLL.insertRent(bb);
		        	 b.setAvailability("unavailable");
		        	 CarDAO.updateAvailability(b.getIdCar(),"unavailable");
		        	 
		         } else {
		        	 new_frame.setVisible(false);
					 new_frame.dispose();
		        	 openFrame2(i,o);
		         } 
				}
		 });
		btnRegister.setBounds(120, 170, 150, 50);
		new_frame.getContentPane().add(btnRegister);
		
	}
	
	public void returnFrame(int i)
	{
		new_frame.setDefaultCloseOperation(3);
	    new_frame.setTitle("Return");
	    new_frame.setSize(400, 300);
	    new_frame.setVisible(true);
	    new_frame.getContentPane().setLayout(null);
	    
	    JLabel lblgenre = new JLabel("Are you sure you want to return the car ");
		lblgenre.setBounds(65, 52, 255, 33);
		new_frame.getContentPane().add(lblgenre);
		
		RentMan b = RentManBLL.findByRentManId(i);
		Car c = CarBLL.findCarById(b.getIdCar());
		
		JLabel lblg = new JLabel(c.getBrand() + " in the color " + c.getColor() + "?");
		lblg.setBounds(65, 92, 355, 33);
		new_frame.getContentPane().add(lblg);
	    
	    JButton btnRegister = new JButton("Yes");
		btnRegister.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				     RentManBLL.deleteRent(b.getIdRentManagement());
		        	 new_frame.removeAll();
					 new_frame.dispose();
				}
		 });
		btnRegister.setBounds(120, 170, 150, 50);
		new_frame.getContentPane().add(btnRegister);
		
	}
	
	public void openFrame2(int id, RentMan bb)
	{
		new_.setDefaultCloseOperation(3);
	    new_.setTitle("Rent");
	    new_.setSize(400, 300);
	    new_.setVisible(true);
	    new_.getContentPane().setLayout(null);
	    
	    JLabel lblgenre = new JLabel("The car is not available at the moment.");
		lblgenre.setBounds(65, 52, 255, 33);
		new_.getContentPane().add(lblgenre);
		
		 JButton btnRegister = new JButton("OK");
			btnRegister.addActionListener(new ActionListener() {
				 public void actionPerformed(ActionEvent e) {
			        	 new_frame.removeAll();
						 new_frame.dispose();
					}
			 });
			btnRegister.setBounds(120, 170, 150, 50);
			new_frame.getContentPane().add(btnRegister);
		
	}
}
