package machinecoding.localcache;

public class LRUCacheEvictionPolicy<K, V> implements EvictionPolicy<K, V> {
    // LRU Specific implementation
    @Override
    public void onAccess(Cache<K, V> cache, CacheNode<K, V> node) {
        // move node to head of list
        cache.moveToHead(node);
    }

    @Override
    public void evict(Cache<K, V> cache) {
        // Remove the least recently used item
        CacheNode<K, V> tail = cache.getTail();
        if(tail != null) {
            cache.remove(tail.key);
            tail.prev.next = null;
        }
    }
}
