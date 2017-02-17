package test.crossover;

import static org.junit.Assert.fail;

import java.util.HashMap;

import org.junit.Test;

import base.City;
import base.Tour;
import crossover.CycleCrossover;
import main.Application;
import test.AbstractTest;

public class CycleCrossoverTest extends AbstractTest {

	@Test
	public void testDoCrossover() {
		System.out.println("Starte Test CycleCrossover");
		new Application();
		Tour t1 = Tour.generateRandom();
		Tour t2 = Tour.generateRandom();
		System.out.println(t1);
		System.out.println(t2);
		Tour tk = new CycleCrossover().doCrossover(t1, t2);
		System.out.println(tk);
		HashMap<City, City> tmp = new HashMap<City, City>();
		for (City c : tk.getCities()) {
			tmp.put(c, c);
		}
		if (tmp.size() != tk.getSize())
			fail("Nicht alle Elemente vorhanden");
		for (int i = 0; i < tk.getCities().size(); i++) {
			if (!tk.getCity(i).equals(t1.getCity(i))) {
				return;
			}
		}
	}

}
