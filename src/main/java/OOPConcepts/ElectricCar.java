package OOPConcepts;

class ElectricCar extends Vehicle {
     private String batteryPower;
        ElectricCar(String name, String model, String batteryPower) {
            super(name, model);
            this.batteryPower = batteryPower;
        }
        public void getElectricCar() {
            getName();
            System.out.print(", battery power is " + batteryPower);
        }
}
