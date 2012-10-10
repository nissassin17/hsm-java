package org.hsm.util;

public class HedspiUtil {
	public static String quoteConvert(String name) {
		return name.replaceAll("'", "''");
	}
}
