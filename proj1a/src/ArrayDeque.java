public class ArrayDeque<T> {
    private int size;
    private T[] items;
    private int nextFirst;
    private int nextLast;
    //constructor
    public ArrayDeque(){
        size = 0;
        items = (T []) new Object[8];
        nextFirst = 2;
        nextLast = 3;
    }

    public ArrayDeque(ArrayDeque other){
        size = other.size;

        nextFirst = other.nextFirst;
        nextLast = other.nextLast;

    }

    //helper method
    private void resize(int capacity){
        T[] a = (T []) new Object[capacity];
        System.arraycopy(items,0,a,0,size);
        items = a;
    }
    //get the front and last of the AList
    private int front(){
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
            nextFirst = items.length - 1;
        }
        items[nextFirst] = item;
        size += 1;
        if (nextFirst == 0){
            nextFirst = items.length - 1;
        }else {
            nextFirst = nextFirst - 1;
        }
    }

    public void addLast(T item){
        if (items.length == size){
            resize(size * 2);
            nextLast = size;
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
        while(n != 0){
            System.out.print(items[ptr] + " ");
            n = n - 1;
            if (ptr == items.length - 1){
                ptr = 0;
            }else if (ptr == last()){
                ptr = 0;
            }else{
                ptr += 1;
            }
        }
        System.out.println();
    }

    public T removeFirst(){
        int ptr = front();
        T thing = items[ptr];
        if (nextFirst == items.length - 1){
            nextFirst = 0;
        }else {
            nextFirst = nextFirst + 1;
        }
        size = size - 1;
        if (size/items.length == 0.25 && size >= 8){
            resize(size);
        }
        items[ptr] = null;
        return thing;
    }

    public T removeLast(){
        int ptr = last();
        T thing = items[ptr];
        if (nextLast == 0){
            nextLast = items.length - 1;
        }else {
            nextLast = nextLast - 1;
        }
        size = size - 1;
        if (size/items.length == 0.25 && size >= 8){
            resize(size);
        }
        items[ptr] = null;
        return thing;
    }

    public T get(int index){
        int ptr = front();
        int n = index;
        while(n != 0) {
            n = n - 1;
            if (ptr == items.length - 1) {
                ptr = 0;
            } else if (ptr == last()) {
                ptr = 0;
            } else {
                ptr += 1;
            }
        }
        return items[ptr];
    }

}
