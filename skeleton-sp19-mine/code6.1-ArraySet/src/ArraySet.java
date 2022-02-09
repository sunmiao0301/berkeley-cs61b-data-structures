import org.omg.CORBA.PRIVATE_MEMBER;
import org.omg.CORBA.REBIND;

import java.util.Iterator;

public class ArraySet<T> implements Iterable<T>{

    private T[] t;
    private int size;

    public ArraySet() {
        t = (T[]) new Object[100];
        size = 0;
    }

    /* Returns true if this map contains a mapping for the specified key.
     */
    public boolean contains(T x) {
        for(int i = 0; i < size; i++){
            if(t[i].equals(x)){
                return true;
            }
        }
        return false;
    }

    /* Associates the specified value with the specified key in this map.
       Throws an IllegalArgumentException if the key is null. */
    public void add(T x) {
        /**
         * https://stackoverflow.com/questions/4501061/java-null-check-why-use-instead-of-equals
         */
        if(x == null)
            return;
        if(this.contains(x))//or if(contains(x)) is ok
            return;
        t[size] = x;
        size++;
    }

    /* Returns the number of key-value mappings in this map. */
    public int size() {
        return size;
    }

    /** returns an iterator (a.k.a. seer) into ME */
    public Iterator<T> iterator() {
        return new ArraySetIterator();
    }

    private class ArraySetIterator implements Iterator<T> {
        private int wizPos;

        public ArraySetIterator() {
            wizPos = 0;
        }

        public boolean hasNext() {
            return wizPos < size;
        }

        public T next() {
            T returnItem = t[wizPos];
            wizPos += 1;
            return returnItem;
        }
    }

    @Override
    public String toString() {
        /* hmmm */
        //对象方法怎么得到参数？
        StringBuilder returnSB = new StringBuilder("{");
        for (int i = 0; i < size - 1; i += 1) {
            returnSB.append(t[i].toString());
            returnSB.append(", ");
        }
        returnSB.append(t[size - 1]);
        returnSB.append("}");
        return returnSB.toString();
    }

    @Override
    public boolean equals(Object other) {
        /* hmmm */
        /**
         *         this can't be null!!!!!!
         *
         *         if(this == null && other == null)
         *             return true;
         *         else if(this == null || other == null)
         *             return false;
         */
        if(other == null)
            return false;
        else if(this.toString().equals(other.toString()))
            return true;
        else
            return false;
    }

    public static void main(String[] args) {
        ArraySet<String> s = new ArraySet<>();
        s.add(null);
        s.add("horse");
        s.add("fish");
        s.add("house");
        s.add("fish");
        System.out.println(s.contains("horse"));
        System.out.println(s.size());

        //iteration
        for(String tmp : s){
            System.out.println(tmp);
        }

        //toString
        System.out.println(s);

        //equals
        ArraySet<Integer> aset2 = new ArraySet<>();
        aset2.add(5);
        aset2.add(23);
        aset2.add(42);

        System.out.println(s.equals(aset2));
        System.out.println(s.equals(null));
        System.out.println(s.equals("fish"));
        System.out.println(s.equals(s));
    }

    /* Also to do:
    1. Make ArraySet implement the Iterable<T> interface.
    2. Implement a toString method.
    3. Implement an equals() method.
    */
}

/**
 * 测试结果如下
 * D:\JDK8\jdk1.8.0_311\bin\java.exe
 * true
 * 3
 */