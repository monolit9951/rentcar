package ua.nure.bei.SummaryTask4.web.command.account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.bei.SummaryTask4.Message;
import ua.nure.bei.SummaryTask4.Path;
import ua.nure.bei.SummaryTask4.db.DBManager;
import ua.nure.bei.SummaryTask4.exceptions.AppException;
import ua.nure.bei.SummaryTask4.models.Account;
import ua.nure.bei.SummaryTask4.util.Util;
import ua.nure.bei.SummaryTask4.web.command.Command;

public class AccountNotPayedCommand extends Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3185953823267166874L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		DBManager manager = DBManager.getInstance();
		String forward = Path.ACCOUNT_NOT_PAYED;
		String id = request.getParameter("id");
		if (!Util.isNumber(id))
			throw new AppException(Message.WRONG_FORMAT_OF_DATE.info());
		Account account = manager.findAccountById(Integer.parseInt(id));
		if (account == null)
			throw new AppException(Message.CANNOT_FIND_ACC_BY_ID.info());
		request.setAttribute("account", account);
		return forward;
	}

}
