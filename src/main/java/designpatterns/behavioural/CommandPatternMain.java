package designpatterns.behavioural;

// Receiver for Light
class Light {
    public void on() {
        System.out.println("Light is on");
    }

    public void off() {
        System.out.println("Light is off");
    }
}

// Receiver for Thermostat
class Thermostat {
    private int temperature;

    public void setTemperature(int temperature) {
        this.temperature = temperature;
        System.out.println("Thermostat set to " + temperature + " degrees");
    }

    public void resetTemperature() {
        this.temperature = 20; // default temperature
        System.out.println("Thermostat reset to 20 degrees");
    }
}

// Command Interface
interface Command {
    void execute();
    void undo();
}

// Concrete Command for Light
class LightOnCommand implements Command {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }

    @Override
    public void undo() {
        light.off();
    }
}

// Concrete Command for Thermostat
class ThermostatSetCommand implements Command {
    private Thermostat thermostat;
    private int temperature;

    public ThermostatSetCommand(Thermostat thermostat, int temperature) {
        this.thermostat = thermostat;
        this.temperature = temperature;
    }

    @Override
    public void execute() {
        thermostat.setTemperature(temperature);
    }

    @Override
    public void undo() {
        thermostat.resetTemperature();
    }
}



// Invoker
class RemoteControl {
    private Command[] onCommands;
    private Command[] offCommands;
    private Command undoCommand;

    public RemoteControl(int slots) {
        onCommands = new Command[slots];
        offCommands = new Command[slots];
    }

    public void setCommand(int slot, Command onCommand, Command offCommand) {
        onCommands[slot] = onCommand;
        offCommands[slot] = offCommand;
    }

    public void onButtonPressed(int slot) {
        onCommands[slot].execute();
        undoCommand = onCommands[slot];
    }

    public void offButtonPressed(int slot) {
        offCommands[slot].execute();
        undoCommand = offCommands[slot];
    }

    public void undoButtonPressed() {
        undoCommand.undo();
    }
}


/**
 * Command Pattern
 * When to Use:
 *
 * 1. To queue and execute requests at different times.
 * 2. To perform operations such as "reset" or "undo".
 * 3. To keep a history of requests made.
 *
 * Example: Remote Control for Home Automation
 *
 * Imagine a remote control that can turn on/off lights,
 * set the thermostat, and control other home devices.
 */
public class CommandPatternMain {
    public static void main(String[] args) {
        RemoteControl remote = new RemoteControl(2);
        Light livingRoomLight = new Light();
        Thermostat thermostat = new Thermostat();

        Command lightOn = new LightOnCommand(livingRoomLight);
        Command lightOff = new LightOnCommand(livingRoomLight);
        Command thermostatSet = new ThermostatSetCommand(thermostat, 22);
        Command thermostatReset = new ThermostatSetCommand(thermostat, 20);

        remote.setCommand(0, lightOn, lightOff);
        remote.setCommand(1, thermostatSet, thermostatReset);

        remote.onButtonPressed(0);
        remote.offButtonPressed(0);
        remote.undoButtonPressed();

        remote.onButtonPressed(1);
        remote.undoButtonPressed();
    }
}
