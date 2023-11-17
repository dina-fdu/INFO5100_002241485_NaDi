import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

// The main class for the calculator that extends the JavaFX Application class
public class Calculator extends Application{
    // Text field used as the calculator screen for output
    private TextField screen;
    // Stores the current value entered or the result of the last operation
    private double currentNumber = 0;
    // Stores the pending operation to apply to the currentNumber
    private String function = "";

    // The start method is the main entry point for all JavaFX applications
    public void start(Stage primaryStage){
        // Set up the display field
        screen = new TextField();
        // Prevent user input in the text field
        screen.setEditable(false);
        // Align text to the left of the screen
        screen.setAlignment(Pos.CENTER_LEFT);

        // Set up the grid layout for buttons
        GridPane grid = new GridPane();
        // Margin around the grid
        grid.setPadding(new Insets(16));
        // Horizontal spacing between buttons
        grid.setHgap(5);
        // Vertical spacing between buttons
        grid.setVgap(5);
        // Align the grid to the center of the scene
        grid.setAlignment(Pos.CENTER);

        // Array of button labels for the calculator
        String[] arrangement ={
                "+", "-", "*", "/", "0", "1", "2", "3", "4", "5", "6", "7", "C", "8", "9", "="
        };

        // Populate the grid with buttons using the arrangement array
        int arrangementIndex = 0;
        for (int i = 0; i < 4; i++){
             for (int j = 0; j < 4; j++){
                 // Create buttons and set their actions
                 Button button = new Button(arrangement[arrangementIndex]);
                 button.setMinSize(50, 50);
                 button.setOnAction(this::action);
                 grid.add(button, j, i);
                 arrangementIndex++;
            }
        }
        // Set up the layout with the screen and the grid of buttons
        HBox hBox = new HBox(5, screen, grid);
        hBox.setAlignment(Pos.CENTER);

        // Create the scene with the HBox layout
        Scene scene = new Scene(hBox, 300, 300);
        primaryStage.setTitle("Calculation");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Action handler for button presses
    private void action(javafx.event.ActionEvent event){
        // Get the text from the button that was pressed
        String buttonText = ((Button)event.getSource()).getText();
        // If the pressed button is neither 'C' (clear) nor '=' (equals)
        if (!"C".equals(buttonText) && !"=".equals(buttonText)) {
            // If an operation button (+, -, *, /) was pressed
            if ("+".equals(buttonText) || "-".equals(buttonText) || "*".equals(buttonText) || "/".equals(buttonText)) {
                // If an operation is already pending, calculate the result with the current number and the number in the screen
                if (!function.isEmpty()) {
                    currentNumber = calculate(currentNumber, Double.parseDouble(screen.getText()), function);
                } else {
                    // If no operation is pending, set the current number to the value on the screen
                    currentNumber = Double.parseDouble(screen.getText());
                }
                // Set the current operation to the button pressed
                function = buttonText;
                // Clear the screen for the new number input
                screen.clear();
            } else {
                // If a number button was pressed, append the number to the screen
                screen.appendText(buttonText);
            }
        } else if ("=".equals(buttonText)) {
            // If the equals button was pressed and an operation is pending
            if (!function.isEmpty() && !screen.getText().isEmpty()) {
                // Parse the next number from the screen and calculate the result
                double nextNumber = Double.parseDouble(screen.getText());
                currentNumber = calculate(currentNumber, nextNumber, function);
                // Clear the current operation
                function = "";
                if (Double.isNaN(currentNumber)) {
                    // Display the result or an error if the result is not a number (NaN)
                    screen.setText("Error");
                } else {
                    // Format the result to two decimal places and display it
                    screen.setText(String.format("%.2f", currentNumber));
                }
            }
        } else if ("C".equals(buttonText)) {
            // If the clear button was pressed, reset the screen and the current number
            screen.clear();
            currentNumber = 0;
            function = "";
        }
    }

    // Calculation logic for the calculator
    private double calculate(double a, double b, String fx){
        // Perform calculation based on the operation (fx)
        switch (fx){
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                // Handle division by zero
                if (b == 0){
                    return  Double.NaN;
                }else {
                    return a / b;
                }
            default:
                return 0;
        }

    }

    // Main method
    public static void main(String[] args){
        launch(args);
    }
}