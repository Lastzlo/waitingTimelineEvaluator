package programmers.task.models;

public class Query {

	private Service service;
	private QuestionType questionType;
	private ResponseType responseType;
	private DateInterval dateInterval;

	private int totalWaitingTime = 0;
	private int numberOfTimelines = 0;

	/**
	 * Regex for query line in the format:
	 * <pre>
	 *    D service_id[.variation_id] question_type_id[.category_id.[sub-category_id]] P/N date_from[-date_to]
	 * </pre>
	 * For example:
	 * <pre>
	 *     D 1.2 1.2.1 P 01.12.2012
	 *     D 1.2 * P 01.12.2012
	 *     D 1.2 1.2.1 N 8.10.2012-20.11.2012
	 *     D * 1.2.1 N 8.10.2012-20.11.2012
	 * </pre>
	 */
	private static final String QUERY_LINE_REGEX = "D (([0-9]*(\\.[0-9]*)?)|\\*) (([0-9]*(\\.[0-9]*)?(\\.[0-9]*)?)|\\*) [PN] (3[01]|[12][0-9]|0?[1-9])\\.(1[012]|0?[1-9])\\.((19|20)\\d{2})(\\-(3[01]|[12][0-9]|0?[1-9])\\.(1[012]|0?[1-9])\\.((19|20)\\d{2}))?$";

	private static final String QUERY_DOESNT_MATCH_PATTERN_MESSAGE = "The query line: \"%s\" doesn't match the pattern \"D service_id[.variation_id] question_type_id[.category_id.[sub-category_id]] P/N date_from[-date_to]\", please check the data";

	public Query(Service service, QuestionType questionType, ResponseType responseType, DateInterval dateInterval) {
		this.service = service;
		this.questionType = questionType;
		this.responseType = responseType;
		this.dateInterval = dateInterval;
	}

	public static Query parseQuery(String s) {
		if (s.matches(QUERY_LINE_REGEX)) {
			String[] params = s.split(" ");

			Service service = Service.parseService(params[1]);
			QuestionType questionType = QuestionType.parseQuestionType(params[2]);
			ResponseType responseType = ResponseType.parseResponseType(params[3]);
			DateInterval dateInterval = DateInterval.parseDateInterval(params[4]);

			return new Query(service, questionType, responseType, dateInterval);
		} else {
			throw new IllegalArgumentException(
					String.format(QUERY_DOESNT_MATCH_PATTERN_MESSAGE, s));
		}
	}

	public void handleWaitingTimeline(WaitingTimeline waitingTimeline) {
	}

	public String getOutput() {
		if (totalWaitingTime == 0) return "*";
		int averageWaitingTime = totalWaitingTime / numberOfTimelines;
		return Integer.toString(averageWaitingTime);
	}


}
