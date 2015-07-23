package eti.niwa.wektorianie.imageprocessing;


import eti.niwa.wektorianie.imageprocessing.color.ColorSplitter;
import eti.niwa.wektorianie.util.ImageIO;
import org.opencv.core.Mat;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class MainProcessing {

    private final ImageIO imageIO;
    private final ColorSplitter colorSplitter;

    private final Mat originalImage;
    private final String originalFilename;

    private MainProcessing(@Nonnull final Mat originalImage, @Nullable String originalFilename) {
        this.originalImage = originalImage;
        this.originalFilename = originalFilename;

        imageIO = new ImageIO();
        colorSplitter = new ColorSplitter();

    }

    @Nonnull
    public static MainProcessing fromFilename(@Nonnull final String filename) {
        return new MainProcessing(ImageIO.loadImage(filename), filename);
    }

    public void process() {
        this.colorSplitter.splitImageIntoColors(this.originalImage).stream()
                .forEach(coloredImage -> imageIO.saveNextTaggedImage(originalFilename, coloredImage.getColor().name(), coloredImage.getImage()));
    }
}
