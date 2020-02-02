package adlere.ylaurelut.model;

public class StationFactory {

	/**
	 * Create a Station object, with the right zones depending on the station name.
	 * @param name Station name
	 * @return a Station matching the given name
	 * @throws InvalidStationNameException 
	 */
	public Station createStation(String name) {
		switch(name) {
			case "A" : 
			case "B" : return new Station(name, 1);
			case "C" : return new Station(name, 2, 3);
			case "D" : return new Station(name, 2);
			case "E" : return new Station(name, 2, 3);
			case "F" : return new Station(name, 3, 4);
			case "G" :
			case "H" :
			case "I" : return new Station(name, 4);
			default : {
				System.err.println("This station name is invalid : " + name);
				return null;
			}
		}
	}
}
