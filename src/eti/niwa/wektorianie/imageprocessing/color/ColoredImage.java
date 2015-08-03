package eti.niwa.wektorianie.imageprocessing.color;

import org.opencv.core.Mat;

public class ColoredImage {

    private final Mat image;
    private final Color color;

    public ColoredImage(Mat image, Color color) {
        this.image = image;
        this.color = color;
    }

    public Mat getImage() {
        return image;
    }

    public Color getColor() {
        return color;
    }
}
