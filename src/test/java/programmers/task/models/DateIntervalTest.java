package programmers.task.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateIntervalTest {

	@Test
	void whenParseDateInterval() {
		DateInterval.parseDateInterval("01.12.2012");
		DateInterval.parseDateInterval("8.12.2012");
		DateInterval.parseDateInterval("8.10.2012-20.11.2012");
	}
}
