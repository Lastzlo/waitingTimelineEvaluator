package programmers.task.models;

import java.util.Optional;

public class QuestionType {

	private final int id;
	private Optional<Integer> categoryId = Optional.empty();
	private Optional<Integer> subCategoryId = Optional.empty();

	public QuestionType(int id) {
		this.id = id;
	}

	public QuestionType(int id, Optional<Integer> categoryId) {
		this.id = id;
		this.categoryId = categoryId;
	}

	public QuestionType(int id, Optional<Integer> categoryId, Optional<Integer> subCategoryId) {
		this.id = id;
		this.categoryId = categoryId;
		this.subCategoryId = subCategoryId;
	}
}
