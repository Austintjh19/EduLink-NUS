package seedu.edulink.model.grade;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ModuleTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Module(null));
    }

    @Test
    public void isValidModuleCode_null_returnsFalse() {
        assertFalse(Module.isValidModuleCode(null));
    }

    @Test
    public void isValidModuleCode_invalidFormats_returnsFalse() {
        assertFalse(Module.isValidModuleCode("CS101")); // Invalid digit format
        assertFalse(Module.isValidModuleCode("CS10101")); // More than 4 digits
        assertFalse(Module.isValidModuleCode("CS")); // Insufficient characters
        assertFalse(Module.isValidModuleCode("CSA101")); // More than 2 letters at the start
        assertFalse(Module.isValidModuleCode("1234AB")); // Starts with digits
        assertFalse(Module.isValidModuleCode("ABCEDF")); // All alphabets
    }

    @Test
    public void isValidModuleCode_validFormats_returnsTrue() {
        assertTrue(Module.isValidModuleCode("CS1101")); // Standard format
        assertTrue(Module.isValidModuleCode("MA1522A")); // With optional letter at the end
        assertTrue(Module.isValidModuleCode("ma1522")); // With lowercase letters
    }

    @Test
    public void stringTest() {
        Module module = new Module("MA1522");
        assertTrue(module.toString().equals("MA1522"));
        assertFalse(module.toString().equals("MA1521"));
    }

    @Test
    public void equals() {
        Module module1 = new Module("CS1010");
        Module module2 = new Module("MA1522");

        // same values -> returns true
        assertTrue(module1.equals(new Module("CS1010")));

        // same object -> returns true
        assertTrue(module1.equals(module1));

        // null -> returns false
        assertFalse(module1.equals(null));

        // different values -> returns false
        assertFalse(module1.equals(module2));
    }

    @Test
    public void hashCode_sameValues_returnsSameHashCode() {
        Module module1 = new Module("MA1522");
        Module module2 = new Module("ma1522");
        assertTrue(module1.hashCode() == module2.hashCode());
    }

    @Test
    public void hashCode_differentValues_returnsDifferentHashCode() {
        Module module1 = new Module("CS3230");
        Module module2 = new Module("MA1522");
        assertFalse(module1.hashCode() == module2.hashCode());
    }
}
