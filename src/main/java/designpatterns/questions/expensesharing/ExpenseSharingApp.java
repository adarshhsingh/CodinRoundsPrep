package designpatterns.questions.expensesharing;

import designpatterns.questions.expensesharing.dao.UserDao;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class ExpenseSharingApp {

    public static void main(String[] args) {
        // Create users
        UserDao userDAO = new UserDao();

        // Adding users
        try {
            User user1 = new User("Alice", "alice@example.com");
            User user2 = new User("Bob", "bob@example.com");
            User user3 = new User("Charlie", "charlie@example.com");
            userDAO.addUser(user1);
            userDAO.addUser(user2);
            userDAO.addUser(user3);
            // Add expenses
            List<User> sharedBy = Arrays.asList(user1, user2, user3);
            Expense expense1 = new EqualExpense("e1", 300, user1, sharedBy);

            // Create balance sheet and add expenses
            BalanceSheet balanceSheet = new BalanceSheet();
            balanceSheet.addExpense(expense1);
            balanceSheet.showBalances();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
