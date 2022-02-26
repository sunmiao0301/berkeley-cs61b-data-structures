import java.util.*;

public class MyHashMap<K, V> implements Map61B<K, V>{
    /**
     * 实现之前，我看了一下测试的代码的情况。
     * 没有出现K是对象的情况，而都是不变类String
     * 言下之意就是说不必考虑K 的hashCode和equals重写情况吧
     */
    private int initialSize;// = 16;
    private double loadFactor;// = 0.75;
    private int size;// = 0; and you should remember to know the different between size and capacity.
    private Entry<K, V>[] mainList = (Entry<K, V>[]) new Object[initialSize];
    private HashSet<K> set;

    private class Entry<K, V> { //you should imitate the node in class LinkedList.
        K key;
        V value;
        Entry<K, V> next;

        Entry(K key, V value){
            this.key = key;
            this.value = value;
            this.next = null;
        }

        Entry(K key, V value, Entry<K, V> entry){
            this.key = key;
            this.value = value;
            this.next = entry;
        }
    }

    //hashOnKey 方法写在内部类中就找不到
    int hashOnKey(K key){
        return (int) key.hashCode() % initialSize;//内部类可以访问外部类
    }


    public MyHashMap(){
        initialSize = 16;
        loadFactor = 0.75;
        size = 0;
        mainList = (Entry<K, V>[]) new Object[initialSize];
        set = new HashSet<>();
    }

    public MyHashMap(int initialSize){
        this.initialSize = initialSize;
        loadFactor = 0.75;
        size = 0;
        mainList = (Entry<K, V>[]) new Object[initialSize];
        set = new HashSet<>();
    }

    public MyHashMap(int initialSize, double loadFactor){
        this.initialSize = initialSize;
        this.loadFactor = loadFactor;
        size = 0;
        mainList = (Entry<K, V>[]) new Object[initialSize];
        set = new HashSet<>();
    }

    @Override
    public void clear() {
        //initialSize = 16;
        //loadFactor = 0.75;
        size = 0;
        mainList = (Entry<K, V>[]) new Object[initialSize];
        set = new HashSet<>();
    }

    @Override
    public boolean containsKey(K key) {
        return set.contains(key);
    }

    @Override
    public V get(K key) {
        Entry<K, V> e = mainList[hashOnKey(key)];
        while(e != null){
            if(key.equals(e.key))
                return e.value;
            e = e.next;
        }
        return null;
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        Entry<K, V> e = mainList[hashOnKey(key)];
        while(e != null){
            if(e.key.equals(key)){
                e.value = value;
                return;
            }
            e = e.next;
        }
        e = mainList[hashOnKey(key)];
        Entry<K, V> insert = new Entry<K, V>(key, value, e.next);
        e.next = insert;

        size++;
        set.add(key);
    }

    @Override
    public Set keySet() {
        return set;
    }

    @Override
    public V remove(K key) {
        Entry<K, V> e = mainList[hashOnKey(key)];
        if(e == null)
            return null;
        while(e.next != null){
            if(e.next.key.equals(key)){
                V tmp = e.value;
                e.next = e.next.next;
                return tmp;
            }
            e = e.next;
        }
        return null;
    }

    @Override
    public Object remove(Object key, Object value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator iterator() {
        return null;
    }
}
