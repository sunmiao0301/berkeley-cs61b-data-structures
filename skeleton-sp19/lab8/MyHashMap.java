import java.util.*;

public class MyHashMap<K, V> implements Map61B<K, V>{

    private int initialSize = 16;
    private double loadFactor = 0.75;
    private int size = 0;
    private Entry[] mainList;
    private HashSet<K> set;

    private class Entry {
        K key;
        V value;
        Entry next;

        Entry(K key, V value, Entry e){
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    public MyHashMap(){

    }

    public MyHashMap(int initialSize){

    }

    public MyHashMap(int initialSize, double loadFactor){

    }

    @Override
    public void clear() {

    }

    @Override
    public boolean containsKey(K key) {
        return set.contains(key);
    }

    @Override
    public V get(K key) {

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {

    }

    @Override
    public Set keySet() {
        return set;
    }

    @Override
    public V remove(K key) {

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
