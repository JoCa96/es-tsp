package crossover;

import java.util.Collections;

import base.City;
import base.Pair;
import base.Tour;
import main.Configuration;

public class RandomCrossover implements ICrossover {

	@Override
	public Pair<Tour, Tour> doCrossover(Tour tour01, Tour tour02) {
		Tour t1=new Tour(), t2=new Tour();
		for(City c:tour01.getCities())
		{
			t1.addCity(c);
		}
		for(City c:tour02.getCities())
		{
			t2.addCity(c);
		}
		Collections.shuffle(t1.getCities(), Configuration.instance.randomSeed);
		Collections.shuffle(t2.getCities(), Configuration.instance.randomSeed);
		return new Pair<Tour, Tour>(t1, t2);
	}

}
