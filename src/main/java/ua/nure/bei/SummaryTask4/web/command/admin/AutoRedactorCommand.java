package ua.nure.bei.SummaryTask4.web.command.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.bei.SummaryTask4.Path;
import ua.nure.bei.SummaryTask4.db.DBManager;
import ua.nure.bei.SummaryTask4.exceptions.AppException;
import ua.nure.bei.SummaryTask4.models.Car;
import ua.nure.bei.SummaryTask4.web.command.Command;

public class AutoRedactorCommand extends Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1562589253868832981L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		String forward = Path.AUTO_REDACTOR_PAGE;
		DBManager manager = DBManager.getInstance();
		List<Car> cars = manager.findAllCars();
		request.setAttribute("carList", cars);
		return forward;
	}

}
