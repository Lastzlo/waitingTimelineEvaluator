package programmers.task;

import java.util.Optional;

class Query {

	private int serviceId;
	private Optional<Integer> variationId = Optional.empty();

	private int questionTypeId;
	private Optional<Integer> categoryId = Optional.empty();
	private Optional<Integer> subCategoryId = Optional.empty();

	private int sumWaitingTime;

	public static Query parseQuery(String s) {
		return new Query();
	}

	public void handleWaitingTimeLine(WaitingTimeLine waitingTimeLine) {

	}

	public boolean outputIsDefined () {
		return sumWaitingTime != 0;
	}

	public String getOutput() {
		if (sumWaitingTime == 0) return "*";
		return Integer.toString(sumWaitingTime);
	}


}
