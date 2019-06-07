package presentation;

import java.util.concurrent.ThreadLocalRandom;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bll.UserBLL;
import model.User;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class RegisterGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;


	/**
	 * Create the frame.
	 */
	public RegisterGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 464, 564);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(184, 28, 236, 39);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(184, 77, 236, 39);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(184, 131, 236, 39);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(184, 182, 236, 39);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(184, 230, 236, 39);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lbltitle = new JLabel("First Name");
		lbltitle.setBounds(26, 31, 132, 33);
		contentPane.add(lbltitle);
		
		JLabel lblauthor = new JLabel("Last Name");
		lblauthor.setBounds(26, 80, 132, 33);
		contentPane.add(lblauthor);
		
		JLabel lblreleaseDate = new JLabel("Password");
		lblreleaseDate.setBounds(26, 134, 115, 33);
		contentPane.add(lblreleaseDate);
		
		JLabel lblgenre = new JLabel("Email");
		lblgenre.setBounds(26, 185, 115, 33);
		contentPane.add(lblgenre);
		
		JLabel lblavailability = new JLabel("Address");
		lblavailability.setBounds(26, 233, 115, 33);
		contentPane.add(lblavailability);
		
		JLabel lblPaymentPlan = new JLabel("Payment Plan");
		lblPaymentPlan.setBounds(138, 284, 161, 33);
		contentPane.add(lblPaymentPlan);
		
		JRadioButton rdbtnMonthly = new JRadioButton("Monthly");
		rdbtnMonthly.setBounds(22, 331, 138, 41);
		contentPane.add(rdbtnMonthly);
		
		JRadioButton rdbtnYearly = new JRadioButton("Yearly");
		rdbtnYearly.setBounds(272, 331, 138, 41);
		contentPane.add(rdbtnYearly);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setBounds(128, 398, 171, 62);
		contentPane.add(btnRegister);
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    String pp;
			    if(rdbtnMonthly.isSelected()) {
			    	pp = "monthly";
			    } else {
			    	pp = "weekly";
			    }
			    int id = ThreadLocalRandom.current().nextInt(1000, 100000 + 1);
			    String fn = textField.getText();
			    String ln = textField_1.getText();
			    String pass = textField_2.getText();
			    String email = textField_3.getText();
			    String address = textField_4.getText();
			    int strike = 0;
			    User u = new User(id, fn, ln, pass, email, address, pp, strike);
			    UserBLL.insertUser(u, email);
			    MainGUI a = new MainGUI();
				a.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
	}
}
