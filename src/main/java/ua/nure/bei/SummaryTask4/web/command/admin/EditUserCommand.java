package ua.nure.bei.SummaryTask4.web.command.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.bei.SummaryTask4.Path;
import ua.nure.bei.SummaryTask4.db.DBManager;
import ua.nure.bei.SummaryTask4.exceptions.AppException;
import ua.nure.bei.SummaryTask4.models.User;
import ua.nure.bei.SummaryTask4.web.command.Command;

public class EditUserCommand extends Command {
	private static final long serialVersionUID = 1L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		List<User> userList = DBManager.getInstance().findAllUsers();
		request.setAttribute("userList", userList);
		String forward = Path.PAGE_ADMIN_USER_EDIT;
		return forward;
	}

}
