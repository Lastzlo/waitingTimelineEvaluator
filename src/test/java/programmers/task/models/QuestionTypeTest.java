package programmers.task.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestionTypeTest {

	@Test
	void whenParseQuestionType() {
		QuestionType.parseQuestionType("*");
		QuestionType.parseQuestionType("1334");
		QuestionType.parseQuestionType("1234.1212");
		QuestionType.parseQuestionType("1234.1212.3");
	}
}
