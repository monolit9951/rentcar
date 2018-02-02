package ua.nure.bei.SummaryTask4.web.command.orders;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.bei.SummaryTask4.Message;
import ua.nure.bei.SummaryTask4.Path;
import ua.nure.bei.SummaryTask4.db.DBManager;
import ua.nure.bei.SummaryTask4.exceptions.AppException;
import ua.nure.bei.SummaryTask4.models.Account;
import ua.nure.bei.SummaryTask4.models.Car;
import ua.nure.bei.SummaryTask4.models.Order;
import ua.nure.bei.SummaryTask4.models.Status;
import ua.nure.bei.SummaryTask4.models.User;
import ua.nure.bei.SummaryTask4.util.Util;
import ua.nure.bei.SummaryTask4.web.command.Command;

public class FormingOrder extends Command {
	private static final Logger LOG = Logger.getLogger(FormingOrder.class);
	private static final long serialVersionUID = 1L;
	private static final long MILI_IN_DAY = 1000 * 60 * 60 * 24;
	private final int MAX_SIZE_OF_DATE = 40;
	private final int DRIVER_PER_DAY = 20;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		DBManager manager = DBManager.getInstance();
		String forward = Path.PAGE_FORMING_ACCOUNT;
		final User user = (User) request.getSession().getAttribute("user");
		final int userId = user.getId();
		if (manager.checkUserForNotPayedAccounts(userId)) {
			throw new AppException("ѕростите , но у вас остались неоплаченные счета!!!");
		}

		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String patronymic = request.getParameter("patronymic");
		String city = request.getParameter("city");
		String sex = request.getParameter("sex");
		String dateStart = request.getParameter("dateBegin");
		String dateEnd = request.getParameter("dateEnd");
		String dateBirth = request.getParameter("dateBirth");
		String driver = request.getParameter("driver");
		String carId = request.getParameter("carId");
		String carPrice = request.getParameter("carPrice");
		LOG.trace("Request parameter: carId --> " + carId);
		LOG.trace("Request parameter: carPrice--> " + carPrice);
		if (!Util.checkValidate(name, surname, city, sex, dateStart, dateEnd, dateBirth, driver, carId, carPrice))
			throw new AppException("Everything must be completed");
		if (!Util.checkMaxSize(MAX_SIZE_OF_DATE, name, surname, city, sex, dateStart, dateEnd, dateBirth, driver))
			throw new AppException("Max size  - 40 characters");
		if (!driver.equals("true") && !driver.equals("false"))
			throw new AppException(Message.WRONG_FORMAT_OF_DATE.info());
		Date dateS = Util.getDate(dateStart);
		LOG.trace("Request parameter: dateStart --> " + dateS);
		Date dateE = Util.getDate(dateEnd);
		LOG.trace("Request parameter: dateEnd --> " + dateE);
		Date dateB = Util.getDate(dateBirth);
		LOG.trace("Request parameter: dateBirth --> " + dateB);
		if (dateS == null || dateE == null || dateB == null || !Util.isNumber(carPrice) || !Util.isNumber(carId))
			throw new AppException(Message.WRONG_FORMAT_OF_DATE.info());
		int days = (int) (((dateE.getTime() - dateS.getTime()) / MILI_IN_DAY) + 1);
		if (days > 7)
			throw new AppException(Message.MAX_RENT_CAR.info());
		if (days <= 0) {
			throw new AppException(Message.WRONG_FORMAT_OF_DATE.info());
		}
		Car car = manager.findCarById(Integer.parseInt(carId));
		if (car == null)
			throw new AppException(Message.CANNOT_FIND_ITEM_BY_ID.info() + "(car)");
		int price = days * Integer.parseInt(carPrice);
		if (new Boolean(driver)) {
			price += DRIVER_PER_DAY * days;
		}
		Order o = new Order(userId, Integer.parseInt(carId), days, price, Status.WAITTOPAY.ordinal(),
				new Boolean(driver), dateS, dateE, name, surname, patronymic, city, sex, dateB);
		Account account = new Account(o.getUserId(), o.getPrice(), Status.PAYED.ordinal(),
				new Date(System.currentTimeMillis()));
		request.getSession().setAttribute("account", account);
		request.getSession().setAttribute("order", o);
		request.getSession().setAttribute("car", car);
		LOG.trace("Here is order : " + o);
		LOG.trace("Here is car :" + car);
		return forward;

	}

}
