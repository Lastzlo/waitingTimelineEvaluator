package programmers.task.models;

import java.util.Optional;

public class QuestionType {

	private int id;
	private Optional<Integer> categoryId = Optional.empty();
	private Optional<Integer> subCategoryId = Optional.empty();

	private boolean isMatchAll = false;

	private QuestionType(int id) {
		this.id = id;
	}

	private QuestionType(int id, int categoryId) {
		this.id = id;
		this.categoryId = Optional.of(categoryId);
	}

	private QuestionType(int id, int categoryId, int subCategoryId) {
		this.id = id;
		this.categoryId = Optional.of(categoryId);
		this.subCategoryId = Optional.of(subCategoryId);
	}

	private static QuestionType createMatchAllQuestionType() {
		QuestionType questionType = new QuestionType();
		questionType.isMatchAll = true;
		return questionType;
	}
	private QuestionType() {}

	public int getId() {
		return id;
	}

	public Optional<Integer> getCategoryId() {
		return categoryId;
	}

	public Optional<Integer> getSubCategoryId() {
		return subCategoryId;
	}

	public boolean isMatchAll() {
		return isMatchAll;
	}

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		QuestionType that = (QuestionType) o;

		if (isMatchAll != that.isMatchAll) return false;
		if (id != that.id) return false;
		if (!categoryId.equals(that.categoryId)) return false;
		return subCategoryId.equals(that.subCategoryId);
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + categoryId.hashCode();
		result = 31 * result + subCategoryId.hashCode();
		result = 31 * result + (isMatchAll ? 1 : 0);
		return result;
	}
}
