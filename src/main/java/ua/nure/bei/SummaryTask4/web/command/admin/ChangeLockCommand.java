package ua.nure.bei.SummaryTask4.web.command.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.bei.SummaryTask4.Message;
import ua.nure.bei.SummaryTask4.Path;
import ua.nure.bei.SummaryTask4.db.DBManager;
import ua.nure.bei.SummaryTask4.exceptions.AppException;
import ua.nure.bei.SummaryTask4.util.Util;
import ua.nure.bei.SummaryTask4.web.command.Command;

public class ChangeLockCommand extends Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7613009621054961035L;
	private static final Logger LOG = Logger.getLogger(ChangeLockCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		String forward = Path.PAGE_ADMIN_USER_EDIT;
		DBManager manager = DBManager.getInstance();
		String id = request.getParameter("id");
		String lock = request.getParameter("lock");
		if (!Util.isNumber(id) || !Util.checkValidate(lock))
			throw new AppException(Message.WRONG_FORMAT_OF_DATE.info());
		switch (lock) {
		case "lock": {
			LOG.trace("lock");
			manager.addUserToLockList(Integer.parseInt(id));
			break;
		}
		case "unlock": {
			LOG.trace("unlock");
			manager.removeUserFromLockList(Integer.parseInt(id));
			break;
		}
		default:
			throw new AppException("wrong format of data");
		}
		request.setAttribute("findUser", manager.findUserById(Integer.parseInt(id)));
		return forward;
	}

}
