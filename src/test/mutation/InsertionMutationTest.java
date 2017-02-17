package test.mutation;

import base.City;
import base.Tour;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Filip on 17.02.2017.
 */
public class InsertionMutationTest {
    @Test
    public void doMutation() throws Exception {
        InsertionMutation test = new InsertionMutation();
        City firstCity = new City(1,1,1);
        City secondCity = new City(2,1,2);
        City thirdCity = new City(3,5,3);
        City fourthCity = new City(4,3,4);
        City fifthCity = new City(5,4,5);
        City sixthCity = new City(6,2,6);
        Tour myTestTour = new Tour();
        myTestTour.addCity(firstCity);
        myTestTour.addCity(sixthCity);
        myTestTour.addCity(thirdCity);
        myTestTour.addCity(secondCity);
        myTestTour.addCity(fifthCity);
        myTestTour.addCity(fourthCity);
        Tour dummyTour = tourCopy(myTestTour);

        // Switch 3 and 5
        test.doMutation(dummyTour,3,5);
        assertEquals(dummyTour.toString(),"{ Tour : 1 6 3 4 5 2  }");

        // Switch 1 and 5
        dummyTour = tourCopy(myTestTour);
        test.doMutation(dummyTour,1,5);
        assertEquals(dummyTour.toString(),"{ Tour : 1 4 3 2 5 6  }");

        // Switch 0 and 4
        dummyTour = tourCopy(myTestTour);
        test.doMutation(dummyTour,0,4);
        assertEquals(dummyTour.toString(),"{ Tour : 5 6 3 2 1 4  }");
    }


    public Tour tourCopy(Tour tour){
        Tour tourDummy = new Tour();
        for(int i = 0; i < tour.getCities().size(); i++){
            tourDummy.addCity(tour.getCity(i));
        }
        return tourDummy;
    }


}