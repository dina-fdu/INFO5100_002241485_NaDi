class House{
    int size=10;
    int number_of_rooms;
    int number_of_floors;
    int year_built;
    boolean parking;
    boolean pool;
    boolean yard;
    boolean made_by_wood;

    class Room{
        int size;
        int number_of_windows;
        String type;
        String flooring_material;
        String wall_color;
        String furniture;
        boolean ventilation;
        boolean decorative_items;
        Room(String id) {
            System.out.println("Room " + id + " created.");
        }

        void turnOnLights() {
            System.out.println("Turning on the lights in the room.");
        }

        void openWindows() { System.out.println("Opening the windows of the room."); }

        void cleanRoom() { System.out.println("Cleaning the room."); }

    }
    class Kitchen{
        int size;
        String layout;
        String flooring_material;
        String appliances;
        String storage_types;
        String lighting_types;
        boolean ventilation;
        boolean seating_area;
        Kitchen(String id) {
            System.out.println("Kitchen " + id + " created.");
        }

        void turnOnFaucets() {
            System.out.println("Turning on the Faucets in the Kitchen.");
        }

        void turnOnOven() { System.out.println("Turning on the oven in the kitchen."); }

        void cleanKitchen() { System.out.println("Cleaning the Kitchen."); }
    }
    House(String id) {
        System.out.println("House " + id + " created.");
    }

    void lockDoors() {
        System.out.println("Locking the doors of the house.");
    }

    void turnOnLights() {
        System.out.println("Turning on the lights in the house.");
    }

    void openWindows() {
        System.out.println("Opening the windows of the house.");
    }
}