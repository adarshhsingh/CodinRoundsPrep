package designpatterns.questions.costexplorer.service;

import designpatterns.questions.costexplorer.model.Customer;
import designpatterns.questions.costexplorer.model.Report;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

public class ReportGenerator {
    private static final Logger logger = LoggerFactory.getLogger(ReportGenerator.class);

    public Report generateMonthlyReport(Customer customer) {
        logger.info("Generating monthly report for customer: " + customer.getId());
        try {
            double monthlyCost = CostCalculator.calculateMonthlyCost(customer.getProduct());
            return new Report(customer.getId(), LocalDate.now(), monthlyCost, null, null, customer.getProduct().getName());
        } catch (Exception e) {
            logger.error("not able to generate report");
            throw new RuntimeException("Error generating monthly report", e);
        }
    }

    public Report generateYearlyReport(Customer customer) {
        logger.info("Generating yearly report for customer: " + customer.getId());

        try {
            double yearlyCost = CostCalculator.calculateYearlyCost(customer.getProduct());
            return new Report(customer.getId(), LocalDate.now(), null, yearlyCost, null, customer.getProduct().getName());
        } catch (Exception e) {
            logger.error("not able to generate report");
            throw new RuntimeException("Error generating monthly report", e);
        }
    }

    public Report generateRemainingYearReport(Customer customer) {
        logger.info("Generating remaining year report for customer: " + customer.getId());

        try {
            double remainingYearCost = CostCalculator.calculateRemainingYearCost(customer.getProduct());
            Report report = new Report(customer.getId(), LocalDate.now(), null, null,remainingYearCost, customer.getProduct().getName());
            logger.info("Generated remaining year report: " + report);
            return report;
        } catch (Exception e) {
            logger.error("Error generating remaining year report for customer: " + customer.getId() + " - " + e.getMessage());
            throw new RuntimeException("Error generating remaining year report", e);
        }
    }
}
