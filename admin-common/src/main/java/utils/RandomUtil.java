package utils;

import java.util.Random;
import java.util.UUID;

public class RandomUtil {

    private static final String ALPHABETIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMERIC = "0123456789";
    private static final String ALPHANUMERIC = ALPHABETIC + NUMERIC;

    public static String randomAlphabetic(int count) {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder(count);

        for (int i = 0; i < count; i++) {
            sb.append(ALPHABETIC.charAt(rand.nextInt(ALPHABETIC.length())));
        }

        return sb.toString();
    }
    
    public static String randomNumeric(int count) {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder(count);

        for (int i = 0; i < count; i++) {
            sb.append(NUMERIC.charAt(rand.nextInt(NUMERIC.length())));
        }

        return sb.toString();
    }
    
    public static String randomAlphanumeric(int count) {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder(count);

        for (int i = 0; i < count; i++) {
            sb.append(ALPHANUMERIC.charAt(rand.nextInt(ALPHANUMERIC.length())));
        }

        return sb.toString();
    }

    public static String generateUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
