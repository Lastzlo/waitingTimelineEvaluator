package programmers.task.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestionTypeTest {

	@Test
	void testParseQuestionType() {
		QuestionType questionType = QuestionType.parseQuestionType("*");

		assertEquals(0, questionType.getId());
		assertTrue(questionType.getCategoryId().isEmpty());
		assertTrue(questionType.getSubCategoryId().isEmpty());
		assertTrue(questionType.isMatchAll());


		questionType = QuestionType.parseQuestionType("1334");

		assertEquals(1334, questionType.getId());
		assertTrue(questionType.getCategoryId().isEmpty());
		assertTrue(questionType.getSubCategoryId().isEmpty());
		assertFalse(questionType.isMatchAll());


		questionType = QuestionType.parseQuestionType("1234.1212");

		assertEquals(1234, questionType.getId());
		assertTrue(questionType.getCategoryId().isPresent());
		assertEquals(1212, questionType.getCategoryId().get());
		assertTrue(questionType.getSubCategoryId().isEmpty());
		assertFalse(questionType.isMatchAll());


		questionType = QuestionType.parseQuestionType("1234.1212.3");

		assertEquals(1234, questionType.getId());
		assertTrue(questionType.getCategoryId().isPresent());
		assertEquals(1212, questionType.getCategoryId().get());
		assertTrue(questionType.getSubCategoryId().isPresent());
		assertEquals(3, questionType.getSubCategoryId().get());
		assertFalse(questionType.isMatchAll());
	}

	@Test
	void whenEquals_thenFalse() {
		QuestionType questionType = QuestionType.parseQuestionType("1.2.3");

		String string = "";
		assertNotEquals(questionType, string);

		QuestionType anotherQuestionType = QuestionType.parseQuestionType("*");
		assertNotEquals(questionType, anotherQuestionType);

		anotherQuestionType = QuestionType.parseQuestionType("2");
		assertNotEquals(questionType, anotherQuestionType);

		anotherQuestionType = QuestionType.parseQuestionType("1.5");
		assertNotEquals(questionType, anotherQuestionType);

		anotherQuestionType = QuestionType.parseQuestionType("1.2.9");
		assertNotEquals(questionType, anotherQuestionType);
	}

	@Test
	void whenEquals_thenTrue() {
		QuestionType questionType = QuestionType.parseQuestionType("1.2.3");
		QuestionType sameQuestionType = QuestionType.parseQuestionType("1.2.3");
		assertEquals(questionType, sameQuestionType);
	}

	@Test
	void testHashCode() {
		QuestionType questionType = QuestionType.parseQuestionType("1.2.3");
		assertEquals(31806, questionType.hashCode());
	}
}
