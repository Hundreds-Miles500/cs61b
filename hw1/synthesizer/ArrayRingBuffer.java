package synthesizer;// TODO: Make sure to make this class a part of the synthesizer package


import java.util.Iterator;

//TODO: Make sure to make this class and all of its methods public
//TODO: Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T>  {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
        this.fillCount = 0;
        this.capacity = capacity;
        this.first = 0;
        this.last = 0;
        rb = (T []) new Object[capacity];
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update last.
        if (isFull()) {
            throw new RuntimeException("The Queue is full");
        }
        rb[last] = x;
        last = (last + 1) % capacity;
        fillCount += 1;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and update
        if (isEmpty()) {
            throw new RuntimeException("The Queue is empty");
        }
        int tmp = first;
        first = first + 1;
        fillCount = fillCount - 1;
        if (first == capacity) {
            first = 0;
        }

        return rb[tmp];
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        // TODO: Return the first item. None of your instance variables should change.
        return rb[first];
    }

    // TODO: When you get to part 5, implement the needed code to support iteration.
    public Iterator<T> iterator() {
        return new ArrayBufferIterator();
    }

    private class ArrayBufferIterator implements Iterator<T> {
        private int Pos;

        public ArrayBufferIterator() {
            Pos = first;
        }

        public boolean hasNext() {
            return Pos != last ;
        }

        public T next() {
            T returnItem = rb[Pos];
            if (Pos == capacity) {
                Pos = 0;
            }
            else {
                Pos += 1;
            }
            return returnItem;

        }
    }

    public static void main(String[] args) {
        ArrayRingBuffer<Double> x = new ArrayRingBuffer<>(169);
        for (int i = 0; i < x.capacity(); i ++) {
            double r = Math.random() - 0.5;
            x.enqueue(r);
        }
        x.dequeue();
        x.enqueue(0.9);
        x.enqueue(0.8);
    }
}