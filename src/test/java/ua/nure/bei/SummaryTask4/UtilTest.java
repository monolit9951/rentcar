package ua.nure.bei.SummaryTask4;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Test;

import ua.nure.bei.SummaryTask4.util.Util;

public class UtilTest {
	static final String LS = System.lineSeparator();

	@Test
	public void testMain() {

	}

	@Test
	public void testIsNumber() {
		String testNumber = "1832182382.342";
		assertEquals(Util.isNumber(testNumber), false);
		testNumber = "192323198318293182913982";
		assertEquals(Util.isNumber(testNumber), false);
		testNumber = "13283291";
		assertEquals(Util.isNumber(testNumber), true);
		assertEquals(Util.isNumber("-123123"), true);
	}

	@Test
	public void testIsDouble() {
		String testNumber = "1832182382.342";
		assertEquals(Util.isDouble(testNumber), true);
		testNumber = "13283291";
		assertEquals(Util.isDouble(testNumber), true);
		assertEquals(Util.isDouble("-123123"), true);
	}

	@Test
	public void testGetDate() {
		String date = "2000-11-01";
		Date d = Util.getDate(date);
		if (d == null)
			fail();
		date = "2000.11-01";
		Date d2 = Util.getDate(date);
		if (d2 != null)
			fail();
	}

}
