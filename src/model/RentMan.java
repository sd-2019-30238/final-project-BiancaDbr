package model;


public class RentMan {
	private int idRentManagement;
	private int idCar;
	private int idUser;
	private String borrowDate;
	private String returnDate;

	public RentMan (int idRentMan, int c, int u, String bd, String rd) {
		setIdRentManagement(idRentMan);
		setIdCar(c);
		setIdUser(u);
		setBorrowDate(bd);
		setReturnDate(rd);
	}
	
	public void setIdCar(int id) {
		this.idCar = id;
	}
	
	public void setIdRentManagement(int id) {
		this.idRentManagement = id;
	}
	
	public void setIdUser(int id) {
		this.idUser = id;
	}
	
	public void setBorrowDate(String d) {
		this.borrowDate = d;
	}
	
	public void setReturnDate(String d) {
		this.returnDate = d;
	}
	
	
	public int getIdCar() {
		return idCar;
	}
	
	public int getIdRentManagement() {
		return idRentManagement;
	}
	
	public int getIdUser() {
		return idUser;
	}
	
	public String getBorrowDate() {
		return borrowDate;
	}
	
	public String getReturnDate() {
		return returnDate;
	}
}