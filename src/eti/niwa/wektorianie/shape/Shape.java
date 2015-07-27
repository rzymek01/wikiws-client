package eti.niwa.wektorianie.shape;

import org.opencv.core.Mat;

import javax.annotation.Nonnull;

public interface Shape {

    void drawOnImage(@Nonnull Mat mat);
}
