package rss_items;

/**
 * Created by hugo on 4/7/15.
 * Class holding information about the offers flight.
 */
public class FlightInfo {

    private String destinationName;
    private String departureName;
    private String cityName;
    private String flightExtern;
    private String journeyLength;
    private String noOfSeatsRemaining;
    private String outDate;

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public String getDepartureName() {
        return departureName;
    }

    public void setDepartureName(String departureName) {
        this.departureName = departureName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getFlightExtern() {
        return flightExtern;
    }

    public void setFlightExtern(String flightExtern) {
        this.flightExtern = flightExtern;
    }

    public String getJourneyLength() {
        return journeyLength;
    }

    public void setJourneyLength(String journeyLength) {
        this.journeyLength = journeyLength;
    }

    public String getNoOfSeatsRemaining() {
        return noOfSeatsRemaining;
    }

    public void setNoOfSeatsRemaining(String noOfSeatsRemaining) {
        this.noOfSeatsRemaining = noOfSeatsRemaining;
    }

    public String getOutDate() {
        return outDate;
    }

    public void setOutDate(String outDate) {
        this.outDate = outDate;
    }
}
