package seedu.edulink.model.student;

import seedu.edulink.commons.util.UserInputUtil;

import static java.util.Objects.requireNonNull;
import static seedu.edulink.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's Major in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidMajor(String)}
 */
public class Major {
    public static final String MESSAGE_CONSTRAINTS =
        "Major names should only contain alphabetical characters and white spaces. "
                + "They are limited to 50 characters in length. Can't be left empty.";
    public static final String VALIDATION_REGEX = "^[a-zA-Z\\s]{1,50}$";

    public final String major;

    /**
     * Constructs a {@code Major}.
     *
     * @param major A valid Major.
     */
    public Major(String major) {
        requireNonNull(major);
        checkArgument(isValidMajor(major), MESSAGE_CONSTRAINTS);
        this.major = UserInputUtil.removeDuplicatedWhiteSpaces(major);
    }

    public static boolean isValidMajor(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return this.major;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Major)) {
            return false;
        }

        Major otherMajor = (Major) other;
        return this.major.equalsIgnoreCase(otherMajor.major);
    }

    @Override
    public int hashCode() {
        return this.major.hashCode();
    }
}
