package programmers.task.serviсes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateParser {
	private static final String DATE_PATTERN = "dd.MM.yyyy";
	private static final String DATE_PATTERN_WHEN_DAY_IN_ONE_CHAR = "d.MM.yyyy";

	public static LocalDate parse(String s) {
		DateTimeFormatter formatter;
		if (s.length() == 9) {
			formatter = DateTimeFormatter.ofPattern(DATE_PATTERN_WHEN_DAY_IN_ONE_CHAR);
		} else {
			formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
		}

		return LocalDate.parse(s, formatter);
	}
}
