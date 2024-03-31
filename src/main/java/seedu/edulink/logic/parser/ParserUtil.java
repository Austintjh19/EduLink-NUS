package seedu.edulink.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.edulink.commons.core.index.Index;
import seedu.edulink.commons.util.StringUtil;
import seedu.edulink.logic.Messages;
import seedu.edulink.logic.commands.ExportCommand;
import seedu.edulink.logic.parser.exceptions.ParseException;
import seedu.edulink.model.grade.Course;
import seedu.edulink.model.grade.Grade;
import seedu.edulink.model.grade.Score;
import seedu.edulink.model.student.Address;
import seedu.edulink.model.student.Email;
import seedu.edulink.model.student.Id;
import seedu.edulink.model.student.Intake;
import seedu.edulink.model.student.Major;
import seedu.edulink.model.student.Name;
import seedu.edulink.model.student.Phone;
import seedu.edulink.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String id} into a {@code Id}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code Id} is invalid.
     */
    public static Id parseId(String id) throws ParseException {
        requireNonNull(id);
        String trimmedId = id.trim();
        if (!Id.isValidId(trimmedId)) {
            throw new ParseException(Id.MESSAGE_CONSTRAINTS);
        }
        return new Id(trimmedId);
    }

    /**
     * Parses a {@code String filename} into a {@code Filename}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code filename} is invalid.
     */
    public static String parseFilename(String fileName) throws ParseException {
        requireNonNull(fileName);
        String trimmedFilename = fileName.trim();

        if (trimmedFilename.isEmpty() || !trimmedFilename.matches(ExportCommand.VALIDATION_FILENAME)) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                ExportCommand.FILENAME_CONSTRAIN));
        }

        return trimmedFilename;
    }

    /**
     * Parses a {@code String major} into a {@code Major}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code Major} is invalid.
     */
    public static Major parseMajor(String major) throws ParseException {
        requireNonNull(major);
        String trimmedMajor = major.trim();
        if (!Major.isValidMajor(trimmedMajor)) {
            throw new ParseException(Major.MESSAGE_CONSTRAINTS);
        }
        return new Major(trimmedMajor);
    }

    /**
     * Parses a {@code String intake} into a {@code Intake}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code Intake} is invalid.
     */
    public static Intake parseIntake(String intake) throws ParseException {
        requireNonNull(intake);
        String trimmedIntake = intake.trim();
        if (!Intake.isValidIntake(trimmedIntake)) {
            throw new ParseException(Intake.MESSAGE_CONSTRAINTS);
        }
        if (!Intake.isValidIntakeYear(trimmedIntake)) {
            throw new ParseException(Intake.INVALID_YEAR);
        }
        return new Intake(trimmedIntake);
    }

    /**
     * Parses a {@code String grade} into a {@code Grade}.
     * {@code String grade} is written as [course]: [score]
     * All whitespaces will be removed.
     *
     * @throws ParseException if either the given course or score is invalid.
     */
    public static Grade parseGrade(String grade) throws ParseException {
        requireNonNull(grade);
        String trimmedGrade = grade.replaceAll("\\s", "");
        String courseCode = trimmedGrade.split(":")[0];
        if (!Course.isValidCourseCode(courseCode)) {
            throw new ParseException(Course.MESSAGE_CONSTRAINTS);
        }
        String scoreStr = trimmedGrade.split(":")[1];
        if (!StringUtil.isDouble(scoreStr)) {
            throw new ParseException(Score.MESSAGE_CONSTRAINTS);
        }
        Double score = Double.parseDouble(scoreStr);
        if (!Score.isValidScore(score)) {
            throw new ParseException(Score.MESSAGE_CONSTRAINTS);
        }
        System.out.println(courseCode);
        System.out.println(scoreStr);
        return new Grade(new Course(courseCode), new Score(score));
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }
}
