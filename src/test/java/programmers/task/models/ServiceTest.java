package programmers.task.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {

	@Test
	void whenParseService_thenCorrect() {
		Service service = Service.parseService("12.3");

		assertEquals(12, service.getId());
		assertTrue(service.getVariationId().isPresent());
		assertEquals(3, service.getVariationId().get());
		assertFalse(service.isMatchAll());


		service = Service.parseService("2");

		assertEquals(2, service.getId());
		assertFalse(service.getVariationId().isPresent());
		assertFalse(service.isMatchAll());

		service = Service.parseService("*");

		assertEquals(0, service.getId());
		assertFalse(service.getVariationId().isPresent());
		assertTrue(service.isMatchAll());
	}
}
