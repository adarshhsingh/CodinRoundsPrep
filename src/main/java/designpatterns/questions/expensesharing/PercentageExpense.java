package designpatterns.questions.expensesharing;

import java.util.List;
import java.util.Map;

public class PercentageExpense extends Expense {
    private Map<User, Double> percentagePaidByUser;

    public PercentageExpense(String expenseId, double amount, User paidBy, List<User> sharedBy) {
        super(expenseId, amount, paidBy, sharedBy);
    }

    @Override
    public void calculateShare(Map< User, Map<User, Double>> balanceSheet) {
    }
    // Specific implementation for percentage-based expense sharing
}
