package adlere.ylaurelut.engine;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import adlere.ylaurelut.model.Station;
import adlere.ylaurelut.model.StationFactory;

public class TestStationFactory {

	private StationFactory stationFactory;
	@Before
	public void init() {
		stationFactory = new StationFactory();
	}
	
	@Test
	public void shouldCreateStationA() {
		Station stationA = stationFactory.createStation("A");
		assertEquals(stationA.getName(), "A");
		assertEquals(stationA.getZone1(), 1);
		assertEquals(stationA.getZone2(), 1);
	}
	
	@Test
	public void shouldCreateStationC() {
		Station stationA = stationFactory.createStation("C");
		assertEquals(stationA.getName(), "C");
		assertEquals(stationA.getZone1(), 2);
		assertEquals(stationA.getZone2(), 3);
	}
	
	@Test
	public void shouldCreateStationF() {
		Station stationA = stationFactory.createStation("F");
		assertEquals(stationA.getName(), "F");
		assertEquals(stationA.getZone1(), 3);
		assertEquals(stationA.getZone2(), 4);
	}
	
	@Test
	public void shouldCreateStationH() {
		Station stationA = stationFactory.createStation("H");
		assertEquals(stationA.getName(), "H");
		assertEquals(stationA.getZone1(), 4);
		assertEquals(stationA.getZone2(), 4);
	}
}
