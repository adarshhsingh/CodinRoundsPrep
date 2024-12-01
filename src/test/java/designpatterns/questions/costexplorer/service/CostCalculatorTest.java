package designpatterns.questions.costexplorer.service;


import designpatterns.questions.costexplorer.model.FixedPricingModel;
import designpatterns.questions.costexplorer.model.PricingModel;
import designpatterns.questions.costexplorer.model.Product;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Optional;

public class CostCalculatorTest {
    @Test
    public void testCalculateMonthlyCost() {
        PricingModel fixedPricingModel = new FixedPricingModel(100.00, 1200.00);
        Product fixedProduct = new Product("Fixed Product", fixedPricingModel) {};

        Assert.assertEquals(100.00, CostCalculator.calculateMonthlyCost(fixedProduct).doubleValue(), 0.01);

        PricingModel usagePricingModel = new FixedPricingModel(50.00, 600.00);
        Product usageProduct = new Product("Usage Product", usagePricingModel) {};

        Assert.assertEquals(50.00, CostCalculator.calculateMonthlyCost(usageProduct).doubleValue(), 0.01);
    }

    @Test
    public void testCalculateYearlyCost() {
        PricingModel fixedPricingModel = new FixedPricingModel(100.00, 1200.00);
        Product fixedProduct = new Product("Fixed Product", fixedPricingModel) {};

        Assert.assertEquals(1200, CostCalculator.calculateYearlyCost(fixedProduct).doubleValue(), 0.01);

        PricingModel usagePricingModel = new FixedPricingModel(50.00, 600.00);
        Product usageProduct = new Product("Usage Product", usagePricingModel) {};

        Assert.assertEquals(600, CostCalculator.calculateYearlyCost(usageProduct).doubleValue(), 0.01);
    }

    @Test
    public void testCalculateRemainingYearCost() {
        PricingModel fixedPricingModel = new FixedPricingModel(100.00, 1200.00);
        Product fixedProduct = new Product("Fixed Product", fixedPricingModel) {};

        double monthlyCost = CostCalculator.calculateMonthlyCost(fixedProduct);

        LocalDate today = LocalDate.now();

        int currentMonth = today.getMonthValue();
        int currentDay = today.getDayOfMonth();

        int totalDaysInCurrentMonth = today.lengthOfMonth();

        int remainingDaysInCurrentMonth = totalDaysInCurrentMonth - currentDay + 1;
        double proratedCurrentMonthCost = monthlyCost * ( remainingDaysInCurrentMonth / totalDaysInCurrentMonth);

        int remainingMonths = 12 - currentMonth;
        double expectedRemainingYearCost = proratedCurrentMonthCost + (monthlyCost * remainingMonths);
        Assert.assertEquals(expectedRemainingYearCost, CostCalculator.calculateRemainingYearCost(fixedProduct).doubleValue(), 0.01);
    }

    @Test
    public void testCalculateMonthlyCostWithNullProduct() {
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            CostCalculator.calculateMonthlyCost(null);
        });
    }

    @Test
    public void testCalculateYearlyCostWithNullProduct() {
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            CostCalculator.calculateYearlyCost(null);
        });
    }

    @Test
    public void testCalculateRemainingYearCostWithNullProduct() {
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            CostCalculator.calculateRemainingYearCost(null);
        });
    }

    @Test
    public void testCalculateMonthlyCostWithNullPricingModel() {
        Product product = new Product("Null PricingModel Product", null) {};
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            CostCalculator.calculateMonthlyCost(product);
        });
    }

    @Test
    public void testCalculateYearlyCostWithNullPricingModel() {
        Product product = new Product("Null PricingModel Product", null) {};
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            CostCalculator.calculateYearlyCost(product);
        });
    }

    @Test
    public void testCalculateRemainingYearCostWithNullPricingModel() {
        Product product = new Product("Null PricingModel Product", null) {};
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            CostCalculator.calculateRemainingYearCost(product);
        });
    }
}
