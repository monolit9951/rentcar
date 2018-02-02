package ua.nure.bei.SummaryTask4.models;

import java.sql.Date;

public class Order {
	private int userId;
	private int id;
	private int days;
	private int price;
	private int statusId;
	private boolean withDriver;
	private Date dateStart;
	private Date dateEnd;
	private String reason;

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	// Passport
	private String name;
	private String surname;
	private String patronymic;
	private String city;
	private String sex;
	private Date borningDate;

	public Order() {

	}

	public Order(int userId, int carId, int days, int price, int statusId, boolean withDriver, Date dateStart,
			Date dateEnd, String name, String surname, String patronymic, String city, String sex, Date borningDate) {
		super();
		this.userId = userId;
		this.days = days;
		this.price = price;
		this.statusId = statusId;
		this.withDriver = withDriver;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.name = name;
		this.surname = surname;
		this.patronymic = patronymic;
		this.city = city;
		this.sex = sex;
		this.borningDate = borningDate;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPatronymic() {
		return patronymic;
	}

	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBorningDate() {
		return borningDate;
	}

	public void setBorningDate(Date borningDate) {
		this.borningDate = borningDate;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Order [userId=" + userId + ", id=" + id + ", days=" + days + ", price=" + price + ", statusId="
				+ statusId + ", withDriver=" + withDriver + ", dateStart=" + dateStart + ", dateEnd=" + dateEnd
				+ ", reason=" + reason + ", name=" + name + ", surname=" + surname + ", patronymic=" + patronymic
				+ ", city=" + city + ", sex=" + sex + ", borningDate=" + borningDate + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public boolean isWithDriver() {
		return withDriver;
	}

	public void setWithDriver(boolean withDriver) {
		this.withDriver = withDriver;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

}
