package designpatterns.questions.stockprices.storage;

public interface StockPriceStorage {
    void upsert(String stock, int timestamp, double price);
    Double getMaxPrice(String stock);
    Double getMaxPrice(String stock, int timestamp);
}
