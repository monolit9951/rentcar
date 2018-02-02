package ua.nure.bei.SummaryTask4.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Messages;

import ua.nure.bei.SummaryTask4.Message;
import ua.nure.bei.SummaryTask4.exceptions.AppException;
import ua.nure.bei.SummaryTask4.exceptions.DBException;
import ua.nure.bei.SummaryTask4.models.Account;
import ua.nure.bei.SummaryTask4.models.Car;
import ua.nure.bei.SummaryTask4.models.Order;
import ua.nure.bei.SummaryTask4.models.Role;
import ua.nure.bei.SummaryTask4.models.Status;
import ua.nure.bei.SummaryTask4.models.User;

public final class DBManager {
	private static final Logger LOG = Logger.getLogger(DBManager.class);
	private static final String ADD_USER = "INSERT INTO users(login,password,name,surname,city,number,roleId) VALUES(?,?,?,?,?,?,?)";
	private static final String FIND_ALL_USERS = "SELECT * from users";
	private static final String FIND_ALL_CARS = "SELECT * from cars";
	private static final String FIND_USER_BY_ID = "SELECT * from users where id = ?";
	private static final String DELETE_USER_BY_ID = "DELETE from users where id = ?";
	private static final String FIND_CAR_BY_ID = "SELECT * from cars where id = ?";
	private static final String ADD_ORDER = "INSERT INTO orders(userId,statusId,days,price,withDriver,dateStart,dateEnd,name,surname,patronymic,sex,city,borningDate) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String FIND_ORDERS_WHERE_STATUS = "SELECT * from orders where statusId=?";
	private static final String CHANGE_ROLE_BY_ID = "UPDATE users SET roleId=? WHERE id=?";
	private static final String FIND_ORDER_BY_ID = "SELECT * from orders where id = ?";
	private static final String FIND_ORDERS_WHERE_USER_WHERE_STATUS = "SELECT * from orders where userId=? AND statusId=?";
	private static final String CHANGE_ORDER_STATUS_WHERE_ID = "UPDATE orders SET statusId=? WHERE id=?";
	private static final String REJECT_ORDER = "UPDATE orders SET statusId=" + Status.REJECTED.ordinal()
			+ " WHERE id=?";
	private static final String SET_MESSAGE_REJECT = "UPDATE orders SET reason=? where id=?";
	private static final String FIND_USER_BY_LOGIN = "SELECT * from users where login=?";
	private static final String ADD_ACCOUNT = "INSERT INTO accounts(userId,statusId,price,dateCreate,message) VALUES(?,?,?,?,?)";
	private static final String FIND_CAR_BY_ORDER_ID = "SELECT * from cars INNER JOIN orders_cars on cars.id=orders_cars.carId where orders_cars.orderId=?";
	private static final String ADD_ORDER_ID_CAR_ID = "INSERT INTO orders_cars(orderId,carId) VALUES(?,?)";
	private static final String FIND_ALL_ACCOUNTS = "SELECT * from accounts";
	private static final String FIND_ACCOUNTS_BY_USER_ID = "SELECT * from accounts where userId = ?";
	private static final String FIND_ACCOUNTS_BY_USER_ID_WHERE_STATUS = "SELECT * from accounts where userId=? AND statusId=?";
	private static final String FIND_ACCOUNT_BY_ID = "SELECT * from accounts where id = ?";
	private static final String CHANGE_ACCOUNT_STATUS_WHERE_ID = "UPDATE accounts SET statusId=? where id=?";
	private static final String FIND_ALL_CARS_WHERE_NAME = "SELECT * from cars WHERE mark LIKE ? OR model LIKE ?";// "SELECT
	private static final String FIND_ALL_CARS_WHERE_AUTO_CLASS = "SELECT * from cars WHERE autoClass=?";
	// *
	// from
	// cars
	// WHERE
	// LOCATE(?,mark)>0";
	private static final String CHECK_USER_FOR_BLOCK = "SELECT EXISTS(SELECT userId FROM locked_users WHERE userId = ?)";
	private static final String DELETE_USER_FROM_LOCK = "DELETE from locked_users where userId = ?";
	private static final String ADD_USER_TO_LOCK_LIST = "INSERT INTO locked_users VALUES(?)";
	private static final String CHANGE_CAR_BY_ID = "update cars SET mark = ?,model = ?,autoClass=?,price=?,body=?,engine=?,transmission=?,fuel=?,imageURL=? WHERE id = ?";
	private static final String ADD_CAR = "INSERT INTO cars (mark,model,autoClass,price,body,engine,transmission,fuel,imageURL) VALUES(?,?,?,?,?,?,?,?,?)";
	private static final String DELETE_CAR_BY_ID = "DELETE from cars where id = ?";
	private static final String FIND_CAR_ID_BY_ORDER_ID = "SELECT carId from orders_cars where orderId=?";
	private static final String DELETE_ORDER_BY_ID = "DELETE from orders where id = ?";
	private static final String CHECK_USER_FOR_NOT_PAYED = "SELECT EXISTS(SELECT userId FROM accounts WHERE userId = ? AND statusId=0)";
	private static DBManager instance;
	private DataSource ds;

