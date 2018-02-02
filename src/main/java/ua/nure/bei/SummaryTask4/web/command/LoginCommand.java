package ua.nure.bei.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.bei.SummaryTask4.Message;
import ua.nure.bei.SummaryTask4.Path;
import ua.nure.bei.SummaryTask4.db.DBManager;
import ua.nure.bei.SummaryTask4.exceptions.AppException;
import ua.nure.bei.SummaryTask4.models.Role;
import ua.nure.bei.SummaryTask4.models.User;

public class LoginCommand extends Command {
	private static final Logger LOG = Logger.getLogger(LoginCommand.class);
	private static final long serialVersionUID = -3071536593627692473L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {

		HttpSession session = request.getSession();
		DBManager manager = DBManager.getInstance();
		String login = request.getParameter("login");
		LOG.trace("Request parameter: loging --> " + login);
		String password = request.getParameter("password");
		if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
			throw new AppException(Message.CANNOT_EMPTY.info());
		}
		User user = manager.findUserByLogin(login);

		if (user == null || !password.equals(user.getPassword())) {
			throw new AppException("Ќе могу найти пользовател€ с таким логином/паролем");
		}
		Role userRole = user.getRole();
		String forward = Path.COMMAND_MAIN;
		session.setAttribute("user", user);
		LOG.trace("Set the session attribute: user --> " + user);
		session.setAttribute("role", userRole);
		LOG.trace("Set the session attribute: role --> " + userRole);
		LOG.info("User " + user + " logged as " + userRole.toString().toLowerCase());
		LOG.debug("Command finished");
		return forward;
	}

}