package adlere.ylaurelut.engine;

public class ZonePricing {

	/**
	 * Stores prices for trips from one zone to another.
	 * Source zone is first index, destination zone is second index.
	 * Because arrays start at index 0, subtract 1 to zone number to have the index.
	 * For instance, a trip price from zone 1 to zone 3 is accessed by calling
	 * pricesPerZones[0][2] 
	 */
	private int[][] pricesPerZones = {
			{240, 240, 280, 300},
			{240, 240, 280, 300},
			{280, 280, 200, 200},
			{300, 300, 200, 200}
	};
	
	/**
	 * Gives the price in cents for a trip from startZone to endZone
	 * @param startZone The startZone, e.g. 1
	 * @param destZone The endZone, e.g. 3
	 * @return Price in cents
	 */
	public int getPriceFromStartToDest(int startZone, int destZone) {
		return pricesPerZones[startZone-1][destZone-1];
	}
}
