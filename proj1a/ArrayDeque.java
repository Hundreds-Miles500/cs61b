public class ArrayDeque<T> {
    private int size;
    private T[] items;
    private int nextFirst;
    private int nextLast;
    //constructor
    public ArrayDeque() {
        size = 0;
        items = (T []) new Object[8];
        nextFirst = 2;
        nextLast = 3;
    }
    //waiting the completement
    public ArrayDeque(ArrayDeque other) {
        size = other.size;
        items = (T []) new Object[other.items.length];
        nextFirst = other.nextFirst;
        nextLast = other.nextLast;
        System.arraycopy(other.items,0, items, 0, last() + 1);
        System.arraycopy(other.items, front(), items, items.length - (items.length - front()), items.length - front());
    }

    //helper method
    private void resize(int capacity) {
        T[] a = (T []) new Object[capacity];
        System.arraycopy(items,0, a, 0, last() + 1);
        nextLast = last() + 1;
        if (front() != 0) {
            System.arraycopy(items, front(), a, capacity - (items.length - front()), items.length - front());
            nextFirst = capacity - (items.length - front()) - 1;
        } else {
            nextFirst = capacity - 1;
        }
        items = a;
    }

    //get the front and last of the AList
    private int front() {
        if (nextFirst == items.length - 1){
            return 0;
        }
        return nextFirst + 1;
    }
    private int last(){
        if (nextLast == 0){
            return items.length - 1;
        }
        return nextLast - 1;
    }

    //method
    public void addFirst(T item){
        if (items.length == size){
            resize(size * 2);
        }
        items[nextFirst] = item;
        size += 1;
        if (nextFirst == 0){
            nextFirst = items.length - 1;
        } else {
            nextFirst = nextFirst - 1;
        }
    }

    public void addLast(T item) {
        if (items.length == size){
            resize(size * 2);
        }
        items[nextLast] = item;
        size += 1;
        if (nextLast == items.length - 1){
            nextLast = 0;
        }else {
            nextLast = nextLast + 1;
        }
    }

    public boolean isEmpty(){
        if (size == 0){
            return true;
        }
        return false;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        int ptr = front();
        int n = size;
        while (n != 0){
            System.out.print(items[ptr] + " ");
            n = n - 1;
            if (ptr == items.length - 1){
                ptr = 0;
            } else{
                ptr += 1;
            }
        }
        System.out.println();
    }

    public T removeFirst(){
        if (size == 0) {
            return null;
        }
        int ptr = front();
        T thing = items[ptr];
        if (nextFirst == items.length - 1){
            nextFirst = 0;
        }else {
            nextFirst = nextFirst + 1;
        }
        size = size - 1;
        double h = (double)size / items.length;
        if (h <= 0.25 && size >= 8){
            resize(size);
        }
        return thing;
    }

    public T removeLast(){
        if (size == 0) {
            return null;
        }
        int ptr = last();
        T thing = items[ptr];
        if (nextLast == 0){
            nextLast = items.length - 1;
        }else {
            nextLast = nextLast - 1;
        }
        size = size - 1;
        double h = (double)size / items.length;
        if (h <= 0.25 && size >= 8){
            resize(size);
        }
        return thing;
    }

    public T get(int index){
        int ptr = front();
        int n = index;
        while (n != 0) {
            n = n - 1;
            if (ptr == items.length - 1) {
                ptr = 0;
            } else {
                ptr += 1;
            }
        }
        return items[ptr];
    }



}
