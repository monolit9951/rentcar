package ua.nure.bei.SummaryTask4.web.command.orders.copy;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.bei.SummaryTask4.Message;
import ua.nure.bei.SummaryTask4.Path;
import ua.nure.bei.SummaryTask4.db.DBManager;
import ua.nure.bei.SummaryTask4.exceptions.AppException;
import ua.nure.bei.SummaryTask4.models.Car;
import ua.nure.bei.SummaryTask4.models.Order;
import ua.nure.bei.SummaryTask4.models.User;
import ua.nure.bei.SummaryTask4.util.Util;
import ua.nure.bei.SummaryTask4.web.command.Command;

public class OrderGetCommand extends Command {

	private static final long serialVersionUID = -7772140982617573596L;
	private static final Logger LOG = Logger.getLogger(OrderGetCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		DBManager manager = DBManager.getInstance();
		String forward = Path.PAGE_ORDER;
		String id = request.getParameter("id");
		if (!Util.isNumber(id))
			throw new AppException(Message.WRONG_FORMAT_OF_DATE.info());
		Order order = manager.findOrderById(Integer.parseInt(id));
		if (order == null) {
			throw new AppException(Message.CANNOT_FIND_ITEM_BY_ID.info());
		}
		User u = (User) request.getSession().getAttribute("user");
		if (u.getId() != order.getUserId())
			throw new AppException(Message.NO_RULES.info());
		Car car = manager.findCarByOrderId(order.getId());
		LOG.trace("found a car -->" + car);
		if (car == null) {
			car = Car.rebuildDeletedCar(Integer.parseInt(id));
			LOG.trace("found deleted car--->" + car);

		}
		request.setAttribute("order", order);
		request.setAttribute("car", car);
		return forward;
	}

}
