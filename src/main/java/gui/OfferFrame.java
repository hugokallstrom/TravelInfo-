package gui;

import rss_parsing.Feed;
import tasks.TimerHandler;
import travelinfo.Constants;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.prefs.Preferences;

/**
 * Created by hugo on 7/30/15.
 * Class handling the GUI of the program.
 */
public class OfferFrame extends JFrame {

    private final TimerHandler timerHandler;
    private JTable jTable;
    private Feed feed;
    private TableController tableController;
    private Preferences prefs;

    public OfferFrame() {
        timerHandler = new TimerHandler();
        feed = new Feed();
        initComponents();
        createJTable();
        tableController = new TableController(jTable, feed, this);
        loadPrefs();
    }

    /**
     * Loads preferences set by the user
     * from previous usage of the program.
     */
    private void loadPrefs() {
        prefs = Preferences.userRoot().node(this.getClass().getName());
        int interval = prefs.getInt(Constants.INTERVAL, 60);
        String destination = prefs.get(Constants.DESTINATION, "all");
        setupTimer(interval);
        tableController.updateFeed(destination);
    }

    /**
     * Initializes components for the GUI.
     */
    private void initComponents() {
        setTitle("TravelInfo");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        createMenu();
    }

    /**
     * Creates the menu for the GUI.
     */
    private void createMenu() {
        JMenuBar menuBar;
        JMenu menu;
        JMenuItem menuItem;

        menuBar = new JMenuBar();
        menu = new JMenu("Settings");

        menuBar.add(menu);
        menuItem = new JMenuItem("Update Feed");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                tableController.updateFeed("all");
            }
        });
        menu.add(menuItem);
        JMenu updateMenu = new JMenu("Update-Interval");
        menuItem = new JMenuItem("Manual");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                timerHandler.stopTimer();
            }
        });
        updateMenu.add(menuItem);
        menuItem = new JMenuItem("30 min");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setupTimer(30);
            }
        });
        updateMenu.add(menuItem);
        menuItem = new JMenuItem("60 min");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setupTimer(60);
            }
        });
        updateMenu.add(menuItem);
        menuItem = new JMenuItem("90 min");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setupTimer(90);
            }
        });
        updateMenu.add(menuItem);
        menuItem = new JMenuItem("120 min");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setupTimer(120);
            }
        });
        updateMenu.add(menuItem);
        menu.add(updateMenu);
        menuItem = new JMenuItem("Set Destination");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String destination = JOptionPane.showInputDialog("Enter destination: ");
                if(destination != null) {
                    tableController.populateTable(destination);
                    prefs.put(Constants.DESTINATION, destination);
                }
            }
        });
        menu.add(menuItem);
        menuItem = new JMenuItem("Show All Destinations");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                    tableController.populateTable("all");
                    prefs.put(Constants.DESTINATION, "all");
                }
        });
        menu.add(menuItem);
        setJMenuBar(menuBar);
    }

    /**
     * Creates the JTable for the GUI, as well
     * as a controller for the JTable.
     */
    private void createJTable() {
        String[] colName = { "Destination", "Date", "Price"};
        if (jTable == null) {
            jTable = new JTable() {
                public boolean isCellEditable(int nRow, int nCol) {
                    return false;
                }
            };
        }
        DefaultTableModel contactTableModel = (DefaultTableModel) jTable.getModel();
        contactTableModel.setColumnIdentifiers(colName);
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        createMouseListener();
        JScrollPane jScrollPane = new JScrollPane(jTable);
        add(jScrollPane);
    }

    /**
     * Creates a mouse listener for the JTable.
     */
    private void createMouseListener() {
        jTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    new DetailedOfferFrame(feed.getOffer(jTable.getSelectedRow()));
                }
            }
        });
    }

    /**
     * Starts a timer with the chosen interval.
     * @param interval Interval in minutes.
     */
    private void setupTimer(int interval) {
        timerHandler.stopTimer();
        timerHandler.startTimer(interval, feed, tableController, this);
        prefs.putInt(Constants.INTERVAL, interval);
    }
}
