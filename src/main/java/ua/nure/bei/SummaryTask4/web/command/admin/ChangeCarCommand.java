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

public class ChangeCarCommand extends Command {

	private static final long serialVersionUID = 5405851664759061212L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		DBManager manager = DBManager.getInstance();
		String forward = Path.COMMAND_EDIT_CAR;
		String mark = request.getParameter("mark");
		String model = request.getParameter("model");
		String autoClass = request.getParameter("autoClass");
		String imageURL = request.getParameter("imageURL");
		String body = request.getParameter("body");
		String engine = request.getParameter("engine");
		String transmission = request.getParameter("transmission");
		String fuel = request.getParameter("fuel");
		String price = request.getParameter("price");
		String id = request.getParameter("id");
		if (imageURL == null || imageURL.isEmpty())
			imageURL = Car.DEFAULT_IMAGE;
		if (!Util.checkValidate(mark, model, autoClass, imageURL, body, engine, transmission, fuel)
				|| !Util.isNumber(price, id) || !Util.isDouble(engine))
			throw new AppException(Message.WRONG_FORMAT_OF_DATE.info());
		Car car = new Car(mark, model, autoClass, imageURL, body, Double.parseDouble(engine), transmission, fuel,
				Integer.parseInt(price));
		manager.changeCarById(car, Integer.parseInt(id));
		return forward;
	}

}
