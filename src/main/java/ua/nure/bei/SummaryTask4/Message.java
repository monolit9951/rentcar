package ua.nure.bei.SummaryTask4;

public enum Message {
	WRONG_FORMAT_OF_DATE("Wrong format of data"), MAX_RENT_CAR("Maximum rental period 1 week"), EXIST_USER(
			"Простите , но пользователь с таким логином уже существует."), CANNOT_FIND_ACC_BY_ID(
					"Cannot find account by this id"), CANNOT_OBTAIN_ACCOUTN_BY_ID(
							"Cannot obtain account by id"), CANNOT_FIND_ITEM_BY_ID(
									"Cannot find this item by id"), CANNOT_OBTAIN_CAR_BY_ID(
											"Cannot obtain car by id"), CANNOT_UPDATE_CAR(
													"Cannot update car by id"), CANNOT_DELETE_USER_BY_ID(
															"cannot delete user by id"), REJECT_WITHOUT_REASON(
																	"Cannot reject without reason..."), NO_RULES("Простите , но у вас нету прав на эту страницу..."), CANNOT_EMPTY("Пожалуйста , заполните все поля");
	String message;

	public String info() {
		return message;
	}

	Message(String message) {
		this.message = message;
	}
}
