package bll;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import dao.UserDAO;
import model.User;

public class UserBLL {
	public static User findUserByEmail(String email) {
		User o = UserDAO.findByEmail(email);
		return o;
	}

	public static int insertUser(User o, String email) {
		User u = UserDAO.findByEmail(email);
        if (u == null) {
        	return UserDAO.insert(o);
		}else {
			throw new NoSuchElementException("The user with email =" + email + " already has an account!");
		}
	}
	
	public static User findById(int i) {
		User o = UserDAO.findById(i);
		return o;
	}
	
	public static void deleteUser(int idUser){
		User u = UserDAO.findById(idUser);
        if (u == null) {
			throw new NoSuchElementException("There is no such user!");
		}else {
			UserDAO.delete(idUser);
		}
	}
	
	public ArrayList<User> selectUsers(){
		return UserDAO.select();
	}
}
