package programmers.task.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

public class DateInterval {
	private Date dateFrom;
	private Optional<Date> dateTo = Optional.empty();

	// regex for data in the format: dd.mm.yyyy
	private static final String DATE_REGEX = "(3[01]|[12][0-9]|0?[1-9])\\.(1[012]|0?[1-9])\\.((?:19|20)\\d{2})";

	public DateInterval(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public DateInterval(Date dateFrom, Date dateTo) {
		this.dateFrom = dateFrom;
		this.dateTo = Optional.of(dateTo);
	}

	public static DateInterval parseDateInterval(String s) {
		String pattern = "dd.mm.yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		try {
			if (s.matches(DATE_REGEX)) {
				Date dateFrom = simpleDateFormat.parse(s);
				return new DateInterval(dateFrom);
			} else {
				String[] strings = s.split("\\-");
				Date dateFrom = simpleDateFormat.parse(strings[0]);
				Date dateTo = simpleDateFormat.parse(strings[1]);
				return new DateInterval(dateFrom, dateTo);
			}
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
}
