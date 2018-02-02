package ua.nure.bei.SummaryTask4.models;

import ua.nure.bei.SummaryTask4.db.DBManager;
import ua.nure.bei.SummaryTask4.exceptions.AppException;

public class Car {
	public static final String DEFAULT_IMAGE = "default_image.jpg";

	@Override
	public String toString() {
		return "Car [id=" + id + ", mark=" + mark + ", model=" + model + ", autoClass=" + autoClass + ", imageURL="
				+ imageURL + ", body=" + body + ", engine=" + engine + ", transmission=" + transmission + ", fuel="
				+ fuel + ", price=" + price + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAutoClass() {
		return autoClass;
	}

	public void setAutoClass(String autoClass) {
		this.autoClass = autoClass;
	}

	private int id;
	private String mark;
	private String model;
	private String autoClass;
	private String imageURL = DEFAULT_IMAGE;// default
	private String body;
	private double engine;
	private String transmission;
	private String fuel;
	private int price;

	public Car() {

	}

	public Car(String mark, String model, String autoClass, String imageURL, String body, double engine,
			String transmission, String fuel, int price) {
		super();
		this.mark = mark;
		this.model = model;
		this.autoClass = autoClass;
		this.imageURL = imageURL;
		this.body = body;
		this.engine = engine;
		this.transmission = transmission;
		this.fuel = fuel;
		this.price = price;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public double getEngine() {
		return engine;
	}

	public void setEngine(double engine) {
		this.engine = engine;
	}

	public String getTransmission() {
		return transmission;
	}

	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}

	public String getFuel() {
		return fuel;
	}

	public void setFuel(String fuel) {
		this.fuel = fuel;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public static final Car rebuildDeletedCar(int id) throws AppException {
		DBManager manager = DBManager.getInstance();
		Car car = new Car();
		int carId = manager.findCarIdInOrder(id);
		car.setId(carId);
		car.setMark("Deleted");
		car.setModel("Deleted");
		return car;
	}
}
