package seedu.address.model.lab;


import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.model.student.exceptions.DuplicateLabException;
import seedu.address.model.student.exceptions.LabNotFoundException;

public class LabList implements Iterable<Lab> {

    private final ObservableList<Lab> internalList = FXCollections.observableArrayList();
    private final ObservableList<Lab> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    //Comparator to sort the list by lab number
    private final Comparator<Lab> sortByLabNumber = new Comparator<>() {
        @Override
        public int compare(Lab l1, Lab l2) {
            // Ignoring the case when l1.labNumber == l2.labNumber as there shouldn't be duplicate labs in the list.
            return l1.labNumber - l2.labNumber;
        }
    };

    /**
     * Returns true if the list contains an equivalent Lab as the given argument.
     *
     * @param toCheck The Lab to be checked
     */
    public boolean contains(Lab toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameLab);
    }

    /**
     * Searches for and returns a Lab that has the same lab number as the given {@code labToGet}
     * (but potentially different LabStatus)
     *
     * @param labToGet The lab you are trying to find in the list.
     * @return A copy of the Lab with the same lab number as the given {@code labToGet} from the internalList.
     */
    public Lab getLab(Lab labToGet) throws LabNotFoundException {
        requireNonNull(labToGet);

        if (!contains(labToGet)) {
            throw new LabNotFoundException(labToGet.labNumber);
        }

        return getLab(labToGet.labNumber);
    }

    /**
     * Searches for and returns a Lab that has the given lab number (but potentially different LabStatus)
     *
     * @param labNumberToGet The lab number of the lab you are trying to find in the list.
     * @return A copy of the Lab with the given lab number from the internalList.
     */
    public Lab getLab(int labNumberToGet) throws LabNotFoundException {
        requireNonNull(labNumberToGet);

        for (Lab l : internalList) {
            if (l.labNumber == labNumberToGet) {
                return l.createCopy();
            }
        }

        throw new LabNotFoundException(labNumberToGet);
    }

    /**
     * Adds a Lab to the list.
     * The Lab must not already exist in the list.
     * Maintains sorted by lab number invariant.
     *
     * @param toAdd The Lab to add.
     */
    public void add(Lab toAdd) throws DuplicateLabException {
        requireNonNull(toAdd);

        if (contains(toAdd)) {
            throw new DuplicateLabException();
        }

        internalList.add(toAdd);
        internalList.sort(sortByLabNumber);
    }

    /**
     * Replaces the Lab {@code target} in the list with {@code editedLab}.
     * {@code target} must exist in the list.
     * The lab identity of {@code editedLab} must not be the same as another existing Lab in the list.
     */
    public void setLab(Lab target, Lab editedLab) throws LabNotFoundException, DuplicateLabException {
        requireAllNonNull(target, editedLab);

        if (target.equals(editedLab)) {
            throw new DuplicateLabException();
        }

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new LabNotFoundException(target.labNumber);
        }

        internalList.set(index, editedLab);
    }

    /**
     * Removes the equivalent Lab from the list.
     * The Lab must exist in the list.
     * Maintains sorted by lab number invariant.
     *
     * @param toRemove The Lab to remove from the list. (has to have the same labNumber and labStatus
     *                 as the lab you are trying to remove from the list)
     */
    public void remove(Lab toRemove) throws LabNotFoundException {
        requireNonNull(toRemove);

        if (!internalList.contains(toRemove)) {
            throw new LabNotFoundException(toRemove.labNumber);
        }

        remove(Index.fromZeroBased(internalList.indexOf(toRemove)));
        internalList.sort(sortByLabNumber);
    }

    /**
     * Removes the lab at the specified index.
     * The index must be < the size of {@code internalList}.
     * Maintains sorted by lab number invariant.
     *
     * @param index The index of the lab to be removed.
     */
    public void remove(Index index) throws LabNotFoundException {
        requireNonNull(index);

        // this guard clause should not be invoked
        if (index.getZeroBased() >= internalList.size()) {
            throw new IndexOutOfBoundsException();
        }

        internalList.remove(index.getZeroBased());
        internalList.sort(sortByLabNumber);
    }

    /**
     * Replaces the contents of this list with {@code replacement}.
     *
     * @param replacement is assumed to not contain duplicate Labs.
     */
    public void setLabs(LabList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code labs}.
     * {@code labs} must not contain duplicate Labs.
     */
    public void setLabs(List<Lab> labs) throws DuplicateLabException {
        requireAllNonNull(labs);
        if (!labsAreUnique(labs)) {
            throw new DuplicateLabException();
        }

        internalList.setAll(labs);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Lab> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Lab> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LabList // instanceof handles nulls
                && internalList.equals(((LabList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code labs} contains only unique Labs.
     */
    private boolean labsAreUnique(List<Lab> labs) {
        for (int i = 0; i < labs.size() - 1; i++) {
            for (int j = i + 1; j < labs.size(); j++) {
                if (labs.get(i).isSameLab(labs.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

}
