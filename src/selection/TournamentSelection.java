package selection;

import base.Pair;
import base.Population;
import base.Tour;
import main.Configuration;
import random.MersenneTwisterFast;

import java.util.ArrayList;
import java.util.List;

public class TournamentSelection implements ISelection {

    private MersenneTwisterFast randomer;

    /**
     * @param randomer DI of MersenneTwister
     */
    public TournamentSelection(MersenneTwisterFast randomer) {
        this.randomer = randomer;
    }

    /**
     * @param population Population from which the Tours are selected from
     * @return the winner
     */
    public List<Pair<Tour, Tour>> doSelection(Population population) {
        //Create Copy of ArrayList
        ArrayList<Tour> list = new ArrayList<>(population.getTours());
        int populationSize = list.size(),
                groupSize = Configuration.instance.tournamentGroupSize,
                selectionPercentage = Configuration.instance.tourBorder,
                groupCount = this.calculateGroupCount(populationSize, selectionPercentage),
                pairCount = groupCount / 2;
        /*
            Check Plausiblity
        */
        groupSize = this.checkGroupSize(groupSize, groupCount, populationSize);
        /*
            Select a pool of challenger
        */
        int randomSelect;
        Tour[][] groups = new Tour[groupCount][groupSize];
        for (int g = 0; g < groupCount; g++) {                          //Iterate over group Count
            for (int i = 0; i < groupSize; i++) {                       //Iterate over group participants
                randomSelect = this.randomer.nextInt(populationSize);   //Get random number from 0 to length - 1 (implicit)
                groups[g][i] = list.get(randomSelect);                  //Save randomed participant
                list.remove(randomSelect);                              //Remove from List
                populationSize--;                                       //Set new length
            }
        }
        /*
            Determine Winner of Group
        */
        double max;
        int index;
        Tour[][] winners = new Tour[pairCount][2];
        for (int g = 0; g < groupCount; g++) {                          //Iterate over group Count
            max = groups[g][0].getFitness();
            index = 0;
            for (int i = 1; i < groupSize; i++) {                       //Iterate over group participants
                if (groups[g][i].getFitness() > max) {                  //Determine, if fitness is higher
                    max = groups[g][i].getFitness();                    //Set new found maximum
                    index = i;                                          //Save index of new winner
                }
            }
            winners[g / 2][g % 2] = groups[g][index];                   //Add winner
        }

        return winners;
    }

    private int checkGroupSize(int groupSize, int groupCount, int populationSize) {
        if ((groupSize * groupCount) > populationSize) {
            int newSize = populationSize / groupCount;
            System.out.println("Set group-sizes were to big and were changed to " + newSize + "!!!");
            System.out.println("Please check your configuration!");
            return newSize;
        }
        return groupSize;
    }

    private int calculateGroupCount(int length, int percentage) {
        int groupCount = (int) (length * (percentage * 0.01));
        if (groupCount % 2 == 1) groupCount++;                      //Check if straight and increment
        return groupCount;
    }

    public String toString() {
        return getClass().getSimpleName();
    }
}