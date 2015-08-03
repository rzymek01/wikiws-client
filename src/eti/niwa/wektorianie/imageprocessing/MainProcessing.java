package eti.niwa.wektorianie.imageprocessing;


import eti.niwa.wektorianie.imageprocessing.basicdetector.BasicDetectors;
import eti.niwa.wektorianie.imageprocessing.color.Color;
import eti.niwa.wektorianie.imageprocessing.color.ColorSplitter;
import eti.niwa.wektorianie.util.ImageIO;
import org.apache.commons.io.FilenameUtils;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;

public class MainProcessing {

    private final ImageIO imageIO;
    private final ColorSplitter colorSplitter;
    private final BasicDetectors basicDetectors;

    private final Mat originalImage;
    private final String originalFilename;

    private MainProcessing(@Nonnull final Mat originalImage, @Nullable String originalFilename) {
        this.originalImage = originalImage;
        this.originalFilename = originalFilename == null ? FilenameUtils.concat(System.getProperty("user.home"), "Wektorianie.png") : originalFilename;

        imageIO = new ImageIO();
        colorSplitter = new ColorSplitter();
        basicDetectors = new BasicDetectors();
    }

    @Nonnull
    public static MainProcessing fromFilename(@Nonnull final String filename) {
        return new MainProcessing(ImageIO.loadImage(filename), filename);
    }

    public void process() {
        Mat resultMat;

        resultMat = preprocessImage(originalImage);
        imageIO.saveNextTaggedImage(originalFilename, "preprocessing".toUpperCase(), resultMat);
        resultMat = splitColors(resultMat);
        imageIO.saveNextTaggedImage(originalFilename, resultMat);
    }

    private Mat preprocessImage(final Mat image) {
        Mat result = image.clone();
        Core.multiply(result, new Scalar(1.5, 1.5, 1.5), result);
        return result;
    }

    private Mat splitColors(Mat image) {
        Mat mat = new Mat(image.size(), image.type());
        mat.setTo(Color.WHITE.getColor());

        this.colorSplitter.splitImageIntoColors(image).stream()
                .map(coloredImage -> {
                    imageIO.saveNextTaggedImage(originalFilename, coloredImage.getColor().name(), coloredImage.getImage());
                    return coloredImage;
                })
                .map(basicDetectors::detectBasicShapes)
                .flatMap(Collection::stream)
                .forEach(shape -> shape.drawOnImage(mat));

        return mat;
    }
}
