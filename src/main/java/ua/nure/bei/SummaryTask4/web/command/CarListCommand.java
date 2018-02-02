package ua.nure.bei.SummaryTask4.web.command;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.bei.SummaryTask4.Path;
import ua.nure.bei.SummaryTask4.comparators.CarsComparatorAsc;
import ua.nure.bei.SummaryTask4.comparators.CarsComparatorDesc;
import ua.nure.bei.SummaryTask4.db.DBManager;
import ua.nure.bei.SummaryTask4.exceptions.AppException;
import ua.nure.bei.SummaryTask4.models.Car;
import ua.nure.bei.SummaryTask4.util.Util;

public class CarListCommand extends Command {

	private static final long serialVersionUID = -6884410438259123427L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		final HttpSession session = request.getSession();
		String forward = Path.PAGE_CAR_LIST;
		List<Car> carList = null;
		String search = request.getParameter("search");
		String findBy = request.getParameter("findBy");
		if (findBy == null)
			findBy = "name";
		if (search != null) {
			session.setAttribute("search", search);
			session.setAttribute("findBy", findBy);
			if (findBy.equals("quility"))
				carList = DBManager.getInstance().findAllCarsWhereWhereQuility(search);
			else
				carList = DBManager.getInstance().findAllCarsWhereWhereName(search);
		} else {
			search = (String) session.getAttribute("search");
			if (search != null)
				if (findBy.equals("quility"))
					carList = DBManager.getInstance().findAllCarsWhereWhereQuility(search);
				else
					carList = DBManager.getInstance().findAllCarsWhereWhereName(search);
			else
				carList = DBManager.getInstance().findAllCars();
		}
		String sortBy = request.getParameter("sortBy");
		if (sortBy != null) {
			session.setAttribute("sortBy", sortBy);
		} else {
			sortBy = (String) session.getAttribute("sortBy");
		}
		if (sortBy != null) {
			String waySort = request.getParameter("waySort");
			if (waySort == null)
				waySort = "asc";
			switch (sortBy) {

			case "price": {
				if (waySort.equals("asc"))
					carList.sort(CarsComparatorAsc.PRICE);
				else
					carList.sort(CarsComparatorDesc.PRICE);
				break;
			}
			case "alphabet": {
				if (waySort.equals("asc"))
					carList.sort(CarsComparatorAsc.NAME);
				else
					carList.sort(CarsComparatorDesc.NAME);
				break;
			}
			}
		}
		request.setAttribute("carList", carList);
		return forward;
	}

}
