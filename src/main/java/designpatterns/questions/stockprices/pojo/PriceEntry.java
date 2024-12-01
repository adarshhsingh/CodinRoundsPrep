package designpatterns.questions.stockprices.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PriceEntry {
    private double price;
    private double maxPriceUpTo;
}
