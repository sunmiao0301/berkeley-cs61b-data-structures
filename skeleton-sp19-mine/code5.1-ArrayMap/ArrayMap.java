import jdk.nashorn.internal.runtime.regexp.joni.ast.StringNode;

import java.util.LinkedList;
import java.util.List;

public class ArrayMap<K, V> {

    private K[] key;
    private V[] value;
    private int size;

    public ArrayMap(int size){
        key = (K[]) new Object[size];
        value = (V[]) new Object[size];
        size = 0;
    }

    public boolean containsKey(K k){
        for(int i = 0; i < size; i++){
            if(key[i].equals(k)){// == or equals?
                return true;
            }
        }
        return false;
    }//: Checks if map contains the key.

    public void put(K k, V v){
            for(int i = 0; i < size; i++){
                if(key[i].equals(k)){// == or equals?
                    value[i] = v;
                    return;
                }
            }
            key[size] = k;
            value[size] = v;
            size++;
    }//: Associate key with value.

    public V get(K k){
        int i = 0;
        while(i < size){
            if(key[i].equals(k)){// == or equals?
                return value[i];
            }
            i++;
        }
        return value[i - 1];//这一句是废的 get之前必须exist(assuming key exists.)
    }//: Returns value, assuming key exists.

    public List keys(){
        List<K> list = new LinkedList<>();
        for(int i = 0; i < size; i++){
            list.add(key[i]);
        }
        return list;
    }//: Returns a list of all keys.

    public int size(){
        return size;
    }//: Returns number of keys.

    public static void main(String[] args){
        ArrayMap<String, Integer> map = new ArrayMap<>(100);
        map.put("simon", 1);
        System.out.println(map.containsKey("simon"));
        System.out.println(map.get("simon"));
        System.out.println(map.keys());
        System.out.println(map.size());
    }
}

