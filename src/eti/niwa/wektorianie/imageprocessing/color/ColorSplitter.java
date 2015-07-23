package eti.niwa.wektorianie.imageprocessing.color;

import com.google.common.collect.ImmutableList;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

public class ColorSplitter {

    public List<ColoredImage> splitImageIntoColors(final Mat mat) {
        final List<ColoredImage> list = EnumSet.allOf(ColoredImage.Color.class).stream().map(color -> {
            Mat dst = new Mat();
            Core.inRange(mat, color.getFirstScalar(), color.getSecondScalar(), dst);
            return new ColoredImage(dst, color);
        }).collect(Collectors.toList());
        return ImmutableList.copyOf(list);
    }
}
