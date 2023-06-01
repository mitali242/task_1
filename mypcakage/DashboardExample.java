package mypcakage;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class DashboardExample extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create the main layout container
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        // Add components to the grid pane
        Label nameLabel = new Label("Welcome, John Doe!");
        gridPane.add(nameLabel, 0, 0);

        Label salesLabel = new Label("Total Sales: $10,000");
        gridPane.add(salesLabel, 0, 1);

        Label ordersLabel = new Label("New Orders: 5");
        gridPane.add(ordersLabel, 0, 2);

        // Create the scene and set it on the stage
        Scene scene = new Scene(gridPane, 300, 200);
        primaryStage.setTitle("Dashboard Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
