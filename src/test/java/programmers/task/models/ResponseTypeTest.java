package programmers.task.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ResponseTypeTest {
	@Test
	void whenParseResponseType_thenFirstAnswer() {
		String s = "P";
		ResponseType responseType = ResponseType.parseResponseType(s);
		assertEquals(ResponseType.FIRST_ANSWER, responseType);
	}

}
