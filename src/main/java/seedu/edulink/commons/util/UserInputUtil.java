package seedu.edulink.commons.util;

/**
 * Helper functions for handling user inputs.
 */
public class UserInputUtil {

    /**
     * Removes duplicate whitespaces from the given string.
     *
     * @param str The input string from which duplicate whitespaces are to be removed.
     * @return A new string with duplicate whitespaces replaced by a single whitespace.
     */
    public static String removeDuplicatedWhiteSpaces(String str) {
        str = str.replaceAll("\\s+", " ");
        return str;
    }
}
