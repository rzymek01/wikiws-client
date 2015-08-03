package eti.niwa.wektorianie.shape;

import eti.niwa.wektorianie.imageprocessing.color.Color;
import org.opencv.core.Mat;

import javax.annotation.Nonnull;

public abstract class Shape {

    protected final Color color;

    public Shape(Color color) {
        this.color = color;
    }

    public abstract void drawOnImage(@Nonnull Mat mat);
}
