package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.student.MasterLabList;
import seedu.address.model.student.Student;

/**
 * Unmodifiable view of a TAddress book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the student list.
     * This list will not contain any duplicate students.
     */
    ObservableList<Student> getStudentList();

    /**
     * Returns a MasterLabList containing all the Labs.
     * This list will not contain any duplicate Labs.
     */
    MasterLabList getMasterLabList();

}
