package eti.niwa.wektorianie.util;

import org.apache.commons.io.FilenameUtils;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public class ImageIO {

    public static Mat loadImage (@Nonnull final String filename) {
        return Highgui.imread(filename, Highgui.CV_LOAD_IMAGE_COLOR);
    }

    public static void saveImage(@Nonnull final String filename, @Nonnull Mat mat) {
        Highgui.imwrite(filename, mat);
    }

    private final Map<String, Integer> map = new HashMap<>();

    public void saveNextTaggedImage(@Nonnull final String originalFilename, @Nonnull final Mat mat) {
        saveNextTaggedImage(originalFilename, "PROCESSED", mat);
    }

    public void saveNextTaggedImage(@Nonnull final String originalFilename, @Nonnull final String tag, @Nonnull final Mat mat) {
        if (!map.containsKey(tag)) {
            map.put(tag, 0);
        }

        int key = map.get(tag);
        map.put(tag, key + 1);

        saveImage(String.format("%s-results-%s-%d.%s",
                FilenameUtils.removeExtension(originalFilename), tag, key, FilenameUtils.getExtension(originalFilename)), mat);
    }
}
