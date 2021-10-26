package seedu.tuitione.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tuitione.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.tuitione.testutil.Assert.assertThrows;
import static seedu.tuitione.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.tuitione.logic.parser.exceptions.ParseException;
import seedu.tuitione.model.remark.Remark;
import seedu.tuitione.model.student.Address;
import seedu.tuitione.model.student.Email;
import seedu.tuitione.model.student.Name;
import seedu.tuitione.model.student.ParentContact;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_REMARK = "#friend";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_REMARK_1 = "friend";
    private static final String VALID_REMARK_2 = "neighbour";
    private static final String VALID_REMARK_3 = "paid";
    private static final String VALID_REMARK_4 = "problematic";
    private static final String VALID_REMARK_5 = "financialAssistance";
    private static final String VALID_REMARK_6 = "temporary";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_STUDENT, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_STUDENT, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone((String) null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        ParentContact expectedParentContact = new ParentContact(VALID_PHONE);
        assertEquals(expectedParentContact, ParserUtil.parsePhone(VALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        ParentContact expectedParentContact = new ParentContact(VALID_PHONE);
        assertEquals(expectedParentContact, ParserUtil.parsePhone(phoneWithWhitespace));
    }

    @Test
    public void parseAddress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAddress((String) null));
    }

    @Test
    public void parseAddress_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAddress(INVALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithoutWhitespace_returnsAddress() throws Exception {
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(VALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_ADDRESS + WHITESPACE;
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(addressWithWhitespace));
    }

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }

    @Test
    public void parseRemark_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseRemark(null));
    }

    @Test
    public void parseRemark_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseRemark(INVALID_REMARK));
    }

    @Test
    public void parseRemark_validValueWithoutWhitespace_returnsRemark() throws Exception {
        Remark expectedRemark = new Remark(VALID_REMARK_1);
        assertEquals(expectedRemark, ParserUtil.parseRemark(VALID_REMARK_1));
    }

    @Test
    public void parseRemark_validValueWithWhitespace_returnsTrimmedRemark() throws Exception {
        String remarkWithWhitespace = WHITESPACE + VALID_REMARK_1 + WHITESPACE;
        Remark expectedRemark = new Remark(VALID_REMARK_1);
        assertEquals(expectedRemark, ParserUtil.parseRemark(remarkWithWhitespace));
    }

    @Test
    public void parseRemarks_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseRemarks(null));
    }

    @Test
    public void parseRemarks_collectionWithInvalidRemarks_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseRemarks(Arrays.asList(VALID_REMARK_1,
                INVALID_REMARK)));
    }

    @Test
    public void parseRemarks_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseRemarks(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseRemarks_collectionWithValidRemarks_returnsRemarkSet() throws Exception {
        Set<Remark> actualRemarkSet = ParserUtil.parseRemarks(Arrays.asList(VALID_REMARK_1, VALID_REMARK_2));
        Set<Remark> expectedRemarkSet = new HashSet<>(Arrays.asList(new Remark(VALID_REMARK_1),
                new Remark(VALID_REMARK_2)));

        assertEquals(expectedRemarkSet, actualRemarkSet);
    }

    @Test
    public void parseRemarks_collectionWithMoreThanFiveValidRemarks_returnsRemarkSet() throws Exception {
        Set<Remark> actualRemarkSet = ParserUtil.parseRemarks(Arrays
                .asList(VALID_REMARK_1, VALID_REMARK_2, VALID_REMARK_3, VALID_REMARK_4,
                        VALID_REMARK_5, VALID_REMARK_6));
        Set<Remark> expectedRemarkSet = new HashSet<>(Arrays.asList(new Remark(VALID_REMARK_1),
                new Remark(VALID_REMARK_2), new Remark(VALID_REMARK_3), new Remark(VALID_REMARK_4),
                new Remark(VALID_REMARK_5)));

        assertEquals(expectedRemarkSet, actualRemarkSet);
    }

    @Test
    public void parseRemarks_collectionWithRepeatedValidRemarks_returnsRemarkSet() throws Exception {
        // all repeated remarks -> only 1 remark in set
        Set<Remark> actualRemarkSet1 = ParserUtil.parseRemarks(Arrays
                .asList(VALID_REMARK_1, VALID_REMARK_1, VALID_REMARK_1, VALID_REMARK_1,
                        VALID_REMARK_1, VALID_REMARK_1));
        Set<Remark> expectedRemarkSet1 = new HashSet<>(Arrays.asList(new Remark(VALID_REMARK_1)));

        assertEquals(expectedRemarkSet1, actualRemarkSet1);

        // some repeated remarks -> take in the first 5 unique remarks
        Set<Remark> actualRemarkSet2 = ParserUtil.parseRemarks(Arrays
                .asList(VALID_REMARK_1, VALID_REMARK_1, VALID_REMARK_2, VALID_REMARK_2, VALID_REMARK_3,
                        VALID_REMARK_4, VALID_REMARK_5, VALID_REMARK_6));
        Set<Remark> expectedRemarkSet2 = new HashSet<>(Arrays.asList(new Remark(VALID_REMARK_1),
                new Remark(VALID_REMARK_2), new Remark(VALID_REMARK_3), new Remark(VALID_REMARK_4),
                new Remark(VALID_REMARK_5)));
        assertEquals(expectedRemarkSet2, actualRemarkSet2);
    }
}
