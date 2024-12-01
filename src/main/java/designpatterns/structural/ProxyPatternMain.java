package designpatterns.structural;

// Subject Interface
interface Image {
    void display();
}

// Real Subject
class RealImage implements Image {
    private String filename;

    public RealImage(String filename) {
        this.filename = filename;
        loadImageFromDisk();
    }

    private void loadImageFromDisk() {
        System.out.println("Loading image: " + filename);
    }

    @Override
    public void display() {
        System.out.println("Displaying image: " + filename);
    }
}

// Proxy
class ProxyImage implements Image {
    private RealImage realImage;
    private String filename;

    public ProxyImage(String filename) {
        this.filename = filename;
    }

    @Override
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(filename);
        }
        realImage.display();
    }
}



/**
 * When to use:
 *
 * 1. To reduce the workload on the target object.
 *
 * Example: Virtual Proxy for Image Loading
 *
 * Consider an application that loads images from the disk.
 * Instead of loading all images at once (which can be memory-intensive), we
 * can use a Proxy to load images on demand.
 */
public class ProxyPatternMain {
    public static void main(String[] args) {
        Image image1 = new ProxyImage("photo1.jpg");
        Image image2 = new ProxyImage("photo2.jpg");

        // Image will be loaded from disk
        image1.display();

        // Image will not be loaded from disk (already loaded)
        image1.display();

        // Image will be loaded from disk
        image2.display();
    }
}
