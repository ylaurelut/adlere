package adlere.ylaurelut.model;

/**
 * Traveler tap at a train station, either entering or exiting.
 * 
 * @author ylaurelu
 *
 */
public class Tap implements Comparable<Tap> {

	private long unixTimestamp;
	private int customerId;
	private String station;
	
	public Tap(int customerId, long unixTimestamp, String station) {
		this.customerId = customerId;
		this.unixTimestamp = unixTimestamp;
		this.station = station;
	}
	
	public int compareTo(Tap anotherTap) {
		return Long.compare(this.unixTimestamp, anotherTap.unixTimestamp);
	}
	
	public int getCustomerId() {
		return customerId;
	}
	
	public String getStation() {
		return station;
	}
	
	public long getUnixTimestamp() {
		return unixTimestamp;
	}
}
