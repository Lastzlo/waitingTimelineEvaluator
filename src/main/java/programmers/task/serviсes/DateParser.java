package programmers.task.serviсes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateParser {
	private static final String DATE_PATTERN = "dd.MM.yyyy";

	public static LocalDate parse(String s) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
		return LocalDate.parse(s, formatter);
	}
}
