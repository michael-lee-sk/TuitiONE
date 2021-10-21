package seedu.tuitione.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.tuitione.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.tuitione.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.tuitione.testutil.TypicalIndexes.INDEX_FIRST_LESSON;
import static seedu.tuitione.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.tuitione.testutil.TypicalIndexes.INDEX_SECOND_LESSON;
import static seedu.tuitione.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.tuitione.testutil.TypicalIndexes.INDEX_THIRD_LESSON;
import static seedu.tuitione.testutil.TypicalIndexes.INDEX_THIRD_STUDENT;
import static seedu.tuitione.testutil.TypicalTuition.getTypicalTuitione;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.tuitione.commons.core.Messages;
import seedu.tuitione.commons.core.index.Index;
import seedu.tuitione.model.Model;
import seedu.tuitione.model.ModelManager;
import seedu.tuitione.model.UserPrefs;
import seedu.tuitione.model.lesson.Lesson;
import seedu.tuitione.model.student.Student;

public class UnenrollCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalTuitione(), new UserPrefs());
    }

    @Test
    public void execute_validUnenrollment_success() {
        UnenrollCommand unenrollCommand = new UnenrollCommand(INDEX_SECOND_STUDENT, INDEX_SECOND_LESSON);

        Model expectedModel = new ModelManager(getTypicalTuitione(), new UserPrefs());
        Student studentToUnenroll = expectedModel.getFilteredStudentList().get(INDEX_SECOND_STUDENT.getZeroBased());

        Lesson testLesson = expectedModel.getFilteredLessonList().get(INDEX_SECOND_LESSON.getZeroBased());
        testLesson.unenrollStudent(studentToUnenroll);
        String expectedMessage = String.format(UnenrollCommand.MESSAGE_UNENROLL_STUDENT_SUCCESS,
                studentToUnenroll.getName(),
                testLesson);

        assertCommandSuccess(unenrollCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidUnenrollment_failure() {
        Lesson testLesson = model.getFilteredLessonList().get(INDEX_THIRD_LESSON.getZeroBased());
        Student alice = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased()); //ALICE
        Student benson = model.getFilteredStudentList().get(INDEX_SECOND_STUDENT.getZeroBased()); //BENSON
        Student carl = model.getFilteredStudentList().get(INDEX_THIRD_STUDENT.getZeroBased()); //CARL

        String expectedMessageAlice = String.format(UnenrollCommand.MESSAGE_STUDENT_NOT_IN_LESSON,
                alice.getName(),
                testLesson.getLessonCode());
        String expectedMessageBenson = String.format(UnenrollCommand.MESSAGE_STUDENT_NOT_IN_LESSON,
                benson.getName(),
                testLesson.getLessonCode());
        String expectedMessageCarl = String.format(UnenrollCommand.MESSAGE_STUDENT_NOT_IN_LESSON,
                carl.getName(),
                testLesson.getLessonCode());

        assertCommandFailure(new UnenrollCommand(INDEX_FIRST_STUDENT, INDEX_THIRD_LESSON),
                model,
                expectedMessageAlice);
        assertCommandFailure(new UnenrollCommand(INDEX_SECOND_STUDENT, INDEX_THIRD_LESSON),
                model,
                expectedMessageBenson);
        assertCommandFailure(new UnenrollCommand(INDEX_THIRD_STUDENT, INDEX_THIRD_LESSON),
                model,
                expectedMessageCarl);
    }

    @Test
    public void execute_invalidUnenrollmentWrongStudentIndex_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        UnenrollCommand unenrollCommand = new UnenrollCommand(outOfBoundIndex, INDEX_FIRST_LESSON);

        assertCommandFailure(unenrollCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidUnenrollmentWrongLessonIndex_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredLessonList().size() + 1);
        UnenrollCommand unenrollCommand = new UnenrollCommand(INDEX_SECOND_STUDENT, outOfBoundIndex);

        assertCommandFailure(unenrollCommand, model, Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        UnenrollCommand unenrollFirstCommand = new UnenrollCommand(INDEX_FIRST_STUDENT, INDEX_FIRST_LESSON);
        UnenrollCommand unenrollSecondCommand = new UnenrollCommand(INDEX_SECOND_STUDENT, INDEX_SECOND_LESSON);

        // same object -> returns true
        assertEquals(unenrollFirstCommand, unenrollFirstCommand);

        // same values -> returns true
        UnenrollCommand enrollFirstCommandCopy = new UnenrollCommand(INDEX_FIRST_STUDENT, INDEX_FIRST_LESSON);
        assertEquals(unenrollFirstCommand, enrollFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, unenrollFirstCommand);

        // null -> returns false
        assertNotEquals(null, unenrollFirstCommand);

        // different student -> returns false
        assertNotEquals(unenrollFirstCommand, unenrollSecondCommand);
    }
}
