class Beverage {
    int price;
    int volume;
    int calories;
    int expiration_date;
    String brand;
    String ingredients;
    boolean keep_in_cold;
    boolean is_functional;

    Beverage(String id) {
        System.out.println("Beverage " + id + " created.");
    }

    void buyBeverage() {
        System.out.println("Buying the beverage.");
    }

    void drinkBeverage() {
        System.out.println("Drinking the beverage.");
    }

    void storeBeverage() {
        System.out.println("Storing the beverage.");
    }
}