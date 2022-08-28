package programmers.task.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueryTest {

	@Test
	void whenParseQuery_queryLineDoesNotMatchPattern_thenIllegalArgumentException() {
		String line = "D F 3 10 P 01.12.2012";
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()-> Query.parseQuery(line));
		String exceptionMessage = exception.getMessage();
		assertEquals(
				"The query line: \"D F 3 10 P 01.12.2012\" doesn't match the pattern \"D service_id[.variation_id] question_type_id[.category_id.[sub-category_id]] P/N date_from[-date_to]\", please check the data", exceptionMessage);
	}

	@Test
	void whenParseQuery_thenCorrect() {
		Query.parseQuery("D 3 10 P 01.12.2012");
		Query.parseQuery("D 1.2 1.2.1 N 8.10.2012-20.11.2012");
		Query.parseQuery("D 1.2 * P 01.12.2012");
		Query.parseQuery("D * 1.2.1 N 8.10.2012-20.11.2012");
	}

	@Test
	void whenHandleWaitingTimeline_butWaitingTimelineServiceInvalid_thenDash() {
		// waiting timeline has different service_id
		Query query = Query.parseQuery("D 3 10 P 01.10.2012");
		WaitingTimeline wtl = WaitingTimeline.parseWaitingTimeline("C 4 10 P 01.12.2012 100");
		query.handleWaitingTimeline(wtl);
		assertEquals("-", query.getOutput());

		// waiting timeline has same service_id but has no variation_id
		query = Query.parseQuery("D 3.1 10 P 01.10.2012");
		wtl = WaitingTimeline.parseWaitingTimeline("C 3 10 P 01.12.2012 100");
		query.handleWaitingTimeline(wtl);
		assertEquals("-", query.getOutput());

		// waiting timeline has same service_id but different variation_id
		query = Query.parseQuery("D 3.1 10 P 01.10.2012");
		wtl = WaitingTimeline.parseWaitingTimeline("C 3.2 10 P 01.12.2012 100");
		query.handleWaitingTimeline(wtl);
		assertEquals("-", query.getOutput());
	}

	@Test
	void whenHandleWaitingTimeline_butWaitingTimelineQuestionTypeInvalid_thenDash() {
		// waiting timeline has different question_type_id
		Query query = Query.parseQuery("D 3.1 10.2.3 P 01.10.2012");
		WaitingTimeline wtl = WaitingTimeline.parseWaitingTimeline("C 3.1 11 P 01.12.2012 100");
		query.handleWaitingTimeline(wtl);
		assertEquals("-", query.getOutput());

		// waiting timeline has same question_type_id but has no category_id
		query = Query.parseQuery("D 3.1 10.2.3 P 01.10.2012");
		wtl = WaitingTimeline.parseWaitingTimeline("C 3.1 10 P 01.12.2012 100");
		query.handleWaitingTimeline(wtl);
		assertEquals("-", query.getOutput());

		// waiting timeline has same question_type_id but different category_id
		query = Query.parseQuery("D 3.1 10.2.3 P 01.10.2012");
		wtl = WaitingTimeline.parseWaitingTimeline("C 3.1 10.6 P 01.12.2012 100");
		query.handleWaitingTimeline(wtl);
		assertEquals("-", query.getOutput());

		// waiting timeline has same question_type_id and same category_id but has no sub-category_id
		query = Query.parseQuery("D 3.1 10.2.3 P 01.10.2012");
		wtl = WaitingTimeline.parseWaitingTimeline("C 3.1 10.2 P 01.12.2012 100");
		query.handleWaitingTimeline(wtl);
		assertEquals("-", query.getOutput());

		// waiting timeline has same question_type_id and same category_id but different sub-category_id
		query = Query.parseQuery("D 3.1 10.2.3 P 01.10.2012");
		wtl = WaitingTimeline.parseWaitingTimeline("C 3.1 10.2.7 P 01.12.2012 100");
		query.handleWaitingTimeline(wtl);
		assertEquals("-", query.getOutput());
	}

	@Test
	void whenHandleWaitingTimeline_butWaitingTimelineResponseTypeInvalid_thenDash() {
		// waiting timeline has different response type
		Query query = Query.parseQuery("D 3 10 P 01.10.2012");
		WaitingTimeline wtl = WaitingTimeline.parseWaitingTimeline("C 3 10 N 01.12.2012 100");
		query.handleWaitingTimeline(wtl);
		assertEquals("-", query.getOutput());
	}

	@Test
	void whenHandleWaitingTimeline_butWaitingTimelineDateInvalid_thenDash() {
		// waiting timeline date is before query date_from
		Query query = Query.parseQuery("D 3 10 P 12.12.2012");
		WaitingTimeline wtl = WaitingTimeline.parseWaitingTimeline("C 3 10 P 03.03.2012 100");
		query.handleWaitingTimeline(wtl);
		assertEquals("-", query.getOutput());
	}


	@Test
	void whenGetOutput_butTotalWaitingTimeInZero_thenDash() {
		Query query = Query.parseQuery("D 3.1 10 P 01.12.2012");
		assertEquals("-", query.getOutput());
	}
}
