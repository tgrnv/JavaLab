package daria.safonova.inb.adress.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SDateUtilD {
	private static final String DATE_PATTERN = "dd.MM.yyyy";
	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter
			.ofPattern(DATE_PATTERN);

	public static String sformatD(LocalDate date) {
		if (date == null)
			return null;
		return DATE_FORMATTER.format(date);
	}

	public static LocalDate sparseD(String dateString) {
		try {
			return DATE_FORMATTER.parse(dateString, LocalDate::from);
		} catch (DateTimeParseException e) {
			return null;
		}
	}

	public static boolean svalidDateD(String dateString) {
		return SDateUtilD.sparseD(dateString) != null;
	}
}
