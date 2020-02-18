package hr.kipson.karolina.gallery.iterator;

import hr.kipson.karolina.gallery.model.Image;

public class ImageCollection implements Collection {
    static final int MAX_ITEMS = 1000;
    int numberOfItems = 0;
    Image[] imageList;

    public ImageCollection()
    {
        imageList = new Image[MAX_ITEMS];

    }

    public void addItem(Image image)
    {
            imageList[numberOfItems] = image;
            numberOfItems = numberOfItems + 1;
    }

    @Override
    public Iterator getIterator() {
        return new ImageIterator(imageList);
    }
}
