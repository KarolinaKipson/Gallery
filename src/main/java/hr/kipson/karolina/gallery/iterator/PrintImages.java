package hr.kipson.karolina.gallery.iterator;

import hr.kipson.karolina.gallery.model.Image;

public class PrintImages {
    ImageCollection images;

    public PrintImages(ImageCollection images)
    {
        this.images = images;
    }

    public void printImages()
    {
        Iterator iterator = images.getIterator();

        while (iterator.hasNext())
        {
            Image image = (Image)iterator.next();
            System.out.println(image.getName());
        }
    }
}
