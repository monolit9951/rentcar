package ua.nure.bei.SummaryTask4.web.command;

import java.util.Map;
import java.util.TreeMap;

import ua.nure.bei.SummaryTask4.web.command.admin.DeleteUserCommand;
import ua.nure.bei.SummaryTask4.web.command.admin.EditCarCommand;
import ua.nure.bei.SummaryTask4.web.command.admin.EditUserCommand;
import ua.nure.bei.SummaryTask4.web.command.admin.GetUserCommand;
import ua.nure.bei.SummaryTask4.web.command.admin.NewCarCommand;
import ua.nure.bei.SummaryTask4.web.command.manager.ArrivedCarCommand;
import ua.nure.bei.SummaryTask4.web.command.manager.ArrivedCarsCommand;
import ua.nure.bei.SummaryTask4.web.command.manager.ConfirmOrderCommand;
import ua.nure.bei.SummaryTask4.web.command.manager.ConfirmReturnCommand;
import ua.nure.bei.SummaryTask4.web.command.manager.ManagerOrderEditCommand;
import ua.nure.bei.SummaryTask4.web.command.manager.ManagerOrdersEditCommand;
import ua.nure.bei.SummaryTask4.web.command.manager.RejectOrderCommand;
import ua.nure.bei.SummaryTask4.web.command.orders.FormingOrder;
import ua.nure.bei.SummaryTask4.web.command.orders.MakeOrderCommand;
import ua.nure.bei.SummaryTask4.web.command.orders.OrderGetCommand;
import ua.nure.bei.SummaryTask4.web.command.orders.OrderMenuCommand;
import ua.nure.bei.SummaryTask4.web.command.orders.OrdersWaitCommand;
import ua.nure.bei.SummaryTask4.web.command.orders.OrdersConfirmedCommand;
import ua.nure.bei.SummaryTask4.web.command.orders.OrdersRejectedCommand;
import ua.nure.bei.SummaryTask4.web.command.orders.PasteAccountCommand;
import ua.nure.bei.SummaryTask4.web.command.account.AccountNotPayedCommand;
import ua.nure.bei.SummaryTask4.web.command.account.MyAccountsCommand;
import ua.nure.bei.SummaryTask4.web.command.account.MyAccountsNotPayedCommand;
import ua.nure.bei.SummaryTask4.web.command.account.PayAccountCommand;
import ua.nure.bei.SummaryTask4.web.command.account.ReturnMoneyCommand;
import ua.nure.bei.SummaryTask4.web.command.admin.AutoRedactorCommand;
import ua.nure.bei.SummaryTask4.web.command.admin.ChangeCarCommand;
import ua.nure.bei.SummaryTask4.web.command.admin.ChangeLockCommand;
import ua.nure.bei.SummaryTask4.web.command.admin.ChangeRoleCommand;
import ua.nure.bei.SummaryTask4.web.command.admin.CreateCarCommand;
import ua.nure.bei.SummaryTask4.web.command.admin.DeleteCarCommand;

public class CommandContainer {

	private static Map<String, Command> commands = new TreeMap<String, Command>();

	static {
		// common commands
		commands.put("login", new LoginCommand());
		commands.put("logout", new LogoutCommand());
		commands.put("reg", new RegisterCommand());
		commands.put("car_list", new CarListCommand());
		commands.put("admin_user_get", new GetUserCommand());
		commands.put("admin_user_edit", new EditUserCommand());
		commands.put("admin_delete_user", new DeleteUserCommand());
		commands.put("choose_car", new ChooseCarCommand());
		commands.put("make_order", new MakeOrderCommand());
		commands.put("paste_order", new FormingOrder());
		commands.put("order_menu", new OrderMenuCommand());
		commands.put("orders_wait", new OrdersWaitCommand());
		commands.put("admin_change_role", new ChangeRoleCommand());
		commands.put("get_order", new OrderGetCommand());
		commands.put("orders_edit", new ManagerOrdersEditCommand());
		commands.put("order_edit", new ManagerOrderEditCommand());
		commands.put("confirm_order", new ConfirmOrderCommand());
		commands.put("orders_confirmed", new OrdersConfirmedCommand());
		commands.put("reject_order", new RejectOrderCommand());
		commands.put("orders_rejected", new OrdersRejectedCommand());
		commands.put("paste_account", new PasteAccountCommand());
		commands.put("arrived_cars", new ArrivedCarsCommand());
		commands.put("arrived_car", new ArrivedCarCommand());
		commands.put("confirm_return", new ConfirmReturnCommand());
		commands.put("my_accounts", new MyAccountsCommand());
		commands.put("my_accounts_not_payed", new MyAccountsNotPayedCommand());
		commands.put("account_not_payed", new AccountNotPayedCommand());
		commands.put("pay_account", new PayAccountCommand());
		commands.put("car_list_clear", new CarListClearCommand());
		commands.put("admin_change_lock", new ChangeLockCommand());
		commands.put("auto_redactor", new AutoRedactorCommand());
		commands.put("edit_car", new EditCarCommand());
		commands.put("change_car", new ChangeCarCommand());
		commands.put("new_car", new NewCarCommand());
		commands.put("create_car", new CreateCarCommand());
		commands.put("delete_car", new DeleteCarCommand());
		commands.put("return_money", new ReturnMoneyCommand());
		commands.put("task", new TaskCommand());
		// commands.put("viewSettings", new ViewSettingsCommand());
		// commands.put("noCommand", new NoCommand());

		// client commands
		// commands.put("listMenu", new ListMenuCommand());

		// admin commands
		// commands.put("listOrders", new ListOrdersCommand());

	}

	/**
	 * Returns command object with the given name.
	 * 
	 * @param commandName
	 *            Name of the command.
	 * @return Command object.
	 */
	public static Command get(String commandName) {
		if (commandName == null || !commands.containsKey(commandName)) {
			return commands.get("noCommand");
		}

		return commands.get(commandName);
	}

}