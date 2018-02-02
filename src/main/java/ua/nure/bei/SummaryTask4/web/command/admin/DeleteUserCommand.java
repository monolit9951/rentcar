package ua.nure.bei.SummaryTask4.web.command.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.bei.SummaryTask4.Path;
import ua.nure.bei.SummaryTask4.db.DBManager;
import ua.nure.bei.SummaryTask4.exceptions.AppException;
import ua.nure.bei.SummaryTask4.web.command.Command;
import ua.nure.bei.SummaryTask4.web.command.LoginCommand;

public class DeleteUserCommand extends Command {
	private static final Logger LOG = Logger.getLogger(DeleteUserCommand.class);
	private static final long serialVersionUID = 1L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		DBManager manager = DBManager.getInstance();
		String forward = Path.PAGE_ADMIN_USER_EDIT;
		String gId = request.getParameter("id");
		int id = Integer.parseInt(gId);
		if (manager.deleteUserById(id))
			LOG.trace("Deleted user with id-->" + id);
		else
			throw new AppException("Cannot delete user with id " + id);
		return forward;
	}

}
