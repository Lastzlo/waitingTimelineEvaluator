package programmers.task.serviсes;

import programmers.task.models.Query;
import programmers.task.models.WaitingTimeline;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WaitingTimelineEvaluator {
	public static ArrayList<String> evaluate(ArrayList<String> lines) {
		Collections.reverse(lines);

		ArrayList<Query> queries = new ArrayList<>();
		lines.forEach(line -> {
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
		});

		ArrayList<String> result = new ArrayList<>();
		for (int i = queries.size() - 1; i >= 0; i--) {
			result.add(queries.get(i).getOutput());
		}

		return result;
	}

}
