package bll;

import java.util.ArrayList;

import dao.CarDAO;
import model.Car;

public class CarBLL {
	public static Car findCarById(int idCar) {
		Car o = CarDAO.findById(idCar);
		return o;
	}

	public static int insertCar(Car b) {
		return CarDAO.insert(b);
			
	}

	public static void deleteCar(int idCar){
		CarDAO.delete(idCar);
	}
	
	public static void updateAvailability(int id, String tb){
		CarDAO.updateAvailability(id, tb);
	}
	
	public ArrayList<Car> selectAllCars(){
		return CarDAO.select();
	}
	
	public ArrayList<Car> selectAvailability(){
		return CarDAO.selectAvailability();
	}
	
	public ArrayList<Car> selectBrand(String s){
		return CarDAO.selectBrand(s);
	}
	
	public ArrayList<Car> selectColor(String s){
		return CarDAO.selectColor(s);
	}
}
