package programmers.task.serviсes;

import programmers.task.models.Query;
import programmers.task.models.WaitingTimeline;

import java.util.ArrayList;

public class WaitingTimelineEvaluator {

	public static ArrayList<String> evaluate(String input) {
		String[] lines = input.split("\n");

		int firstLineValue = Integer.parseInt(lines[0]);
		int countOfAllLines = lines.length - 1;

		if (firstLineValue == lines.length - 1) {
			ArrayList<Query> queries = handleLines(lines);

			ArrayList<String> result = new ArrayList<>();
			for (int i = queries.size() - 1; i >= 0; i--) {
				result.add(queries.get(i).getOutput());
			}

			return result;
		} else {
			throw new IllegalArgumentException(String.format(
					"Issue with first line, the value of the first line: \"%1$s\" " +
							"does not correspond to the count of all lines: \"%2$s\"",
					firstLineValue,
					countOfAllLines));
		}

	}

	private static ArrayList<Query> handleLines(String[] lines) {
		ArrayList<Query> queries = new ArrayList<>();
		for (int i = lines.length - 1; i > 0; i--) {
			String line = lines[i];

			if (line.startsWith("D")) {
				Query ql = Query.parseQuery(line);
				queries.add(ql);
			} else if (line.startsWith("C")) {
				WaitingTimeline wt = WaitingTimeline.parseWaitingTimeline(line);

				queries.forEach(query -> query.handleWaitingTimeline(wt));
			} else {
				throw new IllegalArgumentException(String.format(
						"Unknown line type: \"%s\"", line
				));
			}
		}
		return queries;
	}
}
