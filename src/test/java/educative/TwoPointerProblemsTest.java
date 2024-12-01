package educative;

import educative.TwoPointerProblems;

import static org.junit.Assert.*;

public class TwoPointerProblemsTest {
    TwoPointerProblems twoPointerProblems;

    @org.junit.Before
    public void setUp() throws Exception {
        twoPointerProblems = new TwoPointerProblems();
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void isStringPalindrome() {
        assertTrue(twoPointerProblems.isStringPalindrome("aba"));
        assertFalse(twoPointerProblems.isStringPalindrome("abaa"));
        assertTrue(twoPointerProblems.isStringPalindrome("abaaba"));
    }

    @org.junit.Test
    public void reverArrayTest() {
        int a[] = {1,2,3};
        int reverse[] = {3,2,1};
        twoPointerProblems.reverseArray(a);
        assertArrayEquals(reverse, a);
    }
}