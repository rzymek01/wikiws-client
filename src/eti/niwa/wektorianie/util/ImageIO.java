package eti.niwa.wektorianie.util;

import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;

public class ImageIO {

    public static Mat loadImage (String filename) {
        return Highgui.imread(filename, Highgui.CV_LOAD_IMAGE_COLOR);
    }

    public static void saveImage(String filename, Mat mat) {
        Highgui.imwrite(filename + "result.jpg", mat);
    }

}
