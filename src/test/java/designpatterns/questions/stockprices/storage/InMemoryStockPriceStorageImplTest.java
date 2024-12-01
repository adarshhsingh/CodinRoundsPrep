package designpatterns.questions.stockprices.storage;

import designpatterns.questions.stockprices.pojo.PriceEntry;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static org.junit.Assert.assertEquals;
public class InMemoryStockPriceStorageImplTest {
        private final InMemoryStockPriceStorageImpl stockPriceStorage = InMemoryStockPriceStorageImpl.getInstance();

        @Test
        public void testBasicFunctionality() {
            stockPriceStorage.upsert("AAPL", 1, 150.0);
            assertEquals(150.0, stockPriceStorage.getMaxPrice("AAPL"), 0.0);

            stockPriceStorage.upsert("AAPL", 2, 160.0);
            stockPriceStorage.upsert("AAPL", 3, 155.0);

            assertEquals(160.0, stockPriceStorage.getMaxPrice("AAPL"), 0.0);
            assertEquals(160.0, stockPriceStorage.getMaxPrice("AAPL", 3), 0.0);
            assertEquals(160.0, stockPriceStorage.getMaxPrice("AAPL", 2), 0.0);
            assertEquals(150.0, stockPriceStorage.getMaxPrice("AAPL", 1), 0.0);
        }

        @Test
        public void testConcurrentUpsert() throws InterruptedException {
            int numThreads = 10;
            int numInsertsPerThread = 100;
            ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
            CountDownLatch latch = new CountDownLatch(numThreads);

            for (int i = 0; i < numThreads; i++) {
                final int threadId = i;
                executorService.submit(() -> {
                    for (int j = 0; j < numInsertsPerThread; j++) {
                        int timestamp = threadId * numInsertsPerThread + j;
                        double price = Math.random() * 1000;
                        stockPriceStorage.upsert("MSFT", timestamp, price);
                    }
                    latch.countDown();
                });
            }

            latch.await();
            executorService.shutdown();
            // Verify the final max price
            System.out.println("Max price for MSFT: " + stockPriceStorage.getMaxPrice("MSFT"));
        }

        @Test
        public void testConcurrentRead() throws InterruptedException {
            stockPriceStorage.upsert("GOOGL", 1, 1500.0);
            stockPriceStorage.upsert("GOOGL", 2, 1600.0);
            stockPriceStorage.upsert("GOOGL", 3, 1550.0);

            int numThreads = 10;
            ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
            CountDownLatch latch = new CountDownLatch(numThreads);

            for (int i = 0; i < numThreads; i++) {
                executorService.submit(() -> {
                    for (int j = 0; j < 100; j++) {
                        double maxPrice = stockPriceStorage.getMaxPrice("GOOGL");
                        assertEquals(1600.0, maxPrice, 0.0);
                    }
                    latch.countDown();
                });
            }

            latch.await();
            executorService.shutdown();
        }

        @Test
        public void testMixedOperations() throws InterruptedException {
            int numThreads = 20;
            ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
            CountDownLatch latch = new CountDownLatch(numThreads);

            for (int i = 0; i < numThreads / 2; i++) {
                executorService.submit(() -> {
                    for (int j = 0; j < 100; j++) {
                        int timestamp = (int) (Math.random() * 1000);
                        double price = Math.random() * 1000;
                        stockPriceStorage.upsert("AMZN", timestamp, price);
                    }
                    latch.countDown();
                });
            }

            for (int i = numThreads / 2; i < numThreads; i++) {
                executorService.submit(() -> {
                    for (int j = 0; j < 100; j++) {
                        double maxPrice = stockPriceStorage.getMaxPrice("AMZN");
                        System.out.println("Max price for AMZN: " + maxPrice);
                    }
                    latch.countDown();
                });
            }

            latch.await();
            executorService.shutdown();
        }
}
