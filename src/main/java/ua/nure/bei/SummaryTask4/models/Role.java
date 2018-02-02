package ua.nure.bei.SummaryTask4.models;

public enum Role {
	 ADMIN, MANAGER, USER, UNKNOWN;
	public static Role getRole(User user) {
		int roleId = user.getRoleId();
		return Role.values()[roleId];
	}

	public String getName() {
		return name().toLowerCase();
	}

}