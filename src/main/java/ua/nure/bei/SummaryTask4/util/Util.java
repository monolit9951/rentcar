package ua.nure.bei.SummaryTask4.util;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ua.nure.bei.SummaryTask4.models.Status;

public class Util {
	static final DateFormat formatter = new SimpleDateFormat("yy-MM-dd");

	public static void main(String[] args) {
	}

	public static boolean isNumber(String... args) {
		if (args == null || args.length == 0)
			return false;
		for (String str : args) {
			if (!str.matches("[-+]?[\\d]{1,9}"))
				return false;
		}
		return true;
	}

	public static boolean checkValidate(String... args) {
		if (args == null || args.length == 0)
			return false;
		for (String str : args) {
			if (str == null || str.isEmpty())
				return false;
		}
		return true;
	}

	public static boolean checkMaxSize(int maxSize, String... args) {
		for (String str : args) {
			if (str.length() > maxSize)
				return false;
		}
		return true;
	}

	public static Date getDate(String date) {
		System.out.println("date" + " --->" + date);
		java.util.Date d = null;
		try {
			d = formatter.parse(date);
		} catch (ParseException e) {
			return null;
		}
		Date sqlDate = new Date(d.getTime());
		return sqlDate;
	}

	public static String getStatusMessage(int statusId, String mes) {
		String message = null;
		Status s = Status.values()[statusId];
		switch (s) {
		case WAITTOPAY: {
			message = "Ожидается оплата";
			break;
		}
		case PAYED: {
			message = "Оплачено. Ожидается обработка";
			break;
		}
		case CONFIRMED: {
			message = "Подтвержден";
			break;
		}
		case REJECTED: {
			message = "Отклонен. " + mes;
			break;
		}
		}
		return message;
	}

	public static boolean isDouble(String engine) {
		String regex = "((-|\\+)?[0-9]{1,9}(\\.[0-9]+)?)+";
		if (engine.matches(regex))
			return true;
		else
			return false;
	}

	public static boolean isMoney(String... args) {
		if (args == null || args.length == 0)
			return false;
		for (String str : args) {
			if (!str.matches("[\\d]{1,9}"))
				return false;
		}
		return true;
	}
}
