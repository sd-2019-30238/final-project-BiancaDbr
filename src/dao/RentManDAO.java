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
import model.RentMan;

public class RentManDAO {
	
	protected static final Logger LOGGER = Logger.getLogger(RentManDAO.class.getName());
	private static final String insertStatementString = "INSERT INTO RentManagement (idRentManagement,idCar,idUser, borrowDate, returnDate)"
			+ " VALUES (?,?,?,?,?)";
	private final static String findUserStatementString = "SELECT * FROM RentManagement WHERE idUser = ?";
	private final static String findBBStatementString = "SELECT * FROM RentManagement WHERE idRentManagement = ?";
	private final static String findCarStatementString = "SELECT * FROM RentManagement WHERE idCar = ?";
	private final static String deleteStatementString ="DELETE FROM RentManagement WHERE idRentManagement = ?";
	private final static String updateStatementString ="UPDATE RentManagement SET idUser=? WHERE idRentManagement=?";

	
	public static RentMan findByRentManId(int id){
		RentMan toReturn= null;

		Connection dbConnection = (Connection) DatabaseConnection.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;

		try {
			findStatement = (PreparedStatement) dbConnection.prepareStatement(findBBStatementString);
			findStatement.setLong(1, id);
			rs = findStatement.executeQuery();
			rs.next();

			int idu = rs.getInt("idUser");
			int idc = rs.getInt("idCar");
			String b = rs.getString("borrowDate");
			String r = rs.getString("returnDate");

			toReturn = new RentMan(id,idc,idu,b,r);

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"RentManDAO:findByRentManId " + e.getMessage());
		} finally {
			DatabaseConnection.close(rs);
			DatabaseConnection.close(findStatement);
			DatabaseConnection.close(dbConnection);
		}
		return toReturn;
	}
	
	public static RentMan findByCarId(int id){
		RentMan toReturn= null;

		Connection dbConnection = (Connection) DatabaseConnection.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;

		try {
			findStatement = (PreparedStatement) dbConnection.prepareStatement(findCarStatementString);
			findStatement.setLong(1, id);
			rs = findStatement.executeQuery();
			rs.next();

			int idu = rs.getInt("idUser");
			int idr = rs.getInt("idRentManagement");
			String b = rs.getString("borrowDate");
			String r = rs.getString("returnDate");

			toReturn = new RentMan(idr,id,idu,b,r);

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"RentManDAO:findByCarId " + e.getMessage());
		} finally {
			DatabaseConnection.close(rs);
			DatabaseConnection.close(findStatement);
			DatabaseConnection.close(dbConnection);
		}
		return toReturn;
	}
	
	public static int insert(RentMan b) {
		Connection dbConnection = DatabaseConnection.getConnection();
		PreparedStatement insertStatement = null;
		int insertedId = -1;

		try {
			insertStatement = (PreparedStatement) dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setInt(1, b.getIdRentManagement());
			insertStatement.setInt(2, b.getIdCar());
			insertStatement.setInt(3, b.getIdUser());
			insertStatement.setString(4, b.getBorrowDate());
			insertStatement.setString(5, b.getReturnDate());
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "RentManDAO:insert " + e.getMessage());
		} finally {
			DatabaseConnection.close(insertStatement);
			DatabaseConnection.close(dbConnection);
		}
		return insertedId;
	}
	
	public static int delete(int idRM){
		Connection dbConnection = DatabaseConnection.getConnection();
		PreparedStatement deleteStatement = null;
		int toReturn = 0;

		try{
			deleteStatement = (PreparedStatement) dbConnection.prepareStatement(deleteStatementString);
			deleteStatement.setInt(1, idRM);

			toReturn=deleteStatement.executeUpdate(); // returns 1 for success and 0 for fail
		}
		catch(SQLException e) {
			LOGGER.log(Level.WARNING, "RentManDAO:delete " + e.getMessage());
		} finally {
			DatabaseConnection.close(deleteStatement);
			DatabaseConnection.close(dbConnection);
		}
		return toReturn;
	}
	
	public static int update(int idUser, int idRM){
		Connection dbConnection = DatabaseConnection.getConnection();
		PreparedStatement updateStatement = null;
		int toReturn=-1;

		try{
			updateStatement= (PreparedStatement) dbConnection.prepareStatement(updateStatementString);
			updateStatement.setInt(1,idUser);
			updateStatement.setInt(1,idRM);

			toReturn=updateStatement.executeUpdate();
		} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "BorrowedBookDAO:update " + e.getMessage());
		} finally {
			DatabaseConnection.close(updateStatement);
			DatabaseConnection.close(dbConnection);
		}

		return toReturn;
	}
	
	public static ArrayList<RentMan> findRentedById(int idU){
		Connection dbConnection = DatabaseConnection.getConnection();
		PreparedStatement selectStatement = null;
		ResultSet rs = null;
		ArrayList<RentMan> cars= new ArrayList<RentMan>();

		try{
			selectStatement = (PreparedStatement) dbConnection.prepareStatement(findUserStatementString);
			selectStatement.setInt(1,idU);
			rs = selectStatement.executeQuery();

			while(rs.next()){
				int idr = rs.getInt("idRentManagement");
				int idc = rs.getInt("idCar");
				String b = rs.getString("borrowDate");
				String r = rs.getString("returnDate");

				RentMan c = new RentMan(idr,idc,idU,b,r);
				cars.add(c);
			}
		}
		catch (SQLException e) {
			LOGGER.log(Level.WARNING, "RentManDAO:select " + e.getMessage());
		} finally {
			DatabaseConnection.close(selectStatement);
			DatabaseConnection.close(dbConnection);
		}
		return cars;
	}
}