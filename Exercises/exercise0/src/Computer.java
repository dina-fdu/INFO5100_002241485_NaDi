class Computer {
    int price;
    int size_of_display;
    int memory;
    String operating_system;
    String type;
    String brand;
    boolean is_charged;
    boolean is_open;


    Computer(String id) {
        System.out.println("Computer " + id + " created.");
    }

    void openComputer() {
        System.out.println("Opening the computer.");
    }

    void closeComputer() {
        System.out.println("Closing the computer.");
    }

    void chargeComputer() {
        System.out.println("Charging the computer.");
    }
}
