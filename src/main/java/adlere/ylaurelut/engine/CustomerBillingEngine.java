package adlere.ylaurelut.engine;

import java.util.List;
import java.util.Map;

import adlere.ylaurelut.model.CustomerSummariesOutput;
import adlere.ylaurelut.model.Tap;

public class CustomerBillingEngine {

	/**
	 * This is where the action takes places :)
	 * 
	 * @return The POJO
	 */
	public CustomerSummariesOutput getCustomerSummaries(Tap[] taps) {
		CustomerSummariesOutput result = new CustomerSummariesOutput();
		TapsSorter tapsSorter = new TapsSorter(taps);
		Map<Integer, List<Tap>> tapsByCustomerId = tapsSorter.getTapsSortedByCustomerId();

		tapsByCustomerId.forEach((customerId, tapList) -> {
			TripCalculator tripCalc = new TripCalculator();
			result.addCustomerSummary(tripCalc.getCustomerSummary(customerId, tapList));
		});

		return result;
	}

}
