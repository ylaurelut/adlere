package adlere.ylaurelut.engine;

import java.util.Iterator;
import java.util.List;

import adlere.ylaurelut.model.CustomerSummary;
import adlere.ylaurelut.model.Station;
import adlere.ylaurelut.model.StationFactory;
import adlere.ylaurelut.model.Tap;
import adlere.ylaurelut.model.Trip;

public class TripCalculator {

	/**
	 * Creates the summary for a customer trip, calculating the cheapest fare
	 * between each station. Working assumptions: taps come in pair (entrance tap,
	 * exit tap), and are sorted chronologically.
	 * 
	 * @param customerId The ID of the customer making this trip
	 * @param tapList    List of Tap the customer made in each station, ordered by
	 *                   increasing unixTimestamp
	 * @return The CustomerSummary created
	 * @throws InvalidStationNameException
	 */
	public CustomerSummary getCustomerSummary(int customerId, List<Tap> tapList) {
		CustomerSummary summary = new CustomerSummary(customerId);

		StationFactory stationFactory = new StationFactory();

		Iterator<Tap> iter = tapList.iterator();
		while (iter.hasNext()) {
			Tap entryTap = iter.next();
			Station startStation = stationFactory.createStation(entryTap.getStation());
			startStation.setTimePresentAtStation(entryTap.getUnixTimestamp());
			Station endStation = stationFactory.createStation(iter.next().getStation());

			summary.addTrip(createTrip(startStation, endStation));
		}

		// add all trip fares for "totalCostInCents"
		int total = 0;
		for (Trip trip : summary.getTrips()) {
			total += trip.getCostInCents();
		}
		summary.setTotalCostInCents(total);

		return summary;
	}

	/**
	 * Create a Trip object, and populates the fields, calculating the cheapest fare
	 * from tripStart to tripEnd, taking into account that each station might belong
	 * to 2 different zones.
	 * 
	 * @param tripStart Station where the trip starts
	 * @param tripEnd   Station where the trip ends
	 * @return A populated Trip object
	 */
	public Trip createTrip(Station tripStart, Station tripEnd) {
		Trip trip = new Trip();

		trip.setStationStart(tripStart.getName());
		trip.setStationEnd(tripEnd.getName());
		trip.setStartedJourneyAt(tripStart.getTimePresentAtStation());

		// find out the cheapest combination of start zones and end zones.
		// done manually : ugly but cheap enough when only 4 combinations are possible
		ZonePricing zonePricing = new ZonePricing();
		populatePriceAndZonesIfCheaper(zonePricing, trip, tripStart.getZone1(), tripEnd.getZone1());
		populatePriceAndZonesIfCheaper(zonePricing, trip, tripStart.getZone1(), tripEnd.getZone2());
		populatePriceAndZonesIfCheaper(zonePricing, trip, tripStart.getZone2(), tripEnd.getZone1());
		populatePriceAndZonesIfCheaper(zonePricing, trip, tripStart.getZone2(), tripEnd.getZone2());

		return trip;
	}

	/**
	 * Gets the fare from zoneFrom to zoneTo, and modifies the given Trip if said
	 * fare is cheaper than the costInCents of the Trip, setting also the zoneFrom
	 * and zoneTo values.
	 * 
	 * @param zonePricing A ZonePricing object, to avoid re-creating it at each call
	 *                    of this method
	 * @param trip        The Trip that must be modified if the new fare is cheaper
	 * @param zoneFrom    Zone from which the trip starts
	 * @param zoneTo      Zone where the trip ends
	 */
	public void populatePriceAndZonesIfCheaper(ZonePricing zonePricing, Trip trip, int zoneFrom, int zoneTo) {
		int costInCents = zonePricing.getPriceFromStartToDest(zoneFrom, zoneTo);
		if (trip.getCostInCents() == 0 || costInCents < trip.getCostInCents()) {
			trip.setCostInCents(costInCents);
			trip.setZoneFrom(zoneFrom);
			trip.setZoneTo(zoneTo);
		}
	}
}
