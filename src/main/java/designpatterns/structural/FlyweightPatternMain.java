package designpatterns.structural;

import java.util.HashMap;
import java.util.Map;

interface Character {
    void display(int fontSize);
}

class ConcreteCharacter implements Character {
    private char symbol; // intrinsic state

    public ConcreteCharacter(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public void display(int fontSize) {
        // extrinsic state (context-dependent)
        System.out.println("Character: " + symbol + ", Font size: " + fontSize);
    }
}

class CharacterFactory {
    private Map<java.lang.Character, Character> characters = new HashMap<>();

    public Character getCharacter(char symbol) {
        Character character = characters.get(symbol);
        if (character == null) {
            character = new ConcreteCharacter(symbol);
            characters.put(symbol, character);
        }
        return character;
    }
}


/**
 * When to Use:
 *
 * 1. To share a list of immutable strings across the application.
 *
 * 2. To prevent load time as it allows caching.
 *
 * Example: Imagine a text editor where each character is represented as an object.
 *          If each character object had its own properties (e.g., font, style),
 *          the memory consumption would be very high.
 *          Using the Flyweight pattern, we can share common data among
 *          multiple characters to reduce memory usage.
 */
public class FlyweightPatternMain {
    public static void main(String[] args) {

// Example 1:
        CharacterFactory factory = new CharacterFactory();

        String document = "Hello World";
        int fontSize = 12;

        for (char c : document.toCharArray()) {
            Character character = factory.getCharacter(c);
            character.display(fontSize);
        }

// Example 2:
/**
* In a GIS-Geographic Information System application, there may be thousands of trees, lakes,
* and buildings to be rendered on a map.
*
* Each of these elements might share common properties
* (e.g., the appearance of trees or buildings).
*
* Using the Flyweight Pattern, we can share these common properties to save memory.
*/
        MapElementFactory elementFactory = new MapElementFactory();

        MapElement tree1 = elementFactory.getTree("Oak", "Green", "Rough");
        MapElement tree2 = elementFactory.getTree("Pine", "Green", "Smooth");
        MapElement tree3 = elementFactory.getTree("Oak", "Green", "Rough"); // Reuses the existing Oak tree

        MapElement building1 = elementFactory.getBuilding("Skyscraper", "Blue", 100);
        MapElement building2 = elementFactory.getBuilding("Apartment", "Red", 50);
        MapElement building3 = elementFactory.getBuilding("Skyscraper", "Blue", 100); // Reuses the existing Skyscraper building

        tree1.render(10, 20);
        tree2.render(30, 40);
        tree3.render(50, 60);

        building1.render(100, 200);
        building2.render(300, 400);
        building3.render(500, 600);
    }
}

interface MapElement {
    void render(int x, int y);
}

class Tree implements MapElement {
    private String type;
    private String color;
    private String texture;

    public Tree(String type, String color, String texture) {
        this.type = type;
        this.color = color;
        this.texture = texture;
    }

    @Override
    public void render(int x, int y) {
        System.out.println("Rendering a " + type + " tree at (" + x + ", " + y + ") with color " + color + " and texture " + texture);
    }
}

class Building implements MapElement {
    private String type;
    private String color;
    private int height;

    public Building(String type, String color, int height) {
        this.type = type;
        this.color = color;
        this.height = height;
    }

    @Override
    public void render(int x, int y) {
        System.out.println("Rendering a " + type + " building at (" + x + ", " + y + ") with color " + color + " and height " + height);
    }
}

class MapElementFactory {
        private Map<String, MapElement> elements = new HashMap<>();

        public MapElement getTree(String type, String color, String texture) {
            String key = type + color + texture;
            MapElement element = elements.get(key);
            if (element == null) {
                element = new Tree(type, color, texture);
                elements.put(key, element);
            }
            return element;
        }

        public MapElement getBuilding(String type, String color, int height) {
            String key = type + color + height;
            MapElement element = elements.get(key);
            if (element == null) {
                element = new Building(type, color, height);
                elements.put(key, element);
            }
            return element;
        }
}



