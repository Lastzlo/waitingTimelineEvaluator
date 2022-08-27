package programmers.task.models;

import programmers.task.serviсes.DateParser;

import java.time.LocalDate;

public class WaitingTimeline {

	private Service service;
	private QuestionType questionType;
	private ResponseType responseType;
	private LocalDate date;
	private int time;

	/**
	 * Regex for waiting timeline in the format:
	 * <pre>
	 *    C service_id[.variation_id] question_type_id[.category_id.[sub-category_id]] P/N date time
	 * </pre>
	 * For example:
	 * <pre>
	 *     C 1.1 8.15.1 P 15.10.2012 83
	 *     C 1 10.1 N 01.12.2012 65
	 * </pre>
	 */
	private static final String WAITING_TIMELINE_REGEX = "C ([0-9]*(\\.[0-9]*)?) ([0-9]*(\\.[0-9]*)?(\\.[0-9]*)?) [PN] (3[01]|[12][0-9]|0?[1-9])\\.(1[012]|0?[1-9])\\.((19|20)\\d{2}) [0-9]*$";

	private static final String WAITING_TIMELINE_DOESNT_MATCH_PATTERN_MESSAGE = "The waiting timeline: \"%s\" doesn't match the pattern \"C service_id[.variation_id] question_type_id[.category_id.[sub-category_id]] P/N date time\", please check the data";

	public WaitingTimeline(Service service, QuestionType questionType, ResponseType responseType, LocalDate date, int time) {
		this.service = service;
		this.questionType = questionType;
		this.responseType = responseType;
		this.date = date;
		this.time = time;
	}

	public static WaitingTimeline parseWaitingTimeline(String s) {
		if (s.matches(WAITING_TIMELINE_REGEX)) {
			String[] params = s.split(" ");

			Service service = Service.parseService(params[1]);
			QuestionType questionType = QuestionType.parseQuestionType(params[2]);
			ResponseType responseType = ResponseType.parseResponseType(params[3]);
			LocalDate date = DateParser.parse(params[4]);
			int time = Integer.parseInt(params[5]);

			return new WaitingTimeline(service, questionType, responseType, date, time);
		} else {
			throw new IllegalArgumentException(
					String.format(WAITING_TIMELINE_DOESNT_MATCH_PATTERN_MESSAGE, s));
		}
	}


}
