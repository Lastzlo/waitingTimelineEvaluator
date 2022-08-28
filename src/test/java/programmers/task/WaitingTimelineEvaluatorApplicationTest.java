package programmers.task;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class WaitingTimelineEvaluatorApplicationTest {

	@Test
	void testMain() {
		String data = """
				7
				C 1.1 8.15.1 P 15.10.2012 83
				C 1 10.1 P 01.12.2012 65
				C 1.1 5.5.1 P 01.11.2012 117
				D 1.1 8 P 01.01.2012-01.12.2012
				C 3 10.2 N 02.10.2012 100
				D 1 * P 8.10.2012-20.11.2012
				D 3 10 P 01.12.2012
				""";
		InputStream stdin = System.in;
		try {
			System.setIn(new ByteArrayInputStream(data.getBytes()));
			WaitingTimelineEvaluatorApplication.main(new String[0]);
		} finally {
			System.setIn(stdin);
		}
	}
}
