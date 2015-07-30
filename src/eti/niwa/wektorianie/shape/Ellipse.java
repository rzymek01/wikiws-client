package eti.niwa.wektorianie.shape;

import eti.niwa.wektorianie.imageprocessing.color.Color;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Size;

import javax.annotation.Nonnull;

public class Ellipse implements Shape {

    private final Point center;
    private final Color color;
    private final double aAxe;
    private final double bAxe;


    public Ellipse(final Color color, final Point center, double aAxe, double bAxe) {
        this.center = center;
        this.color = color;
        this.aAxe = aAxe;
        this.bAxe = bAxe;
    }

    @Override
    public void drawOnImage(@Nonnull Mat mat) {
        Core.ellipse(mat, center, new Size(aAxe, bAxe), 0, 0, 360, color.getColor());
    }
}
