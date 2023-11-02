
import java.io.*;
// Base class Shape
abstract class Shape implements Serializable{
    transient static String color = "blue";
    @Serial
    private static final long serialVersionUID = 1L;

    abstract double calculateArea();
    abstract double calculatePerimeter();

    void showColor(){
        System.out.println("The color of this shape is " + color);
    }
}

// Derived class Triangle
class Triangle extends Shape{
    double base;
    double height;
    double sideLeft;
    double sideRight;
    @Serial
    private static final long serialVersionUID = 1L;

    Triangle(double base, double height, double sideLeft, double sideRight){
        this.base = base;
        this.height = height;
        this.sideLeft = sideLeft;
        this.sideRight = sideRight;
    }

    double calculateArea(){
        return 0.5 * height * base;
    }
    double calculatePerimeter(){
        return base + sideRight + sideLeft;
    }
}

// Derived class Rectangle
class Rectangle extends Shape{
    double length;
    double width;
    @Serial
    private static final long serialVersionUID = 1L;

    Rectangle(double length, double width){
        this.length = length;
        this.width = width;
    }

    double calculateArea(){
        return length * width;
    }
    double calculatePerimeter(){
        return 2 * (length + width);
    }
}

// Derived class Circle
class Circle extends Shape{
    double radius;
    @Serial
    private static final long serialVersionUID = 1L;

    Circle(double radius){
        this.radius = radius;
    }

    // Overriding abstract methods from Shape
    double calculateArea() {
        return 3.14 * radius * radius;
    }
    double calculatePerimeter(){
        return 2 * 3.14 * radius;
    }
}

// Derived class Square
class Square extends Rectangle{
    @Serial
    private static final long serialVersionUID = 1L;

    Square(double side){
        super(side, side);
    }
}

public class SerializationTest{
    public static void serialize(Object obj, String fileName) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object deserialize(String fileName) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            return in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args){
        //Create object
        Shape triangle = new Triangle(6, 4, 5, 5);
        Shape rectangle = new Rectangle(8, 6);
        Shape circle = new Circle(6);
        Shape square = new Square(6);

        //serialize object
        serialize(triangle,"triangle.serial");
        serialize(rectangle,"rectangle.serial");
        serialize(circle,"circle.serial");
        serialize(square,"square.serial");

        //Deserialize object
        Shape deserializeTriangle = (Shape) deserialize("triangle.serial");
        Shape deserializeRectangle = (Shape) deserialize("rectangle.serial");
        Shape deserializeCircle = (Shape) deserialize("circle.serial");
        Shape deserializeSquare = (Shape) deserialize("square.serial");

        // Test deserialization
        System.out.println("Area of deserialized triangle is " + deserializeTriangle.calculateArea());
        System.out.println("Perimeter of deserialized triangle is " + deserializeTriangle.calculatePerimeter());
        System.out.println("Area of deserialized rectangle is " + deserializeRectangle.calculateArea());
        System.out.println("Perimeter of deserialized rectangle is " + deserializeRectangle.calculatePerimeter());
        System.out.println("Area of deserialized circle is " + deserializeCircle.calculateArea());
        System.out.println("Perimeter of deserialized circle is " + deserializeCircle.calculatePerimeter());
        System.out.println("Area of deserialized square is " + deserializeSquare.calculateArea());
        System.out.println("Perimeter of deserialized square is " + deserializeSquare.calculatePerimeter());

    }
}