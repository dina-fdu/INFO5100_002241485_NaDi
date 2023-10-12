class Accessory {
    int price;
    String brand;
    String type;
    String color;
    String material;
    boolean is_cultural;
    boolean is_durable;
    boolean for_woman;

    Accessory(String id) {
        System.out.println("Accessory " + id + " created.");
    }

    void buyAccessory() {
        System.out.println("Buying the accessory.");
    }

    void wearAccessory() {
        System.out.println("Wearing the accessory.");
    }

    void removeAccessory() {
        System.out.println("Removing the accessory.");
    }
}