package programmers.task.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {

	@Test
	void testParseService() {
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

	@Test
	void whenEquals_thenFalse() {
		Service service = Service.parseService("1.3");

		String string = "";
		assertNotEquals(service, string);

		Service anotherService = Service.parseService("*");
		assertNotEquals(service, anotherService);

		anotherService = Service.parseService("2");
		assertNotEquals(service, anotherService);

		anotherService = Service.parseService("1.5");
		assertNotEquals(service, anotherService);
	}

	@Test
	void whenEquals_thenTrue() {
		Service service = Service.parseService("1.3");
		Service sameService = Service.parseService("1.3");
		assertEquals(service, sameService);
	}

	@Test
	void testHashCode() {
		Service service = Service.parseService("1.3");
		assertEquals(1054, service.hashCode());
	}
}
