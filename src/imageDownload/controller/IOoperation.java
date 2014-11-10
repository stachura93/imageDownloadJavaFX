package imageDownload.controller;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Created by bartlomiejstachura on 08/11/14.
 */
public class IOoperation {
    private static String folderLocalisation;
    private static Integer MIN_PHOTO_WIDTH, MIN_PHOTO_HEIGHT;
    private int howManyPhoto;
    private ArrayList<String> allFilesInLocalisation;
    private static ArrayList<File> saveFilesInDisk = new ArrayList<>();

    /**
     * Download files and save how Image .JPG
     * @param arrayList url link
     * @throws IOException
     */
    public void saveToFile(ArrayList<String> arrayList) throws IOException {
        howManyPhoto = 0;
        for (String iterator : arrayList) {
            URL urlConnect = new URL(iterator);
            BufferedImage bufferedImage = ImageIO.read(urlConnect);
            // Use Width and Height
            if ((bufferedImage.getWidth() > MIN_PHOTO_WIDTH) && (bufferedImage.getHeight() > MIN_PHOTO_HEIGHT)) {
                howManyPhoto++;
                File file = new File(folderLocalisation + "/" + howManyPhoto + ".jpg");
                saveFilesInDisk.add(file);
                ImageIO.write(bufferedImage, "JPEG", file);
            } else
                System.out.println("too small image");
        }
    }

    /**
     * Show all files in folder
     * @return List<String> files
     * @throws IOException
     */
    public ArrayList<String> showAllFilesInFolder() throws IOException {
        allFilesInLocalisation = new ArrayList<>();
        Files.walk(Paths.get(folderLocalisation)).forEach(filePath -> {
            if (Files.isRegularFile(filePath)) {
                allFilesInLocalisation.add(filePath.toString());
            }
        });
        return allFilesInLocalisation;
    }

    /**
     * We copy only download files
     * @return List destination all download files
     */
    public ArrayList<String> showAllDownloadFilesInFolder() {
        allFilesInLocalisation = new ArrayList<>();
        for (File file : saveFilesInDisk) {
            allFilesInLocalisation.add(file.getAbsolutePath());
        }
        return allFilesInLocalisation;
    }

    /**
     * Delete all files
     */
    public static void deleteAllDownloadFiles() {
        for (File file : saveFilesInDisk) {
            file.delete();
        }
    }

    /**
     * Download image only bigger then Width
     * @param MIN_PHOTO_WIDTH
     */
    public static void setMIN_PHOTO_WIDTH(Integer MIN_PHOTO_WIDTH) {
        IOoperation.MIN_PHOTO_WIDTH = MIN_PHOTO_WIDTH;
    }

    /**
     * Download image only bigger then Height
     * @param MIN_PHOTO_HEIGHT
     */
    public static void setMIN_PHOTO_HEIGHT(Integer MIN_PHOTO_HEIGHT) {
        IOoperation.MIN_PHOTO_HEIGHT = MIN_PHOTO_HEIGHT;
    }

    /**
     * We set localisation when files can be download
     * @param folderLocalisation
     */
    public static void setFolderLocalisation(String folderLocalisation) {
        IOoperation.folderLocalisation = folderLocalisation;
    }
}
