package seedu.edulink.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.edulink.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's ID in the Application.
 * Guarantees: immutable; is valid as declared in {@link #isValidId(String)}
 */
public class Id {

    public static final String MESSAGE_CONSTRAINTS =
        "Student IDs must consist of alphanumeric characters only and follow a specific format. "
            + "Specifically, IDs must start with a letter, followed by exactly seven digits, "
                + "and end with another letter."
            + " For example, 'A0265801R'. The ID field cannot be left empty.";
    public static final String VALIDATION_REGEX = "^[a-zA-Z]\\d{7}[a-zA-Z]$";

    public final String id;

    /**
     * Constructs a {@code Id}.
     *
     * @param value A valid Id.
     */
    public Id(String value) {
        requireNonNull(value);
        checkArgument(isValidId(value), MESSAGE_CONSTRAINTS);
        id = value.toUpperCase();
    }

    public static boolean isValidId(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return this.id;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Id)) {
            return false;
        }

        Id otherId = (Id) other;
        return this.id.equals(otherId.id);
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }
}
