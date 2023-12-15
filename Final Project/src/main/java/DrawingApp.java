import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.image.WritableImage;
import javax.imageio.ImageIO;
import javafx.embed.swing.SwingFXUtils;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.tensorflow.SavedModelBundle;
import org.tensorflow.Session;
import org.tensorflow.Tensor;
import org.tensorflow.ndarray.FloatNdArray;
import org.tensorflow.types.TFloat32;
import org.tensorflow.ndarray.StdArrays;
import org.tensorflow.ndarray.index.Indices;
import javafx.scene.control.Label;

// Observer interface
interface Observer {
    void update(String message);
}

// Subject class to manage observers
class Subject {
    private List<Observer> observers = new ArrayList<>();

    // Attach an observer to the subject
    public void attach(Observer observer) {
        observers.add(observer);
    }

    // Notify all observers about a massage
    public void notifyAllObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}

// Concrete implementation of Observer
//class ConcreteObserver implements Observer {
//    @Override
//    public void update(String message) {
//        System.out.println("Observer: " + message);
//    }
//}

// Main JavaFX application class
public class DrawingApp extends BaseApplication implements Observer {
    private static Subject subject;

    // Label to display the prediction result
    private Label predictionLabel;

    @Override
    public void update(String message) {
        System.out.println("Observer: " + message);
    }

    // Method to update the text of the prediction label
    private void setPredictionLabel(String text) {
        this.predictionLabel.setText(text);
    }

    // Implementation of the onDigitRecognized method from the DigitRecognitionObserver interface
    public void onDigitRecognized(int digit) {
        Platform.runLater(() -> {
            setPredictionLabel("Prediction: " + digit);
        });
    }

    // Canvas for drawing
    public Canvas canvas = new Canvas(280, 280);
    public GraphicsContext gc = canvas.getGraphicsContext2D();


    @Override
    protected void setup() {
        subject = new Subject();
        subject.attach(this);
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);

        // Set black as the fill color
        gc.setFill(Color.BLACK);
        // Fill the entire canvas with black
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        // Set the line width for drawing
        gc.setLineWidth(666);
        // Set white as the pen color for drawing
        gc.setFill(Color.WHITE);

        // Create a Save button
        Button btnSave = new Button("Recognize");
        // Create a Clear button
        Button btnClear = new Button("Clear");
        // Initialize the prediction label
        predictionLabel = new Label("Prediction: None");


        HBox buttonBar = new HBox(10);
        buttonBar.setAlignment(Pos.CENTER);
        buttonBar.getChildren().addAll(btnSave, predictionLabel, btnClear);

        root.getChildren().addAll(canvas, buttonBar);

        // Event handler for mouse dragging on the canvas
        canvas.setOnMouseDragged(e -> {
            double size = 20;
            double x = e.getX() - size / 2;
            double y = e.getY() - size / 2;
            gc.fillOval(x, y, size, size);
        });

        // Setup button event handlers
        btnRecognize(btnSave);
        btnClear(btnClear);
        Scene scene = new Scene(root, 300, 400);
        stage.setTitle("Draw Number");
        stage.setScene(scene);
    }

    // Event handler for the Recognize button
    public void btnRecognize(Button btnSave){
        // Event handler for the Save button
        btnSave.setOnAction(e -> {
            WritableImage writableImage = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
            canvas.snapshot(null, writableImage);

            BufferedImage bufferedImage = SwingFXUtils.fromFXImage(writableImage, null);
            BufferedImage resizedImage = new BufferedImage(28, 28, BufferedImage.TYPE_INT_ARGB);

            // Transform the image to fit the model's input requirements
            AffineTransform at = new AffineTransform();
            at.scale(28.0 / canvas.getWidth(), 28.0 / canvas.getHeight());
            AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
            resizedImage = scaleOp.filter(bufferedImage, resizedImage);

            try {
                // Save the processed image as a PNG file
                ImageIO.write(resizedImage, "png", new File("number.png"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            // Call the digit recognition method
            recognizeDigit("number.png");
            // Notify observers when clicked
            subject.notifyAllObservers("CLICKED");
        });
    }

    // Event handler for the Clear button
    public void btnClear(Button btnClear){
        btnClear.setOnAction(e -> {
        // Clear the canvas
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Set the canvas background to black
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Reset the pen color to white and keep the previous line width
        gc.setFill(Color.WHITE);
        gc.setLineWidth(666);

        // Reset the prediction label
        predictionLabel.setText("Prediction: None");
    });

    }


    public static void main(String[] args) {
        launch(args);
    }

    // Method to find the index of the maximum value in a probability array
    public static int argmax(FloatNdArray probabilities) {
        float maxVal = Float.NEGATIVE_INFINITY;
        int idx = 0;
        for (int i = 0; i < probabilities.shape().size(0); i++) {
            float curVal = probabilities.getFloat(i);
            if (curVal > maxVal) {
                maxVal = curVal;
                idx = i;
            }
        }
        return idx;
    }

    // Method to recognize a digit from an image file
    public void recognizeDigit(String filePath) {
        try {
            BufferedImage img = ImageIO.read(new File(filePath));

            // Convert the image to 28x28 grayscale and normalize to [0, 1]
            float[][][][] imageData = new float[1][28][28][1];
            int cnt = 0;
            for (int y = 0; y < img.getHeight(); y++) {
                for (int x = 0; x < img.getWidth(); x++) {
                    int p = img.getRGB(x, y);
                    int r = (p >> 16) & 0xff;
                    int g = (p >> 8) & 0xff;
                    int b = p & 0xff;
                    float gray = (r + g + b) / 3.0f / 255.0f;
                    imageData[0][y][x][0] = gray;
                    cnt++;
                }
            }

            Tensor imageTensor = TFloat32.tensorOf(StdArrays.ndCopyOf(imageData));
            SavedModelBundle smb = SavedModelBundle.load("my_mnist_cnn_model", "serve");
            Session s = smb.session();

            // Run the model and get the result
            TFloat32 result = (TFloat32) s.runner()
                    .feed("serving_default_conv2d_input", imageTensor)
                    .fetch("StatefulPartitionedCall")
                    .run().get(0);

            // Get the predicted label
            int predLabel = argmax(result.slice(Indices.at(0), Indices.all()));
            onDigitRecognized(predLabel);

            // Update the label text in the JavaFX UI thread
            Platform.runLater(() -> {
                predictionLabel.setText("Prediction: " + predLabel);  // 更新标签文本
            });

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}

