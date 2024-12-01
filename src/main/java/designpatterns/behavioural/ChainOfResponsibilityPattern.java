package designpatterns.behavioural;

abstract class SupportHandler {
    protected SupportHandler nextHandler;

    public void setNextHandler(SupportHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void handleRequest(String request);
}

class Level1Support extends SupportHandler {
    @Override
    public void handleRequest(String request) {
        if (request.equals("Basic Issue")) {
            System.out.println("Level 1 Support: Handling basic issue.");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
}

class Level2Support extends SupportHandler {
    @Override
    public void handleRequest(String request) {
        if (request.equals("Intermediate Issue")) {
            System.out.println("Level 2 Support: Handling intermediate issue.");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
}

class Level3Support extends SupportHandler {
    @Override
    public void handleRequest(String request) {
        if (request.equals("Complex Issue")) {
            System.out.println("Level 3 Support: Handling complex issue.");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
}


/**
 * When to Use:
 *
 * 1. To handle various requests in different ways without knowing the sequence
 *    and type of requests beforehand.
 *
 * 2. For event bubbling in the DOM, where the event propagates through nested elements.
 *
 * Example: Technical Support Chain
 *
 * Imagine a technical support system where customer queries pass through
 * different levels of support (e.g., Level 1, Level 2, and Level 3)
 * until they are resolved.
 */
public class ChainOfResponsibilityPattern {
    public static void main(String[] args) {
        SupportHandler level1 = new Level1Support();
        SupportHandler level2 = new Level2Support();
        SupportHandler level3 = new Level3Support();

        level1.setNextHandler(level2);
        level2.setNextHandler(level3);

        level1.handleRequest("Basic Issue");
        level1.handleRequest("Intermediate Issue");
        level1.handleRequest("Complex Issue");
        level1.handleRequest("Unknown Issue");
    }
}
