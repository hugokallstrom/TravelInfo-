package rss_parsing;

import rss_items.Offer;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by hugo on 4/7/15.
 * Class representing a feed with offers and
 * the option to filter offers based on a destination.
 */
public class Feed {

    private ArrayList<Offer> offers;
    private ArrayList<Offer> filteredOffers;

    public Feed() {
        offers = new ArrayList<>();
        filteredOffers = new ArrayList<>();
    }

    public Offer getOffer(int index) {
        return filteredOffers.get(index);
    }

    public ArrayList<Offer> filterOffers(String destination) {
        filteredOffers.clear();
        for(Offer offer : offers) {
            if(destination.equals(offer.getFlightInfo().getDestinationName()) || destination.equals("all")) {
                filteredOffers.add(offer);
            }
        }
        return filteredOffers;
    }

    public void updateOffers() throws IOException, XMLStreamException {
        offers.clear();
        Parser parser = new Parser();
        offers = parser.readFeed();
    }
}
