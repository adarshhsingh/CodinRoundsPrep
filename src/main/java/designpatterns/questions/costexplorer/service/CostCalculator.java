package designpatterns.questions.costexplorer.service;

import designpatterns.questions.costexplorer.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

public class CostCalculator {
    private static final Logger logger = LoggerFactory.getLogger(CostCalculator.class);

    public static Double calculateMonthlyCost(Product product) {
        if (product == null || product.getPricingModel() == null) {
            logger.error("Product or PricingModel is null");
            throw new IllegalArgumentException("Product or PricingModel cannot be null");
        }
        logger.info("Calculated monthly cost: " + product.getPricingModel().calculateMonthlyCost());
        return product.getPricingModel().calculateMonthlyCost();
    }

    public static Double calculateYearlyCost(Product product) {
        if (product == null || product.getPricingModel() == null) {
            logger.error("Product or PricingModel is null");
            throw new IllegalArgumentException("Product or PricingModel cannot be null");
        }
        logger.info("Calculated yearly cost: " + product.getPricingModel().calculateYearlyCost());
        return product.getPricingModel().calculateYearlyCost();
    }

    public static Double calculateRemainingYearCost(Product product) {
        if (product == null || product.getPricingModel() == null) {
            logger.error("Product or PricingModel is null");
            throw new IllegalArgumentException("Product or PricingModel cannot be null");
        }

        double monthlyCost = calculateMonthlyCost(product);

        LocalDate today = LocalDate.now();

        int currentMonth = today.getMonthValue();
        int currentDay = today.getDayOfMonth();

        int totalDaysInCurrentMonth = today.lengthOfMonth();

        int remainingDaysInCurrentMonth = totalDaysInCurrentMonth - currentDay + 1;
        double proratedCurrentMonthCost = monthlyCost * ( remainingDaysInCurrentMonth / totalDaysInCurrentMonth);

        int remainingMonths = 12 - currentMonth;
        double remainingYearCost = proratedCurrentMonthCost + (monthlyCost * remainingMonths);

        logger.info("Calculated remaining year cost: " + remainingYearCost);
        return remainingYearCost;
    }
}
