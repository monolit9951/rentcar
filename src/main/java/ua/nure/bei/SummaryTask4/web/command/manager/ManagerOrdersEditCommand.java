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

public class ManagerOrdersEditCommand extends Command {

	private static final long serialVersionUID = 4997548248084655481L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		DBManager manager = DBManager.getInstance();
		String forward = Path.PAGE_ORDERS_EDIT;
		List<Order> orders = manager.findAllOrdersWhereStatus(Status.PAYED.ordinal());
		request.setAttribute("orders", orders);
		return forward;
	}

}
