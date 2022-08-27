package programmers.task.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {

	@Test
	void whenParseService_thenCorrect() {
		Service.parseService("12.3");
		Service.parseService("2");
		Service.parseService("*");
	}
}
