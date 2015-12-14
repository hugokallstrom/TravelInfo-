package gui;

import rss_items.Offer;
import rss_parsing.Feed;
import tasks.FeedUpdater;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by hugo on 8/3/15.
 * Class handling the data which should be displayed
 * in the JTable of the GUI.
 */
public class TableController {

    private JTable jTable;
    private Feed feed;
    private Frame frame;
    private DefaultTableModel tableModel;

    public TableController(JTable jTable, Feed feed, Frame frame) {
        this.jTable = jTable;
        this.feed = feed;
        this.frame = frame;
        tableModel = (DefaultTableModel) jTable.getModel();
    }

    /**
     * Updates the feed in a separate thread.
     * @param destination which destination to be displayed after the update is finished.
     */
    public void updateFeed(String destination) {
        FeedUpdater feedUpdater = new FeedUpdater(feed, this, destination, frame);
        feedUpdater.execute();
    }

    /**
     * Populates the table with offers to the specific
     * destination.
     * @param destination The offers destination.
     */
    public void populateTable(String destination) {
        tableModel.setRowCount(0);
        tableInsert(feed.filterOffers(destination));
        updateModel();
    }

    /**
     * Inserts data from offers into the tableModel.
     * @param offers The offers to be inserted.
     */
    private void tableInsert(ArrayList<Offer> offers) {
        for (Offer offer : offers) {
            String[] data = new String[3];
            data[0] = offer.getFlightInfo().getDestinationName();
            data[1] = offer.getFlightInfo().getOutDate();
            data[2] = offer.getPriceInfo().getCurrentPrice();
            tableModel.addRow(data);
        }
    }

    /**
     * Updates the tableModel for the JTable.
     */
    private void updateModel() {
        jTable.setModel(tableModel);
        tableModel.fireTableDataChanged();
    }
}
