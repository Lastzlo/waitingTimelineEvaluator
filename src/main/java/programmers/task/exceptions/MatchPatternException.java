package programmers.task.exceptions;

public class MatchPatternException extends RuntimeException {

	private static final String MESSAGE_TEMPLATE = "Text '%1$s' doesn't match the pattern '%2$s'";

	private final String matchedString;
	private final String pattern;

	public MatchPatternException(String matchedString, String pattern) {
		super(String.format(MESSAGE_TEMPLATE, matchedString, pattern));
		this.matchedString = matchedString;
		this.pattern = pattern;
	}

	public String getMatchedString() {
		return matchedString;
	}

	public String getPattern() {
		return pattern;
	}
}
