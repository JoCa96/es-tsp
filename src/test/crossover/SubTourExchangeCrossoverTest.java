package test.crossover;

import base.Tour;
import org.junit.Test;
import test.AbstractTest;

/**
 * Created by Max on 17.02.2017.
 */
public class SubTourExchangeCrossoverTest extends AbstractTest{

    @Test
    public void TestDoCrossover(){
        Tour tour01 = Tour.generateRandom();
        Tour tour02 = Tour.generateRandom();

        System.out.println(tour01);
        System.out.println(tour02);

        Tour[] testresult = new SubTourExchangeCrossover().doCrossover(tour01, tour02);

        System.out.println(testresult[0]);
        System.out.println(testresult[1]);

        if(testresult[0].getSize() != tour01.getSize() || testresult[1].getSize() != tour02.getSize())
        {
            System.out.println("Zu wenig Zeichen!");
        }
        else if (testresult[0].equals(tour01)||(testresult[1].equals(tour02)))
        {
            System.out.println("Klasse fehlerhaft");
        }
        else System.out.println("Klasse!");
    }
}
