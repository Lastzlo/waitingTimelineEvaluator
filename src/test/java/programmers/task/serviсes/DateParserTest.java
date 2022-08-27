package programmers.task.serviсes;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DateParserTest {

	@Test
	void whenParse_thenCorrect() {
		LocalDate date = LocalDate.of(2003, 11, 25);
		String line = "25.11.2003";
		LocalDate parsedData = DateParser.parse(line);
		assertEquals(date, parsedData);

		date = LocalDate.of(2003, 11, 5);
		line = "5.11.2003";
		parsedData = DateParser.parse(line);
		assertEquals(date, parsedData);
	}
}
