package programmers.task.models;

import org.junit.jupiter.api.Test;
import programmers.task.serviсes.DateParser;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DateIntervalTest {

	@Test
	void whenParseDateInterval() {
		DateInterval.parseDateInterval("01.12.2012");
		DateInterval.parseDateInterval("8.12.2012");
		DateInterval.parseDateInterval("8.10.2012-20.11.2012");
	}

	@Test
	void whenIsDateInInterval_thenFalse() {
		DateInterval dateInterval = DateInterval.parseDateInterval("8.10.2012-20.11.2012");
		LocalDate dateBeforeDateInterval = DateParser.parse("03.03.2003");
		assertFalse(dateInterval.isDateInInterval(dateBeforeDateInterval));

		LocalDate dateAfterDateInterval = DateParser.parse("03.03.2077");
		assertFalse(dateInterval.isDateInInterval(dateBeforeDateInterval));
	}

	@Test
	void whenIsDateInInterval_thenTrue() {
		DateInterval dateInterval = DateInterval.parseDateInterval("20.01.2012-20.11.2012");
		LocalDate date = DateParser.parse("03.05.2012");
		assertTrue(dateInterval.isDateInInterval(date));

		dateInterval = DateInterval.parseDateInterval("8.10.2012");
		date = DateParser.parse("03.03.2033");
		assertTrue(dateInterval.isDateInInterval(date));
	}
}
