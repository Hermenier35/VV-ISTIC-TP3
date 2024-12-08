package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringUtilsTest {

    @Test
    public void testEmptyString() {
        String emptyString = "";
        assertTrue(StringUtils.isBalanced(emptyString));
    }

    @Test
    public void testNullString() {
        String nullString = null;
        assertTrue(StringUtils.isBalanced(nullString));
    }

    @Test
    public void testOneLetter() {
        String emptyString = "a";
        assertTrue(StringUtils.isBalanced(emptyString));
    }

    @Test
    public void testFailSimpleBracket() {
        String testString = "(";
        assertFalse(StringUtils.isBalanced(testString));
    }

    @Test
    public void testMultipleLetters() {
        String emptyString = "abc";
        assertTrue(StringUtils.isBalanced(emptyString));
    }

    @Test
    public void testMultipleBracket() {
        String testString = "()[]{}";
        assertTrue(StringUtils.isBalanced(testString));
    }

    @Test
    public void testFailBadMiddleOpenBracket() {
        String testString = "([)";
        assertFalse(StringUtils.isBalanced(testString));
    }

    @Test
    public void testSimpleMultipleImbrication() {
        String testString = "([{}])";
        assertTrue(StringUtils.isBalanced(testString));
    }

    @Test
    public void testFailsimpleBadImbrication() {
        String testString = "[(])";
        assertFalse(StringUtils.isBalanced(testString));
    }

    @Test
    public void testComplexeBrackets() {
        String testString = "Object:{id:1, arg:[(1,2), (2,3)]}";
        assertTrue(StringUtils.isBalanced(testString));
    }

    @Test
    public void testFailCloseSimpleBracket() {
        String testString = ")";
        assertFalse(StringUtils.isBalanced(testString));
    }

}

