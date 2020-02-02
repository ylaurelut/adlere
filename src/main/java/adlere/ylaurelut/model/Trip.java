package adlere.ylaurelut.model;

/**
 * Maps the JSON structure of a trip, in the output file
 * 
 * @author ylaurelu
 *
 */
public class Trip {

	private String stationStart;
	private String stationEnd;
	private long startedJourneyAt;
	private int costInCents;
	private int zoneFrom;
	private int zoneTo;
	
	public void setStationStart(String stationStart) {
		this.stationStart = stationStart;
	}
	public void setStationEnd(String stationEnd) {
		this.stationEnd = stationEnd;
	}
	public void setStartedJourneyAt(long startedJourneyAt) {
		this.startedJourneyAt = startedJourneyAt;
	}
	public void setCostInCents(int costInCents) {
		this.costInCents = costInCents;
	}
	public void setZoneFrom(int zoneFrom) {
		this.zoneFrom = zoneFrom;
	}
	public void setZoneTo(int zoneTo) {
		this.zoneTo = zoneTo;
	}
	public int getCostInCents() {
		return this.costInCents;
	}
	public int getZoneFrom() {
		return this.zoneFrom;
	}
	public int getZoneTo() {
		return this.zoneTo;
	}
	public String getStationStart() {
		return stationStart;
	}
	public String getStationEnd() {
		return stationEnd;
	}
	public long getStartedJourneyAt() {
		return startedJourneyAt;
	}
}
