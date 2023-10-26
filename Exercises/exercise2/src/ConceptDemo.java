// Base class Shape
abstract class Shape{
    // Static field
    static String color = "blue";

    // Abstract methods for abstraction
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
    Triangle(double base, double height, double sideLeft, double sideRight){
        this.base = base;
        this.height = height;
        this.sideLeft = sideLeft;
        this.sideRight = sideRight;
    }

    // Overriding abstract methods from Shape
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
    Rectangle(double length, double width){
        this.length = length;
        this.width = width;
    }

    // Overriding abstract methods from Shape
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
    Square(double side){
        super(side, side);
    }
}

public class ConceptDemo {
    public static void main(String[] args){
        Shape triangle = new Triangle(6, 4, 5, 5);
        Shape rectangle = new Rectangle(8, 6);
        Shape circle = new Circle(6);
        Shape square = new Square(6);

        // Demonstrating polymorphism
        System.out.println("Area of triangle is " + triangle.calculateArea());
        System.out.println("Perimeter of triangle is " + triangle.calculatePerimeter());
        System.out.println("Area of rectangle is " + rectangle.calculateArea());
        System.out.println("Perimeter of rectangle is " + rectangle.calculatePerimeter());
        System.out.println("Area of circle is " + circle.calculateArea());
        System.out.println("Perimeter of circle is " + circle.calculatePerimeter());
        System.out.println("Area of square is " + square.calculateArea());
        System.out.println("Perimeter of square is " + square.calculatePerimeter());

        triangle.showColor();
        rectangle.showColor();
        circle.showColor();
        square.showColor();

    }
}