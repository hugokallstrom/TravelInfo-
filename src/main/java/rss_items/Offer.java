package rss_items;

/**
 * Created by hugo on 4/7/15.
 * Class used to represent an offer.
 */
public class Offer {

    private FlightInfo flightInfo;
    private HotelInfo hotelInfo;
    private PriceInfo priceInfo;

    private String campaignName;
    private String contentLink;
    private String bookLink;
    private String journeyLengthWeeks;

    public Offer() {
        this.flightInfo = new FlightInfo();
        this.hotelInfo = new HotelInfo();
        this.priceInfo = new PriceInfo();
    }

    public FlightInfo getFlightInfo() {
        return flightInfo;
    }

    public HotelInfo getHotelInfo() {
        return hotelInfo;
    }

    public PriceInfo getPriceInfo() {
        return priceInfo;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public String getContentLink() {
        return contentLink;
    }

    public void setContentLink(String contentLink) {
        this.contentLink = contentLink;
    }

    public String getBookLink() {
        return bookLink;
    }

    public void setBookLink(String bookLink) {
        this.bookLink = bookLink;
    }

    public String getJourneyLengthWeeks() {
        return journeyLengthWeeks;
    }

    public void setJourneyLengthWeeks(String journeyLengthWeeks) {
        this.journeyLengthWeeks = journeyLengthWeeks;
    }

    public String toString() {
        return  "Campaign name: " + getCampaignName() + "\n" +
                "Content Link: " + getContentLink() + "\n" +
                "Length: " + getJourneyLengthWeeks() + "\n" +
                "Book link: " + getBookLink() + "\n" +
                "Current price: " + getPriceInfo().getCurrentPrice() + "\n" +
                "Original price: " + getPriceInfo().getOriginalPrice() + "\n" +
                "Currency: " +  getPriceInfo().getPriceCurrency() + "\n" +
                "Flight date: " + getFlightInfo().getOutDate() + "\n" +
                "Destination: " + getFlightInfo().getDestinationName() + "\n" +
                "City name: " + getFlightInfo().getCityName() + "\n" +
                "Departure from: " + getFlightInfo().getDepartureName() + "\n" +
                "Flight extern: " + getFlightInfo().getFlightExtern() + "\n" +
                "Seats remaining: " + getFlightInfo().getNoOfSeatsRemaining() + "\n" +
                "Hotel rating: " + getHotelInfo().getHotelGrade() + "\n" +
                "Hotel name: " + getHotelInfo().getHotelName() + "\n" +
                "Room description: " + getHotelInfo().getRoomDescription() + "\n";
    }
}
