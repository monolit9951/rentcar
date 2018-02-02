package ua.nure.bei.SummaryTask4.web.command.manager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.bei.SummaryTask4.Message;
import ua.nure.bei.SummaryTask4.Path;
import ua.nure.bei.SummaryTask4.db.DBManager;
import ua.nure.bei.SummaryTask4.exceptions.AppException;
import ua.nure.bei.SummaryTask4.models.Status;
import ua.nure.bei.SummaryTask4.util.Util;
import ua.nure.bei.SummaryTask4.web.command.Command;

public class ConfirmOrderCommand extends Command {
	private static final Logger LOG = Logger.getLogger(ConfirmOrderCommand.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = -14748414857941000L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.debug("Confirm Order");
		DBManager manager = DBManager.getInstance();
		String forward = Path.COMMAND_ORDERS_EDIT;
		String id = request.getParameter("id");
		if (!Util.isNumber(id))
			throw new AppException(Message.WRONG_FORMAT_OF_DATE.info());
		manager.changeOrderStatus(Status.CONFIRMED.ordinal(), Integer.parseInt(id));
		return forward;
	}

}
