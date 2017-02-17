package crossover;

import java.util.ArrayList;

import base.Tour;
import main.Configuration;

public class HeuristicCrossover implements ICrossover {
	private Tour doSingleCrossover(Tour tour01, Tour tour02) {
		final Tour tourChild = new Tour();
		ArrayList<Integer> nonce = new ArrayList<Integer>();
		for (int i = 0; i < tour01.getSize(); i++) {
			nonce.add(i);
		}
		int zaehler = 1;
		int t1 = gibZufall(nonce);
		int t2 = tour02.getCities().indexOf(tour01.getCity(t1));
		tourChild.addCity(tour01.getCity(t1));

		while (tourChild.getSize() < tour01.getCities().size()) {
			if(nonce.size()<=1)
			{
				for (int i = 0; i < tour01.getSize(); i++) {
					nonce.add(i);
				}
			}
			System.out.println("Zähler: " + zaehler++);
			System.out.println("Größe: " + tourChild.getSize());
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
				System.out.println(tourChild);
				System.out.println(tour01.getCity(t1));
				if (tourChild.containsCity(tour01.getCity(t1))) {
					System.out.println("if1");

					if (tour02.getCities().indexOf(tour01.getCity(t1 - 1)) + 1 >= tour02.getSize() - 1) {
						System.out.println("aT2 ist:" + t2);
						System.out.println("asize ist:" + (tour02.getSize() - 1));
						t2 = 0;
						System.out.println("abcT2 ist:" + t2);
					} else {
						t2 = tour02.getCities().indexOf(tour01.getCity(t1 - 1)) + 1;
						System.out.println("abT2 ist:" + t2);
						System.out.println("absize ist:" + (tour02.getSize() - 1));
					}
					System.out.println(tour02.getCity(t2));
					if (tourChild.containsCity(tour02.getCity(t2))) {
						System.out.println("if2");
						t1 = gibZufall(nonce);
						System.out.println("if2zufallerezugt");
					} else {
						tourChild.addCity(tour02.getCity(t2));
						t1 = tour01.getCities().indexOf(tour02.getCity(t2));
					}
				} else {
					tourChild.addCity(tour01.getCity(t1));
					t2 = tour02.getCities().indexOf(tour01.getCity(t1));
				}
			} else if (distance01 >= distance02) {
				t2 = t2 + 1;
				if (tourChild.containsCity(tour02.getCity(t2))) {
					System.out.println("if3");
					if (tour01.getCities().indexOf(tour02.getCity(t2 - 1)) + 1 >= tour02.getSize() - 1) {
						System.out.println("T1 ist:" + t1);
						System.out.println("size ist:" + (tour02.getSize() - 1));
						t1 = 0;
						System.out.println("T1 ist:" + t1);
					} else {
						t1 = tour01.getCities().indexOf(tour02.getCity(t2 - 1)) + 1;
						System.out.println("xyT1 ist:" + t1);
						System.out.println("xysize ist:" + (tour02.getSize() - 1));
					}
					System.out.println(tour02.getCity(t1));

					if (tourChild.containsCity(tour01.getCity(t1))) {
						System.out.println("if4");
						t1 = gibZufall(nonce);
						System.out.println("if4zufallerzeugt");
					} else {
						tourChild.addCity(tour01.getCity(t1));
						t2 = tour02.getCities().indexOf(tour01.getCity(t1));
					}
				} else {
					tourChild.addCity(tour02.getCity(t2));
					t1 = tour01.getCities().indexOf(tour02.getCity(t2));
				}
			} else {
				/*
				t2 = t2 + 1;
				t1 = t1 + 1;
				if (tourChild.containsCity(tour02.getCity(t2))) {
					t1 = gibZufall(nonce);
				} else
					tourChild.addCity(tour02.getCity(t2));
					*/
			}
		}
		

		return tourChild;

	}

	private int gibZufall(ArrayList<Integer> d) {
		int i = Configuration.instance.randomSeed.nextInt(d.size());
		System.out.println("i"+i);
		int ret = d.get(i);
		d.remove(i);
		System.out.println("Zufallszahl:" + ret);
		System.out.println("d Size:" + d.size());
		return ret;
	}

	public String toString() {
		return getClass().getSimpleName();
	}

	@Override
	public Tour[] doCrossover(Tour tour01, Tour tour02) {
		return new Tour[] { doSingleCrossover(tour01, tour02), doSingleCrossover(tour02, tour01) };
	}
}