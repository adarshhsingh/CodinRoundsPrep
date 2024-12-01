package designpatterns.questions.stockprices.service;

import designpatterns.questions.stockprices.storage.InMemoryStockPriceStorageImpl;
import designpatterns.questions.stockprices.storage.StockPriceStorage;

public class StockPriceServiceImpl implements StockPriceService {

    private final StockPriceStorage stockPriceStorage;

    public StockPriceServiceImpl(StockPriceStorage stockPriceStorage) {
        this.stockPriceStorage = stockPriceStorage;
    }

    /**
     * @param timestamp
     * @param price
     */
    @Override
    public void addOrUpdatePrice(String stock, int timestamp, double price) {
        stockPriceStorage.upsert(stock,timestamp, price);
    }

    /**
     * @return
     */
    @Override
    public double getMaxPrice(String stock) {
        return stockPriceStorage.getMaxPrice(stock);
    }

    public double getMaxPrice(String stock, int timestamp) {
        return stockPriceStorage.getMaxPrice(stock);
    }

    public static void main(String[] args) {
        StockPriceService manager = new StockPriceServiceImpl(InMemoryStockPriceStorageImpl.getInstance());
        manager.addOrUpdatePrice("Gold", 1, 1500.0);
        manager.addOrUpdatePrice("Gold", 3, 1510.0);
        manager.addOrUpdatePrice("Gold", 2, 1520.0);

        // Testing getPriceAtTimestamp
        System.out.println("Price for Gold MAX: " + manager.getMaxPrice("Gold"));
        System.out.println("Price for Gold at timestamp 4: " + manager.getMaxPrice("Gold", 4)); // Should print 1510.0 (last known price before timestamp 4)
        System.out.println("Price for Gold at timestamp 1: " + manager.getMaxPrice("Gold", 1)); // Should print 1510.0 (last known price before timestamp 4)
    }
}
