package designpatterns.questions.costexplorer.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FixedPricingModel implements PricingModel {
    private Double monthlyCost;
    private Double yearlyCost;

    /**
     * calculates monthly cost
     * @return
     */
    @Override
    public Double calculateMonthlyCost() {
        return this.monthlyCost;
    }

    /**
     * calculates yealry cost
     * @return
     */
    @Override
    public Double calculateYearlyCost() {
        return this.yearlyCost;
    }
}
