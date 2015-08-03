package eti.niwa.wektorianie.shape;

import eti.niwa.wektorianie.imageprocessing.color.Color;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;

import javax.annotation.Nonnull;

public class Circle extends Shape {

    private final Point point;

    private final int radius;

    public Circle(final Point point, final Color color, final int radius) {
        super(color);
        this.point = point;
        this.radius = radius;
    }

    @Override
    public void drawOnImage(@Nonnull Mat mat) {
        Core.circle(mat, point, radius, color.getColor(), 1);

    }
}
