class Dessert {
    int price;
    int calories;
    int expiration_date;
    String flavor;
    String brand;
    String ingredients;
    String texture;
    boolean keep_in_cold;


    Dessert(String id) {
        System.out.println("Dessert " + id + " created.");
    }

    void buyDessert() {
        System.out.println("Buying the dessert.");
    }

    void shareDessert() {
        System.out.println("Sharing the dessert.");
    }

    void storeDessert() {
        System.out.println("Storing the dessert.");
    }
}