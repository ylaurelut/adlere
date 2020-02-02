package adlere.ylaurelut.engine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import adlere.ylaurelut.model.CustomerSummary;
import adlere.ylaurelut.model.Station;
import adlere.ylaurelut.model.StationFactory;
import adlere.ylaurelut.model.Tap;
import adlere.ylaurelut.model.Trip;

public class TestTripCalculator {

	private ZonePricing zonePricing;
	private StationFactory stationFactory;

	@Before
	public void init() {
		zonePricing = new ZonePricing();
		stationFactory = new StationFactory();
	}
	
	@Test
	public void shouldFillThePrice() {
		Trip trip = new Trip();
		TripCalculator tripCalculator = new TripCalculator();
		tripCalculator.populatePriceAndZonesIfCheaper(zonePricing, trip, 1, 2);

		assertEquals(trip.getCostInCents(), 240);
		assertEquals(trip.getZoneFrom(), 1);
		assertEquals(trip.getZoneTo(), 2);
	}

	@Test
	public void shouldFillCheaperPrice() {
		Trip trip = new Trip();
		TripCalculator tripCalculator = new TripCalculator();
		tripCalculator.populatePriceAndZonesIfCheaper(zonePricing, trip, 3, 1);
		assertEquals(trip.getCostInCents(), 280);
		assertEquals(trip.getZoneFrom(), 3);
		assertEquals(trip.getZoneTo(), 1);

		tripCalculator.populatePriceAndZonesIfCheaper(zonePricing, trip, 2, 1);
		assertEquals(trip.getCostInCents(), 240);
		assertEquals(trip.getZoneFrom(), 2);
		assertEquals(trip.getZoneTo(), 1);
	}

	@Test
	public void shouldCreateTrip() {
		TripCalculator tripCalculator = new TripCalculator();

		Station start = stationFactory.createStation("G");
		Station end = stationFactory.createStation("D");

		Trip trip = tripCalculator.createTrip(start, end);
		assertEquals(300, trip.getCostInCents());
	}

	@Test
	public void shouldCreateCheapestTrip() {
		TripCalculator tripCalculator = new TripCalculator();

		Station start = stationFactory.createStation("F");
		Station end = stationFactory.createStation("C");

		Trip trip = tripCalculator.createTrip(start, end);
		assertEquals(200, trip.getCostInCents());
		assertEquals(3, trip.getZoneFrom());
		assertEquals(3, trip.getZoneTo());
	}
	
	@Test
	public void shouldCreateTripFromTaps() {
		TripCalculator tripCalculator = new TripCalculator();
		List<Tap> tapList = new ArrayList<Tap>();
		tapList.add(new Tap(1, 1, "A"));
		tapList.add(new Tap(1, 3, "C"));
		tapList.add(new Tap(1, 6, "F"));
		tapList.add(new Tap(1, 8, "B"));
		
		CustomerSummary summary = tripCalculator.getCustomerSummary(1, tapList);
		assertEquals(1, summary.getCustomerId());
		assertNotNull(summary.getTrips());
		assertEquals(2, summary.getTrips().size());
		Trip trip = summary.getTrips().get(0);
		assertEquals(1, trip.getZoneFrom());
		assertEquals(2, trip.getZoneTo());
		assertEquals(240, trip.getCostInCents());
		assertEquals(1, trip.getStartedJourneyAt());

		trip = summary.getTrips().get(1);
		assertEquals(3, trip.getZoneFrom());
		assertEquals(1, trip.getZoneTo());
		assertEquals(280, trip.getCostInCents());
		assertEquals(6, trip.getStartedJourneyAt());
		
		assertEquals(520, summary.getTotalCostInCents());
	}
}
