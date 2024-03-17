package seedu.address.commons.util;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;

/**
 * Helper functions for handling strings.
 */
public class StringUtil {

    /**
     * Returns true if the {@code sentence} contains the {@code word}.
     *   Ignores case and a full word match is not required, however must be in chronological order.
     *   <br>examples:<pre>
     *       containsIgnoreCase("ABc def", "abc") == true
     *       containsIgnoreCase("ABc def", "DEF") == true
     *       containsIgnoreCase("ABc def", "AB") == true
     *       containsIgnoreCase("ABc def", "de") == true
     *       containsIgnoreCase("ABc def", "bd def") == false // not in chronological order.
     *       </pre>
     * @param sentence cannot be null
     * @param words cannot be null, cannot be empty
     * @return {@code true} if the {@code sentence} contains the {@code word}, {@code false} otherwise.
     */
    public static boolean containsIgnoreCase(String sentence, String words) {
        requireNonNull(sentence);
        requireNonNull(words);

        final String preppedSentence = sentence.toLowerCase().trim();
        final String preppedWords = words.toLowerCase().trim();

        checkArgument(!preppedWords.isEmpty(), "Word parameter cannot be empty");

        String[] wordsInPreppedSentence = preppedSentence.split(" ");
        String[] wordsInPreppedWords = words.split("\\s+");

        if (wordsInPreppedWords.length > wordsInPreppedSentence.length) {
            return false;
        } else if (wordsInPreppedSentence.length == wordsInPreppedWords.length) {
            return preppedSentence.indexOf(preppedWords) == 0;
        } else {
            return Arrays.stream(wordsInPreppedSentence)
                    .anyMatch(sentencePart -> sentencePart.indexOf(preppedWords) == 0);
        }
    }

    /**
     * Returns a detailed message of the t, including the stack trace.
     */
    public static String getDetails(Throwable t) {
        requireNonNull(t);
        StringWriter sw = new StringWriter();
        t.printStackTrace(new PrintWriter(sw));
        return t.getMessage() + "\n" + sw.toString();
    }

    /**
     * Returns true if {@code s} represents a non-zero unsigned integer
     * e.g. 1, 2, 3, ..., {@code Integer.MAX_VALUE} <br>
     * Will return false for any other non-null string input
     * e.g. empty string, "-1", "0", "+1", and " 2 " (untrimmed), "3 0" (contains whitespace), "1 a" (contains letters)
     * @throws NullPointerException if {@code s} is null.
     */
    public static boolean isNonZeroUnsignedInteger(String s) {
        requireNonNull(s);

        try {
            int value = Integer.parseInt(s);
            return value > 0 && !s.startsWith("+"); // "+1" is successfully parsed by Integer#parseInt(String)
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}
