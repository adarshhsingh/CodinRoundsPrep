package designpatterns.structural;

// Implementor interface
interface Device {
    void turnOn();
    void turnOff();
}

// Concrete Implementor
class TV implements Device {
    @Override
    public void turnOn() {
        System.out.println("TV turned on.");
    }

    @Override
    public void turnOff() {
        System.out.println("TV turned off.");
    }
}

// Concrete Implementor
class Radio implements Device {
    @Override
    public void turnOn() {
        System.out.println("Radio turned on.");
    }

    @Override
    public void turnOff() {
        System.out.println("Radio turned off.");
    }
}

// Abstraction
abstract class RemoteControl {
    protected Device device;

    public RemoteControl(Device device) {
        this.device = device;
    }

    public abstract void togglePower();
}

// Refined Abstraction
class AdvancedRemoteControl extends RemoteControl {
    private boolean isOn = false;

    public AdvancedRemoteControl(Device device) {
        super(device);
    }

    @Override
    public void togglePower() {
        if (isOn) {
            device.turnOff();
            isOn = false;
        } else {
            device.turnOn();
            isOn = true;
        }
    }
}

/**
 * When to Use:
 *
 * 1. To extend a class in several independent dimensions.
 *
 * 2. To change the implementation at runtime.
 *
 * 3. To share the implementation between objects.
 *
 * Example: Imagine a remote control that works with different devices
 *          (e.g., TV, Radio).
 */
public class BridgePatternMain {
    public static void main(String[] args) {
        Device tv = new TV();
        Device radio = new Radio();

        RemoteControl tvRemote = new AdvancedRemoteControl(tv);
        RemoteControl radioRemote = new AdvancedRemoteControl(radio);

        tvRemote.togglePower();  // Output: TV turned on.
        tvRemote.togglePower();  // Output: TV turned off.

        radioRemote.togglePower();  // Output: Radio turned on.
        radioRemote.togglePower();  // Output: Radio turned off.
    }
}
