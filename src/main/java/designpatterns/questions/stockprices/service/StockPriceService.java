package designpatterns.questions.stockprices.service;

public interface StockPriceService {
    void addOrUpdatePrice(String stock, int timestamp, double price);
    double getMaxPrice(String stock);
    double getMaxPrice(String stock, int timestamp);
}
