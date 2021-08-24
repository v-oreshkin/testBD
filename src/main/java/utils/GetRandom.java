package utils;

import aquality.selenium.core.logging.Logger;


import java.util.Random;

public class GetRandom {

    private static final int DEFAULT_RANGE_FOR_DOUBLE_RANDOM = 9;
    private static Random random = new Random();

    public static int getRandomDoubleValue(){
        Logger.getInstance().info("Get random value");
        int randomDigit =random.nextInt(DEFAULT_RANGE_FOR_DOUBLE_RANDOM);
        String doubleValue = String.valueOf(randomDigit).concat(String.valueOf(randomDigit));
        return Integer.parseInt(doubleValue);
    }
}
