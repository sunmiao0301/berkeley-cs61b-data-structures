import com.sun.org.apache.bcel.internal.generic.LADD;
import com.sun.org.apache.xerces.internal.impl.xs.util.XSInputSource;

import java.util.List;

public class ArrayDeque<T> {
    /**
     * 我们强烈建议您在本练习中将阵列视为圆形。
     * 换句话说，如果你的前端指针位于零位置，并且你 addFirst，
     * 前端指针应该循环回到数组的末尾（因此双端队列中的新前端项将是基础数组中的最后一项）。
     * 与非循环方法相比，这将导致更少的头痛。
     */

    T[] arr;//= (T[]) new Object[8];
    int size;
    int first;// = 0;
    int last;// = 0;

    ArrayDeque(){
        arr = (T[]) new Object[8];
        size = 0;
        first = 0;
        last = 0;
    }

    public void addFirst(T item) {
        arr[(first + arr.length) % arr.length] = item;
        first--;
        size++;
    }

    public void addLast(T item){
        arr[last] = item;
        last++;
        size++;
    }

    public boolean isEmpty() {
        if(size == 0)
            return true;
        return false;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for(int i = first; i < last; i++){
            System.out.print(arr[(i + arr.length) % arr.length]);
        }
    }
    public T removeFirst() {
        T tmp = arr[(first + arr.length) % arr.length];
        arr[(first + arr.length) % arr.length] = null;
        first++;
        size--;
        return tmp;
    }
    public T removeLast() {
        T tmp = arr[last];
        arr[last] = null;
        //...
        return tmp;//ignore
    }
    public T get(int index) {
        return arr[index];//ignore
    }
}
