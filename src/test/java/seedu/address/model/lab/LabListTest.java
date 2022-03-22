package seedu.address.model.lab;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.student.Student;
import seedu.address.model.student.UniqueStudentList;
import seedu.address.model.student.exceptions.DuplicateLabException;
import seedu.address.model.student.exceptions.DuplicateStudentException;
import seedu.address.model.student.exceptions.LabNotFoundException;


class LabListTest {

    @Test
    public void contains_nullLab_throwsNullPointerException() {
        LabList labs = new LabList();
        assertThrows(NullPointerException.class, () -> labs.contains(null));
    }

    @Test
    public void contains_labInList_success() {
        LabList labs = new LabList();
        labs.add(new Lab("1"));
        assertTrue(labs.contains(new Lab("1")));
    }

    @Test
    public void contains_labNotInList_failure() {
        LabList labs = new LabList();
        labs.add(new Lab("1"));
        assertFalse(labs.contains(new Lab("2")));
    }

    @Test
    public void contains_listEmpty_failure() {
        LabList labs = new LabList();
        assertFalse(labs.contains(new Lab("2")));
    }

    @Test
    public void getLab_labInList_success() {
        LabList labs = new LabList();
        Lab l = new Lab("1");
        labs.add(l);
        assertEquals(l, labs.getLab(l));
        assertEquals(l, labs.getLab(1));
    }

    @Test
    public void getLab_labNotInList_throwsLabNotFoundException() {
        LabList labs = new LabList();
        Lab l = new Lab("1");
        labs.add(l);
        assertThrows(LabNotFoundException.class, () -> labs.getLab(new Lab("2")));
        assertThrows(LabNotFoundException.class, () -> labs.getLab(2));
    }

    @Test
    public void getLab_listEmpty_throwsLabNotFoundException() {
        LabList labs = new LabList();
        assertThrows(LabNotFoundException.class, () -> labs.getLab(new Lab("2")));
        assertThrows(LabNotFoundException.class, () -> labs.getLab(2));
    }

    @Test
    public void add_nullLab_throwsNullPointerException() {
        LabList labs = new LabList();
        assertThrows(NullPointerException.class, () -> labs.add(null));
    }

    @Test
    public void add_validLab_success() {
        LabList labs = new LabList();
        assertDoesNotThrow(() -> labs.add(new Lab("1")));
    }

    @Test
    public void add_duplicateLab_throwsDuplicateLabException() {
        LabList labs = new LabList();
        labs.add(new Lab("1"));
        assertThrows(DuplicateLabException.class, () -> labs.add(new Lab("1")));
    }

    @Test
    public void setLab_nullTargetLab_throwsNullPointerException() {
        LabList labs = new LabList();
        labs.add(new Lab("1"));
        assertThrows(NullPointerException.class, () -> labs.setLab(null, new Lab("2")));
    }

    @Test
    public void setLab_nullEditedLab_throwsNullPointerException() {
        LabList labs = new LabList();
        labs.add(new Lab("1"));
        assertThrows(NullPointerException.class, () -> labs.setLab(new Lab("1"), null));
    }

    @Test
    public void setLab_targetLabNotInList_throwsLabNotFoundException() {
        LabList labs = new LabList();
        labs.add(new Lab("1").of(LabStatus.GRADED));
        assertThrows(LabNotFoundException.class, () -> labs.setLab(new Lab("1"), new Lab("2")));
    }

    @Test
    public void setLab_targetLabSameAsEdited_throwsDuplicateLabException() {
        LabList labs = new LabList();
        labs.add(new Lab("1"));
        assertThrows(DuplicateLabException.class, () -> labs.setLab(new Lab("1"), new Lab("1")));
    }

    @Test
    public void setLab_targetLabInList_success() {
        LabList labs = new LabList();
        labs.add(new Lab("1"));
        labs.setLab(new Lab("1"), new Lab("2"));
        assertEquals(new Lab("2"), labs.getLab(2));
        assertThrows(LabNotFoundException.class, () -> labs.getLab(1));
    }

    @Test
    public void remove_nullLab_throwsNullPointerException() {
        LabList labs = new LabList();
        labs.add(new Lab("1"));
        assertThrows(NullPointerException.class, () -> labs.remove((Lab) null));
        assertThrows(NullPointerException.class, () -> labs.remove((Index) null));
    }

    @Test
    public void remove_indexOutOfBounds_throwsIndexOutOfBoundsException() {
        LabList labs = new LabList();
        assertThrows(IndexOutOfBoundsException.class, () -> labs.remove(Index.fromOneBased(1)));
    }

    @Test
    public void remove_labDoesNotExist_throwsLabNotFoundException() {
        LabList labs = new LabList();
        assertThrows(LabNotFoundException.class, () -> labs.remove(new Lab("2")));
    }

    @Test
    public void remove_existingLab_success() {
        LabList labs = new LabList();
        labs.add(new Lab("1"));
        labs.remove(new Lab("1"));
        LabList expectedLabList = new LabList();
        assertEquals(expectedLabList, labs);
        labs.add(new Lab("1"));
        labs.remove(Index.fromOneBased(1));
        assertEquals(expectedLabList, labs);
    }

    @Test
    public void setLabs_nullLabList_throwsNullPointerException() {
        LabList labs = new LabList();
        assertThrows(NullPointerException.class, () -> labs.setLabs((LabList) null));
        assertThrows(NullPointerException.class, () -> labs.setLabs((List<Lab>) null));
    }

    @Test
    public void setLabs_labList_replacesOwnListWithProvidedLabList() {
        LabList labs = new LabList();
        labs.add(new Lab("1"));
        LabList expectedLabList = new LabList();
        expectedLabList.add(new Lab("2").of(LabStatus.GRADED));
        labs.setLabs(expectedLabList);
        assertEquals(expectedLabList, labs);
    }

    @Test
    public void setStudents_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStudentList.setStudents((List<Student>) null));
    }

    @Test
    public void setStudents_list_replacesOwnListWithProvidedList() {
        uniqueStudentList.add(ALICE);
        List<Student> personList = Collections.singletonList(BOB);
        uniqueStudentList.setStudents(personList);
        UniqueStudentList expectedUniqueStudentList = new UniqueStudentList();
        expectedUniqueStudentList.add(BOB);
        assertEquals(expectedUniqueStudentList, uniqueStudentList);
    }

    @Test
    public void setStudents_listWithDuplicateStudents_throwsDuplicateStudentException() {
        List<Student> listWithDuplicatePersons = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateStudentException.class, () -> uniqueStudentList.setStudents(listWithDuplicatePersons));
    }
}
