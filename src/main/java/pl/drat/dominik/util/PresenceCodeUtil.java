package pl.drat.dominik.util;

import java.util.Random;

public class PresenceCodeUtil {

    public static Long generatePresenceCode() {
        Random random = new Random();
        int min = 10000;
        int max = 99999;
        return Long.valueOf(random.nextInt(max - min + 1) + min);
    }
}
