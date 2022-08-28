package programmers.task.models;

import java.util.Optional;

class Service {
	private int id;
	private Optional<Integer> variationId = Optional.empty();
	private boolean isMatchAll = false;

	public Service(int serviceId, int variationId) {
		this.id = serviceId;
		this.variationId = Optional.of(variationId);
	}

	public Service(int serviceId) {
		this.id = serviceId;
	}

	private Service() {}

	public static Service createMatchAllService() {
		Service service = new Service();
		service.isMatchAll = true;
		return service;
	}

	public int getId() {
		return id;
	}

	public Optional<Integer> getVariationId() {
		return variationId;
	}

	public boolean isMatchAll() {
		return isMatchAll;
	}

	public static Service parseService(String s) {
		if (s.matches("[0-9]*\\.[0-9]*")) {
			String[] strings = s.split("\\.");
			int serviceId = Integer.parseInt(strings[0]);
			int variationId = Integer.parseInt(strings[1]);

			return new Service(serviceId, variationId);
		} else if (s.matches("\\*")) {
			return createMatchAllService();
		} else {
			return new Service(Integer.parseInt(s));
		}
	}

	/**
	 * @param service the service we want to compare it to
	 * @return {@code true} if {@code this.id} and {@code this.variationId}
	 * the same or can wrap {@code service.id} and {@code service.variationId}
	 *
	 * For example, we have a query line with service_id.variation_id = 9.3,
	 * it can wrap the waiting timeline with service_id.variation_id = 9.3,
	 * or we have a query line with service_id.variation_id = 2,
	 * it can wrap the waiting timelines with service_id.variation_id = 2 and service_id.variation_id = 2.3 etc.
	 */
	public boolean canWrap(Service service) {
		if (isMatchAll) return true;
		if (this.id != service.id) return false;
		if (this.variationId.isPresent()) {
			if (service.variationId.isEmpty()) return false;
			return this.variationId.get().equals(service.variationId.get());
		} else return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Service service = (Service) o;

		if (isMatchAll != service.isMatchAll) return false;
		if (id != service.id) return false;
		return variationId.equals(service.variationId);
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + variationId.hashCode();
		result = 31 * result + (isMatchAll ? 1 : 0);
		return result;
	}
}
