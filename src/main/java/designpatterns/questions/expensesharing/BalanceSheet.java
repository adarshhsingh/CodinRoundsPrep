package designpatterns.questions.expensesharing;

import java.util.HashMap;
import java.util.Map;

public class BalanceSheet {
    private Map<User, Map<User, Double>> balances;

    public BalanceSheet() {
        this.balances = new HashMap<>();
    }

    public void addExpense(Expense expense) {
        expense.calculateShare(balances);
    }

    public void showBalances() {
        for (User user : balances.keySet()) {
            System.out.println("***************************");
            System.out.println("Balances for " + user.getName() + ":");
            for (User other : balances.get(user).keySet()) {
                double amount = balances.get(user).get(other);
                if (amount != 0) {
                    if (amount < 0) {
                        System.out.println(user.getName() + " owes " + other.getName() + ": " + -amount);
                    } else {
                        System.out.println(other.getName() + " owes " + user.getName() + ": " + amount);
                    }
                }
            }
        }
    }
}

