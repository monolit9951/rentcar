package ua.nure.bei.SummaryTask4.web.command.orders.copy;

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
import ua.nure.bei.SummaryTask4.models.User;
import ua.nure.bei.SummaryTask4.web.command.Command;

public class OrdersConfirmedCommand extends Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = -440719449593042756L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		DBManager manager = DBManager.getInstance();
		User u = (User) request.getSession().getAttribute("user");
		List<Order> orders = manager.findAllOrdersOfUserWhereStatus(u.getId(), Status.CONFIRMED.ordinal());
		request.setAttribute("orders", orders);
		String forward = Path.PAGE_ORDERS_CONFIRMED;
		return forward;
	}

}
