import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.awt.*;

public class Main extends Application{
    private TextField screen;
    private double currentNumber = 0;
    private String function = "";

    public void start(Stage primaryStage){
        screen = new TextField();
        screen.setEditable(false);
        screen.setAlignment(Pos.CENTER_LEFT);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(16));
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setAlignment(Pos.CENTER);

        String[] arrangement ={
                "+", "-", "*", "/", "0", "1", "2", "3", "4", "5", "6", "7", "C", "8", "9", "="
        };

        int arrangementIndex = 0;
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                Button button = new Button(arrangement[arrangementIndex]);
                button.setMinSize(50, 50);
                button.setOnAction(this::action);
                grid.add(button, j, i);
                arrangementIndex++;
            }
        }
        HBox hBox = new HBox(5, screen, grid);
        hBox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(hBox, 300, 300);
        primaryStage.setTitle("Calculation");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void action(javafx.event.ActionEvent event){
        String buttonText = ((Button)event.getSource()).getText();
        if (!"C".equals(buttonText) && !"=".equals(buttonText)) {
            if ("+".equals(buttonText) || "-".equals(buttonText) || "*".equals(buttonText) || "/".equals(buttonText)) {
                if (!function.isEmpty()) {
                    currentNumber = calculate(currentNumber, Double.parseDouble(screen.getText()), function);
                } else {
                    currentNumber = Double.parseDouble(screen.getText());
                }
                function = buttonText;
                screen.clear();
            } else {
                screen.appendText(buttonText);
            }
        } else if ("=".equals(buttonText)) {
            if (!function.isEmpty() && !screen.getText().isEmpty()) {
                double nextNumber = Double.parseDouble(screen.getText());
                currentNumber = calculate(currentNumber, nextNumber, function);
                function = "";
                if (Double.isNaN(currentNumber)) {
                    screen.setText("Error");
                } else {
                    screen.setText(String.format("%.2f", currentNumber));
                }
            }
        } else if ("C".equals(buttonText)) {
            screen.clear();
            currentNumber = 0;
            function = "";
        }
    }
    private double calculate(double a, double b, String fx){
        switch (fx){
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                if (b == 0){
                    return  Double.NaN;
                }else {
                    return a / b;
                }
            default:
                return 0;
        }

    }
    public static void main(String[] args){
        launch(args);
    }
}