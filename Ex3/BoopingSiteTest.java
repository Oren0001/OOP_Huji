import oop.ex3.searchengine.Hotel;
import oop.ex3.searchengine.HotelDataset;
import org.junit.*;
import static org.junit.Assert.*;
import java.lang.Math;

/**
 * Tests the different methods of the BoopingSite's API.
 */
public class BoopingSiteTest {

    private static final String CITY_WITHOUT_HOTELS = "Mos Eisely";
    private static BoopingSite boopingSiteBig, boopingSiteSmall, boopingSiteEmpty;
    private static Hotel[] hotelsBig, hotelsSmall;

    /**
     * Creates objects once before all the tests are run.
     */
    @BeforeClass
    public static void createObjectsBeforeClass() {
        boopingSiteBig = new BoopingSite("hotels_dataset.txt");
        boopingSiteSmall = new BoopingSite("hotels_tst1.txt");
        boopingSiteEmpty = new BoopingSite("hotels_tst2.txt");

        hotelsBig = HotelDataset.getHotels("hotels_dataset.txt");
        hotelsSmall = HotelDataset.getHotels("hotels_tst1.txt");
    }


    /*----=  The following code snippet will test the method getHotelsInCityByRating  =-----*/


    /**
     * Tests cases where the method, getHotelsInCityByRating, doesn't meet the sorting requirements.
     */
    @Test
    public void testByRatingAndProperty() {
        Hotel[] sortedHotels = boopingSiteSmall.getHotelsInCityByRating(hotelsSmall[0].getCity());
        for (int i=0; i<(sortedHotels.length - 1); i++) {

            int rating1 = sortedHotels[i].getStarRating();
            int rating2 = sortedHotels[i+1].getStarRating();
            String property1 = sortedHotels[i].getPropertyName();
            String property2 = sortedHotels[i+1].getPropertyName();

            if (rating1 == rating2 && property1.equals(property2))
                continue;
            else if (rating1 == rating2)
                assertTrue(0 > property1.compareTo(property2));
            else
                assertTrue(rating1 > rating2);
        }
    }


    /**
     * Tests cases where the hotels aren't located in the given city.
     */
    @Test
    public void testInCityByRating() {
        Hotel[] sortedHotels1 = boopingSiteSmall.getHotelsInCityByRating(hotelsSmall[1].getCity());
        for (Hotel hotel: sortedHotels1)
            assertEquals(hotelsSmall[1].getCity(), hotel.getCity());
        Hotel[] sortedHotels2 = boopingSiteBig.getHotelsInCityByRating(hotelsBig[0].getCity());
        for (Hotel hotel: sortedHotels2)
            assertEquals(hotelsBig[0].getCity(), hotel.getCity());
    }


    /**
     * Tests cases where the method, getHotelsInCityByRating, should return an empty array.
     */
    @Test
    public void testInCityByRatingReturnEmpty() {
        assertEquals(0, boopingSiteBig.getHotelsInCityByRating(CITY_WITHOUT_HOTELS).length);
        assertEquals(0, boopingSiteSmall.getHotelsInCityByRating(CITY_WITHOUT_HOTELS).length);
        assertEquals(0, boopingSiteEmpty.getHotelsInCityByRating(hotelsSmall[1].getCity()).length);
    }


    /*----=  End of getHotelsInCityByRating test  =-----*/
    /*----=  The following code snippet will test the method getHotelsByProximity  =-----*/


    /**
     * Tests cases where the method, getHotelsByProximity, doesn't meet the sorting requirements.
     */
    @Test
    public void testNotInCityByProximity() {
        Hotel[] sortedHotels = boopingSiteBig.getHotelsByProximity(50, 20);
        for (int i=0; i<(sortedHotels.length - 1); i++) {
            int result = compareDistances(sortedHotels[i], sortedHotels[i+1], 50,20);
            if (result == 0 && sortedHotels[i].getNumPOI() == sortedHotels[i+1].getNumPOI())
                continue;
            else if (result == 0)
                assertTrue(sortedHotels[i].getNumPOI() > sortedHotels[i+1].getNumPOI());
            else
                assertEquals(-1,result);
        }
    }


    /**
     * Tests cases where the method, getHotelsByProximity, should return an empty array.
     */
    @Test
    public void testByProximityReturnEmpty() {
        assertEquals(0, boopingSiteBig.getHotelsByProximity(91, 20).length);
        assertEquals(0, boopingSiteSmall.getHotelsByProximity(-91, 20).length);
        assertEquals(0, boopingSiteBig.getHotelsByProximity(88, 181).length);
        assertEquals(0, boopingSiteSmall.getHotelsByProximity(88, -181).length);
        assertEquals(0, boopingSiteBig.getHotelsByProximity(-91, -181).length);
        assertEquals(0, boopingSiteSmall.getHotelsByProximity(91, 181).length);
        assertEquals(0, boopingSiteBig.getHotelsByProximity(-91, 181).length);
        assertEquals(0, boopingSiteSmall.getHotelsByProximity(91, -181).length);
        assertEquals(0,boopingSiteEmpty.getHotelsByProximity(60,70).length);
    }


