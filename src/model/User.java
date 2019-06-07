package model;

public class User {
	private int idUser;
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private String address;
	private String paymentPlan;
	private int strike;

	public User (int idUser, String fn, String ln, String pass, String email,
			String address, String paymentPlan, int strike) {
		setIdUser(idUser);
		setFirstName(fn);
		setLastName(ln);
		setPassword(pass);
		setEmail(email);
		setaddress(address);
		setPaymentPlan(paymentPlan);
		setStrike(strike);
	}
	
	public void setIdUser(int id) {
		this.idUser = id;
	}
	
	public void setFirstName(String fn) {
		this.firstName = fn;
	}
	
	public void setLastName(String ln) {
		this.lastName = ln;
	}
	
	public void setPassword(String pass) {
		this.password = pass;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setaddress(String ad) {
		this.address = ad;
	}
	
	public void setPaymentPlan(String paymentPlan) {
		this.paymentPlan = paymentPlan;
	}
	
	public void setStrike(int s) {
		this.strike = s;
	}
	
	public int getIdUser() {
		return idUser;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getaddress() {
		return address;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPaymentPlan() {
		return paymentPlan;
	}
	
	public int getStrike() {
		return strike;
	}
}
