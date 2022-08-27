package programmers.task;

import org.junit.jupiter.api.Test;
import programmers.task.serviсes.WaitingTimelineEvaluator;

import static org.junit.jupiter.api.Assertions.*;

class WaitingTimelineEvaluatorTest {

	@Test
	void whenEvaluate_butIssueWithFirstLine_thenIllegalArgumentException() {
		String input = """
				8
				C 1.1 8.15.1 P 15.10.2012 83
				C 1 10.1 P 01.12.2012 65
				C 1.1 5.5.1 P 01.11.2012 117
				D 1.1 8 P 01.01.2012-01.12.2012
				C 3 10.2 N 02.10.2012 100
				D 1 * P 8.10.2012-20.11.2012
				D 3 10 P 01.12.2012
				""";

		IllegalArgumentException exception = assertThrows(
				IllegalArgumentException.class, () -> WaitingTimelineEvaluator.evaluate(input));
		String exceptionMessage = exception.getMessage();
		assertEquals(
				"Issue with first line, the value of the first line: \"8\" " +
						"does not correspond to the count of all lines: \"7\"", exceptionMessage);
	}

	@Test
	void whenEvaluate_butUnknownLineType_thenIllegalArgumentException() {
		String input = """
				7
				C 1.1 8.15.1 P 15.10.2012 83
				C 1 10.1 P 01.12.2012 65
				C 1.1 5.5.1 P 01.11.2012 117
				D 1.1 8 P 01.01.2012-01.12.2012
				Z 3 10.2 N 02.10.2012 100
				D 1 * P 8.10.2012-20.11.2012
				D 3 10 P 01.12.2012
				""";

		IllegalArgumentException exception = assertThrows(
				IllegalArgumentException.class, () -> WaitingTimelineEvaluator.evaluate(input));
		String exceptionMessage = exception.getMessage();
		assertEquals("Unknown line type: \"Z 3 10.2 N 02.10.2012 100\"", exceptionMessage);
	}
}
