package seedu.edulink.model.grade;

import static java.util.Objects.requireNonNull;
import static seedu.edulink.commons.util.AppUtil.checkArgument;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Represents a Module in the Application.
 * Guarantees: immutable; is valid as declared in {@link #isValidModuleCode(String)}
 */
public class Module {
    public static final String MESSAGE_CONSTRAINTS =
        "Module code should only contain alphanumeric characters starting with min. 2 to max.4 alphabets."
            + " Followed by 4 digits and may end with an optional alphabet."
            + " e.g GESS1035, and it should not be blank";
    private static final Pattern MODULE_CODE_PATTERN = Pattern.compile("[a-zA-Z]{2,4}\\d{4}[a-zA-Z]?");

    public final String moduleCode;

    /**
     * Constructs a {@code Module}.
     *
     * @param moduleCode A valid module code.
     */
    public Module(String moduleCode) {
        requireNonNull(moduleCode);
        checkArgument(isValidModuleCode(moduleCode), MESSAGE_CONSTRAINTS);
        this.moduleCode = parseModule(moduleCode);
    }

    /**
     * Returns true if a given string is a valid module code.
     */
    public static boolean isValidModuleCode(String test) {
        return test != null && MODULE_CODE_PATTERN.matcher(test).matches();
    }

    public String parseModule(String moduleCode) {
        return moduleCode.toUpperCase();
    }

    @Override
    public String toString() {
        return moduleCode;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Module)) {
            return false;
        }

        Module otherModule = (Module) other;
        return moduleCode.equals(otherModule.moduleCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(moduleCode);
    }
}
