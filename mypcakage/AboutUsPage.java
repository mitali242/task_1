package mypcakage;
import javafx.scene.control.Label;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javafx.scene.layout.StackPane;
public class AboutUsPage extends StackPane {
    public AboutUsPage() {
    	Label titleLabel = new Label("\"The online examination system based on ");
        // Add more labels, images, or other components as needed
        getChildren().add(titleLabel);
    }
}
