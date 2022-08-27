package programmers.task.models;

import java.util.Optional;

public class QuestionType {

	private int id;
	private Optional<Integer> categoryId = Optional.empty();
	private Optional<Integer> subCategoryId = Optional.empty();

	private boolean isMatchAll = false;

	public QuestionType(int id) {
		this.id = id;
	}

	public QuestionType(int id, int categoryId) {
		this.id = id;
		this.categoryId = Optional.of(categoryId);
	}

	public QuestionType(int id, int categoryId, int subCategoryId) {
		this.id = id;
		this.categoryId = Optional.of(categoryId);
		this.subCategoryId = Optional.of(subCategoryId);
	}

	public static QuestionType createMatchAllQuestionType() {
		QuestionType questionType = new QuestionType();
		questionType.isMatchAll = true;
		return questionType;
	}
	private QuestionType() {}

	public static QuestionType parseQuestionType(String s) {
		if (s.matches("[0-9]*\\.[0-9]*\\.[0-9]*")) {
			String[] strings = s.split("\\.");
			int id = Integer.parseInt(strings[0]);
			int categoryId = Integer.parseInt(strings[1]);
			int subCategoryId = Integer.parseInt(strings[2]);

			return new QuestionType(id, categoryId, subCategoryId);
		} else if (s.matches("[0-9]*\\.[0-9]*")) {
			String[] strings = s.split("\\.");
			int id = Integer.parseInt(strings[0]);
			int categoryId = Integer.parseInt(strings[1]);

			return new QuestionType(id, categoryId);
		} else if (s.matches("\\*")) {
			return createMatchAllQuestionType();
		} else {
			return new QuestionType(Integer.parseInt(s));
		}
	}
}
