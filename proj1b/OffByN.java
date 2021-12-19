public class OffByN implements CharacterComparator {
    private int N;
    public OffByN(int N) {
        this.N = N;
    }

    public boolean equalChars(char x, char y) {
        int value = x - y;
        if (value == N || value == -N) {
            return true;
        }
        return false;
    }
}
