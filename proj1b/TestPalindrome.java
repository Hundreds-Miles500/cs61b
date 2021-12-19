import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    //new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void TestPalindrome() {
        String a = "a";
        assertTrue(palindrome.isPalindrome(a));
        String b = "aaaabbbbaaaa";
        assertTrue(palindrome.isPalindrome(b));
        String c = "abcdefg";
        assertFalse(palindrome.isPalindrome(c));
        String f = "";
        assertTrue(palindrome.isPalindrome(f));
    }

    @Test
    public void testPalindromeOne() {
        CharacterComparator ofo = new OffByOne();
        assertTrue(palindrome.isPalindrome("flake",ofo));
        assertFalse(palindrome.isPalindrome("abcde",ofo));
    }

    @Test
    public void testPalindrome() {
        CharacterComparator ofo = new OffByN(5);
        assertTrue(palindrome.isPalindrome("ffafaa",ofo));
        assertFalse(palindrome.isPalindrome("abcde",ofo));
    }
}
