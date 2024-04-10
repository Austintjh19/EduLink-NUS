package seedu.edulink.commons.util;

/**
 * A class for Handling counter variables
 */
public class CounterUtil {
    /**
     * Returns incremented counter if not overflowed otherwise return 0
     */
    public static int incrementCounter(int counter) {
        if (counter == Integer.MAX_VALUE - 1) {
            return 0;
        } else {
            return counter + 1;
        }
    }

    /**
     * Returns incremented counter if within limit else reset to 0
     */
    public static int incrementDetailsCounter(int counter, int limit) {
        if (counter == limit - 1) {
            return 0;
        } else {
            return counter + 1;
        }
    }

    /**
     * Returns decremented counter if counter > 0 else reset to limit - 1
     */
    public static int decrementDetailsCounter(int counter, int limit) {
        if (counter == 0) {
            return limit - 1;
        } else {
            return counter - 1;
        }
    }
}
