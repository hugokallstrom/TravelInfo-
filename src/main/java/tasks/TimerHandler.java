package tasks;

import gui.TableController;
import rss_parsing.Feed;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by hugo on 8/2/15.
 * A timer used to update the feed within a specific interval.
 */
public class TimerHandler {

    private Timer timer;

    public TimerHandler() {
        timer = new Timer();
    }

    /**
     * Starts the timer.
     * @param interval How often the timer should fire.
     * @param feed The feed which should be updated.
     * @param tableController Table which should be updated with data.
     * @param frame Frame to display error dialog in.
     */
    public void startTimer(int interval, final Feed feed, final TableController tableController, final Frame frame) {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                FeedUpdater feedUpdater = new FeedUpdater(feed, tableController, "none", frame);
                feedUpdater.execute();
            }
        }, interval * 1000*60, interval * 1000*60);
    }

    /**
     * Stops the timer.
     */
    public void stopTimer() {
        timer.cancel();
        timer.purge();
        timer = new Timer();
    }

 }
