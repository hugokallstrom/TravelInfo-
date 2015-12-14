package rss_parsing;

import rss_items.Offer;
import travelinfo.Constants;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;

/**
 * Created by hugo on 4/7/15.
 * Class used to parse an RSS-Feed from XML to Offer-objects.
 */
public class Parser {

    private URL url;
    private String lastUpdate;

    public Parser() throws MalformedURLException {
            url = new URL(Constants.FEED_URL);
    }

    /**
     * Reads the xml data.
     * @return all offers read.
     * @throws XMLStreamException
     * @throws IOException
     */
    public ArrayList<Offer> readFeed() throws XMLStreamException, IOException {
        XMLEventReader eventReader = createReader();
        Offer offer = new Offer();
        ArrayList<Offer> offers = new ArrayList<>();

        while (eventReader.hasNext()) {
            XMLEvent event = eventReader.nextEvent();
            if (event.isStartElement()) {
                offer = buildOffer(event, eventReader, offer);
            } else if (event.isEndElement()) {
                if (event.asEndElement().getName().getLocalPart().equals(Constants.OFFER)) {
                        offers.add(offer);
                }
            }
        }

        return offers;
    }

    private Offer buildOffer(XMLEvent event, XMLEventReader eventReader, Offer offer) throws XMLStreamException {
        String localPart = event.asStartElement().getName().getLocalPart();
        String characterData = getCharacterData(eventReader);

        switch (localPart) {
            case Constants.START_ELEMENT:
                lastUpdate = event.asStartElement().getAttributeByName(QName.valueOf(Constants.LAST_UPDATE)).getValue();
                break;
            case Constants.OFFER:
                offer = new Offer();
                break;
            case Constants.CAMPAIGN_NAME:
                offer.setCampaignName(characterData);
                break;
            case Constants.CONTENT_LINK:
                offer.setContentLink(characterData);
                break;
            case Constants.BOOK_LINK:
                offer.setBookLink(characterData);
                break;
            case Constants.JOURNEY_LENGTH_WEEKS:
                offer.setJourneyLengthWeeks(characterData);
                break;
            case Constants.DEPARTURE_NAME:
                offer.getFlightInfo().setDepartureName(characterData);
                break;
            case Constants.OUT_DATE:
                offer.getFlightInfo().setOutDate(characterData);
                break;
            case Constants.DESTINATION_NAME:
                offer.getFlightInfo().setDestinationName(characterData);
                break;
            case Constants.CITY_NAME:
                offer.getFlightInfo().setCityName(characterData);
                break;
            case Constants.JOURNEY_LENGTH:
                offer.getFlightInfo().setJourneyLength(characterData);
                break;
            case Constants.FLIGHT_EXTERN:
                offer.getFlightInfo().setFlightExtern(characterData);
                break;
            case Constants.SEATS_REMAINING:
                offer.getFlightInfo().setNoOfSeatsRemaining(characterData);
                break;
            case Constants.CURRENT_PRICE:
                offer.getPriceInfo().setCurrentPrice(characterData);
                break;
            case Constants.ORIGINAL_PRICE:
                offer.getPriceInfo().setOriginalPrice(characterData);
                break;
            case Constants.PRICE_CURRENCY:
                offer.getPriceInfo().setPriceCurrency(characterData);
                break;
            case Constants.ROOM_DESCRIPTION:
                offer.getHotelInfo().setRoomDescription(characterData);
                break;
            case Constants.HOTEL_GRADE:
                offer.getHotelInfo().setHotelGrade(characterData);
                break;
            case Constants.HOTEL_GRADE_STRING:
                offer.getHotelInfo().setHotelGradestring(characterData);
                break;
            case Constants.HOTEL_IMAGE:
                offer.getHotelInfo().setHotelImage(characterData);
                break;
            case Constants.HOTEL_NAME:
                offer.getHotelInfo().setHotelName(characterData);
                break;
        }
        return offer;
    }

    private XMLEventReader createReader() throws XMLStreamException, IOException {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        InputStream in = url.openStream();
        return inputFactory.createXMLEventReader(in);
    }

    private String getCharacterData(XMLEventReader eventReader) throws XMLStreamException {
        String result = "";
        XMLEvent event = eventReader.nextEvent();
        if (event instanceof Characters) {
            result = event.asCharacters().getData();
        }
        return result;
    }
}