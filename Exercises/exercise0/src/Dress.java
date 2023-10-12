class Dress {
    int price;
    int size;
    int length;
    String color;
    String type;
    String brand;
    String material;
    String pattern;

    Dress(String id) {
        System.out.println("Dress " + id + " created.");
    }

    void buyDress() {
        System.out.println("Buying the dress.");
    }

    void washDress() {
        System.out.println("Washing the dress.");
    }

    void tryDress() {
        System.out.println("Trying on the dress.");
    }
}