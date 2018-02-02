package ua.nure.bei.SummaryTask4.web.command.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.bei.SummaryTask4.Message;
import ua.nure.bei.SummaryTask4.Path;
import ua.nure.bei.SummaryTask4.db.DBManager;
import ua.nure.bei.SummaryTask4.exceptions.AppException;
import ua.nure.bei.SummaryTask4.util.Util;
import ua.nure.bei.SummaryTask4.web.command.Command;

public class ChangeRoleCommand extends Command {

	private static final long serialVersionUID = -85791643862616551L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		DBManager manager = DBManager.getInstance();
		String forward = Path.PAGE_ADMIN_USER_EDIT;
		String roleId = request.getParameter("roleId");
		String id = request.getParameter("id");
		if (!Util.isNumber(roleId, id)) {
			throw new AppException(Message.WRONG_FORMAT_OF_DATE.info());
		}
		if (!manager.changeRoleById(Integer.parseInt(id), Integer.parseInt(roleId))) {
			throw new AppException("Cannot change user with this id " + id);
		}
		request.setAttribute("findUser", manager.findUserById(Integer.parseInt(id)));
		return forward;
	}

}
