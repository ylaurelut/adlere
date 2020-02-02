package adlere.ylaurelut.engine;

import static org.junit.Assert.assertTrue;

import java.io.StringReader;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import adlere.ylaurelut.model.Tap;
import adlere.ylaurelut.model.TapsInput;

public class TestTapsSorter {

	private final String inputText = "{'taps':[{'unixTimestamp':1,'customerId':1,'station':'A'},{'unixTimestamp':2,'customerId':1,'station':'D'},{'unixTimestamp':2,'customerId':2,'station':'B'},{'unixTimestamp':3,'customerId':2,'station':'C'},{'unixTimestamp':3,'customerId':3,'station':'H'},{'unixTimestamp':3,'customerId':2,'station':'H'},{'unixTimestamp':10,'customerId':2,'station':'G'},{'unixTimestamp':20,'customerId':2,'station':'D'},{'unixTimestamp':27,'customerId':3,'station':'E'},{'unixTimestamp':30,'customerId':3,'station':'E'},{'unixTimestamp':35,'customerId':3,'station':'A'},{'unixTimestamp':41,'customerId':4,'station':'A'},{'unixTimestamp':65,'customerId':2,'station':'F'},{'unixTimestamp':70,'customerId':4,'station':'E'},{'unixTimestamp':47,'customerId':4,'station':'I'},{'unixTimestamp':81,'customerId':4,'station':'F'}]}";
	private Tap[] taps;

	@Before
	public void initTaps() {
		Gson gson = new Gson();
		TapsInput tapsInput = gson.fromJson(new StringReader(inputText), TapsInput.class);
		taps = tapsInput.getTaps();
	}

	@Test
	public void tapArrayShouldBeLoaded() {
		assertTrue(taps.length == 16);
	}

	@Test
	public void shouldSortTapsByCustomerId() {
		TapsSorter tapsSorter = new TapsSorter(taps);
		Map<Integer, List<Tap>> tapsByCustomerId = tapsSorter.getTapsSortedByCustomerId();
		assertTrue(tapsByCustomerId.keySet().size() == 4);
		assertTrue(((List<Tap>)tapsByCustomerId.get(1)).size() == 2);
		assertTrue(((List<Tap>)tapsByCustomerId.get(2)).size() == 6);
		assertTrue(((List<Tap>)tapsByCustomerId.get(3)).size() == 4);
		assertTrue(((List<Tap>)tapsByCustomerId.get(4)).size() == 4);
		
		List<Tap> tapList = (List<Tap>)tapsByCustomerId.get(4);
		assertTrue(((Tap)tapList.get(0)).getUnixTimestamp() < ((Tap)tapList.get(1)).getUnixTimestamp() && 
				((Tap)tapList.get(1)).getUnixTimestamp() < ((Tap)tapList.get(2)).getUnixTimestamp() &&
				((Tap)tapList.get(2)).getUnixTimestamp() < ((Tap)tapList.get(3)).getUnixTimestamp()	); 
	}
}
