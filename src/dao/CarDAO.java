package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.jdbc.PreparedStatement;

import model.Car;
import connection.DatabaseConnection;

public class CarDAO {
	protected static final Logger LOGGER = Logger.getLogger(CarDAO.class.getName());
	private static final String insertStatementString = "INSERT INTO Car (idCar,brand,color,year,availability)"
			+ " VALUES (?,?,?,?,?)";
	private final static String findStatementString = "SELECT * FROM Car WHERE idCar = ?";
	private final static String selectStatementString ="SELECT * FROM Car";
	private final static String deleteStatementString ="DELETE FROM Car WHERE idCar = ?";
	private final static String updateStatementString ="UPDATE Car SET brand=?,color=?,year=?,availability=? WHERE idCar=?";
	private final static String selectBrandStatementString ="SELECT * FROM Car WHERE brand = ?";
	private final static String selectColorStatementString ="SELECT * FROM Car WHERE color = ?";
	private final static String selectAvailableStatementString ="SELECT * FROM Car WHERE availability = ?";
	
	public static Car findById(int idCar){
		Car toReturn= null;

		Connection dbConnection = (Connection) DatabaseConnection.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;

		try {
			findStatement = (PreparedStatement) dbConnection.prepareStatement(findStatementString);
			findStatement.setLong(1, idCar);
			rs = findStatement.executeQuery();
			rs.next();

			String b = rs.getString("brand");
			String c = rs.getString("color");
			int year = rs.getInt("year");
			String a = rs.getString("availability");

			toReturn = new Car(idCar,b,c,year,a);

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"CarDAO:findById " + e.getMessage());
		} finally {
			DatabaseConnection.close(rs);
			DatabaseConnection.close(findStatement);
			DatabaseConnection.close(dbConnection);
		}
		System.out.println(toReturn.getIdCar()+" " +toReturn.getBrand());
		return toReturn;
	}

	public static int insert(Car b) {
		Connection dbConnection = DatabaseConnection.getConnection();
		PreparedStatement insertStatement = null;
		int insertedId = -1;

		try {
			insertStatement = (PreparedStatement) dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setInt(1, b.getIdCar());
			insertStatement.setString(2, b.getBrand());
			insertStatement.setString(3, b.getColor());
			insertStatement.setInt(4, b.getYear());
			insertStatement.setString(5, b.getAvailability());
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "CarDAO:insert " + e.getMessage());
		} finally {
			DatabaseConnection.close(insertStatement);
			DatabaseConnection.close(dbConnection);
		}
		return insertedId;
	}

	public static int delete(int idCar){
		Connection dbConnection = DatabaseConnection.getConnection();
		PreparedStatement deleteStatement = null;
		int toReturn = 0;

		try{
			System.out.println("Se sterge");
			deleteStatement = (PreparedStatement) dbConnection.prepareStatement(deleteStatementString);
			deleteStatement.setInt(1, idCar);
			System.out.println(idCar);

			toReturn=deleteStatement.executeUpdate(); // returns 1 for success and 0 for fail
		}
		catch(SQLException e) {
			LOGGER.log(Level.WARNING, "CarDAO:delete " + e.getMessage());
		} finally {
			DatabaseConnection.close(deleteStatement);
			DatabaseConnection.close(dbConnection);
		}
		return toReturn;
	}
	
	public static int updateAvailability(int idCar, String a){
		Connection dbConnection = DatabaseConnection.getConnection();
		PreparedStatement updateStatement = null;
		int toReturn=-1;

		try{
			updateStatement= (PreparedStatement) dbConnection.prepareStatement(updateStatementString);
			updateStatement.setInt(1,idCar);
			updateStatement.setString(4, a);

			toReturn=updateStatement.executeUpdate();

		} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "CarDAO:update " + e.getMessage());
		} finally {
			DatabaseConnection.close(updateStatement);
			DatabaseConnection.close(dbConnection);
		}

		return toReturn;
	}
	
	public static ArrayList<Car> select(){
		Connection dbConnection = DatabaseConnection.getConnection();
		PreparedStatement selectStatement = null;
		ResultSet rs = null;
		ArrayList<Car> cars= new ArrayList<Car>();

		try{
			selectStatement = (PreparedStatement) dbConnection.prepareStatement(selectStatementString);
			rs = selectStatement.executeQuery();

			while(rs.next()){
				int id= rs.getInt("idCar");
				String br = rs.getString("brand");
				String c = rs.getString("color");
				int y = rs.getInt("year");
				String a = rs.getString("availability");
				Car b= new Car(id,br,c,y,a);
				cars.add(b);
			}
		}
		catch (SQLException e) {
			LOGGER.log(Level.WARNING, "CarDAO:select " + e.getMessage());
		} finally {
			DatabaseConnection.close(selectStatement);
			DatabaseConnection.close(dbConnection);
		}
		return cars;
	}
	
	public static ArrayList<Car> selectAvailability() {
		Connection dbConnection = DatabaseConnection.getConnection();
		PreparedStatement selectStatement = null;
		ResultSet rs = null;
		ArrayList<Car> cars= new ArrayList<Car>();

		try{
			String s = "available";
			selectStatement = (PreparedStatement) dbConnection.prepareStatement(selectAvailableStatementString);
			selectStatement.setString(1, s);
			rs = selectStatement.executeQuery();

			while(rs.next()){
				int id= rs.getInt("idCar");
				String br = rs.getString("brand");
				String c = rs.getString("color");
				int y = rs.getInt("year");
				String a = rs.getString("availability");
				Car b= new Car(id,br,c,y,a);
				cars.add(b);
			}
		}
		catch (SQLException e) {
			LOGGER.log(Level.WARNING, "CarDAO:select " + e.getMessage());
		} finally {
			DatabaseConnection.close(selectStatement);
			DatabaseConnection.close(dbConnection);
		}
		return cars;
	}
	
	public static ArrayList<Car> selectBrand(String s) {
		Connection dbConnection = DatabaseConnection.getConnection();
		PreparedStatement selectStatement = null;
		ResultSet rs = null;
		ArrayList<Car> cars= new ArrayList<Car>();

		try{
			selectStatement = (PreparedStatement) dbConnection.prepareStatement(selectBrandStatementString);
			selectStatement.setString(1, s);
			rs = selectStatement.executeQuery();

			while(rs.next()){
				int id= rs.getInt("idCar");
				String br = rs.getString("brand");
				String c = rs.getString("color");
				int y = rs.getInt("year");
				String a = rs.getString("availability");
				Car b= new Car(id,br,c,y,a);
				cars.add(b);
			}
		}
		catch (SQLException e) {
			LOGGER.log(Level.WARNING, "CarDAO:select " + e.getMessage());
		} finally {
			DatabaseConnection.close(selectStatement);
			DatabaseConnection.close(dbConnection);
		}
		return cars;
	}
	
	public static ArrayList<Car> selectColor(String s) {
		Connection dbConnection = DatabaseConnection.getConnection();
		PreparedStatement selectStatement = null;
		ResultSet rs = null;
		ArrayList<Car> cars= new ArrayList<Car>();

		try{
			selectStatement = (PreparedStatement) dbConnection.prepareStatement(selectColorStatementString);
			selectStatement.setString(1, s);
			rs = selectStatement.executeQuery();

			while(rs.next()){
				int id= rs.getInt("idCar");
				String br = rs.getString("brand");
				String c = rs.getString("color");
				int y = rs.getInt("year");
				String a = rs.getString("availability");
				Car b= new Car(id,br,c,y,a);
				cars.add(b);
			}
		}
		catch (SQLException e) {
			LOGGER.log(Level.WARNING, "CarDAO:select " + e.getMessage());
		} finally {
			DatabaseConnection.close(selectStatement);
			DatabaseConnection.close(dbConnection);
		}
		return cars;
	}

}
