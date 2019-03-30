package main;

import java.time.Instant;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Utilities {

    private static final Random random = new Random();

    public static int getTime() {
        return (int) Instant.now().getEpochSecond();
    }

    public static int getRand(int min, int max) {
        if (min >= 0) {
            return random.nextInt(max + 1 - min) + min;
        } else {
            return 0;
        }
    }

    public static boolean nameCheck(String name) {
        if (name.isEmpty())
            return false;
        
        char[] tmp = name.toCharArray();
        for (int i = 0; i < tmp.length; i++) {
            if (((int) tmp[i] >= 'a' && (int) tmp[i] <= 'z') || ((int) tmp[i] >= 'A' && (int) tmp[i] <= 'Z')) {
                return true;
            }
        }

        return false;
    }

    public static void log(Exception e) {
        Logger.getLogger(GameDB.class.getName()).log(Level.SEVERE, null, e);
    }
}
