package ua.nure.bei.SummaryTask4.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ua.nure.bei.SummaryTask4.Message;
import ua.nure.bei.SummaryTask4.Path;
import ua.nure.bei.SummaryTask4.models.Role;

public class AuthFilter implements Filter {
	Map<Role, List<String>> commands = null;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		if (checkAcces(request)) {
			chain.doFilter(request, response);
		} else {
			request.setAttribute("errorMessage", Message.NO_RULES.info());
			request.getRequestDispatcher(Path.PAGE_ERROR_PAGE).forward(request, response);
		}

	}

	private boolean checkAcces(ServletRequest request) {
		final HttpServletRequest httpRequest = (HttpServletRequest) request;
		final HttpSession session = httpRequest.getSession();
		String command = request.getParameter("command");
		if (command == null || command.isEmpty())
			return true;
		if (commands.get(Role.UNKNOWN).contains(command)) {
			return true;
		}
		Role userRole = (Role) session.getAttribute("role");
		if (commands.get(Role.USER).contains(command)) {
			if (userRole == Role.USER || userRole == Role.MANAGER || userRole == Role.ADMIN)
				return true;
		}
		if (commands.get(Role.MANAGER).contains(command)) {
			if (userRole == Role.MANAGER || userRole == Role.ADMIN)
				return true;
		}

		if (commands.get(Role.ADMIN).contains(command)) {
			if (userRole == Role.ADMIN)
				return true;
		}
		return false;
	}

	public void destroy() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public void init(FilterConfig config) throws ServletException {
		commands = (Map<Role, List<String>>) config.getServletContext().getAttribute("commands");

	}

}
