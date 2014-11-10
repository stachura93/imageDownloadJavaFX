package imageDownload.controller;

import com.sun.javafx.tk.Toolkit;
import imageDownload.main.CreateStage;
import imageDownload.menuItem.MessageBox;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    @FXML
    private CheckMenuItem deleteFile;

    @FXML
    private TextField giveHeight;

    @FXML
    private TextField userGiveWebsiteLink;

    @FXML
    private ProgressIndicator progressBar;

    @FXML
    private Slider imagePixelHeight;

    @FXML
    private TextField giveWidth;

    @FXML
    private Slider imagePixelWidth;

    ///////////////////////////////////////////////////////////////////

    /**
     * Show information about autor in menuItem
     *
     * @param event
     */
    @FXML
    private void showMessegeAboutMe(ActionEvent event) {
        MessageBox.show("Bartłomiej Stachura" + "\n" + "b.stachura93@gmail.com", "AboutMe");
    }

    /**
     * If user click Download button
     * connect URL site, download photo on disk, create new panel to view all photo
     *
     * @param event
     */
    @FXML
    void buttonClickDownloadImage(ActionEvent event) {
        progressBar.setVisible(true);

        // User choose localisation
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Give folder path");
        File selectedDirectory = chooser.showDialog(null);

        // Give localisation all files / Save / Open / Folder
        WebsiteParse.setWebsiteURL(userGiveWebsiteLink.getText());
        IOoperation.setFolderLocalisation(selectedDirectory.getAbsolutePath());

        // Get min photo WIDTH and HEIGHT
        IOoperation.setMIN_PHOTO_HEIGHT(Integer.parseInt(giveHeight.getText()));
        IOoperation.setMIN_PHOTO_WIDTH(Integer.parseInt(giveWidth.getText()));

        System.out.println(userGiveWebsiteLink.getText());
        System.out.println(selectedDirectory.getAbsolutePath());

        // Download and save photo
        try {
            WebsiteParse websiteParse = new WebsiteParse();

            IOoperation iOoperation = new IOoperation();
            iOoperation.saveToFile(websiteParse.getImgClearLink());
        } catch (IOException e) {
            System.out.println("Problem witch JSoup Parse");
        }
        progressBar.setProgress(100);

        // Create new Panel witch new Photo
        try {
            CreateStage.setDeleteFile(deleteFile.isSelected());
            CreateStage createStage = new CreateStage("/imageDownload/view/DownloadPane.fxml", "Download");
        } catch (IOException e) {
            System.out.println("error stage loading");
        }
    }

    /**
     * If user change slider position Observable
     *
     * @param slider
     * @param textField
     */
    private void IfSliderChange(Slider slider, TextField textField) {
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                textField.setText(String.valueOf(newValue.intValue()));
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        IfSliderChange(imagePixelWidth, giveWidth);
        IfSliderChange(imagePixelHeight, giveHeight);
    }
}