	private DBManager() throws NamingException {
		Context ctx = new InitialContext();
		Context context = (Context) ctx.lookup("java:/comp/env");
		ds = (DataSource) context.lookup("jdbc/parking_MYSQL");
	}

	public static synchronized DBManager getInstance() {
		if (instance == null) {
			try {
				instance = new DBManager();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return instance;
	}

	public Connection getConnection() {
		Connection con = null;
		try {
			con = ds.getConnection();
		} catch (SQLException ex) {

		}
		return con;
	}

	public List<User> findAllUsers() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<User> userList = new ArrayList<User>();
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(FIND_ALL_USERS);
			while (rs.next()) {
				User u = extractUser(rs);
				userList.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, stmt, rs);
		}

		return userList;
	}

	public List<Car> findAllCars() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<Car> carList = new ArrayList<Car>();
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(FIND_ALL_CARS);
			while (rs.next()) {
				Car car = extractCar(rs);
				carList.add(car);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, stmt, rs);
		}
		return carList;
	}

	public List<Account> findAllAccounts() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<Account> accList = new ArrayList<Account>();
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(FIND_ALL_ACCOUNTS);
			while (rs.next()) {
				Account acc = extractAccount(rs);
				accList.add(acc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, stmt, rs);
		}
		return accList;
	}

	public List<Order> findAllOrdersOfUserWhereStatus(int userId, int statusId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Order> orders = new ArrayList<Order>();
		try {
			con = getConnection();
			pstmt = con.prepareStatement(FIND_ORDERS_WHERE_USER_WHERE_STATUS);
			pstmt.setInt(1, userId);
			pstmt.setInt(2, statusId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Order order = extractOrder(rs);
				orders.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt, rs);
		}
		return orders;
	}

	public List<Order> findAllOrdersWhereStatus(int statusId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Order> orders = new ArrayList<Order>();
		try {
			con = getConnection();
			pstmt = con.prepareStatement(FIND_ORDERS_WHERE_STATUS);
			pstmt.setInt(1, statusId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Order order = extractOrder(rs);
				orders.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt, rs);
		}
		return orders;
	}

	public List<Car> findAllCarsWhereWhereName(String search) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Car> cars = new ArrayList<Car>();
		try {
			con = getConnection();

			pstmt = con.prepareStatement(FIND_ALL_CARS_WHERE_NAME);
			pstmt.setString(1, "%" + search + "%");
			pstmt.setString(2, "%" + search + "%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Car car = extractCar(rs);
				cars.add(car);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt, rs);
		}
		return cars;
	}

	public List<Car> findAllCarsWhereWhereQuility(String search) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Car> cars = new ArrayList<Car>();
		try {
			con = getConnection();

			pstmt = con.prepareStatement(FIND_ALL_CARS_WHERE_AUTO_CLASS);
			pstmt.setString(1, search);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Car car = extractCar(rs);
				cars.add(car);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt, rs);
		}
		return cars;
	}

	public List<Account> findAllAccountsByUserId(int userId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Account> accounts = new ArrayList<Account>();
		try {
			con = getConnection();
			pstmt = con.prepareStatement(FIND_ACCOUNTS_BY_USER_ID);
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Account acc = extractAccount(rs);
				accounts.add(acc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt, rs);
		}
		return accounts;
	}

	public List<Account> findAllAccountsByUserIdWhereStatus(int userId, int ordinal) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Account> accounts = new ArrayList<Account>();
		try {
			con = getConnection();
			pstmt = con.prepareStatement(FIND_ACCOUNTS_BY_USER_ID_WHERE_STATUS);
			pstmt.setInt(1, userId);
			pstmt.setInt(2, ordinal);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Account acc = extractAccount(rs);
				accounts.add(acc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt, rs);
		}
		return accounts;
	}

	public User findUserByLogin(String login) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User u = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(FIND_USER_BY_LOGIN);
			pstmt.setString(1, login);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				u = extractUser(rs);
			}
			con.commit();
		} catch (SQLException ex) {
			ex.printStackTrace();
			rollback(con);
		} finally {
			close(con, pstmt, rs);
		}
		return u;
	}

	public User findUserById(int id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User u = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(FIND_USER_BY_ID);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				u = extractUser(rs);
			}
			con.commit();
		} catch (SQLException ex) {
			ex.printStackTrace();
			rollback(con);
			// throw new
			// DBException(Messages.ERR_CANNOT_OBTAIN_ORDERS_BY_STATUS_ID, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return u;
	}

	public boolean checkUserForBlock(int id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(CHECK_USER_FOR_BLOCK);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next())
				return rs.getBoolean(1);
			else
				return false;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
			// throw new
			// DBException(Messages.ERR_CANNOT_OBTAIN_ORDERS_BY_STATUS_ID, ex);
		} finally {
			close(con, pstmt, rs);
		}
	}

	public boolean checkUserForNotPayedAccounts(int id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(CHECK_USER_FOR_NOT_PAYED);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next())
				return rs.getBoolean(1);
			else
				return false;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		} finally {
			close(con, pstmt, rs);
		}
	}

	public Account findAccountById(int id) throws DBException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Account acc = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(FIND_ACCOUNT_BY_ID);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				acc = extractAccount(rs);
			}
			con.commit();
		} catch (SQLException ex) {
			ex.printStackTrace();
			rollback(con);
			throw new DBException(Message.CANNOT_OBTAIN_ACCOUTN_BY_ID.info(), ex);
		} finally {
			close(con, pstmt, rs);
		}
		return acc;
	}

	public Car findCarById(int id) throws DBException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Car c = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(FIND_CAR_BY_ID);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				c = extractCar(rs);
			}
			con.commit();
		} catch (SQLException ex) {
			ex.printStackTrace();
			rollback(con);
			throw new DBException(Message.CANNOT_OBTAIN_CAR_BY_ID.info(), ex);
		} finally {
			close(con, pstmt, rs);
		}
		return c;
	}

	public Car findCarByOrderId(int id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Car c = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(FIND_CAR_BY_ORDER_ID);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				c = extractCar(rs);
			}
			LOG.trace(pstmt);
			con.commit();
		} catch (SQLException ex) {
			ex.printStackTrace();
			rollback(con);
			// throw new
			// DBException(Messages.ERR_CANNOT_OBTAIN_ORDERS_BY_STATUS_ID, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return c;
	}

	public int findCarIdInOrder(int orderId) throws AppException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(FIND_CAR_ID_BY_ORDER_ID);
			pstmt.setInt(1, orderId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				con.commit();
				return rs.getInt("carId");
			} else
				throw new AppException("Cannot find car by order_id");
		} catch (SQLException ex) {
			ex.printStackTrace();
			LOG.trace(ex);
			rollback(con);
			throw new AppException("Cannot find car by order_id");
		} finally {
			close(con, pstmt, rs);
		}
	}

	public Order findOrderById(int id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Order o = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(FIND_ORDER_BY_ID);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				o = extractOrder(rs);
			}
			con.commit();
		} catch (SQLException ex) {
			ex.printStackTrace();
			rollback(con);
		} finally {
			close(con, pstmt, rs);
		}
		return o;
	}

	public boolean deleteUserById(int id) throws DBException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(DELETE_USER_BY_ID);
			pstmt.setInt(1, id);
			int update = pstmt.executeUpdate();
			if (update < 1)
				return false;
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Message.CANNOT_DELETE_USER_BY_ID.info(), ex);
		} finally {
			close(con, pstmt, rs);
		}
		return true;
	}

	public boolean deleteCarById(int id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(DELETE_CAR_BY_ID);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException ex) {
			LOG.trace(ex);
			rollback(con);
			return false;
		} finally {
			close(con, pstmt, rs);
		}
		return true;

	}

	public boolean removeUserFromLockList(int userId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(DELETE_USER_FROM_LOCK);
			pstmt.setInt(1, userId);
			int update = pstmt.executeUpdate();
			con.commit();
			if (update > 0)
				return true;
		} catch (SQLException ex) {
			ex.printStackTrace();
			rollback(con);
			return false;
		} finally {
			close(con, pstmt, rs);
		}
		return false;

	}

	public boolean deleteOrderById(int id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(DELETE_ORDER_BY_ID);
			pstmt.setInt(1, id);
			int update = pstmt.executeUpdate();
			con.commit();
			if (update > 0)
				return true;
		} catch (SQLException ex) {
			ex.printStackTrace();
			rollback(con);
			return false;
		} finally {
			close(con, pstmt, rs);
		}
		return false;

	}

	public boolean changeRoleById(int id, int roleId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(CHANGE_ROLE_BY_ID);
			pstmt.setInt(1, roleId);
			pstmt.setInt(2, id);
			int update = pstmt.executeUpdate();
			con.commit();
			if (update <= 0)
				return false;
		} catch (SQLException ex) {
			ex.printStackTrace();
			rollback(con);
			return false;
		} finally {
			close(con, pstmt, rs);
		}
		return true;

	}

	public boolean changeCarById(Car car, int id) throws DBException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(CHANGE_CAR_BY_ID);
			// mark,model,autoClass,price,body,engine,transmission,fuel,imageURL
			int i = 1;
			pstmt.setString(i++, car.getMark());
			pstmt.setString(i++, car.getModel());
			pstmt.setString(i++, car.getAutoClass());
			pstmt.setInt(i++, car.getPrice());
			pstmt.setString(i++, car.getBody());
			pstmt.setDouble(i++, car.getEngine());
			pstmt.setString(i++, car.getTransmission());
			pstmt.setString(i++, car.getFuel());
			pstmt.setString(i++, car.getImageURL());
			pstmt.setInt(i++, id);
			int update = pstmt.executeUpdate();
			con.commit();
			if (update <= 0)
				return false;
		} catch (SQLException ex) {
			ex.printStackTrace();
			rollback(con);
			throw new DBException(Message.CANNOT_UPDATE_CAR.info(), ex);
		} finally {
			close(con, pstmt, rs);
		}
		return true;

	}

	public boolean addCar(Car car) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(ADD_CAR);
			// mark,model,autoClass,price,body,engine,transmission,fuel,imageURL
			int i = 1;
			pstmt.setString(i++, car.getMark());
			pstmt.setString(i++, car.getModel());
			pstmt.setString(i++, car.getAutoClass());
			pstmt.setInt(i++, car.getPrice());
			pstmt.setString(i++, car.getBody());
			pstmt.setDouble(i++, car.getEngine());
			pstmt.setString(i++, car.getTransmission());
			pstmt.setString(i++, car.getFuel());
			pstmt.setString(i++, car.getImageURL());
			int update = pstmt.executeUpdate();
			con.commit();
			if (update <= 0)
				return false;
		} catch (SQLException ex) {
			ex.printStackTrace();
			rollback(con);
			return false;
		} finally {
			close(con, pstmt, rs);
		}
		return true;

	}

	public boolean rejectOrder(int orderId, String reason) {
		Connection con = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			pstmt1 = con.prepareStatement(REJECT_ORDER);
			pstmt1.setInt(1, orderId);
			int update1 = pstmt1.executeUpdate();
			pstmt2 = con.prepareStatement(SET_MESSAGE_REJECT);
			pstmt2.setString(1, reason);
			pstmt2.setInt(2, orderId);
			int update2 = pstmt2.executeUpdate();
			LOG.trace(pstmt2);
			if (update1 + update2 < 2) {
				con.rollback();
				return false;
			}
			con.commit();
		} catch (SQLException ex) {
			ex.printStackTrace();
			rollback(con);
			return false;
		} finally {
			close(pstmt2);
			close(con, pstmt1, rs);
		}
		return true;

	}

	public boolean changeOrderStatus(int statusId, int id) { // confirm order
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(CHANGE_ORDER_STATUS_WHERE_ID);
			pstmt.setInt(1, statusId);
			pstmt.setInt(2, id);
			int update = pstmt.executeUpdate();
			con.commit();
			if (update <= 0)
				return false;
		} catch (SQLException ex) {
			ex.printStackTrace();
			rollback(con);
			return false;
		} finally {
			close(con, pstmt, rs);
		}
		return true;

	}

	public boolean changeAccountStatusId(int statusId, int id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(CHANGE_ACCOUNT_STATUS_WHERE_ID);
			pstmt.setInt(1, statusId);
			pstmt.setInt(2, id);
			int update = pstmt.executeUpdate();
			con.commit();
			if (update <= 0)
				return false;
		} catch (SQLException ex) {
			ex.printStackTrace();
			rollback(con);
			return false;
		} finally {
			close(con, pstmt, rs);
		}
		return true;

	}

	public boolean addUser(User u) throws DBException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(ADD_USER);
			int i = 1;

			pstmt.setString(i++, u.getLogin());
			pstmt.setString(i++, u.getPassword());
			pstmt.setString(i++, u.getName());
			pstmt.setString(i++, u.getSurname());
			pstmt.setString(i++, u.getCity());
			pstmt.setString(i++, u.getNumber());
			pstmt.setInt(i, u.getRoleId());
			pstmt.executeUpdate();
			con.commit();
			return true;
		} catch (SQLException ex) {
			LOG.trace(ex);
			LOG.trace(pstmt);
			rollback(con);
			throw new DBException(Message.EXIST_USER.info(), ex);
		} finally {
			close(con, pstmt, rs);
		}

	}

	public boolean addOrderIdCarId(int orderId, int carId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			int i = 1;
			pstmt = con.prepareStatement(ADD_ORDER_ID_CAR_ID);
			pstmt.setInt(i++, orderId);
			pstmt.setInt(i++, carId);
			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException ex) {
			LOG.trace(ex);
			rollback(con);
			return false;
		} finally {
			close(con, pstmt, rs);
		}
		return true;
	}

	public boolean addOrder(Order o) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(ADD_ORDER, Statement.RETURN_GENERATED_KEYS);//
			int i = 1;
			pstmt.setInt(i++, o.getUserId());
			pstmt.setInt(i++, o.getStatusId());
			pstmt.setInt(i++, o.getDays());
			pstmt.setInt(i++, o.getPrice());
			pstmt.setBoolean(i++, o.isWithDriver());
			pstmt.setDate(i++, o.getDateStart());
			pstmt.setDate(i++, o.getDateEnd());
			pstmt.setString(i++, o.getName());
			pstmt.setString(i++, o.getSurname());
			pstmt.setString(i++, o.getPatronymic());
			pstmt.setString(i++, o.getSex());
			pstmt.setString(i++, o.getCity());
			pstmt.setDate(i++, o.getBorningDate());
			LOG.trace(pstmt);
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				o.setId(rs.getInt(1));
			}
			LOG.trace("i set ord id to " + o.getId());
			con.commit();
		} catch (SQLException ex) {
			LOG.trace("We have already have this order");
			LOG.trace(ex);
			rollback(con);
			return false;
		} finally {
			close(con, pstmt, rs);
		}
		return true;
	}

	public boolean addAccount(Account account) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(ADD_ACCOUNT);//
			int i = 1;
			pstmt.setInt(i++, account.getUserId());
			pstmt.setInt(i++, account.getStatusId());
			pstmt.setInt(i++, account.getPrice());
			pstmt.setDate(i++, account.getDateCreate());
			pstmt.setString(i++, account.getMessage());
			LOG.trace(pstmt);
			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException ex) {
			LOG.trace("We have already have this account");
			LOG.trace(ex);
			rollback(con);
			return false;
		} finally {
			close(con, pstmt, rs);
		}
		return true;
	}

	public boolean addUserToLockList(int userId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(ADD_USER_TO_LOCK_LIST);//
			pstmt.setInt(1, userId);
			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException ex) {
			LOG.trace(ex);
			rollback(con);
			return false;
		} finally {
			close(con, pstmt, rs);
		}
		return true;
	}

	private User extractUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setLogin(rs.getString("login"));
		user.setPassword(rs.getString("password"));
		user.setName(rs.getString("name"));
		user.setSurname(rs.getString("surname"));
		user.setCity(rs.getString("city"));
		user.setNumber(rs.getString("number"));
		int roleId = rs.getInt("roleId");
		user.setRoleId(roleId);
		user.setRole(Role.getRole(user));
		return user;
	}

	private Order extractOrder(ResultSet rs) throws SQLException {
		Order order = new Order();
		order.setDays(rs.getInt("days"));
		order.setBorningDate(rs.getDate("borningDate"));
		order.setDateStart(rs.getDate("dateStart"));
		order.setDateEnd(rs.getDate("dateEnd"));
		order.setCity(rs.getString("city"));
		order.setId(rs.getInt("id"));
		order.setName(rs.getString("name"));
		order.setPatronymic(rs.getString("patronymic"));
		order.setPrice(rs.getInt("price"));
		order.setSex(rs.getString("sex"));
		order.setStatusId(rs.getInt("statusId"));
		order.setSurname(rs.getString("surname"));
		order.setUserId(rs.getInt("userId"));
		order.setWithDriver(rs.getBoolean("withDriver"));
		order.setReason(rs.getString("reason"));

		return order;
	}

	private Car extractCar(ResultSet rs) throws SQLException {
		Car car = new Car();
		car.setId(rs.getInt("id"));
		car.setBody(rs.getString("body"));
		car.setEngine(rs.getDouble("engine"));
		car.setFuel(rs.getString("fuel"));
		car.setImageURL(rs.getString("imageURL"));
		car.setMark(rs.getString("mark"));
		car.setModel(rs.getString("model"));
		car.setAutoClass(rs.getString("autoClass"));
		car.setPrice(rs.getInt("price"));
		car.setTransmission(rs.getString("transmission"));
		return car;
	}

	private Account extractAccount(ResultSet rs) throws SQLException {
		Account acc = new Account();
		acc.setDateCreate(rs.getDate("dateCreate"));
		acc.setId(rs.getInt("id"));
		acc.setMessage(rs.getString("message"));
		acc.setPrice(rs.getInt("price"));
		acc.setStatusId(rs.getInt("statusId"));
		acc.setUserId(rs.getInt("userId"));
		return acc;
	}

	private void rollback(Connection con) {
		if (con != null) {
			try {
				con.rollback();
			} catch (SQLException ex) {
			}
		}
	}

	private void close(Connection con, Statement stmt, ResultSet rs) {
		close(rs);
		close(stmt);
		close(con);
	}

	private void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException ex) {
			}
		}
	}

	private void close(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException ex) {
			}
		}
	}

	private void close(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException ex) {
			}
		}
	}

}