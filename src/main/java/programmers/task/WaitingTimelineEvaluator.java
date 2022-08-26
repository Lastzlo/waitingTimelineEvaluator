package programmers.task;

import java.util.ArrayList;

public class WaitingTimelineEvaluator {

	public static ArrayList<String> evaluate (String input) {
		String[] lines = input.split("\n");

		int firstLineValue = Integer.parseInt(lines[0]);

		int countOfAllLines = lines.length - 1;
		assert (firstLineValue != countOfAllLines);

		if (firstLineValue != lines.length - 1) throw new IllegalArgumentException(
				String.format("The value of the first line: \"%1$s\" does not correspond to the count of all lines: \"%2$s\"", firstLineValue, countOfAllLines));

//		assert lines[lines.length - 1].startsWith("D");
		ArrayList<Query> queries = new ArrayList<>();
		for (int i = lines.length - 1; i > 0; i--) {
			String line = lines[i];

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

		ArrayList<String> results = new ArrayList<>();
		for (int i = queries.size() - 1; i >= 0; i--) {
			results.add(queries.get(i).getOutput());
		}

		return results;
	}
}
