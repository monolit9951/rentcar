package ua.nure.bei.SummaryTask4.web.command.account;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.bei.SummaryTask4.Path;
import ua.nure.bei.SummaryTask4.db.DBManager;
import ua.nure.bei.SummaryTask4.exceptions.AppException;
import ua.nure.bei.SummaryTask4.models.Account;
import ua.nure.bei.SummaryTask4.models.Status;
import ua.nure.bei.SummaryTask4.models.User;
import ua.nure.bei.SummaryTask4.web.command.Command;

public class MyAccountsCommand extends Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3587130131133551093L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		DBManager manager = DBManager.getInstance();
		String forward = Path.PAGE_MY_ACCOUNTS;
		User u = (User) request.getSession().getAttribute("user");
		List<Account> accounts = manager.findAllAccountsByUserIdWhereStatus(u.getId(), Status.PAYED.ordinal());
		request.setAttribute("accounts", accounts);
		return forward;
	}

}
