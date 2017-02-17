package crossover;

import java.util.ArrayList;

import base.Pair;
import base.Tour;
import main.Configuration;

public class HeuristicCrossover implements ICrossover {
	private Tour doSingleCrossover(Tour tour01, Tour tour02) {
		final Tour tourChild = new Tour();
		ArrayList<Integer> nonce = new ArrayList<Integer>();
		for (int i = 0; i < tour01.getSize(); i++) {
			nonce.add(i);
		}
		int t1 = getRandom(nonce);
		int t2 = tour02.getCities().indexOf(tour01.getCity(t1));
		tourChild.addCity(tour01.getCity(t1));

		while (tourChild.getSize() < tour01.getCities().size()) {
			if (nonce.size() <= 1) {
				for (int i = 0; i < tour01.getSize(); i++) {
					nonce.add(i);
				}
			}
			if (t2 >= tour02.getSize() - 1) {
				t2 = 0;
			}
			if (t1 >= tour02.getSize() - 1) {
				t1 = 0;
			}
			double distance01 = Tour.euclideanDistance(tour01.getCity(t1).getX(), tour01.getCity(t1).getY(),
					tour01.getCity(t1 + 1).getX(), tour01.getCity(t1 + 1).getY());
			double distance02 = Tour.euclideanDistance(tour02.getCity(t2).getX(), tour02.getCity(t2).getY(),
					tour02.getCity(t2 + 1).getX(), tour02.getCity(t2 + 1).getY());

			if (distance01 < distance02) {
				t1 = t1 + 1;
				if (tourChild.containsCity(tour01.getCity(t1))) {
					if (tour02.getCities().indexOf(tour01.getCity(t1 - 1)) + 1 >= tour02.getSize() - 1) {
						t2 = 0;
					} else {
						t2 = tour02.getCities().indexOf(tour01.getCity(t1 - 1)) + 1;
					}
					if (tourChild.containsCity(tour02.getCity(t2))) {
						t1 = getRandom(nonce);
					} else {
						tourChild.addCity(tour02.getCity(t2));
						t1 = tour01.getCities().indexOf(tour02.getCity(t2));
					}
				} else {
					tourChild.addCity(tour01.getCity(t1));
					t2 = tour02.getCities().indexOf(tour01.getCity(t1));
				}
			} else if (distance01 > distance02) {
				t2 = t2 + 1;
				if (tourChild.containsCity(tour02.getCity(t2))) {
					if (tour01.getCities().indexOf(tour02.getCity(t2 - 1)) + 1 >= tour02.getSize() - 1) {
						t1 = 0;
					} else {
						t1 = tour01.getCities().indexOf(tour02.getCity(t2 - 1)) + 1;
					}

					if (tourChild.containsCity(tour01.getCity(t1))) {
						t1 = getRandom(nonce);
					} else {
						tourChild.addCity(tour01.getCity(t1));
						t2 = tour02.getCities().indexOf(tour01.getCity(t1));
					}
				} else {
					tourChild.addCity(tour02.getCity(t2));
					t1 = tour01.getCities().indexOf(tour02.getCity(t2));
				}
			} else {

				t2 = t2 + 1;
				t1 = t1 + 1;
				if (tourChild.containsCity(tour02.getCity(t2))) {
					t1 = getRandom(nonce);
				} else
					tourChild.addCity(tour02.getCity(t2));

			}
		}

		return tourChild;

	}

	private int getRandom(ArrayList<Integer> d) {
		int i = Configuration.instance.randomSeed.nextInt(d.size());
		int ret = d.get(i);
		d.remove(i);
		return ret;
	}

	public String toString() {
		return getClass().getSimpleName();
	}

	@Override
	public Pair<Tour, Tour> doCrossover(Tour tour01, Tour tour02) {
		return new Pair<Tour, Tour>(doSingleCrossover(tour01, tour02), doSingleCrossover(tour02, tour01));
	}
}