package designpatterns.creational;

// Document interface
interface Document {
    void open();
}

// Concrete implementations
class WordDocument implements Document {
    public void open() {
        System.out.println("Opening Word document");
    }
}

class PDFDocument implements Document {
    public void open() {
        System.out.println("Opening PDF document");
    }
}

class ExcelDocument implements Document {
    public void open() {
        System.out.println("Opening Excel document");
    }
}

// Factory class
class DocumentFactory {
    public static Document createDocument(String type) {
        return switch (type) {
            case "Word" -> new WordDocument();
            case "PDF" -> new PDFDocument();
            case "Excel" -> new ExcelDocument();
            default -> throw new IllegalArgumentException("Unknown document type");
        };
    }
}

/**
 * When to use:
 *
 * 1. When the type of objects required cannot be anticipated beforehand.
 * 2. When multiple objects that share similar characteristics need to be created.
 * 3. When you want to generalize the object instantiation process
 * since the object setup is complex in nature.
 */
public class FactoryPatternMain {
    public static void main(String[] args) {
        Document doc = DocumentFactory.createDocument("Word");
        doc.open();
         doc = DocumentFactory.createDocument("PDF");
        doc.open();
         doc = DocumentFactory.createDocument("Excel");
        doc.open();
         doc = DocumentFactory.createDocument("XML");
        doc.open();
    }
}

