package tasks;

import gui.TableController;
import rss_parsing.Feed;

import javax.swing.*;
import javax.xml.stream.XMLStreamException;
import java.awt.*;
import java.io.IOException;

/**
 * Created by hugo on 8/6/15.
 * Class used to update the feed.
 */
public class FeedUpdater extends SwingWorker<Integer, String> {

    private final Feed feed;
    private final TableController tableController;
    private final Frame frame;
    private String destination;

    public FeedUpdater(final Feed feed, TableController tableController, String destination, Frame frame) {
        this.feed = feed;
        this.tableController = tableController;
        this.destination = destination;
        this.frame = frame;
    }

    /**
     * Updates the feed in a separate thread. Catches exceptions and
     * displays an error dialog. After update, the results are filtered by
     * destination.
     * @return null
     * @throws Exception
     */
    @Override
    protected Integer doInBackground() throws Exception {
        try {
            feed.updateOffers();
        } catch (IOException | XMLStreamException e) {
            JOptionPane.showMessageDialog(frame, "Connection error, try again");
        }
        if(!destination.equals("none")) {
            tableController.populateTable(destination);
        }
        return null;
    }

}
