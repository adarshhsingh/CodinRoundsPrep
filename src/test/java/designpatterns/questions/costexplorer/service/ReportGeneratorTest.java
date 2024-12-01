package designpatterns.questions.costexplorer.service;

import designpatterns.questions.costexplorer.model.Customer;
import designpatterns.questions.costexplorer.model.FixedPricingModel;
import designpatterns.questions.costexplorer.model.Product;
import designpatterns.questions.costexplorer.model.Report;
import org.junit.Test;

import static org.junit.Assert.*;


import java.time.LocalDate;

public class ReportGeneratorTest {

    @Test
    public void testGenerateMonthlyReport() {
        Product product = new Product("Fixed Product", new FixedPricingModel(100.00, 1200.00)) {};
        Customer customer = new Customer("C001", "John Doe", product, LocalDate.now());

        ReportGenerator reportGenerator = new ReportGenerator();
        Report report = reportGenerator.generateMonthlyReport(customer);

        assertNotNull(report);
        assertEquals(customer.getId(), report.getCustomerId());
        assertEquals(100.00, (report.getMonthlyCost()==null)?0.00:report.getMonthlyCost(),0.01);
        assertEquals(0.00, (report.getYearlyCost()==null)?0.00:report.getYearlyCost(),0.01);
        assertEquals(0.00, (report.getRemainingYearCost()==null)?0.00:report.getRemainingYearCost(),0.01);
        assertEquals(product.getName(), report.getProductName());
    }

    @Test
    public void testGenerateYearlyReport() {
        Product product = new Product("Fixed Product", new FixedPricingModel(100.00, 1200.00)) {};
        Customer customer = new Customer("C001", "John Doe", product, LocalDate.now());

        ReportGenerator reportGenerator = new ReportGenerator();
        Report report = reportGenerator.generateYearlyReport(customer);

        assertNotNull(report);
        assertEquals(customer.getId(), report.getCustomerId());
        assertEquals(0.00, (report.getMonthlyCost()==null)?0.00:report.getMonthlyCost(),0.01);
        assertEquals(1200.00, (report.getYearlyCost()==null)?0.00:report.getYearlyCost(),0.01);
        assertEquals(0.00, (report.getRemainingYearCost()==null)?0.00:report.getRemainingYearCost(),0.01);
        assertEquals(product.getName(), report.getProductName());
    }

    @Test
    public void testGenerateRemainingYearReport() {
        Product product = new Product("Fixed Product", new FixedPricingModel(100.00, 1200.00)) {};
        Customer customer = new Customer("C001", "John Doe", product, LocalDate.now());

        ReportGenerator reportGenerator = new ReportGenerator();
        Report report = reportGenerator.generateRemainingYearReport(customer);

        double monthlyCost = CostCalculator.calculateMonthlyCost(product);

        LocalDate today = LocalDate.now();

        int currentMonth = today.getMonthValue();
        int currentDay = today.getDayOfMonth();

        int totalDaysInCurrentMonth = today.lengthOfMonth();

        int remainingDaysInCurrentMonth = totalDaysInCurrentMonth - currentDay + 1;
        double proratedCurrentMonthCost = monthlyCost * ( remainingDaysInCurrentMonth / totalDaysInCurrentMonth);

        int remainingMonths = 12 - currentMonth;
        double expectedRemainingYearCost = proratedCurrentMonthCost + (monthlyCost * remainingMonths);

        assertNotNull(report);
        assertEquals(customer.getId(), report.getCustomerId());
        assertEquals(0.00, (report.getMonthlyCost()==null)?0.00:report.getMonthlyCost(),0.01);
        assertEquals(0.01, (report.getYearlyCost()==null)?0.00:report.getYearlyCost(),0.01);
        assertEquals(expectedRemainingYearCost, (report.getRemainingYearCost()==null)?0.00:report.getRemainingYearCost(),0.01);
        assertEquals(product.getName(), report.getProductName());
    }
}
