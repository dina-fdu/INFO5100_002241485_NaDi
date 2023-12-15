import javafx.application.Application;
import javafx.stage.Stage;

// This is an abstract base class for an application.
// It extends the 'Application' class from JavaFX.
public abstract class BaseApplication extends Application {
    // Protected variable 'stage' to hold the primary stage of the application.
    protected Stage stage;

    // The start method is overridden from the Application class.
    // This is where the primary stage is initialized.
    @Override
    public void start(Stage primaryStage) {
        // Assigning the primary stage to the class variable 'stage'.
        this.stage = primaryStage;

        // Calling the 'setup' method, which needs to be implemented by subclasses.
        // This method should set up the scene and layout for the stage.
        setup();
        // Displaying the primary stage.
        primaryStage.show();
    }

    // An abstract method 'setup'.
    protected abstract void setup();
}