package designpatterns.questions.expensesharing;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public abstract class Expense {
    private String expenseId;
    private double amount;
    private User paidBy;
    private List<User> sharedBy;

    // Constructors, Getters, and Setters
    // Abstract method to calculate the amount each user needs to pay
    public abstract void calculateShare(Map< User, Map<User, Double>> balanceSheet);
}

