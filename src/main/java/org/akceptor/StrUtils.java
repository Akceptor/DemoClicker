package org.akceptor;

import java.util.List;
import java.util.Random;

public class StrUtils {

	//Test2
    private static final Random randomGenerator = new Random();
    /**
     * Returns random element from list
     *
     * @param list of elements
     * @return random element
     */
    public static String randomString(List<String> list) {
        int index = randomGenerator.nextInt(list.size());
        return list.get(index);
    }
}