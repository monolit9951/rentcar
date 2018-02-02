package ua.nure.bei.SummaryTask4.web.command.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.bei.SummaryTask4.Message;
import ua.nure.bei.SummaryTask4.Path;
import ua.nure.bei.SummaryTask4.db.DBManager;
import ua.nure.bei.SummaryTask4.exceptions.AppException;
import ua.nure.bei.SummaryTask4.models.User;
import ua.nure.bei.SummaryTask4.util.Util;
import ua.nure.bei.SummaryTask4.web.command.Command;

public class GetUserCommand extends Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3283101292750145947L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		DBManager manager = DBManager.getInstance();
		String forward = Path.PAGE_ADMIN_USER_EDIT;
		String findBy = request.getParameter("findBy");
		String info = request.getParameter("info");
		switch (findBy) {
		case "id": {
			if (!Util.isNumber(info)) {
				throw new AppException(Message.WRONG_FORMAT_OF_DATE.info());
			}
			User u = manager.findUserById(Integer.parseInt(info));
			request.setAttribute("findUser", u);
			break;
		}
		case "login": {
			User u = manager.findUserByLogin(info);
			request.setAttribute("findUser", u);
			break;
		}
		}
		return forward;
	}

}
