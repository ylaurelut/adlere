package adlere.ylaurelut.engine;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestZonePricing {

	@Test
	public void shouldReturnPrices() {
		ZonePricing zonePricing = new ZonePricing();
		assertEquals(zonePricing.getPriceFromStartToDest(1, 2), 240);
		assertEquals(zonePricing.getPriceFromStartToDest(3, 1), 280);
		assertEquals(zonePricing.getPriceFromStartToDest(4, 4), 200);
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void shouldReturnArrayIndexOutOfBoundsException() {
		ZonePricing zonePricing = new ZonePricing();
		zonePricing.getPriceFromStartToDest(1, 5);
	}
}
