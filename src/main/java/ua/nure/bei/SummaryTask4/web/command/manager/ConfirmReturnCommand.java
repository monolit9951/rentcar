package ua.nure.bei.SummaryTask4.web.command.manager;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.bei.SummaryTask4.Message;
import ua.nure.bei.SummaryTask4.Path;
import ua.nure.bei.SummaryTask4.db.DBManager;
import ua.nure.bei.SummaryTask4.exceptions.AppException;
import ua.nure.bei.SummaryTask4.models.Account;
import ua.nure.bei.SummaryTask4.models.Order;
import ua.nure.bei.SummaryTask4.models.Status;
import ua.nure.bei.SummaryTask4.util.Util;
import ua.nure.bei.SummaryTask4.web.command.Command;

public class ConfirmReturnCommand extends Command {

	private static final long serialVersionUID = -2583802685693638430L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		String forward = Path.COMMAND_ARRIVED_CARS;
		DBManager manager = DBManager.getInstance();
		String id = request.getParameter("id");
		if (!Util.isNumber(id))
			throw new AppException(Message.WRONG_FORMAT_OF_DATE.info());
		Order o = manager.findOrderById(Integer.parseInt(id));
		manager.changeOrderStatus(Status.RETURNED.ordinal(), o.getId());
		String repair = request.getParameter("repair");
		if (new Boolean(repair)) {
			String money = request.getParameter("money");
			if (!Util.isMoney(money))
				throw new AppException("wrong param");
			Account acc = new Account(o.getUserId(), Integer.parseInt(money), Status.WAITTOPAY.ordinal(),
					"Счет за ремонт", new Date(System.currentTimeMillis()));
			manager.addAccount(acc);
		}
		return forward;
	}

}
