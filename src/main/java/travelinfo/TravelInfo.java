package travelinfo;

import gui.OfferFrame;

import javax.swing.*;

/**
 * Created by hugo on 4/7/15.
 * Main class starting the GUI of the program.
 */
public class TravelInfo {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                OfferFrame offerFrame = new OfferFrame();
                offerFrame.setVisible(true);
            }
        });
    }
}
