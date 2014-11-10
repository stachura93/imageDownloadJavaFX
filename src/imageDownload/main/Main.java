package imageDownload.main;

import imageDownload.controller.Controller;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.awt.*;


/**
 *  @author StachuraBartłomiej
 *  Create simple application
 *  Parse website in JSoup
 *  Build simple JavaFX window
 *  Give possibility user set website
 *  Download Image in this website
 *  Delete all download files If user don't change this
 */
public class Main extends Application {

    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    /**
     * We get information about screen size and division by 2
     */
    public static final int DEFAULT_SCREEN_WIDTH = (int) screenSize.getWidth() / 2;
    public static final int DEFAULT_SCREEN_HEIGHT = (int) screenSize.getHeight() / 2;


    @Override
    public void start(Stage primaryStage) throws Exception {
        final String appName = "DownloadWebsiteImage";
        Parent root = null;
        root = (Parent) FXMLLoader.load(getClass().getResource("/imageDownload/view/MainPanel.fxml"));

        primaryStage.setTitle(appName);
        primaryStage.setScene(new Scene(root));

        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    public static void main(String[] args) {
     launch(args);
    }
}
