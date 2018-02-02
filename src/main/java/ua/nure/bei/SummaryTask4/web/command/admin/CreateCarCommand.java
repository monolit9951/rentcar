package ua.nure.bei.SummaryTask4.web.command.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.bei.SummaryTask4.Message;
import ua.nure.bei.SummaryTask4.Path;
import ua.nure.bei.SummaryTask4.db.DBManager;
import ua.nure.bei.SummaryTask4.exceptions.AppException;
import ua.nure.bei.SummaryTask4.models.Car;
import ua.nure.bei.SummaryTask4.util.Util;
import ua.nure.bei.SummaryTask4.web.command.Command;

public class CreateCarCommand extends Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1596246507463926379L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		String forward = Path.COMMAND_EDIT_CAR;
		DBManager manager = DBManager.getInstance();
		String mark = request.getParameter("mark");
		String model = request.getParameter("model");
		String autoClass = request.getParameter("autoClass");
		String imageURL = request.getParameter("imageURL");
		String body = request.getParameter("body");
		String engine = request.getParameter("engine");
		String transmission = request.getParameter("transmission");
		String fuel = request.getParameter("fuel");
		String price = request.getParameter("price");
		if (!Util.checkValidate(mark, model, autoClass, body, engine, transmission, fuel)
				|| !Util.isNumber(price) || !Util.isDouble(engine))
			throw new AppException(Message.WRONG_FORMAT_OF_DATE.info());
		if (imageURL == null || imageURL.isEmpty())
			imageURL = Car.DEFAULT_IMAGE;
		Car car = new Car(mark, model, autoClass, imageURL, body, Double.parseDouble(engine), transmission, fuel,
				Integer.parseInt(price));
		manager.addCar(car);
		return forward;
	}

}
