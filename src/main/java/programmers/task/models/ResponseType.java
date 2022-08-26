package programmers.task.models;

public enum ResponseType {
	FIRST_ANSWER,
	NEXT_ANSWER;

	public static ResponseType parseResponseType(String s) {
		return switch (s) {
			case "P" -> FIRST_ANSWER;
			case "N" -> NEXT_ANSWER;
			default -> throw new IllegalArgumentException(String.format(
					"Response type: \"%s\" is not supported", s));
		};
	}
}
