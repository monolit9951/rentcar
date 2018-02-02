package ua.nure.bei.SummaryTask4.web.command.account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.bei.SummaryTask4.Message;
import ua.nure.bei.SummaryTask4.Path;
import ua.nure.bei.SummaryTask4.db.DBManager;
import ua.nure.bei.SummaryTask4.exceptions.AppException;
import ua.nure.bei.SummaryTask4.models.Order;
import ua.nure.bei.SummaryTask4.models.Status;
import ua.nure.bei.SummaryTask4.models.User;
import ua.nure.bei.SummaryTask4.util.Util;
import ua.nure.bei.SummaryTask4.web.command.Command;

public class ReturnMoneyCommand extends Command {
	private static final Logger LOG = Logger.getLogger(ReturnMoneyCommand.class);
	private static final long serialVersionUID = 7535444849799592440L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		DBManager manager = DBManager.getInstance();
		String id = request.getParameter("id");
		// User u = (User) request.getSession().getAttribute("user");
		// LOG.trace("user---->"+u);
		LOG.trace("order_id---->" + id);
		if (!Util.isNumber(id))
			throw new AppException(Message.WRONG_FORMAT_OF_DATE.info());
		Order o = manager.findOrderById(Integer.parseInt(id));
		if (o == null || o.getStatusId() != Status.REJECTED.ordinal()) {
			throw new AppException(Message.WRONG_FORMAT_OF_DATE.info());
		}
		request.getSession().setAttribute("res", "Вам вернулась сумма в размере " + o.getPrice()+"$");
		manager.deleteOrderById(o.getId());

		return Path.PAGE_MY_ACCOUNTS;
	}

}
