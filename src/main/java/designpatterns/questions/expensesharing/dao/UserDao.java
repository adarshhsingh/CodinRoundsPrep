package designpatterns.questions.expensesharing.dao;

import database.expensesharing.ExpenseDatabaseUtil;
import designpatterns.questions.expensesharing.User;

import java.sql.*;

public class UserDao {
    public void addUser(User user) throws SQLException {
        String query = "INSERT INTO Users (name, email) VALUES (?, ?)";
        try {
            Connection conn = ExpenseDatabaseUtil.getExpenseTableConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, user.getName());
                stmt.setString(2, user.getEmail());
                stmt.executeUpdate();
        } catch (SQLException e) {
            if(e instanceof SQLIntegrityConstraintViolationException) {
                System.out.println("User "+ user.getEmail()+" already exists.");
            } else {
                throw e;
            }
        }
    }

    public User getUser(int userId) throws SQLException {
        String query = "SELECT * FROM Users WHERE user_id = ?";
        try (Connection conn = ExpenseDatabaseUtil.getExpenseTableConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("user_id"), rs.getString("name"), rs.getString("email"));
            }
        }
        return null;
    }

}
