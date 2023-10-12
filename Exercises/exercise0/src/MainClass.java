public class MainClass {
    public static void main(String[] args) {

        House house1 = new House("House1");
        House house2 = new House("House2");
        House house3 = new House("House3");

        House.Room room1 = house1.new Room("R1");
        House.Room room2 = house1.new Room("R2");
        House.Room room3 = house1.new Room("R3");

        House.Kitchen kitchen1 = house1.new Kitchen("K1");
        House.Kitchen kitchen2 = house1.new Kitchen("K2");
        House.Kitchen kitchen3 = house1.new Kitchen("K3");

        Accessory accessory1 = new Accessory("Accessory1");
        Accessory accessory2 = new Accessory("Accessory2");
        Accessory accessory3 = new Accessory("Accessory3");

        Beverage beverage1 = new Beverage("Beverage1");
        Beverage beverage2 = new Beverage("Beverage2");
        Beverage beverage3 = new Beverage("Beverage3");

        Clock clock1 = new Clock("Clock1");
        Clock clock2 = new Clock("Clock2");
        Clock clock3 = new Clock("Clock3");

        Computer computer1 = new Computer("Computer1");
        Computer computer2 = new Computer("Computer2");
        Computer computer3 = new Computer("Computer3");

        Dessert dessert1 = new Dessert("Dessert1");
        Dessert dessert2 = new Dessert("Dessert2");
        Dessert dessert3 = new Dessert("Dessert3");

        Dress dress1 = new Dress("Dress1");
        Dress dress2 = new Dress("Dress2");
        Dress dress3 = new Dress("Dress3");

        Furniture furniture1 = new Furniture("Furniture1");
        Furniture furniture2 = new Furniture("Furniture2");
        Furniture furniture3 = new Furniture("Furniture3");

        Makeup makeup1 = new Makeup("Makeup1");
        Makeup makeup2 = new Makeup("Makeup2");
        Makeup makeup3 = new Makeup("Makeup3");

        Window window1 = new Window("Window1");
        Window window2 = new Window("Window2");
        Window window3 = new Window("Window3");

    }
}
