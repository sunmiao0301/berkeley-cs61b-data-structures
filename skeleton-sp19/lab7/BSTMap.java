import java.util.Iterator;
import java.util.Set;

public class BSTMap<K, V> implements Map61B<K, V>{

    private class Entry<K, V>{

    }

    //p.s. assumed that the K is comparable.
    @Override
    public void clear() {

    }

    @Override
    public boolean containsKey(K key) {
        return false;
    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void put(K key, V value) {

    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
