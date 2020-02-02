package adlere.ylaurelut.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adlere.ylaurelut.model.Tap;

public class TapsSorter {

	private Tap[] taps;
	
	public TapsSorter(Tap[] taps) {
		this.taps = taps;
	}
	
	/**
	 * Goes through the input array of taps, and sorts each Tap by unixTimestamp for each customerId
	 * @return a Map of Tap sorted by unixTimestamp, where the map key is the customerID.
	 */
	public Map<Integer, List<Tap>> getTapsSortedByCustomerId() {
		Map<Integer, List<Tap>> tapsByCustomerId = new HashMap<Integer, List<Tap>>();
		for(int i=0; i<taps.length; i++) {
			Tap tap = taps[i];
			List<Tap> tapListForCustomerId = tapsByCustomerId.get(tap.getCustomerId());
			if(tapListForCustomerId == null) {
				List<Tap> tapList = new ArrayList<Tap>();
				tapList.add(tap);
				tapsByCustomerId.put(tap.getCustomerId(), tapList);
			} else {
				tapListForCustomerId.add(tap);
			}
		}
		
		return tapsByCustomerId;
	}
}
