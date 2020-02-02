package adlere.ylaurelut.model;

public class Station {

	private String name;
	private int zone1;
	private int zone2;
	private long timePresentAtStation;
	
	public Station(String name, int zone) {
		this(name, zone, zone);
	}
	
	public Station(String name, int zone1, int zone2) {
		this.name = name;
		this.zone1 = zone1;
		this.zone2 = zone2;
	}

	public String getName() {
		return name;
	}

	public int getZone1() {
		return zone1;
	}

	public int getZone2() {
		return zone2;
	}
	
	public void setTimePresentAtStation(long timestamp) {
		this.timePresentAtStation = timestamp;
	}
	
	public long getTimePresentAtStation() {
		return timePresentAtStation;
	}
}
