package designpatterns.behavioural;

import java.util.ArrayList;
import java.util.List;

interface InsuranceVisitor {
    void visit(CarInsurance carInsurance);
    void visit(HealthInsurance healthInsurance);
    void visit(HomeInsurance homeInsurance);
}

class PremiumCalculator implements InsuranceVisitor {
    @Override
    public void visit(CarInsurance carInsurance) {
        // Calculate premium for car insurance
        double premium = carInsurance.getBasePremium() * 1.1; // Example calculation
        System.out.println("Calculated Car Insurance Premium: " + premium);
    }

    @Override
    public void visit(HealthInsurance healthInsurance) {
        // Calculate premium for health insurance
        double premium = healthInsurance.getBasePremium() * 1.2; // Example calculation
        System.out.println("Calculated Health Insurance Premium: " + premium);
    }

    @Override
    public void visit(HomeInsurance homeInsurance) {
        // Calculate premium for home insurance
        double premium = homeInsurance.getBasePremium() * 1.3; // Example calculation
        System.out.println("Calculated Home Insurance Premium: " + premium);
    }
}

interface Insurable {
    void accept(InsuranceVisitor visitor);
}

class CarInsurance implements Insurable {
    private double basePremium;

    public CarInsurance(double basePremium) {
        this.basePremium = basePremium;
    }

    public double getBasePremium() {
        return basePremium;
    }

    @Override
    public void accept(InsuranceVisitor visitor) {
        visitor.visit(this);
    }
}

class HealthInsurance implements Insurable {
    private double basePremium;

    public HealthInsurance(double basePremium) {
        this.basePremium = basePremium;
    }

    public double getBasePremium() {
        return basePremium;
    }

    @Override
    public void accept(InsuranceVisitor visitor) {
        visitor.visit(this);
    }
}

class HomeInsurance implements Insurable {
    private double basePremium;

    public HomeInsurance(double basePremium) {
        this.basePremium = basePremium;
    }

    public double getBasePremium() {
        return basePremium;
    }

    @Override
    public void accept(InsuranceVisitor visitor) {
        visitor.visit(this);
    }
}


/**
 * Visitor Pattern
 *
 * When to Use:
 *
 * 1. To perform similar operations on different objects of a data structure.
 * 2. To perform specific operations on different objects in the data structure.
 * 3. To add extensibility to libraries or frameworks.
 *
 * Real-World Example: Insurance Premium Calculation
 * Consider a scenario in an insurance company where different
 * types of insurance policies (e.g., car insurance, health insurance, and home insurance)
 * need to have their premiums calculated.
 * The calculation method differs for each type of insurance.
 */
public class VisitorPatternMain {
    public static void main(String[] args) {
        List<Insurable> policies = new ArrayList<>();
        policies.add(new CarInsurance(300));
        policies.add(new HealthInsurance(500));
        policies.add(new HomeInsurance(1000));

        InsuranceVisitor premiumCalculator = new PremiumCalculator();

        for (Insurable policy : policies) {
            policy.accept(premiumCalculator);
        }
    }
}
