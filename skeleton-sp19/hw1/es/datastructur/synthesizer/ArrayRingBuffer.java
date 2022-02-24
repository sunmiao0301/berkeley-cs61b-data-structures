package es.datastructur.synthesizer;
import java.util.Iterator;

//TODO: Make sure to that this class and all of its methods are public
//TODO: Make sure to add the override tag for all overridden methods
//TODO: Make sure to make this class implement BoundedQueue<T>

public class ArrayRingBuffer<T> implements BoundedQueue<T>  {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        first = 0;
        last = 0;
        fillCount = 0;
        rb = (T[]) new Object[capacity];
    }

    @Override
    public boolean equals(Object o){
        if((ArrayRingBuffer)o.capacity() != this.capacity())
            return false;
        for(T t : ab)
    }


    @Override
    public int capacity() {
        return rb.length;
    }

    @Override
    public int fillCount() {
        return fillCount;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update
        //       last.
        //try catch
        rb[last++] = x;
        if(last == capacity())
            last = 0;
        fillCount++;
        //return;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and
        //       update first.
        T tmp = rb[first];//这里有点疑问了，要是数组存储的是基本数据类型还好，要是引用类型怎么办
        //解决办法难道就是不null这个索引处的值？ rb[first] == null;
        first++;
        if(first == capacity())
            first = 0;
        fillCount--;
        return tmp;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T peek() {
        // TODO: Return the first item. None of your instance variables should
        //       change.
        return rb[first];
    }

    @Override
    public Iterator<T> iterator() {
        //return null;
        return new MiaoIterator();
    }

    private class MiaoIterator implements Iterator<T>{

        private int wizPos;

        public MiaoIterator(){
            wizPos = 0;
        }

        @Override
        public boolean hasNext() {
            return wizPos < capacity();
        }

        @Override
        public T next() {
            T returnItem = rb[wizPos];
            wizPos++;
            return returnItem;
        }
    }

    // TODO: When you get to part 4, implement the needed code to support
    //       iteration and equals.
}
    // TODO: Remove all comments that say TODO when you're done.
