package seedu.edulink.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.edulink.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's email in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidEmail(String)}
 */
public class Email {
    public static final String MESSAGE_CONSTRAINTS = "The email address must be a maximum of"
        + " 100 characters long, with the following constraints:\n"
        + "- The local part (before the '@' symbol) can contain "
        + "letters (uppercase and lowercase), " + "digits, and the following special characters:"
        + " '.', '_', '+', '-'.\n"
        + "- The domain part (after the '@' symbol) "
        + "can contain letters (uppercase and lowercase), "
        + "digits, and the following special characters: '.', '-'.\n"
        + "- The top-level domain (after the last '.') can be between 2 and 10 characters "
        + "long and can only contain letters (uppercase and lowercase).";
    private static final String SPECIAL_CHARACTERS = "+_.-";
    private static final String ALPHANUMERIC_NO_UNDERSCORE = "[^\\W_]+"; // alphanumeric characters except underscore
    private static final String LOCAL_PART_REGEX = "^" + ALPHANUMERIC_NO_UNDERSCORE + "([" + SPECIAL_CHARACTERS + "]"
        + ALPHANUMERIC_NO_UNDERSCORE + ")*";

    public static final String VALIDATION_REGEX = LOCAL_PART_REGEX + "@"
        + "[a-zA-Z0-9][a-zA-Z0-9-.]+[a-zA-Z0-9]\\.[a-zA-Z]{2,10}$";

    public final String value;

    /**
     * Constructs an {@code Email}.
     *
     * @param email A valid email address.
     */
    public Email(String email) {
        requireNonNull(email);
        checkArgument(isValidEmail(email), MESSAGE_CONSTRAINTS);
        value = email;
    }

    /**
     * Returns if a given string is a valid email.
     */
    public static boolean isValidEmail(String test) {
        return test.matches(VALIDATION_REGEX) && test.length() <= 100;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Email)) {
            return false;
        }

        Email otherEmail = (Email) other;
        return value.equals(otherEmail.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
