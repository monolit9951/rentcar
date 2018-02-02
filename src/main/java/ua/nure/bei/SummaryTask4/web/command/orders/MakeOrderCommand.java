package ua.nure.bei.SummaryTask4.web.command.orders;

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
import ua.nure.bei.SummaryTask4.util.Util;
import ua.nure.bei.SummaryTask4.web.command.Command;

public class MakeOrderCommand extends Command {
	private static final Logger LOG = Logger.getLogger(MakeOrderCommand.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = -6697256918853878580L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		String forward = Path.PAGE_MAKE_ORDER;
		DBManager manager = DBManager.getInstance();
		String id = request.getParameter("id");
		if (!Util.isNumber(id))
			throw new AppException("wrong format of data");
		Car c = manager.findCarById(Integer.parseInt(id));
		if(c==null) throw new AppException(Message.CANNOT_FIND_ITEM_BY_ID.info());
		request.setAttribute("car", c);
		LOG.trace("get a car -->" + c);
		return forward;
	}

}
