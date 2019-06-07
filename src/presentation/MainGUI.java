package presentation;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import bll.UserBLL;
import model.User;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class MainGUI extends JFrame {

	
	
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField = new JPasswordField(20);


	/**
	 * Create the frame.
	 */
	public MainGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 515, 413);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setBounds(302, 10, 171, 41);
		panel.add(btnRegister);
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    RegisterGUI a = new RegisterGUI();
			    a.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(152, 246, 171, 41);
		panel.add(btnLogin);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String email = textField.getText();
				String password = new String(passwordField.getPassword());
				String s1 = "admin";
				String s2 = "0000";
				if (s1.equals(email) && s2.equals(password)) {
					StaffGUI a = new StaffGUI();
				    a.setVisible(true);
					setVisible(false);
					dispose();
				}else {
					User o = UserBLL.findUserByEmail(email);
					if (o == null || !o.getPassword().equals(password)) {
						JOptionPane.showMessageDialog(contentPane, "Wrong email or password! Try again!", "Error", JOptionPane.ERROR_MESSAGE);
					}else {
						ClientGUI a = new ClientGUI(o.getIdUser());
					    a.setVisible(true);
						setVisible(false);
						dispose(); 
					}
				}
			}
		});
		
		textField = new JTextField();
		textField.setBounds(211, 89, 236, 39);
		panel.add(textField);
		textField.setColumns(10);
		
		passwordField.setBounds(211, 156, 236, 39);
		panel.add(passwordField);
		passwordField.setColumns(10);
		
		JLabel lblgenre = new JLabel("Email");
		lblgenre.setBounds(55, 92, 115, 33);
		panel.add(lblgenre);
		
		JLabel lblreleaseDate = new JLabel("Password");
		lblreleaseDate.setBounds(55, 159, 115, 33);
		panel.add(lblreleaseDate);
	}
}
