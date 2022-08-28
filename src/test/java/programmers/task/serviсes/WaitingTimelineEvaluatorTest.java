package programmers.task.serviсes;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WaitingTimelineEvaluatorTest {

	@Test
	void whenEvaluate_butUnknownLineType_thenIllegalArgumentException() {
		ArrayList<String> strings = new ArrayList<>(List.of("Z 1 10.1 P 01.12.2012 65"));
		IllegalArgumentException exception = assertThrows(
				IllegalArgumentException.class, () -> WaitingTimelineEvaluator.evaluate(strings));
		String exceptionMessage = exception.getMessage();
		assertEquals("Unknown line type: \"Z 1 10.1 P 01.12.2012 65\"", exceptionMessage);
	}

	@Test
	void  testEvaluate_thenList() {
		String input = """
				C 1.1 8.15.1 P 15.10.2012 83
				C 1 10.1 P 01.12.2012 65
				C 1.1 5.5.1 P 01.11.2012 117
				D 1.1 8 P 01.01.2012-01.12.2012
				C 3 10.2 N 02.10.2012 100
				D 1 * P 8.10.2012-20.11.2012
				D 3 10 P 01.12.2012
				""";

		ArrayList<String> lines = new ArrayList<>(List.of(input.split("\n")));
		ArrayList<String> results = WaitingTimelineEvaluator.evaluate(lines);
		assertEquals(3, results.size());
		assertEquals("83", results.get(0));
		assertEquals("100", results.get(1));
		assertEquals("-", results.get(2));

	}
}
