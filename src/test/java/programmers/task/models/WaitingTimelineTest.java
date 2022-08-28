package programmers.task.models;

import org.junit.jupiter.api.Test;
import programmers.task.serviсes.DateParser;

import static org.junit.jupiter.api.Assertions.*;

class WaitingTimelineTest {

	@Test
	void whenParseWaitingTimeline_butWaitingTimelineDoesNotMatchPattern_thenIllegalArgumentException() {
		String line = "C 1.1 8.15.1 Mistake 15.10.2012 83";
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()-> WaitingTimeline.parseWaitingTimeline(line));
		String exceptionMessage = exception.getMessage();
		assertEquals(
				"The waiting timeline: \"C 1.1 8.15.1 Mistake 15.10.2012 83\" doesn't match the pattern \"C service_id[.variation_id] question_type_id[.category_id.[sub-category_id]] P/N date time\", please check the data", exceptionMessage);
	}

	@Test
	void whenParseWaitingTimeline_thenCorrect() {
		WaitingTimeline wtl = WaitingTimeline.parseWaitingTimeline("C 1 10.1 N 01.12.2012 65");

		assertEquals(Service.parseService("1"), wtl.getService());
		assertEquals(QuestionType.parseQuestionType("10.1"), wtl.getQuestionType());
		assertEquals(ResponseType.parseResponseType("N"), wtl.getResponseType());
		assertEquals(DateParser.parse("01.12.2012"), wtl.getDate());
		assertEquals(65, wtl.getTime());


		wtl = WaitingTimeline.parseWaitingTimeline("C 1.1 8.15.1 P 15.10.2012 83");

		assertEquals(Service.parseService("1.1"), wtl.getService());
		assertEquals(QuestionType.parseQuestionType("8.15.1"), wtl.getQuestionType());
		assertEquals(ResponseType.parseResponseType("P"), wtl.getResponseType());
		assertEquals(DateParser.parse("15.10.2012"), wtl.getDate());
		assertEquals(83, wtl.getTime());

	}
}
