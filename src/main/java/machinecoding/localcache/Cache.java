package machinecoding.localcache;

public interface Cache<K, V> {
    V get(K key);
    void put(K key, V value);
    int size();
    void clear();
    void remove(K key);
    CacheNode<K, V> getHead();
    CacheNode<K, V> getTail();
    void moveToHead(CacheNode<K, V> node);
}
