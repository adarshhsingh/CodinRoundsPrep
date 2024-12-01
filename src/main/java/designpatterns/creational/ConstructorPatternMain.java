package designpatterns.creational;


class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void display() {
        System.out.println("Name: " + name + ", Age: " + age);
    }
}


/**
 * When to Use:
 *
 * 1. When you want to create multiple instances of the same template,
 *    since the instances can share methods but can still be different.
 *
 * 2. It can be useful in Libraries and Plugins design.
 */
public class ConstructorPatternMain {
    public static void main(String[] args) {
        Person person1 = new Person("Alice", 30);
        Person person2 = new Person("Bob", 25);

        person1.display();
        person2.display();
    }
}
