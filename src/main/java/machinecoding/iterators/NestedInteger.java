package machinecoding.iterators;

import java.util.List;

public class NestedInteger {
    private Integer value;
    private List<NestedInteger> list;

    // Constructor for a single integer
    public NestedInteger(Integer value) {
        this.value = value;
        this.list = null;
    }

    // Constructor for a list
    public NestedInteger(List<NestedInteger> list) {
        this.value = null;
        this.list = list;
    }

    // Returns true if this NestedInteger holds a single integer, false if it holds a nested list
    public boolean isInteger() {
        return value != null;
    }

    // Returns the single integer that this NestedInteger holds, or null if it holds a nested list
    public Integer getInteger() {
        return value;
    }

    // Returns the nested list that this NestedInteger holds, or null if it holds a single integer
    public List<NestedInteger> getList() {
        return list;
    }
}