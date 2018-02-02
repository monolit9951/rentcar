package ua.nure.bei.SummaryTask4.listeners;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;

import ua.nure.bei.SummaryTask4.models.Role;

@WebListener
public class ContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		Map<Role, List<String>> commands = new HashMap<>();
		ServletContext sc = sce.getServletContext();
		commands.put(Role.ADMIN, stringToList(sc.getInitParameter("admin")));
		commands.put(Role.MANAGER, stringToList(sc.getInitParameter("manager")));
		commands.put(Role.USER, stringToList(sc.getInitParameter("user")));
		commands.put(Role.UNKNOWN, stringToList(sc.getInitParameter("unknown")));
		sc.setAttribute("commands", commands);
		ServletContext context = sce.getServletContext();
		String localesFileName = context.getInitParameter("locales");
		String localesFileRealPath = context.getRealPath(localesFileName);
		Properties locales = new Properties();
		try {
			locales.load(new FileInputStream(localesFileRealPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		context.setAttribute("locales", locales);
	}

	private List<String> stringToList(String initParameter) {
		if (initParameter.isEmpty())
			return new ArrayList<>();
		return Arrays.asList(initParameter.split(" "));
	}

}
