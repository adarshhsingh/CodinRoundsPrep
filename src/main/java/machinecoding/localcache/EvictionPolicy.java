package machinecoding.localcache;

public interface EvictionPolicy<K, V> {
    void onAccess(Cache<K, V> cache, CacheNode<K, V> node);
    void evict(Cache<K, V> cache);
}
