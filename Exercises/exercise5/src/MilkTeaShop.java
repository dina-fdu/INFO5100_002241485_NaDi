//A simple simulation of a milk tea shop ordering system using various design patterns in Java
import java.util.ArrayList;
import java.util.List;

// MilkTea class with Builder pattern for creating MilkTea objects
class MilkTea{
    private String flavor;
    private String sweetness;
    private String iceAmount;

    // Builder inner class for MilkTea
    static class Builder{
        private String flavor = "Original";
        private String sweetness = "Medium";
        private String iceAmount = "Regular";

        // Methods for setting properties and returning the Builder object
        public Builder flavor(String flavor){
            this.flavor = flavor;
            return this;
        }
        public Builder sweetness(String sweetness){
            this.sweetness = sweetness;
            return this;
        }
        public Builder iceAmount(String iceAmount){
            this.iceAmount = iceAmount;
            return this;
        }

        // Method to build and return a MilkTea object
        public MilkTea build(){
            return new MilkTea(this);
        }
    }

    // Private constructor for MilkTea using Builder
    private MilkTea(Builder builder){
        this.flavor = builder.flavor;
        this.sweetness = builder.sweetness;
        this.iceAmount = builder.iceAmount;
    }

    // Overriding toString method for printing MilkTea properties
    public String toString(){
        return "MilkTea{" + "flavor='" + flavor + '\'' + ", sweetness = '" + sweetness + '\'' + ", iceAmount = '" + iceAmount + '\'' + '}';
    }
}

// MilkTeaFactory interface for factory pattern
interface MilkTeaFactory{
    MilkTea createMilkTea();
}

// Factory class for creating Oats MilkTea
class OatsMilkTeaFactory implements MilkTeaFactory{
    public MilkTea createMilkTea(){
        return new MilkTea.Builder().flavor("Oats").build();
    }
}

// Factory class for creating Pearl MilkTea
class PearlMilkTeaFactory implements MilkTeaFactory{
    public MilkTea createMilkTea(){
        return new MilkTea.Builder().flavor("Pearl").build();
    }
}

// Singleton pattern for generating unique order IDs
class OrderIDGenerator{
    private static OrderIDGenerator instance;
    private int id = 0;
    private OrderIDGenerator(){}
    public static synchronized  OrderIDGenerator getInstance(){
        if (instance == null){
            instance = new OrderIDGenerator();
        }
        return instance;
    }
    public int getNextID(){
        return ++id;
    }
}

// Observer interface for order updates
interface OrderObserver{
    void update(String status);
}

// Customer class implementing OrderObserver
class Customer implements  OrderObserver{
    private String name;
    public Customer(String name){
        this.name = name;
    }
    public void update(String status){
        System.out.println("Customer" + name + ", your order is " + status);
    }
}

// Order class for managing and notifying observers
class Order{
    private List<OrderObserver> observers = new ArrayList<>();
    private String status;
    public void attach(OrderObserver observer){
        observers.add(observer);
    }
    public void setStatus(String status){
        this.status = status;
        notifyAllObservers();
    }
    private void notifyAllObservers(){
        for (OrderObserver observer : observers){
            observer.update(status);
        }
    }
}

// Main class for the MilkTeaShop
public class MilkTeaShop {
    public static void main(String[] args){
        // Creating a milk tea using Builder pattern
        MilkTea milkTea = new MilkTea.Builder().flavor("Oats").sweetness("Less").iceAmount("Less").build();
        System.out.println(milkTea);

        // Using Factory pattern to create milk tea
        MilkTeaFactory milkTeaFactory = new OatsMilkTeaFactory();
        MilkTea oatsMilkTea = milkTeaFactory.createMilkTea();
        System.out.println((oatsMilkTea));

        // Generating unique order ID using Singleton pattern
        int orderId = OrderIDGenerator.getInstance().getNextID();
        System.out.println("Order ID: " + orderId);

        // Creating a customer and order, then updating order status using Observer pattern
        Customer customer = new Customer(" Dina");
        Order order = new Order();
        order.attach(customer);
        order.setStatus("Ready");
    }
}