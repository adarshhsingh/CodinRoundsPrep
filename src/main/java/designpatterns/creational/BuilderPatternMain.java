package designpatterns.creational;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
class User {
    private String firstName;
    private String lastName;
    private int age;
    private String address;

    @Override
    public String toString() {
        return "User [firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + ", address=" + address + "]";
    }
}

/**
 * When to Use:
 *
 * 1. When building apps that require you to create complex objects.
 *
 * 2. A good example would be a DOM, where we might need
 *    to create plenty of nodes and attributes.
 */
public class BuilderPatternMain {
    public static void main(String[] args) {
        User user = new User.UserBuilder()
                .firstName("John")
                .lastName("Doe")
                .age(30)
                .address("123 Main St")
                .build();

        System.out.println(user);
        System.out.println(user.getAddress());

    }
}
