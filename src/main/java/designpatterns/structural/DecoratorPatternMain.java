package designpatterns.structural;

// Component
interface Notifier {
    void send(String message);
}

// Concrete Component
class EmailNotifier implements Notifier {
    @Override
    public void send(String message) {
        System.out.println("Sending Email: " + message);
    }
}

// Concrete Component
class SMSNotifier implements Notifier {
    @Override
    public void send(String message) {
        System.out.println("Sending SMS: " + message);
    }
}

// Decorator
abstract class NotifierDecorator implements Notifier {
    protected Notifier wrappedNotifier;

    public NotifierDecorator(Notifier notifier) {
        this.wrappedNotifier = notifier;
    }

    public void send(String message) {
        wrappedNotifier.send(message);
    }
}

// Concrete Decorator
class LoggingNotifierDecorator extends NotifierDecorator {
    public LoggingNotifierDecorator(Notifier notifier) {
        super(notifier);
    }

    @Override
    public void send(String message) {
        System.out.println("Logging: " + message);
        super.send(message);
    }
}

/**
 * When to Use:
 *
 * 1. To modify or extend the functionality of an object without
 *    changing its base code.
 *
 * 2. To implement additional functionalities of similar objects
 *    instead of reusing the same code.
 *
 * Example: Imagine a simple notification system that can send messages via
 *          email or SMS. We want to add additional functionalities like
 *          logging the message before sending it.
 */
public class DecoratorPatternMain {
    public static void main(String[] args) {
        Notifier emailNotifier = new EmailNotifier();
        Notifier smsNotifier = new SMSNotifier();
        emailNotifier.send("Hello via Email!");
        smsNotifier.send("Hello via SMS!");

        Notifier loggedEmailNotifier = new LoggingNotifierDecorator(emailNotifier);
        Notifier loggedSmsNotifier = new LoggingNotifierDecorator(smsNotifier);

        loggedEmailNotifier.send("Hello via Email!");
        loggedSmsNotifier.send("Hello via SMS!");
    }
}
