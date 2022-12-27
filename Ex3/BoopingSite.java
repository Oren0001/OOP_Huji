import oop.ex3.searchengine.Hotel;
import oop.ex3.searchengine.HotelDataset;

import java.util.*;
import java.lang.Math;

/**
 * Represents a hotel booking site that allows for personalized search methodologies.
 * @author Oren Motiei
 */
public class BoopingSite {

    private static final int MAX_LATITUDE = 90;
    private static final int MAX_LONGITUDE = 180;
    private String datasetName;
    private Hotel[] hotels;

    /**
     * Initializes a new Booping site.
     * @param name name of a dataset.
     */
    public BoopingSite(String name) {
        datasetName = name;
        hotels = HotelDataset.getHotels(name);
    }


    /**
     * @param city the city in which the hotels are located.
     * @return an array of hotels located in the given city, sorted from the highest star-rating to lowest.
     *         Hotels that have the same rating will be organized according to the alphabet order
     *         of the hotel's property name.
     */
    public Hotel[] getHotelsInCityByRating(String city) {
        ArrayList<Hotel> cityHotels = filterHotelsByCity(city);
        class InCityByRatingComparator implements Comparator<Hotel> { // local class for comparator use
            @Override
            public int compare(Hotel a, Hotel b) {
                int ratingCompare = ( (Integer) b.getStarRating() ).compareTo(a.getStarRating());
                int alphabetCompare = a.getPropertyName().compareTo(b.getPropertyName());
                if (ratingCompare == 0)
                    return alphabetCompare == 0 ? ratingCompare : alphabetCompare;
                else
                    return ratingCompare;
            }
        }
        Collections.sort(cityHotels, new InCityByRatingComparator());
        return cityHotels.toArray(new Hotel[0]);
    }


    /**
     * @param latitude the latitude of a geographic location.
     * @param longitude the longitude of a geographic location.
     * @return an array of hotels sorted according to their Euclidean distance, in ascending order.
     *         Hotels that are at the same distance from the given location are organized according to the
     *         number of points-of-interest (in a decreasing order).
     */
    public Hotel[] getHotelsByProximity(double latitude, double longitude) {
        if (invalidInput(latitude, longitude))
            return new Hotel[0];
        Hotel[] hotelsCopy = HotelDataset.getHotels(datasetName);
        Arrays.sort(hotelsCopy, new ByProximityComparator(latitude, longitude));
        return hotelsCopy;
    }


    /**
     * @param city the city in which the hotels are located.
     * @param latitude the latitude of a geographic location.
     * @param longitude the longitude of a geographic location.
     * @return an array of hotels in the given city, sorted according to their Euclidean distance,
     *         in ascending order. Hotels that are at the same distance from the given location are
     *         organized according to the number of points-of-interest (in a decreasing order).
     */
    public Hotel[] getHotelsInCityByProximity(String city, double latitude, double longitude) {
        if (invalidInput(latitude, longitude))
            return new Hotel[0];
        ArrayList<Hotel> cityHotels = filterHotelsByCity(city);
        Collections.sort(cityHotels, new ByProximityComparator(latitude, longitude));
        return cityHotels.toArray(new Hotel[0]);
    }


    /*
     * @param city: the city that will be used to filter the hotels.
     * @return an array list that contains hotels of the given city.
     */
    private ArrayList<Hotel> filterHotelsByCity(String city) {
        ArrayList<Hotel> cityHotels = new ArrayList<Hotel>();
        for (Hotel hotel: hotels) {
            if (hotel != null && hotel.getCity().equals(city))
                cityHotels.add(hotel);
        }
        return cityHotels;
    }


    /*
     * Checks if latitude and longitude have legal values or not.
     * @param latitude: the latitude of a geographic location.
     * @param longitude: the longitude of a geographic location.
     * @return true if input is invalid, false otherwise.
     */
    private boolean invalidInput(double latitude, double longitude) {
        return (Math.abs(latitude) > MAX_LATITUDE) || (Math.abs(longitude) > MAX_LONGITUDE);
    }


    /*
     * A static class that is used to compare proximity and number of points-of-intrest.
     */
    private static class ByProximityComparator implements Comparator<Hotel> {
        private double latitude, longitude;
        /** A constructor to initialize the comparator. */
        public ByProximityComparator(double latitude, double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }
        /** Overrides the compare method of Comparator. */
        @Override
        public int compare(Hotel a, Hotel b) {
            int proximityCompare = compareProximity(a, b, latitude, longitude);
            int numPOICompare = ( (Integer) b.getNumPOI() ).compareTo(a.getNumPOI());
            if (proximityCompare == 0)
                return numPOICompare == 0 ? proximityCompare : numPOICompare;
            else
                return proximityCompare;
        }
        private int compareProximity(Hotel a, Hotel b, double latitude, double longitude) {
            double x1 = a.getLongitude();
            double y1 = a.getLatitude();
            double x2 = b.getLongitude();
            double y2 = b.getLatitude();
            double distance1 = Math.sqrt(Math.pow(x1 - longitude, 2) + Math.pow(y1 - latitude, 2));
            double distance2 = Math.sqrt(Math.pow(x2 - longitude, 2) + Math.pow(y2 - latitude, 2));
            return Double.compare(distance1, distance2);
        }
    }

}
