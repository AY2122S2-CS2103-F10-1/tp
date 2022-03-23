package seedu.address.model.lab;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.model.student.exceptions.DuplicateLabException;

/**
 * Represents a Lab entry.
 * Guarantees: immutable; is valid as declared in {@link #isValidLab(String)}
 */
public class Lab {

    public static final String MESSAGE_CONSTRAINTS =
            "Lab number should be a valid positive integer";

    /*
     * Lab number has to be a positive Integer.
     */
    public static final String VALIDATION_REGEX = "[1-9]\\d*";

    public final int labNumber;

    public final LabStatus labStatus;

    /**
     * Constructs an {@code Lab}.
     *
     * @param labNumber A valid lab number.
     */
    public Lab(String labNumber) {
        // labStatus is always initialized to {@code LabStatus.UNSUBMITTED}
        this(labNumber, LabStatus.UNSUBMITTED);
    }

    /**
     * Constructs an {@code Lab}.
     *
     * @param labNumber A valid lab number.
     * @param labStatus The status of the Lab to be created.
     */
    private Lab(String labNumber, LabStatus labStatus) {
        requireNonNull(labNumber);
        checkArgument(isValidLab(labNumber), MESSAGE_CONSTRAINTS);
        this.labNumber = Integer.parseInt(labNumber);
        this.labStatus = labStatus;
    }

    /**
     * Returns true if a given string is a valid lab number.
     */
    public static boolean isValidLab(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns a new immutable lab with the same attributes as {@code this}.
     */
    public Lab createCopy() {
        return new Lab(String.valueOf(labNumber), labStatus);
    }

    /**
     * Returns a new immutable lab with the same lab number as {@code this} and the given lab status.
     */
    public Lab editLabStatus(LabStatus status) throws DuplicateLabException {
        if (status.equals(this.labStatus)) {
            throw new DuplicateLabException();
        }
        return new Lab(String.valueOf(labNumber), status);
    }

    /**
     * Returns a new immutable lab with the same attributes as this.
     */
    public Lab of(String labStatusString) {
        requireNonNull(labStatusString);
        return of(LabStatus.toLabStatus(labStatusString));
    }

    /**
     * Returns a new immutable {@code Lab} with the specified {@code LabStatus}
     */
    public Lab of(LabStatus labStatus) {
        requireNonNull(labStatus);
        return new Lab(String.valueOf(labNumber), labStatus);
    }

    /**
     * Returns true if both Labs have the same lab number.
     * This defines a weaker notion of equality between two Labs.
     */
    public boolean isSameLab(Lab otherLab) {
        if (otherLab == this) {
            return true;
        }

        return otherLab != null && otherLab.labNumber == this.labNumber;
    }

    @Override
    public String toString() {
        return "Lab " + labNumber;
    }

    /**
     * Returns true if both Labs have the same lab number and LabStatus.
     * This defines a stronger notion of equality between two Labs.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Lab // instanceof handles nulls
                && labNumber == (((Lab) other).labNumber) // labNumber check
                && labStatus == (((Lab) other).labStatus)); // labStatus check
    }

}