package ua.nure.bei.SummaryTask4.web.command.orders.copy;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.bei.SummaryTask4.Message;
import ua.nure.bei.SummaryTask4.Path;
import ua.nure.bei.SummaryTask4.db.DBManager;
import ua.nure.bei.SummaryTask4.exceptions.AppException;
import ua.nure.bei.SummaryTask4.models.Account;
import ua.nure.bei.SummaryTask4.models.Car;
import ua.nure.bei.SummaryTask4.models.Order;
import ua.nure.bei.SummaryTask4.models.Status;
import ua.nure.bei.SummaryTask4.web.command.Command;

public class PasteAccountCommand extends Command {

	private static final long serialVersionUID = -5908430896216601947L;
	private static final Logger LOG = Logger.getLogger(PasteAccountCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		DBManager manager = DBManager.getInstance();
		String forward = Path.COMMAND_MAIN;
		HttpSession session = request.getSession();
		Car c = (Car) session.getAttribute("car");
		Order o = (Order) session.getAttribute("order");
		Account a = (Account) session.getAttribute("account");
		if (o == null || c == null)
			throw new AppException(Message.WRONG_FORMAT_OF_DATE.info());
		a.setStatusId(Status.PAYED.ordinal());
		o.setStatusId(Status.PAYED.ordinal());
		a.setMessage("Покупка");
		if (!(manager.addOrder(o) && manager.addOrderIdCarId(o.getId(), c.getId()) && manager.addAccount(a)))
			throw new AppException("something is going wrong. Tell us about the problem");
		request.setAttribute("resMessage", "Готово. Приятной езды!");
		LOG.trace("account-->" + a);
		LOG.trace("order-->" + o); //
		session.removeAttribute("order");
		session.removeAttribute("car");
		session.removeAttribute("account");
		return forward;

	}

}
