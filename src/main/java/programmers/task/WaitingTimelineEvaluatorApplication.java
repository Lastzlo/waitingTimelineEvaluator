package programmers.task;

import java.util.ArrayList;

public class WaitingTimelineEvaluatorApplication {
	public static void main(String[] args) {
		String input = """
				7
				C 1.1 8.15.1 P 15.10.2012 83
				C 1 10.1 P 01.12.2012 65
				C 1.1 5.5.1 P 01.11.2012 117
				D 1.1 8 P 01.01.2012-01.12.2012
				C 3 10.2 N 02.10.2012 100
				D 1 * P 8.10.2012-20.11.2012
				D 3 10 P 01.12.2012
				""";
		String[] strings = input.split("\n");

		int countOfLines = Integer.parseInt(strings[0]);
		assert (countOfLines == 7);

		ArrayList<Query> queries = new ArrayList<>();

		assert strings[strings.length - 1].startsWith("D");
		for (int i = strings.length - 1; i > 0; i--) {
			String line = strings[i];

			if (line.startsWith("D")) {
				// add to queryLines
				Query ql = Query.parseQuery(line);
				queries.add(ql);
			} else {
				// map string to waitingTimelines
				WaitingTimeLine wt = WaitingTimeLine.parseWaitingTimeLine(line);

				// handle queries
				queries.forEach(query -> query.handleWaitingTimeLine(wt));
			}

		}

		// print results
		for (int i = queries.size() - 1; i >= 0; i--) {
			System.out.println(queries.get(i).getOutput());
		}
	}

}
