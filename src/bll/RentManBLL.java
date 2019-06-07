package bll;

import java.util.ArrayList;

import dao.RentManDAO;
import model.RentMan;

public class RentManBLL {
	public static RentMan findByRentManId(int idRM) {
		RentMan o = RentManDAO.findByRentManId(idRM);
		return o;
	}
	
	public static RentMan findByCarId(int idC) {
		RentMan o = RentManDAO.findByCarId(idC);
		return o;
	}

	public static int insertRent(RentMan b) {
		return RentManDAO.insert(b);
			
	}
	
	public static int deleteRent(int idRM){
	    return RentManDAO.delete(idRM);
}
	public static int updateRent(int id, int idrm){
		return RentManDAO.update(id, idrm);
	}
	
	public ArrayList<RentMan> selectRented(int i){
		return RentManDAO.findRentedById(i);
	}
}