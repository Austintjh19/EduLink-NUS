package seedu.edulink.commons.util;

/**
 * A class for Handling counter variables
 */
public class CounterUtil {
    /**
     * Returns incremented counter if not overflowed otherwise return 0
     */
    public static int incrementCounter(int counter) {
        try {
            counter = counter + 1;
            return counter;
        } catch (ArithmeticException e) {
            return 0;
        }
    }
}
