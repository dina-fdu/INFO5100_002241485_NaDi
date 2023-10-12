class Furniture {
    int price;
    int weight;
    String brand;
    String type;
    String color;
    String material;
    String style;
    boolean is_functional;

    Furniture(String id) {
        System.out.println("Furniture " + id + " created.");
    }

    void buyFurniture() {
        System.out.println("Buying the furniture.");
    }

    void cleanFurniture() {
        System.out.println("Cleaning the furniture.");
    }

    void sellFurniture() {
        System.out.println("Selling the furniture.");
    }
}