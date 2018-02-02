package ua.nure.bei.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.bei.SummaryTask4.Message;
import ua.nure.bei.SummaryTask4.Path;
import ua.nure.bei.SummaryTask4.db.DBManager;
import ua.nure.bei.SummaryTask4.exceptions.AppException;
import ua.nure.bei.SummaryTask4.models.Car;
import ua.nure.bei.SummaryTask4.util.Util;

public class ChooseCarCommand extends Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = -427509522969683750L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		DBManager manager = DBManager.getInstance();
		String forward = Path.PAGE_CHOOSE_CAR;
		String id = request.getParameter("id");
		if (!Util.isNumber(id)) {
			throw new AppException(Message.WRONG_FORMAT_OF_DATE.info());
		}
		Car car = manager.findCarById(Integer.parseInt(id));
		if(car==null) throw new AppException(Message.CANNOT_FIND_ITEM_BY_ID.info());
		request.setAttribute("car", car);
		return forward;
	}

}
