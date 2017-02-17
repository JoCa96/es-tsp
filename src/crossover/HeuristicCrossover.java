package crossover;

import java.util.ArrayList;

import base.Tour;
import main.Configuration;

public class HeuristicCrossover implements ICrossover {
	private Tour doSingleCrossover(Tour tour01, Tour tour02) {
		final Tour tourChild = new Tour();
		ArrayList<Integer> nonce = new ArrayList<Integer>();
		int t1 = gibZufall(nonce, tour01.getSize() - 2);
		int t2 = tour02.getCities().indexOf(tour01.getCity(t1));
		tourChild.addCity(tour01.getCity(t1));

		while (tourChild.getCities().size() != tour01.getCities().size()) {
			if (t2 >= tour02.getSize() - 1)
				t2 = 0;
			if (t1 >= tour02.getSize() - 1)
				t1 = 0;
			double distance01 = Tour.euclideanDistance(tour01.getCity(t1).getX(), tour01.getCity(t1).getY(),
					tour01.getCity(t1 + 1).getX(), tour01.getCity(t1 + 1).getY());
			double distance02 = Tour.euclideanDistance(tour02.getCity(t2).getX(), tour02.getCity(t2).getY(),
					tour02.getCity(t2 + 1).getX(), tour02.getCity(t2 + 1).getY());

			if (distance01 < distance02) {
				t1 = t1 + 1;
				if (tourChild.containsCity(tour01.getCity(t1))) {
					t1 = gibZufall(nonce, tour01.getSize() - 2);
				} else {
					tourChild.addCity(tour01.getCity(t1));
					t2 = tour02.getCities().indexOf(tour01.getCity(t1));
				}
			} else if (distance01 > distance02) {
				t2 = t2 + 1;
				if (tourChild.containsCity(tour02.getCity(t2))) {
					t1 = gibZufall(nonce, tour01.getSize() - 2);
				} else {
					tourChild.addCity(tour02.getCity(t2));
					t1 = tour01.getCities().indexOf(tour02.getCity(t2));
				}
			} else {
				t2 = t2 + 1;
				t1 = t1 + 1;
				if (tourChild.containsCity(tour02.getCity(t2))) {
					t1 = gibZufall(nonce, tour02.getSize() - 2);
				} else
					tourChild.addCity(tour02.getCity(t2));
			}
		}
	

	return tourChild;

	}

	private int gibZufall(ArrayList<Integer> d, int max) {
		int ret = 0;
		do {
			ret = Configuration.instance.randomSeed.nextInt(max);
		} while (d.contains(ret));
		d.add(ret);
		return ret;
	}

	public String toString() {
		return getClass().getSimpleName();
	}

	@Override
	public Tour[] doCrossover(Tour tour01, Tour tour02) {
		return new Tour[]{doSingleCrossover(tour01, tour02), doSingleCrossover(tour02, tour01)};
	}
}