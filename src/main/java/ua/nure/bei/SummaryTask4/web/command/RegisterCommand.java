package ua.nure.bei.SummaryTask4.web.command;

import java.io.IOException;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.bei.SummaryTask4.Message;
import ua.nure.bei.SummaryTask4.db.DBManager;
import ua.nure.bei.SummaryTask4.exceptions.AppException;
import ua.nure.bei.SummaryTask4.models.Role;
import ua.nure.bei.SummaryTask4.models.User;
import ua.nure.bei.SummaryTask4.util.Util;

public class RegisterCommand extends Command {

	/**
	 * 
	 */
	private static final Logger LOG = Logger.getLogger(RegisterCommand.class);
	private static final long serialVersionUID = 7568870753664437288L;
	private static final String CHECKNUMBER_FOR_UKRAINE = "^380[\\d]{9,9}$";
	private final int MAX_SIZE_OF_DATE = 40;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.debug("Register Command");
		String forward = ua.nure.bei.SummaryTask4.Path.PAGE_LOGIN;
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String city = request.getParameter("city");
		String number = request.getParameter("number");
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		LOG.trace("request login : " + login);
		if ((!Util.checkValidate(name, surname, city, number, login, password))
				|| (!Util.checkMaxSize(MAX_SIZE_OF_DATE, name, surname, city, number, login, password)))
			throw new AppException(Message.WRONG_FORMAT_OF_DATE.info());
		Matcher mtch = Pattern.compile(CHECKNUMBER_FOR_UKRAINE).matcher(number);
		if (!mtch.find()) {
			throw new AppException(Message.WRONG_FORMAT_OF_DATE.info());
		}
		final DBManager manager = DBManager.getInstance();
		final User u = new User(login, password, name, surname, city, number, 2, Role.USER);
		manager.addUser(u); // throw AppEx
		LOG.trace("I added user ---> " + u);
		return forward;
	}

}
