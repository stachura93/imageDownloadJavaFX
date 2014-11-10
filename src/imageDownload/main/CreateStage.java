package imageDownload.main;

import imageDownload.controller.IOoperation;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

/**
 * Created by bartlomiejstachura on 08/11/14.
 */
public class CreateStage extends Stage {

    private static boolean deleteFile = true;
    private Stage primaryStage;

    /**
     * Create stage JavaFX
     * @param fileFXML
     * @param title
     * @throws IOException
     */
    public CreateStage(String fileFXML, String title) throws IOException {
        final String appName = title;
        Parent root = null;
        root = (Parent) FXMLLoader.load(getClass().getResource(fileFXML));
        this.primaryStage = new Stage();
        this.primaryStage.setTitle(appName);

        this.primaryStage.setScene(new Scene(root));
        this.primaryStage.setResizable(true);
        if (deleteFile == true) {
            this.primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    IOoperation.deleteAllDownloadFiles();
                }
            });
        }
        this.primaryStage.show();
    }

    /**
     * How files must be delete
     * @param deleteFile
     */
    public static void setDeleteFile(boolean deleteFile) {
        CreateStage.deleteFile = deleteFile;
    }
}
