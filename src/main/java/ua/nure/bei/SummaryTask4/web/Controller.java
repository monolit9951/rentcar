package ua.nure.bei.SummaryTask4.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.bei.SummaryTask4.Path;
import ua.nure.bei.SummaryTask4.exceptions.AppException;
import ua.nure.bei.SummaryTask4.web.command.Command;
import ua.nure.bei.SummaryTask4.web.command.CommandContainer;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 3297639917898747836L;
	private static final Logger LOG = Logger.getLogger(Controller.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processGet(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processPost(request, response);
	}

	private void processPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOG.debug("Controller starts(POST)");
		String commandName = request.getParameter("command");
		LOG.trace("Request parameter: command --> " + commandName);
		Command command = CommandContainer.get(commandName);
		LOG.trace("Obtained command --> " + command);
		String forward = Path.PAGE_ERROR_PAGE;
		try {
			forward = command.execute(request, response);
		} catch (AppException ex) {
			request.getSession().setAttribute("errorMessage", ex.getMessage());
			response.sendRedirect(getServletContext().getContextPath() + forward);
			return;
		} catch (Exception ex) {
			request.getSession().setAttribute("errorMessage", ex.getMessage());
			response.sendRedirect(getServletContext().getContextPath() + forward);
			return;
		}
		LOG.trace("Forward address --> " + forward);
		LOG.debug("Controller finished, now go redirect --> " + forward);
		response.sendRedirect(getServletContext().getContextPath() + forward);
	}

	private void processGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		LOG.debug("Controller starts");
		String commandName = request.getParameter("command");
		LOG.trace("Request parameter: command --> " + commandName);
		Command command = CommandContainer.get(commandName);
		LOG.trace("Obtained command --> " + command);
		String forward = Path.PAGE_ERROR_PAGE;
		try {
			forward = command.execute(request, response);
			if (forward == null) {
				throw new AppException("Error");
			}
		} catch (AppException ex) {
			request.setAttribute("errorMessage", ex.getMessage());
		}
		LOG.trace("Forward address --> " + forward);
		LOG.debug("Controller finished, now go to redirect address --> " + forward);
		request.getRequestDispatcher(forward).forward(request, response);
	}

	public Controller() {
	}
}
