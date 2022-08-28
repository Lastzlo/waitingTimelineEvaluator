package programmers.task.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestionTypeTest {

	@Test
	void whenParseQuestionType_thenQuestionType() {
		QuestionType qt = QuestionType.parseQuestionType("*");

		assertEquals(0, qt.getId());
		assertTrue(qt.getCategoryId().isEmpty());
		assertTrue(qt.getSubCategoryId().isEmpty());
		assertTrue(qt.isMatchAll());


		qt = QuestionType.parseQuestionType("1334");

		assertEquals(1334, qt.getId());
		assertTrue(qt.getCategoryId().isEmpty());
		assertTrue(qt.getSubCategoryId().isEmpty());
		assertFalse(qt.isMatchAll());


		qt = QuestionType.parseQuestionType("1234.1212");

		assertEquals(1234, qt.getId());
		assertTrue(qt.getCategoryId().isPresent());
		assertEquals(1212, qt.getCategoryId().get());
		assertTrue(qt.getSubCategoryId().isEmpty());
		assertFalse(qt.isMatchAll());


		qt = QuestionType.parseQuestionType("1234.1212.3");

		assertEquals(1234, qt.getId());
		assertTrue(qt.getCategoryId().isPresent());
		assertEquals(1212, qt.getCategoryId().get());
		assertTrue(qt.getSubCategoryId().isPresent());
		assertEquals(3, qt.getSubCategoryId().get());
		assertFalse(qt.isMatchAll());
	}
}
