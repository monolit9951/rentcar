package ua.nure.bei.SummaryTask4.models;

import java.sql.Date;

public class Account {
	
	private int id;
	private int userId;
	private int price;
	private int statusId;
	private Date dateCreate;
	private String message;

	public Account() {

	}

	public Date getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Account(int userId, int price, int statusId, String message, Date dateCreate) {
		this.userId = userId;
		this.price = price;
		this.statusId = statusId;
		this.message = message;
		this.dateCreate = dateCreate;
	}

	public Account(int userId, int price, int statusId, Date dateCreate) {
		this.userId = userId;
		this.price = price;
		this.statusId = statusId;
		this.dateCreate = dateCreate;
	}
	@Override
	public String toString() {
		return "Account [id=" + id + ", userId=" + userId + ", price=" + price + ", statusId=" + statusId
				+ ", dateCreate=" + dateCreate + ", message=" + message + "]";
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
