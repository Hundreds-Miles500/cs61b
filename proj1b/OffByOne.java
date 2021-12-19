public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y) {
        int value = x - y;
        if (value == 1 || value == -1) {
            return true;
        }
        return false;
    }

}
