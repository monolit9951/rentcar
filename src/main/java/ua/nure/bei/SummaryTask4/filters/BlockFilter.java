package ua.nure.bei.SummaryTask4.filters;

import java.io.IOException;
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
import ua.nure.bei.SummaryTask4.Path;
import ua.nure.bei.SummaryTask4.db.DBManager;
import ua.nure.bei.SummaryTask4.models.Role;
import ua.nure.bei.SummaryTask4.models.User;

public class BlockFilter implements Filter {
	Map<Role, List<String>> commands = null;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (checkAcces(request)) {
			chain.doFilter(request, response);
		} else {
			request.setAttribute("errorMessage", "Your account is blocked");
			request.getRequestDispatcher(Path.PAGE_ERROR_PAGE).forward(request, response);
		}

	}

	private boolean checkAcces(ServletRequest request) {
		final HttpServletRequest httpRequest = (HttpServletRequest) request;
		final HttpSession session = httpRequest.getSession();
		final DBManager manager = DBManager.getInstance();
		User u = (User) session.getAttribute("user");
		String command = request.getParameter("command");
		if (commands.get(Role.UNKNOWN).contains(command))
			return true;
		if (u == null)
			return true;
		else
			return !manager.checkUserForBlock(u.getId());
	}

	@SuppressWarnings("unchecked")
	@Override
	public void init(FilterConfig config) throws ServletException {
		commands = (Map<Role, List<String>>) config.getServletContext().getAttribute("commands");

	}

	public void destroy() {
		// TODO Auto-generated method stub
	}

}
