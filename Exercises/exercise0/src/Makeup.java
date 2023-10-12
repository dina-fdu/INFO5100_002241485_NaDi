class Makeup {
    int price;
    String shade;
    String type;
    String brand;
    String ingredients;
    String texture;
    boolean for_dry_skin;
    boolean is_vegan;

    Makeup(String id) {
        System.out.println("Makeup " + id + " created.");
    }

    void buyMakeup() {
        System.out.println("Buying the makeup.");
    }

    void useMakeup() {
        System.out.println("Using the makeup.");
    }

    void testMakeup() {
        System.out.println("Testing the makeup.");
    }
}