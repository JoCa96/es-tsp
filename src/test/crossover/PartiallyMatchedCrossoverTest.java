package test.crossover;

import static org.junit.Assert.fail;

import java.util.HashMap;

import org.junit.Test;

import base.City;
import base.Tour;
import crossover.PartiallyMatchedCrossover;
import test.AbstractTest;;

public class PartiallyMatchedCrossoverTest extends AbstractTest{

	@Test
	public void testDoCrossover() {
		System.out.println("Starte Test PartiallydingsdaCrossover");
		Tour t1 = Tour.generateRandom();
		Tour t2 = Tour.generateRandom();
		System.out.println(t1);
		System.out.println(t2);
		Tour[] tk = new PartiallyMatchedCrossover().doCrossover(t1, t2);
		System.out.println(tk[0]);
		System.out.println(tk[1]);
		HashMap<City, City> tmp = new HashMap<City, City>();
		for (City c : tk[0].getCities()) {
			tmp.put(c, c);
		}
		if (tmp.size() != tk[0].getSize())
			fail("Nicht alle Elemente vorhanden");
		for (int i = 0; i < tk[0].getCities().size(); i++) {
			if (!tk[0].getCity(i).equals(t1.getCity(i))) {
				return;
			}
		}
		for (City c : tk[1].getCities()) {
			tmp.put(c, c);
		}
		if (tmp.size() != tk[1].getSize())
			fail("Nicht alle Elemente vorhanden");
		for (int i = 0; i < tk[1].getCities().size(); i++) {
			if (!tk[1].getCity(i).equals(t1.getCity(i))) {
				return;
			}
		}
	}

}
