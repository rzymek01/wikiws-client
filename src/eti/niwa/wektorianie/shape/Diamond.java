package eti.niwa.wektorianie.shape;

import eti.niwa.wektorianie.imageprocessing.color.Color;
import eti.niwa.wektorianie.util.ImageHelper;
import org.opencv.core.Mat;
import org.opencv.core.Point;

import javax.annotation.Nonnull;
import java.util.List;

public class Diamond implements Shape {

    private final Color color;
    private final List<Point> points;

    public Diamond(Color color, List<Point> points) {
        this.color = color;
        this.points = points;
    }

    @Override
    public void drawOnImage(@Nonnull Mat mat) {
        ImageHelper.edges(points).forEach(edge -> edge.drawOnImage(mat, color));
    }
}
