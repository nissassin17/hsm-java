package org.hedspi.posgresql.hedspi_student_manager.util;

public class HedspiUtil {
	public static String quoteConvert(String name) {
		return name.replaceAll("'", "''");
	}
}
