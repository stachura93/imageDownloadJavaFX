package imageDownload.menuItem;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * We create simple message box
 * JavaFx stage is necessary, FX on this moment don't supports JOption panel
 * Created by bartlomiejstachura on 07/11/14.
 */
public class MessageBox {

    /**
     * Create simple frame and show message to user
     * @param message - Your message
     * @param title - Frame title
     */
    public static void show(String message, String title) {
        // Only stage and simple configuration
        final Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.setMinWidth(250);
        stage.setMinHeight(150);

        // Button and Label and standard param
        javafx.scene.control.Label label = new Label(message);
        javafx.scene.control.Button button = new javafx.scene.control.Button("OK");
        button.setOnAction(e -> stage.close());

        // Simple layout configuration
        VBox pane = new VBox(20);
        pane.setSpacing(15);
        pane.getChildren().addAll(label, button);
        pane.setAlignment(Pos.CENTER);

        // Add to scene and show all
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.showAndWait();
    }
}
