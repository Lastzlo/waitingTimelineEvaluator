package programmers.task.models;

import programmers.task.serviсes.DateParser;

import java.time.LocalDate;
import java.util.Optional;

public class DateInterval {
	private LocalDate dateFrom;
	private Optional<LocalDate> dateTo = Optional.empty();

	// regex for data in the format: dd.mm.yyyy
	private static final String DATE_REGEX = "(3[01]|[12][0-9]|0?[1-9])\\.(1[012]|0?[1-9])\\.((?:19|20)\\d{2})";

	public DateInterval(LocalDate dateFrom) {
		this.dateFrom = dateFrom;
	}

	public DateInterval(LocalDate dateFrom, LocalDate dateTo) {
		this.dateFrom = dateFrom;
		this.dateTo = Optional.of(dateTo);
	}

	public static DateInterval parseDateInterval(String s) {
		if (s.matches(DATE_REGEX)) {
			LocalDate dateFrom = DateParser.parse(s);
			return new DateInterval(dateFrom);
		} else {
			String[] strings = s.split("\\-");
			LocalDate dateFrom = DateParser.parse(strings[0]);
			LocalDate dateTo = DateParser.parse(strings[1]);
			return new DateInterval(dateFrom, dateTo);
		}
	}
}
