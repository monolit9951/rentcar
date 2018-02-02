package ua.nure.bei.SummaryTask4.web.command.manager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.bei.SummaryTask4.Message;
import ua.nure.bei.SummaryTask4.Path;
import ua.nure.bei.SummaryTask4.db.DBManager;
import ua.nure.bei.SummaryTask4.exceptions.AppException;
import ua.nure.bei.SummaryTask4.models.Car;
import ua.nure.bei.SummaryTask4.models.Order;
import ua.nure.bei.SummaryTask4.util.Util;
import ua.nure.bei.SummaryTask4.web.command.Command;

public class ArrivedCarCommand extends Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5958705807175013717L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		DBManager manager = DBManager.getInstance();
		String forward = Path.PAGE_ARRIVED_CAR;
		String id = request.getParameter("id");
		if (!Util.isNumber(id))
			throw new AppException(Message.WRONG_FORMAT_OF_DATE.info());
		Order o = manager.findOrderById(Integer.parseInt(id));
		if (o == null)
			throw new AppException(Message.CANNOT_FIND_ITEM_BY_ID.info());
		Car c = manager.findCarByOrderId(o.getId());
		request.setAttribute("order", o);
		request.setAttribute("car", c);
		return forward;
	}

}
