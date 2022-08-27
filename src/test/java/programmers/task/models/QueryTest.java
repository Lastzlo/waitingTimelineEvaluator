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
}
