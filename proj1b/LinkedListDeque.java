
public class LinkedListDeque<T> implements Deque<T> {
    private int size;
    private IntNode sentinel;
    //DLList default constructor
    public LinkedListDeque(){
        size = 0;
        sentinel = new IntNode(sentinel, null, sentinel);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }
    //copy the LinkedList
    public LinkedListDeque(LinkedListDeque other){
        this();
        IntNode ptr = other.sentinel.next;
        while (this.size != other.size){
            this.addLast((T) ptr.item);
            ptr = ptr.next;
        }
    }


    //class IntNode
    private class IntNode{
        private IntNode prev;
        private T item;
        private IntNode next;

        public IntNode(IntNode p, T i, IntNode n){
            prev = p;
            item = i;
            next = n;
        }
        //to help getRecursive of DLList
        public T getRecursive(int index) {
            if (index == 0) {
                return item;
            }
            return next.getRecursive(index - 1);
        }
    }

    //method
    @Override
    public void addFirst(T item){
        sentinel.next = new IntNode(sentinel, item, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }

    @Override
    public void addLast(T item){
        sentinel.prev = new IntNode(sentinel.prev, item, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque(){
        IntNode ptr = sentinel.next;
        while (ptr.item != null){
            System.out.print(ptr.item + " ");
            ptr = ptr.next;
        }
        System.out.println(" ");
    }

    @Override
    public T removeFirst(){
        IntNode ptr = sentinel.next;
        if (ptr.item == null){
            return null;
        }
        sentinel.next = ptr.next;
        ptr.next.prev = sentinel;
        ptr.next = null;
        ptr.prev = null;
        size = size - 1;
        return ptr.item;
    }

    @Override
    public T removeLast(){
        IntNode ptr = sentinel.prev;
        if (ptr.item == null){
            return null;
        }
        sentinel.prev = ptr.prev;
        ptr.prev.next = sentinel;
        ptr.next = null;
        ptr.prev = null;
        size = size - 1;
        return ptr.item;
    }

    @Override
    public T get(int index){
        IntNode ptr = sentinel.next;
        if (ptr.item == null){
            return null;
        }
        while (index != 0){
            ptr = ptr.next;
            index--;
        }
        return ptr.item;
    }

    public T getRecursive(int index) {
        if (sentinel.next.item == null){
            return null;
        }
        return sentinel.next.getRecursive(index);
    }

}
