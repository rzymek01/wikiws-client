package eti.niwa.wektorianie.imageprocessing.basicdetector;

import com.google.common.collect.ImmutableList;
import eti.niwa.wektorianie.shape.Circle;
import eti.niwa.wektorianie.shape.Shape;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.imgproc.Imgproc;

import javax.annotation.Nonnull;
import java.util.List;

public class CircleDetector implements BaseBasicDetector{

    @Override
    @Nonnull
    public List<Shape> processImage(@Nonnull ProcessedImage image) {
        final Mat mat = new Mat();
        // TODO: WTF does this parameters mean?!?oneone!?
        Imgproc.HoughCircles(image.getColoredImage().getImage(), mat, Imgproc.CV_HOUGH_GRADIENT, 2, image.getColoredImage().getImage().height() / 4, 100, 50, 10, 400);

        final ImmutableList.Builder<Shape> builder = ImmutableList.builder();

        for (int i = 0; i < mat.cols(); i++)
        {
            final double params[] = mat.get(0, i);
            final Point center=new Point(Math.round(params[0]), Math.round(params[1]));
            int radius = (int)Math.round(params[2]);

            builder.add(new Circle(center, image.getColoredImage().getColor(), radius));
        }

        return builder.build();
    }
}
