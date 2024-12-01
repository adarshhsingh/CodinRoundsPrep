package designpatterns.questions.costexplorer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class Report {
    private String customerId;
    private LocalDate reportDate;
    private Double monthlyCost;
    private Double yearlyCost;
    private Double remainingYearCost;
    String productName;
}
