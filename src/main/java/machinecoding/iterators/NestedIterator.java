package machinecoding.iterators;

import java.util.*;

public class NestedIterator implements Iterator<Integer> {
    private Stack<ListIterator<NestedInteger>> stack;

    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new Stack<>();
        stack.push(nestedList.listIterator());
    }

    @Override
    public Integer next() {
        // Ensure that the top of the stack is an integer before calling next()
        hasNext();
        return stack.peek().next().getInteger();
    }

    @Override
    public boolean hasNext() {
        while (!stack.isEmpty()) {
            if (!stack.peek().hasNext()) {
                stack.pop();
            } else {
                NestedInteger nested = stack.peek().next();
                if (nested.isInteger()) {
                    stack.peek().previous(); // Move the iterator back to point to the integer
                    return true;
                }
                stack.push(nested.getList().listIterator());
            }
        }
        return false;
    }

    public static void main(String[] args) {
        List<NestedInteger> nestedList = Arrays.asList(
                new NestedInteger(1),
                new NestedInteger(Arrays.asList(
                        new NestedInteger(2),
                        new NestedInteger(Arrays.asList(
                                new NestedInteger(3),
                                new NestedInteger(4)
                        )),
                        new NestedInteger(5)
                )),
                new NestedInteger(6)
        );

        NestedIterator iterator = new NestedIterator(nestedList);
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}