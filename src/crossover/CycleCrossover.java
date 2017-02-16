package crossover;

import java.util.List;
import java.util.Vector;

import base.City;
import base.Tour;
import main.Configuration;

public class CycleCrossover implements ICrossover {
	public Tour doCrossover(Tour tour01, Tour tour02) {
		final List<Integer> cI = new Vector<Integer>();

		int t1 = Configuration.instance.randomSeed.nextInt(tour01.getSize() - 1);
		cI.add(t1);
		City t2 = tour02.getCities().get(t1);
		t1 = tour01.getCities().indexOf(t2);

		while (t1 != cI.get(0)) {
			cI.add(t1);
			t2 = tour02.getCities().get(t1);
			t1 = tour01.getCities().indexOf(t2);
		}

		for (final int index : cI) {
			City tmp=tour01.getCities().get(index);
			tour01.getCities().set(index, tour02.getCities().get(index));
			tour01.getCities().set(index, tmp);
		}
		return tour01;
	}

	public String toString() {
		return getClass().getSimpleName();
	}
}