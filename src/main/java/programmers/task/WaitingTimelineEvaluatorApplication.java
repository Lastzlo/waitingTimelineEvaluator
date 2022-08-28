package programmers.task;

import programmers.task.serviсes.WaitingTimelineEvaluator;

import java.util.ArrayList;
import java.util.Scanner;

public class WaitingTimelineEvaluatorApplication {
	public static void main(String[] args) {
		System.out.println("Please paste data:");
		Scanner scanner = new Scanner(System.in);
		int countOfLines = Integer.parseInt(scanner.nextLine());
		System.out.println("Count of all lines: " + countOfLines);

		ArrayList<String> lines = new ArrayList<>();
		for (int i = 0; i < countOfLines; i++) {
			lines.add(scanner.nextLine());
		}
		scanner.close();
		System.out.println("Input:");
		lines.forEach(System.out::println);

		ArrayList<String> results = WaitingTimelineEvaluator.evaluate(lines);
		System.out.println("Output:");
		results.forEach(System.out::println);
	}

}
