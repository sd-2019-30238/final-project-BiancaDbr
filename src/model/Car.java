package model;

import java.util.Observable;

public class Car  extends Observable{
	private int idCar;
	private String brand;
	private String color;
	private int year;
	private String availability;

	public Car (int idUser, String b, String c, int y, String a) {
		setIdCar(idUser);
		setBrand(b);
		setColor(c);
		setYear(y);
		setAvailability(a);
	}
	
	public void setIdCar(int id) {
		this.idCar = id;
	}
	
	public void setBrand(String fn) {
		this.brand = fn;
	}
	
	public void setColor(String ln) {
		this.color = ln;
	}
	
	public void setYear(int pass) {
		this.year = pass;
	}
	
	public void setAvailability(String email) {
		this.availability = email;
        setChanged();
        notifyObservers(availability);
	}
	
	
	public int getIdCar() {
		return idCar;
	}
	
	public String getBrand() {
		return brand;
	}
	
	public String getColor() {
		return color;
	}
	
	public int getYear() {
		return year;
	}
	
	public String getAvailability() {
		return availability;
	}
}