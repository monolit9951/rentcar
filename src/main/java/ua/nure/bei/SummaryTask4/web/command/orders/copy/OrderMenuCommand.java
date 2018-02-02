package ua.nure.bei.SummaryTask4.web.command.orders.copy;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.bei.SummaryTask4.Path;
import ua.nure.bei.SummaryTask4.exceptions.AppException;
import ua.nure.bei.SummaryTask4.web.command.Command;

public class OrderMenuCommand extends Command {

	private static final long serialVersionUID = 9035405227419490044L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		String forward = Path.PAGE_ORDER_MENU;
		return forward;
	}

}
