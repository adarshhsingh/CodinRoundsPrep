package designpatterns.structural;

// Old Payment Processor Interface
interface OldPaymentProcessor {
    void pay(String paymentDetails);
}

// Old Payment Processor Implementation
class OldPaymentProcessorImpl implements OldPaymentProcessor {
    @Override
    public void pay(String paymentDetails) {
        System.out.println("Processing payment using Old Payment Processor: " + paymentDetails);
    }
}

// New Payment Gateway Interface
interface NewPaymentGateway {
    void makePayment(String accountNumber, double amount);
}

// New Payment Gateway Implementation
class NewPaymentGatewayImpl implements NewPaymentGateway {
    @Override
    public void makePayment(String accountNumber, double amount) {
        System.out.println("Processing payment using New Payment Gateway for account: " + accountNumber + " with amount: " + amount);
    }
}

// Adapter Class
class PaymentAdapter implements OldPaymentProcessor {
    private NewPaymentGateway newPaymentGateway;

    public PaymentAdapter(NewPaymentGateway newPaymentGateway) {
        this.newPaymentGateway = newPaymentGateway;
    }

    @Override
    public void pay(String paymentDetails) {
        // Extracting account number and amount from payment details for demonstration purposes
        String[] details = paymentDetails.split(",");
        String accountNumber = details[0];
        double amount = Double.parseDouble(details[1]);

        // Using the new payment gateway to process the payment
        newPaymentGateway.makePayment(accountNumber, amount);
    }
}


/**
 * When to Use:
 *
 * 1. To enable old APIs to work with new refactored ones.
 *
 * 2. To allow an object to cooperate with a class that has an incompatible interface.
 *
 * 3. To reuse the existing functionality of classes.
 *
 * Example: Imagine you have an existing payment processing system that works
 *          with a specific interface.
 *          You now have a new payment gateway that uses a different interface.
 *          Instead of changing your existing system, you can use the Adapter
 *          Pattern to make the new payment gateway compatible with the existing system.
 */
public class AdapterPatternMain {
    public static void main(String[] args) {
        // Using the old payment processor
        OldPaymentProcessor oldProcessor = new OldPaymentProcessorImpl();
        oldProcessor.pay("12345,100.0");

        // Using the new payment gateway through the adapter
        NewPaymentGateway newGateway = new NewPaymentGatewayImpl();
        OldPaymentProcessor adapter = new PaymentAdapter(newGateway);
        adapter.pay("67890,200.0");
    }
}
