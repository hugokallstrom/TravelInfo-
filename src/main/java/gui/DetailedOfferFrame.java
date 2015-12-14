package gui;

import rss_items.Offer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

/**
 * Created by hugo on 7/30/15.
 * Class used to view an offer in a more
 * detailed view.
 */
public class DetailedOfferFrame extends JFrame {

    private Offer offer;
    private JPanel panel;

    public DetailedOfferFrame(Offer offer) {
        this.offer = offer;
        initComponents();
    }

    /**
     * Initializes components for the GUI.
     */
    private void initComponents() {
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel.setLayout(new GridLayout(2, 1, 5, 5));
        setTitle(offer.getCampaignName());
        addImage(offer.getHotelInfo().getHotelImage());
        addOfferInfo(offer);
        add(panel);
        setSize(1000, 700);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    /**
     * Adds an image to the GUI.
     * @param hotelImage The URL to the image.
     */
    private void addImage(String hotelImage) {
        Image image = null;
        try {
            URL url = new URL(hotelImage);
            image = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JLabel label = new JLabel(new ImageIcon(image));
        label.setVerticalAlignment(JLabel.TOP);
        panel.add(label);

    }

    /**
     * Adds information about the offer to the GUI.
     * @param offer The offer which to extract information from.
     */
    private void addOfferInfo(Offer offer) {
        JTextArea jTextArea = new JTextArea("Detailed Info");
        jTextArea.setText(offer.toString());
        jTextArea.setEditable(false);
        panel.add(jTextArea);
    }

}
