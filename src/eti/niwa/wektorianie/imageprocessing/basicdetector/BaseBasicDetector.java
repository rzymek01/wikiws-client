package eti.niwa.wektorianie.imageprocessing.basicdetector;

import eti.niwa.wektorianie.imageprocessing.color.ColoredImage;
import eti.niwa.wektorianie.shape.Shape;

import javax.annotation.Nonnull;
import java.util.List;

public interface BaseBasicDetector {

    @Nonnull
    List<Shape> processImage(@Nonnull ProcessedImage image);

    class ProcessedImage {
        private final ColoredImage coloredImage;

        public ProcessedImage(ColoredImage coloredImage) {
            this.coloredImage = coloredImage;
        }

        public ColoredImage getColoredImage() {
            return coloredImage;
        }
    }
}
