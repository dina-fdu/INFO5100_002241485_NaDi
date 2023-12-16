To reproduce the results:

**Step 1:** Train a CNN on MNIST with Python TensorFlow: 
```
cd 'Final Project'
python3 tensorflow model.py
```
**Step 2:** Open Java project with IDEA and run the code at this path `Final Project/src/main/java/DrawingApp.java`

------------

Our code is a JavaFX-based graphical user interface application that implements a simple digital recognition tool. All basic components work seamlessly.
1.	**GUI Development with JavaFX:**
   
This code uses the JavaFX library to construct a graphical user interface (GUI), which includes buttons, labels, a canvas, etc.
It uses Canvas and GraphicsContext for drawing, as well as VBox and HBox for layout.

2.	**Include a class diagram of the project:**
   
The class diagram is at `./Final Project/class_diagram.png`.

3.	**Use of Inheritance, Encapsulation, and Interfaces:**
   
Inheritance: BaseApplication is a subclass of JavaFX's Application class.

Encapsulation: Class member variables (such as subject, predictionLabel, canvas) are encapsulated within the class and accessed or modified through methods (such as setPredictionLabel).

Interfaces: An Observer interface is implemented, for the observer pattern.

4.	**Use of a Design Pattern:**
   
This code implements the observer design pattern. The Subject class acts as the observed object, with the Observer interface and ConcreteObserver class acting as observers. It achieves a function that notifies observers when clicked.
 
5.	**Code must be well commented:**
   
Our code is well-commented.

6.	**Include screenshots of various tests:**
   
Screenshots of various tests can be seen in the path `./Final Project/screenshots of various tests`

7.	**Appropriate Exception Handling:**
   
In the `recognizeDigit` method, a try-catch block is used to handle potential IOExceptions.
