package imageDownload.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DownloadPane implements Initializable {

    @FXML
    private ListView<String> listView;

    @FXML
    private ImageView showImage;

    private IOoperation iooperation;


    /**
     * Try show photo in ImageView
     * When user change selected item we load another photo
     */
    private void showImageView() {
        listView.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                String showImageLocalisation = listView.getItems().get((int) newValue.intValue());
                try {
                    BufferedImage bufferedImage = ImageIO.read(new File(showImageLocalisation));
                    Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                    showImage.setImage(image);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Load all files and show user in ListView panel
     */
    private void showFilesInListView() {
        iooperation = new IOoperation();
        ObservableList<String> names = null;
        try {
            names = FXCollections.observableArrayList(iooperation.showAllFilesInFolder());
        } catch (IOException e) {
            e.printStackTrace();
        }
        listView.getItems().setAll(names);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showFilesInListView();
        showImageView();
    }
}
