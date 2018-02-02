package ua.nure.bei.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.bei.SummaryTask4.Path;
import ua.nure.bei.SummaryTask4.exceptions.AppException;

public class CarListClearCommand extends Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5864318181229697224L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		String forward = Path.COMMAND_CAR_LIST;
		request.getSession().removeAttribute("sortBy");
		request.getSession().removeAttribute("search");
		request.getSession().removeAttribute("findBy");
		return forward;
	}

}
