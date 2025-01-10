import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import view.GameView;
import controller.GameController;

public class Main extends Application {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    @Override
    public void start(Stage primaryStage) {
        GameView gameView = new GameView(WIDTH, HEIGHT);
        Scene scene = new Scene(gameView, WIDTH, HEIGHT);

        // Initialize controller with view and scene
        new GameController(gameView, scene);

        primaryStage.setTitle("Breakout");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}