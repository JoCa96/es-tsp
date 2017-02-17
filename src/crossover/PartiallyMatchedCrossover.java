package crossover;

import java.util.ArrayList;
import java.util.List;

import base.City;
import base.Tour;
import main.Configuration;

public class PartiallyMatchedCrossover implements ICrossover {
    public Tour[] doCrossover(Tour tour01,Tour tour02) {
	 
	    List<City> parent1 = tour01.getCities();
	    List<City> parent2 = tour02.getCities();
	
	    int splitStart = Configuration.instance.randomSeed.nextInt(parent1.size());
	    int splitEnd = Configuration.instance.randomSeed.nextInt(parent2.size());
	    
	    if (splitStart > splitEnd) {
	        int tmp = splitStart;
	        splitStart = splitEnd;
	        splitEnd = tmp;
	    }
	
	    List<City> middleP1 = parent1.subList(splitStart, splitEnd);
	    List<City> middleP2 = parent2.subList(splitStart, splitEnd);
	
	    Tour child1 = createChild(parent1, middleP2, splitStart);
	    Tour child2 = createChild(parent2, middleP1, splitStart);
	    
	    return new Tour[] {child1, child2};
	}
	
	public Tour createChild(List<City> parent, List<City> middle, int splitStart) {
	    
		ArrayList<City> child = new ArrayList<City>();
		
		for (int i = 0; i < parent.size(); i++) {
			child.add(null);
		}
		
		for (int i = 0; i < middle.size(); i++){
			child.get(splitStart +i);
			child.set(splitStart + i, middle.get(i));
		}
		
		for (int j = 0; j < parent.size(); j++) {
			if(child.get(j) != null) {
				continue;
			}
			
			City input = parent.get(j);
			
			while(child.contains(input)) {
				int indexInChild = child.indexOf(input);
				input = parent.get(indexInChild);
			}
			
			child.set(j, input);
		}
		
		System.err.println(child.size());
		return new Tour(child);
	}
	
	
    public String toString() {
        return getClass().getSimpleName();
    }
}