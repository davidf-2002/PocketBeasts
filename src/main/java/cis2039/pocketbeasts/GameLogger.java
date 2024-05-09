package cis2039.pocketbeasts;

import java.util.logging.*;

/**
 * This class is used to log important aspects of the game.
 * It does so by adding messages to a GameLog.log file
 */
public class GameLogger {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(GameLogger.class.getName());

    static {
        try {
            FileHandler fh = new FileHandler("GameLog.log", true);
            fh.setFormatter(new SimpleFormatter());
            logger.addHandler(fh);
            logger.setLevel(Level.FINE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void logEvent(String message) {
        logger.fine(message);
    }
}
