package ua.nure.bei.SummaryTask4.models;

import java.io.Serializable;

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3462261458044070107L;

	@Override
	public String toString() {
		return "User [id=" + id + ", roleId=" + roleId + ", login=" + login + ", password=" + password + ", name="
				+ name + ", surname=" + surname + ", city=" + city + ", number=" + number + ", role=" + role + "]";
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public User(String login, String password, String name, String surname, String city, String number, int roleId,
			Role role) {
		super();
		this.roleId = roleId;
		this.login = login;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.city = city;
		this.number = number;
		this.role = role;
	}

	private int id;
	private int roleId;

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	private String login;
	private String password;
	private String name;
	private String surname;
	private String city;
	private String number;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	private Role role;

	public User() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public static void main(String[] args) {
		User u = new User();
		u.setRoleId(0);
		System.out.println(Role.getRole(u).getName());
	}
}
