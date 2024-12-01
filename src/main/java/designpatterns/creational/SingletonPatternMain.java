package designpatterns.creational;

class DatabaseConnection {
    private static DatabaseConnection instance;

    private DatabaseConnection() {
        // private constructor to prevent instantiation
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public void connect() {
        System.out.println("Connecting to the database");
    }
}


/**
 * When to Use:
 *
 * 1. When you want a single object to coordinate actions across a system.
 *
 * 2. Services can be singleton since they store the state,
 *    configuration, and provide access to resources.
 *
 * 3. Databases such as MongoDB utilize the Singleton pattern for
 *    database connections.
 *
 * 4. Configurations are used if there is an object with a specific
 *    configuration, and we don’t need a new instance every time that
 *    configuration object is needed.
 */
public class SingletonPatternMain {
    public static void main(String[] args) {
        DatabaseConnection db1 = DatabaseConnection.getInstance();
        DatabaseConnection db2 = DatabaseConnection.getInstance();

        db1.connect();

        // Check if both references point to the same instance
        System.out.println(db1 == db2); // true
    }
}
