package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.jdbc.PreparedStatement;

import connection.DatabaseConnection;
import model.User;

public class UserDAO {
	protected static final Logger LOGGER = Logger.getLogger(UserDAO.class.getName());
	private static final String insertStatementString = "INSERT INTO User (idUser,firstName,lastName,password"
			+ ",email,address,strike,paymentPlan)"
			+ " VALUES (?,?,?,?,?,?,?,?)";
	private final static String findEmailStatementString = "SELECT * FROM User WHERE email = ?";
	private final static String findIdStatementString = "SELECT * FROM User WHERE idUser = ?";
	private final static String deleteStatementString ="DELETE FROM User WHERE idUser = ?";
	private final static String selectStatementString ="SELECT * FROM User WHERE strike>=3";
	
	public static User findByEmail(String email){
		User toReturn= null;

		Connection dbConnection = (Connection) DatabaseConnection.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;

		try {
			findStatement = (PreparedStatement) dbConnection.prepareStatement(findEmailStatementString);
			findStatement.setNString(1, email);
			rs = findStatement.executeQuery();
			rs.next();
			int id = rs.getInt("idUser");
			String fn = rs.getString("firstName");
			String ln = rs.getString("lastName");
			String pass = rs.getString("password");
			String em = rs.getString("email");
			String address = rs.getString("address");
			int s = rs.getInt("strike");
			String p = rs.getString("paymentPlan");

			toReturn = new User(id,fn,ln,pass,em,address,p,s);

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"UserDAO:findByEmail " + e.getMessage());
		} finally {
			DatabaseConnection.close(rs);
			DatabaseConnection.close(findStatement);
			DatabaseConnection.close(dbConnection);
		}
		return toReturn;
	}
	
	public static User findById(int id){
		User toReturn= null;

		Connection dbConnection = (Connection) DatabaseConnection.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;

		try {
			findStatement = (PreparedStatement) dbConnection.prepareStatement(findIdStatementString);
			findStatement.setInt(1, id);
			rs = findStatement.executeQuery();
			rs.next();
			String fn = rs.getString("firstName");
			String ln = rs.getString("lastName");
			String pass = rs.getString("password");
			String em = rs.getString("email");
			String address = rs.getString("address");
			String p = rs.getString("paymentPlan");
			int s = rs.getInt("strike");

			toReturn = new User(id,fn,ln,pass,em,address,p,s);

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"UserDAO:findById " + e.getMessage());
		} finally {
			DatabaseConnection.close(rs);
			DatabaseConnection.close(findStatement);
			DatabaseConnection.close(dbConnection);
		}
		return toReturn;
	}

	public static int insert(User u) {
		Connection dbConnection = DatabaseConnection.getConnection();
		PreparedStatement insertStatement = null;
		int insertedId = -1;

		try {
			insertStatement = (PreparedStatement) dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setInt(1, u.getIdUser());
			insertStatement.setString(2, u.getFirstName());
			insertStatement.setString(3, u.getLastName());
			insertStatement.setString(4, u.getPassword());
			insertStatement.setString(5, u.getEmail());
			insertStatement.setString(6, u.getaddress());
			insertStatement.setInt(7, u.getStrike());
			insertStatement.setString(8, u.getPaymentPlan());
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "UserDAO:insert " + e.getMessage());
		} finally {
			DatabaseConnection.close(insertStatement);
			DatabaseConnection.close(dbConnection);
		}
		return insertedId;
	}
	
	public static int delete(int id){
		Connection dbConnection = DatabaseConnection.getConnection();
		PreparedStatement deleteStatement = null;
		int toReturn = 0;

		try{
			System.out.println("Se sterge userul ");
			deleteStatement = (PreparedStatement) dbConnection.prepareStatement(deleteStatementString);
			deleteStatement.setInt(1, id);
			System.out.println(id);

			toReturn=deleteStatement.executeUpdate(); // returns 1 for success and 0 for fail
		}
		catch(SQLException e) {
			LOGGER.log(Level.WARNING, "UserDAO:delete " + e.getMessage());
		} finally {
			DatabaseConnection.close(deleteStatement);
			DatabaseConnection.close(dbConnection);
		}
		return toReturn;
	}
	
	public static ArrayList<User> select(){
		Connection dbConnection = DatabaseConnection.getConnection();
		PreparedStatement selectStatement = null;
		ResultSet rs = null;
		ArrayList<User> cars= new ArrayList<User>();

		try{
			selectStatement = (PreparedStatement) dbConnection.prepareStatement(selectStatementString);
			rs = selectStatement.executeQuery();

			while(rs.next()){
				int id = rs.getInt("idUser");
				String fn = rs.getString("firstName");
				String ln = rs.getString("lastName");
				String pass = rs.getString("password");
				String em = rs.getString("email");
				String address = rs.getString("address");
				String p = rs.getString("paymentPlan");
				int s = rs.getInt("strike");

				User b = new User(id,fn,ln,pass,em,address,p,s);
				cars.add(b);
			}
		}
		catch (SQLException e) {
			LOGGER.log(Level.WARNING, "UserDAO:select " + e.getMessage());
		} finally {
			DatabaseConnection.close(selectStatement);
			DatabaseConnection.close(dbConnection);
		}
		return cars;
	}

}
