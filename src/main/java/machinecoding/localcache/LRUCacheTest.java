package machinecoding.localcache;

public class LRUCacheTest {
    public static void main(String[] args) {
        EvictionPolicy<Integer, String> evictionPolicy = new LRUCacheEvictionPolicy<>();
        LRUCace<Integer, String> cache = new LRUCace<>(3, evictionPolicy);
        cache.put(1, "One");
        cache.put(2, "Two");
        cache.put(3, "Three");

        System.out.println(cache.get(2));
        cache.put(4,"Four");
        System.out.println(cache.get(1));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
    }
}
