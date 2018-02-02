package ua.nure.bei.SummaryTask4.web.command.orders.copy;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.bei.SummaryTask4.Path;
import ua.nure.bei.SummaryTask4.db.DBManager;
import ua.nure.bei.SummaryTask4.exceptions.AppException;
import ua.nure.bei.SummaryTask4.models.Order;
import ua.nure.bei.SummaryTask4.models.Status;
import ua.nure.bei.SummaryTask4.models.User;
import ua.nure.bei.SummaryTask4.web.command.Command;
import ua.nure.bei.SummaryTask4.web.command.orders.FormingOrder;

public class OrdersWaitCommand extends Command {
	private static final Logger LOG = Logger.getLogger(OrdersWaitCommand.class);
	private static final long serialVersionUID = 9035405227419490044L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		String forward = Path.PAGE_ORDERS_WAIT;
		DBManager manager = DBManager.getInstance();
		User u = (User) request.getSession().getAttribute("user");
		List<Order> orders = manager.findAllOrdersOfUserWhereStatus(u.getId(), Status.PAYED.ordinal());
		request.setAttribute("orders", orders);
		LOG.trace("Here is orders -->" + orders);
		return forward;
	}

}
