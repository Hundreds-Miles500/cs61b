public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> c = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i ++) {
            c.addLast(word.charAt(i));
        }
        return c;
    }
    // original Palindrome method
    public boolean isPalindrome(String word) {
        Deque words = wordToDeque(word);
        if (helperDeque(words)) {
            return true;
        } else {
            return false;
        }

    }

    private boolean helperDeque(Deque d) {
        if (d.size() <= 1) {
            return true;
        }
        Object first = d.removeFirst();
        Object last = d.removeLast();

        if (!first.equals(last)) {
            return false;
        } else {
            return helperDeque(d);
        }

    }
    //offByOne Palindrome method plus
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque words = wordToDeque(word);
        if (helperDeque(words, cc)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean helperDeque(Deque<Character> d, CharacterComparator cc) {
        if (d.size() <= 1) {
            return true;
        }
        char first = d.removeFirst();
        char last = d.removeLast();

        if (!cc.equalChars(first,last)) {
            return false;
        } else {
            return helperDeque(d, cc);
        }

    }



}
