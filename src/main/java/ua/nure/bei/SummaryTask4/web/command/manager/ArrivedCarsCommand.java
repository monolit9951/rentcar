package ua.nure.bei.SummaryTask4.web.command.manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.bei.SummaryTask4.Path;
import ua.nure.bei.SummaryTask4.db.DBManager;
import ua.nure.bei.SummaryTask4.exceptions.AppException;
import ua.nure.bei.SummaryTask4.models.Order;
import ua.nure.bei.SummaryTask4.models.Status;
import ua.nure.bei.SummaryTask4.web.command.Command;

public class ArrivedCarsCommand extends Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3442421799565580413L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		String forward = Path.PAGE_ARRIVED_CARS;
		DBManager manager = DBManager.getInstance();
		List<Order> orders = manager.findAllOrdersWhereStatus(Status.CONFIRMED.ordinal());
		request.setAttribute("orders", orders);
		return forward;
	}

}
