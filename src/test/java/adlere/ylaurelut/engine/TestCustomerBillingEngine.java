package adlere.ylaurelut.engine;

import static org.junit.Assert.assertEquals;

import java.io.StringReader;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import adlere.ylaurelut.model.CustomerSummariesOutput;
import adlere.ylaurelut.model.CustomerSummary;
import adlere.ylaurelut.model.TapsInput;
import adlere.ylaurelut.model.Trip;

public class TestCustomerBillingEngine {
	private final String inputText = "{'taps':[{'unixTimestamp':1,'customerId':1,'station':'A'},{'unixTimestamp':2,'customerId':1,'station':'D'},{'unixTimestamp':2,'customerId':2,'station':'B'},{'unixTimestamp':3,'customerId':2,'station':'C'},{'unixTimestamp':3,'customerId':3,'station':'H'},{'unixTimestamp':3,'customerId':2,'station':'H'},{'unixTimestamp':10,'customerId':2,'station':'G'},{'unixTimestamp':20,'customerId':2,'station':'D'},{'unixTimestamp':27,'customerId':3,'station':'E'},{'unixTimestamp':30,'customerId':3,'station':'E'},{'unixTimestamp':35,'customerId':3,'station':'A'},{'unixTimestamp':41,'customerId':4,'station':'A'},{'unixTimestamp':47,'customerId':4,'station':'I'},{'unixTimestamp':65,'customerId':2,'station':'F'},{'unixTimestamp':70,'customerId':4,'station':'E'},{'unixTimestamp':81,'customerId':4,'station':'F'}]}";
	private CustomerSummariesOutput cso;

	@Before
	public void initTaps() {
		Gson gson = new Gson();
		TapsInput tapsInput = gson.fromJson(new StringReader(inputText), TapsInput.class);

		CustomerBillingEngine cbe = new CustomerBillingEngine();

		cso = cbe.getCustomerSummaries(tapsInput.getTaps());
	}

	@Test
	public void shouldHave4Customers() {
		assertEquals(4, cso.getCustomerSummaries().size());
	}

	@Test
	public void testCustomer1() {
		CustomerSummary summary = cso.getCustomerSummaries().get(0);
		assertEquals(1, summary.getTrips().size());
		Trip firstTrip = summary.getTrips().get(0);
		assertEquals("A", firstTrip.getStationStart());
		assertEquals("D", firstTrip.getStationEnd());
		assertEquals(1, firstTrip.getStartedJourneyAt());
	}
	
	@Test
	public void testCustomer2() {
		CustomerSummary summary = cso.getCustomerSummaries().get(1);
		assertEquals(3, summary.getTrips().size());
		assertEquals(720, summary.getTotalCostInCents());
		Trip trip = summary.getTrips().get(0);
		assertEquals("B", trip.getStationStart());
		assertEquals("C", trip.getStationEnd());
		assertEquals(2, trip.getStartedJourneyAt());
		
		trip = summary.getTrips().get(1);
		assertEquals(4, trip.getZoneFrom());
		assertEquals(4, trip.getZoneTo());
		assertEquals(3, trip.getStartedJourneyAt());
		assertEquals(200, trip.getCostInCents());
		
		trip = summary.getTrips().get(2);
		assertEquals("D", trip.getStationStart());
		assertEquals("F", trip.getStationEnd());
		assertEquals(20, trip.getStartedJourneyAt());
		assertEquals(2, trip.getZoneFrom());
		assertEquals(3, trip.getZoneTo());
	}
}
