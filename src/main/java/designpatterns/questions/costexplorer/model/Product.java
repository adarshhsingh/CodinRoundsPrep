package designpatterns.questions.costexplorer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Product {
    protected String name;
    protected PricingModel pricingModel;
}
