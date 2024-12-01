package machinecoding.localcache;

public class CacheNode <K, V> {
    public K key;
    public V value;
    CacheNode<K,V> prev;
    CacheNode<K,V> next;

    public CacheNode(K key, V value) {
        this.key = key;
        this.value = value;
    }
}
