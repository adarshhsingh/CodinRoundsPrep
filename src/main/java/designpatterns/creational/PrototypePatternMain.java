package designpatterns.creational;

class Student implements Cloneable {
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Student [name=" + name + ", age=" + age + "]";
    }
}

/**
 * When to Use:
 *
 * 1. To eliminate the overhead of initializing an object.
 *
 * 2. When you want the system to be independent of how the products in it are created.
 *
 * 3. When creating objects from a database whose values are copied to the cloned object.
 */
public class PrototypePatternMain {
    public static void main(String[] args) {
        try {
            Student student1 = new Student("Alice", 20);
            Student student2 = (Student) student1.clone();

            System.out.println(student1);
            System.out.println(student2);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}

