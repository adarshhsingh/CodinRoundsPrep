package machinecoding.localcache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class LRUCace <K, V> implements Cache<K, V> {
    private final int capacity;
    private final ConcurrentHashMap<K, CacheNode<K,V>> cache;
    private final EvictionPolicy<K, V> evictionPolicy;
    private CacheNode<K, V> head, tail;
    private final ReentrantLock lock = new ReentrantLock();

    public LRUCace(int capacity, EvictionPolicy<K, V> evictionPolicy) {
        this.capacity = capacity;
        this.evictionPolicy = evictionPolicy;
        this.cache = new ConcurrentHashMap<>(capacity);
    }

    public V get(K key) {
        lock.lock();
        try {
            CacheNode<K, V> node = cache.get(key);
            if(node == null) return null;
            evictionPolicy.onAccess(this, node);
            return node.value;
            /**if(!cache.containsKey(key)) return null;
            CacheNode<K, V> node = cache.get(key);
            moveToHead(node);
            return node.value;**/
        } finally {
            lock.unlock();
        }
    }

    public void put(K key, V value) {
        lock.lock();
        try {
            CacheNode<K, V> node = cache.get(key);
            if(node != null) {
                node.value = value;
                evictionPolicy.onAccess(this, node);
            } else {
                if(cache.size() >= capacity) {
                    evictionPolicy.evict(this);
                }
                CacheNode<K, V> newNode = new CacheNode<>(key, value);
                addNode(newNode);
                cache.put(key, newNode);
            }

            /*if (cache.containsKey(key)) {
                CacheNode<K, V> node = cache.get(key);
                node.value = value;
                moveToHead(node);
            } else {
                if (cache.size() == capacity) {
                    cache.remove(tail.key);
                    removeNode(tail);
                }
                CacheNode<K, V> newNode = new CacheNode<>(key, value);
                addNode(newNode);
                cache.put(key, newNode);*
            }*/
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int size() {
        lock.lock();
        try {
            return cache.size();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void clear() {
        lock.lock();
        try {
            cache.clear();
            head = null;
            tail = null;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void remove(K key) {
        lock.lock();
        try {
            CacheNode<K, V> node = cache.remove(key);
            if (node != null) {
                removeNode(node);
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public CacheNode<K, V> getHead() {
        lock.lock();
        try {
            return head;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public CacheNode<K, V> getTail() {
        lock.lock();
        try {
            return tail;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void moveToHead(CacheNode<K, V> node) {
        lock.lock();
        try {
            if(node != head) {
                removeNode(node);
                addNode(node);
            }
        } finally {
            lock.unlock();
        }
    }

    private void addNode(CacheNode<K, V> node) {
        node.next = head;
        node.prev = null;
        if(head != null) {
            head.prev = node;
        }
        head = node;
        if(tail == null) {
            tail = node;
        }
    }

    private void removeNode(CacheNode<K, V> node) {
        if(node.prev != null) {
            node.prev.next = node.next;
        } else {
            head = node.next;
        }

        if(node.next != null) {
            node.next.prev = node.prev;
        } else {
            tail = node.prev;
        }
    }
}
