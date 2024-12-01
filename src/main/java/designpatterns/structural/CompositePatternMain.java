package designpatterns.structural;

import java.util.ArrayList;
import java.util.List;

// Component
interface FileSystemComponent {
    void showDetails();
}

// Leaf
class File implements FileSystemComponent {
    private String name;

    public File(String name) {
        this.name = name;
    }

    @Override
    public void showDetails() {
        System.out.println("File: " + name);
    }
}

// Composite
class Directory implements FileSystemComponent {
    private String name;
    private List<FileSystemComponent> components = new ArrayList<>();

    public Directory(String name) {
        this.name = name;
    }

    public void addComponent(FileSystemComponent component) {
        components.add(component);
    }

    @Override
    public void showDetails() {
        System.out.println("Directory: " + name);
        for (FileSystemComponent component : components) {
            component.showDetails();
        }
    }
}

/**
 * When to Use:
 *
 * 1. To allow the reuse of objects without worrying about their compatibility.
 *
 * 2. To develop a scalable application that uses plenty of objects.
 *
 * 3. To create a tree-like hierarchy of objects.
 *
 * Example: Imagine a file system where you can have files and directories,
 *          and directories can contain files and other directories.
 *
 */
public class CompositePatternMain {
    public static void main(String[] args) {
        FileSystemComponent file1 = new File("file1.txt");
        FileSystemComponent file2 = new File("file2.txt");

        Directory dir1 = new Directory("dir1");
        dir1.addComponent(file1);
        dir1.addComponent(file2);

        FileSystemComponent file3 = new File("file3.txt");

        Directory dir2 = new Directory("dir2");
        dir2.addComponent(dir1);
        dir2.addComponent(file3);

        dir2.showDetails();
    }
}