    /*----=  End of getHotelsByProximity test  =-----*/
    /*----=  The following code snippet will test the method getHotelsInCityByProximity  =-----*/


    /**
     * Tests cases where the method, getHotelsInCityByProximity, doesn't meet the sorting requirements.
     */
    @Test
    public void testByProximityAndPOI() {
        Hotel[] sortedHotels = boopingSiteBig.getHotelsInCityByProximity(hotelsSmall[0].getCity(),
                -10, -50);
        for (int i=0; i<(sortedHotels.length - 1); i++) {
            int result = compareDistances(sortedHotels[i], sortedHotels[i+1], -10,-50);
            if (result == 0 && sortedHotels[i].getNumPOI() == sortedHotels[i+1].getNumPOI())
                continue;
            else if (result == 0)
                assertTrue(sortedHotels[i].getNumPOI() > sortedHotels[i+1].getNumPOI());
            else
                assertEquals(-1,result);
        }
    }


    /**
     * Tests cases where the hotels aren't located in the given city.
     */
    @Test
    public void testInCityByProximity() {
        Hotel[] sortedHotels1 = boopingSiteSmall.getHotelsInCityByProximity(hotelsSmall[5].getCity(),
                40, -90);
        for (Hotel hotel: sortedHotels1)
            assertEquals(hotelsSmall[5].getCity(), hotel.getCity());
        Hotel[] sortedHotels2 = boopingSiteBig.getHotelsInCityByProximity(hotelsBig[5].getCity(),
                0,100);
        for (Hotel hotel: sortedHotels2)
            assertEquals(hotelsBig[5].getCity(), hotel.getCity());
    }


    /**
     * Tests cases where the method, getHotelsInCityByProximity, should return an empty array.
     */
    @Test
    public void testInCityByProximityReturnEmpty() {
        assertEquals(0, boopingSiteBig.getHotelsInCityByProximity(hotelsBig[2].getCity(),
                200, -90).length);
        assertEquals(0, boopingSiteSmall.getHotelsInCityByProximity(hotelsSmall[2].getCity(),
                -100.33, 180).length);
        assertEquals(0, boopingSiteBig.getHotelsInCityByProximity(hotelsBig[2].getCity(),
                88.22, 400).length);
        assertEquals(0, boopingSiteSmall.getHotelsInCityByProximity(hotelsSmall[2].getCity(),
                77.77, -400).length);
        assertEquals(0, boopingSiteBig.getHotelsInCityByProximity(hotelsBig[2].getCity(),
                -91.55, -181.99).length);
        assertEquals(0, boopingSiteSmall.getHotelsInCityByProximity(hotelsSmall[2].getCity(),
                91.001, 181.001).length);
        assertEquals(0, boopingSiteBig.getHotelsInCityByProximity(hotelsBig[2].getCity(),
                -911, 333).length);
        assertEquals(0, boopingSiteSmall.getHotelsInCityByProximity(hotelsSmall[2].getCity(),
                911, -333).length);
        assertEquals(0,boopingSiteEmpty.getHotelsInCityByProximity(hotelsSmall[2].getCity(),
                60,70).length);
    }


    /*----=  End of getHotelsInCityByProximity test  =-----*/


    /*
     * Checks if hotel1's distance to the source is equal, less or greater
     * than hotel2's distance to the source.
     * @param hotel1: a Hotel object.
     * @param hotel2: a Hotel object.
     * @param latitude: the latitude of the source point.
     * @param longitude: the longitude of the source point.
     * @return 0 if distances are equal, -1 if hotel1's distance to the source is less than hotel2's
     *         distance to the source and 1 otherwise.
     */
    private int compareDistances(Hotel hotel1, Hotel hotel2, double latitude, double longitude) {
        double x1 = hotel1.getLongitude();
        double y1 = hotel1.getLatitude();
        double x2 = hotel2.getLongitude();
        double y2 = hotel2.getLatitude();
        double distance1 = Math.sqrt(Math.pow(x1 - longitude, 2) + Math.pow(y1 - latitude, 2));
        double distance2 = Math.sqrt(Math.pow(x2 - longitude, 2) + Math.pow(y2 - latitude, 2));
        return Double.compare(distance1, distance2);
    }

}
