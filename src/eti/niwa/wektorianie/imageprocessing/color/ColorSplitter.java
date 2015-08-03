package eti.niwa.wektorianie.imageprocessing.color;

import com.google.common.collect.ImmutableList;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

public class ColorSplitter {

    public List<ColoredImage> splitImageIntoColors(final Mat mat) {
        final List<ColoredImage> list = EnumSet.complementOf(EnumSet.of(Color.WHITE)).stream().map(color -> {
            Mat dst = new Mat();
            Mat tmp = new Mat();
            Imgproc.cvtColor(mat, tmp, Imgproc.COLOR_BGR2HSV);
            Core.inRange(tmp, color.getFirstScalar(), color.getSecondScalar(), dst);
            return new ColoredImage(dst, color);
        }).collect(Collectors.toList());
        return ImmutableList.copyOf(list);
    }
}
