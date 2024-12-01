package designpatterns.questions.expensesharing;

import java.util.List;
import java.util.Map;

public class ExactExpense extends Expense {
    private Map<User, Double> amountPaidByUser;

    public ExactExpense(String expenseId, double amount, User paidBy, List<User> sharedBy) {
        super(expenseId, amount, paidBy, sharedBy);
    }

    @Override
    public void calculateShare(Map< User, Map<User, Double>> balanceSheet) {
        int numberOfUsers = getSharedBy().size();
        double share = getAmount() / numberOfUsers;
        for(User user: getSharedBy()) {
            //balanceSheet.put(user,share);
        }

    }
    // Specific implementation for exact expense sharing
}
