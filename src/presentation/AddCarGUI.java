package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.Car;
import bll.CarBLL;

@SuppressWarnings("serial")
public class AddCarGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;


	/**
	 * Create the frame.
	 */
	public AddCarGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 472, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(236, 13, 160, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(236, 51, 160, 22);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(236, 86, 160, 22);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(236, 121, 160, 22);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblTitle = new JLabel("Brand");
		lblTitle.setBounds(89, 16, 56, 16);
		contentPane.add(lblTitle);
		
		JLabel lblAuthor = new JLabel("Color");
		lblAuthor.setBounds(89, 54, 56, 16);
		contentPane.add(lblAuthor);
		
		JLabel lblReleaseDate = new JLabel("Year");
		lblReleaseDate.setBounds(77, 89, 87, 16);
		contentPane.add(lblReleaseDate);
		
		JLabel lblGenre = new JLabel("Availability");
		lblGenre.setBounds(89, 124, 56, 16);
		contentPane.add(lblGenre);
		
		JButton btnAddBook = new JButton("Add Car");
		btnAddBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    int id = ThreadLocalRandom.current().nextInt(1000, 100000 + 1);
			    String brand = textField.getText();
			    String color = textField_1.getText();
			    int year = Integer.parseInt(textField_2.getText());
			    String availability = textField_3.getText();
			    Car u = new Car(id, brand, color, year, availability);
			    CarBLL.insertCar(u);
			    
				StaffGUI a = new StaffGUI();
			    a.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		btnAddBook.setBounds(176, 179, 97, 42);
		contentPane.add(btnAddBook);
	}
}