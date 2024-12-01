package designpatterns.questions.expensesharing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EqualExpense extends Expense {
    public EqualExpense(String expenseId, double amount, User paidBy, List<User> sharedBy) {
        super(expenseId, amount, paidBy, sharedBy);
    }

    @Override
    public void calculateShare(Map< User, Map<User, Double>> balanceSheet) {
        int numberOfUsers = getSharedBy().size();
        double share = getAmount() / numberOfUsers;
        for (User user : getSharedBy()) {
            if(!user.equals(getPaidBy())) {
                balanceSheet.putIfAbsent(user, new HashMap<>());
                balanceSheet.putIfAbsent(getPaidBy(), new HashMap<>());

                // Update the Balance Sheet for the User Who Owes Money
                balanceSheet.get(user).put(getPaidBy(), balanceSheet.get(user).getOrDefault(getPaidBy(),0.00) -  share);

                // Update the Balance Sheet for the User Who Payed Money
                balanceSheet.get(getPaidBy()).put(user, balanceSheet.get(getPaidBy()).getOrDefault(user, 0.0) + share);
            }
        }

    }
    // Specific implementation for equal expense sharing
}
