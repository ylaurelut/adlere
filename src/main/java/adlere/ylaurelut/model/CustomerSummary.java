package adlere.ylaurelut.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Maps the JSON structure for the output information of a customer
 * 
 * @author ylaurelu
 *
 */
public class CustomerSummary {

	private int customerId;
	private List<Trip> trips;
	private int totalCostInCents;
	
	public CustomerSummary(int customerId) {
		this.customerId = customerId;
		this.trips = new ArrayList<Trip>();
	}

	public void addTrip(Trip trip) {
		this.trips.add(trip);
	}

	public int getCustomerId() {
		return customerId;
	}

	public List<Trip> getTrips() {
		return trips;
	}

	public void setTotalCostInCents(int totalCostInCents) {
		this.totalCostInCents = totalCostInCents;
	}

	public int getTotalCostInCents() {
		return totalCostInCents;
	}
}
