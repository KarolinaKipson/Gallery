package hr.kipson.karolina.gallery.iterator;

import hr.kipson.karolina.gallery.model.Image;

public class ImageIterator implements Iterator {

    Image[] imageList;

    int index = 0;


    public  ImageIterator (Image[] imageList)
    {
        this.imageList = imageList;
    }

    @Override
    public Object next()
    {
        Image image =  imageList[index];
        index += 1;
        return image;
    }

    @Override
    public boolean hasNext()
    {
        if (index >= imageList.length ||
                imageList[index] == null){
            return false;
        } else{
            return true;
        }

    }


}
