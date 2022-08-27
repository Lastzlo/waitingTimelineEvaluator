package programmers.task.models;

import java.util.Optional;

class Service {
	private final int id;
	private Optional<Integer> variationId = Optional.empty();

	public Service(int serviceId, int variationId) {
		this.id = serviceId;
		this.variationId = Optional.of(variationId);
	}

	public Service(int serviceId) {
		this.id = serviceId;
	}

	public static Service parseService(String s) {
		if (s.matches("[0-9]*\\.[0-9]*")) {
			String[] strings = s.split("\\.");
			int serviceId = Integer.parseInt(strings[0]);
			int variationId = Integer.parseInt(strings[1]);

			return new Service(serviceId, variationId);
		} else {
			return new Service(Integer.parseInt(s));
		}
	}

//	/**
//	 * @param service the service we want to compare it to
//	 * @return {@code true} if {@code this.id} and {@code this.variationId}
//	 * the same or can wrap {@code service.id} and {@code service.variationId}
//	 *
//	 * For example, we have a query line with service_id.variation_id = 9.3,
//	 * it can wrap the waiting timeline with service_id.variation_id = 9.3,
//	 * or we have a query line with service_id.variation_id = 2,
//	 * it can wrap the waiting timelines with service_id.variation_id = 2 and service_id.variation_id = 2.3 etc.
//	 */
//	public boolean canWrap(Service service) {
//		if (this.id != service.id) return false;
//		if (this.variationId.isPresent()) {
//			if (service.variationId.isEmpty()) return false;
//			return this.variationId.get().equals(service.variationId.get());
//		} else return true;
//	}
}
