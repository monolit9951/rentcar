package ua.nure.bei.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import ua.nure.bei.SummaryTask4.Path;

public class LogoutCommand extends Command {

	private static final long serialVersionUID = 3992826021654188339L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String forward = Path.COMMAND_MAIN;
		final HttpSession session = request.getSession();
		session.removeAttribute("user");
		session.removeAttribute("role");
		return forward;
	}

}
