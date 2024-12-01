package designpatterns.questions.stockprices.storage;

import designpatterns.questions.stockprices.pojo.PriceEntry;

import java.awt.font.LineMetrics;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.locks.ReentrantLock;

public class InMemoryStockPriceStorageImpl implements StockPriceStorage {
    private ConcurrentHashMap<String, ConcurrentSkipListMap<Integer, PriceEntry>> priceMap = new ConcurrentHashMap<>();
    private static InMemoryStockPriceStorageImpl inMemoryStockPriceStorage;
    private InMemoryStockPriceStorageImpl() {
        //
    }

    public static InMemoryStockPriceStorageImpl getInstance() {
        if(inMemoryStockPriceStorage == null) {
            inMemoryStockPriceStorage = new InMemoryStockPriceStorageImpl();
        }
        return inMemoryStockPriceStorage;
    }

    /**
     * @param stock
     * @param timestamp
     * @param price
     */
    @Override
    public void upsert(String stock, int timestamp, double price) {
        ConcurrentSkipListMap<Integer, PriceEntry> stockPrices = priceMap.computeIfAbsent(stock, k -> new ConcurrentSkipListMap<>());
        Map.Entry<Integer, PriceEntry> lastEntry = stockPrices.floorEntry(timestamp);
        double currentMax = (lastEntry == null) ? price : Math.max(price, lastEntry.getValue().getMaxPriceUpTo());
        stockPrices.put(timestamp, new PriceEntry(price, currentMax));
        // Update subsequent entries if needed
        Map.Entry<Integer, PriceEntry> nextEntry = stockPrices.higherEntry(timestamp);
        while (nextEntry != null && nextEntry.getValue().getMaxPriceUpTo() < currentMax) {
            nextEntry.getValue().setMaxPriceUpTo(currentMax);
            nextEntry = stockPrices.higherEntry(nextEntry.getKey());
        }
        System.out.println("****");
        System.out.println("**After entry -" + stock + ", time: " + timestamp + " price :" + price);
        // Iterating over entries
        for (var entry : stockPrices.entrySet()) {
            System.out.println(entry.getKey() + " => price at timestamp: " + entry.getValue().getPrice()+ " => max price till timestamp: " + entry.getValue().getMaxPriceUpTo());
        }
    }

    /**
     * @param stock
     */
    @Override
    public Double getMaxPrice(String stock) {
        ConcurrentSkipListMap<Integer, PriceEntry> stockPrices = priceMap.get(stock);
        if(stockPrices == null || stockPrices.isEmpty()) {
            return null;
        }
        return stockPrices.lastEntry().getValue().getMaxPriceUpTo();
    }

    /**
     * @param stock
     * @param timestamp
     * @return
     */
    @Override
    public Double getMaxPrice(String stock, int timestamp) {
        ConcurrentSkipListMap<Integer, PriceEntry> stockPrices = priceMap.get(stock);
        if(stockPrices == null || stockPrices.isEmpty()) {
            return null;
        }
        Map.Entry<Integer, PriceEntry> lastEntry = stockPrices.floorEntry(timestamp);
        if(lastEntry == null) return null;

        return lastEntry.getValue().getMaxPriceUpTo();
    }

    public static void main(String[] args) {
        StockPriceStorage manager = new InMemoryStockPriceStorageImpl();
        manager.upsert("Gold", 1, 1500.0);
        manager.upsert("Gold", 3, 1510.0);
        manager.upsert("Gold", 2, 1520.0);

        // Testing getPriceAtTimestamp
        System.out.println("Price for Gold MAX: " + manager.getMaxPrice("Gold"));
        System.out.println("Price for Gold at timestamp 4: " + manager.getMaxPrice("Gold", 4)); // Should print 1510.0 (last known price before timestamp 4)
        System.out.println("Price for Gold at timestamp 1: " + manager.getMaxPrice("Gold", 1)); // Should print 1510.0 (last known price before timestamp 4)

    }
}
