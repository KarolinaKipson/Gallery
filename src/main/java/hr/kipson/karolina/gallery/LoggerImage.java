package hr.kipson.karolina.gallery;

import hr.kipson.karolina.gallery.model.Image;
import hr.kipson.karolina.gallery.model.SiteUser;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class LoggerImage {

    private static LoggerImage logger = null;
    private final String logFile = "gallery_log.txt";
    private PrintWriter writer;

    private LoggerImage() {
        try {
            FileWriter fw = new FileWriter(logFile);
            writer = new PrintWriter(fw, true);
        } catch (IOException e) {}
    }

    public static synchronized LoggerImage getInstance(){
        if(logger == null)
            logger = new LoggerImage();
        return logger;
    }
    public void logUserDeleteImage (SiteUser user, Image image) {
        writer.println("User: " + user.getName() + " deleted image " + image.getName());
    }

    public void logUserUploadImage (SiteUser user) {
        writer.println("User: " + user.getName() + " uploaded neww image.");
    }

    public void logImageFromBuilder (Image image) {
        writer.println("Image with id " + image.getId() + " and name " + image.getName() + " was built by Builder.");
    }
}
