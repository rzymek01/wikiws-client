package eti.niwa.wektorianie.imageprocessing.basicdetector;

import com.google.common.collect.ImmutableList;
import eti.niwa.wektorianie.shape.Ellipse;
import eti.niwa.wektorianie.shape.Shape;
import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO: what if ellipse is not horizontal/vertical?
 */
public class EllipseDetection implements BaseBasicDetector{

    public static final double MIN_AREA = 100;
    public static final double MIN_POINTS = 50;
    public static final double MAX_AREA_DIFFERENCE = 1000;

    // originally comes from http://opencv-code.com/tutorials/detecting-simple-shapes-in-an-image/
    // and this http://stackoverflow.com/questions/4785419/detection-of-coins-and-fit-ellipses-on-an-image
    @Override
    @Nonnull
    public List<Shape> processImage(@Nonnull ProcessedImage image) {
        final List<MatOfPoint> contours = new ArrayList<>();
        Imgproc.findContours(image.getColoredImage().getImage(), contours, new Mat(), Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

        final ImmutableList.Builder<Shape> builder = ImmutableList.builder();

        contours.stream()
                .filter(contour -> contour.toList().size() < MIN_POINTS)
                .forEach(contour -> {
            final MatOfPoint2f contour2f = new MatOfPoint2f( contour.toArray() );

            final double area = Imgproc.contourArea(contour);
            if (area < MIN_AREA) {
                return;
            }

            final MatOfPoint2f approx = new MatOfPoint2f();
            //TODO: wtf is this 0.02?
            Imgproc.approxPolyDP(contour2f, approx, Imgproc.arcLength(contour2f, true) * 0.02, true);

            if (!Imgproc.isContourConvex(new MatOfPoint(approx.toArray()))) {
                return;
            }

            final Rect boundingRect = Imgproc.boundingRect(contour);
            final double aAxe = boundingRect.width / 2;
            final double bAxe = boundingRect.height / 2;
            final double longerRadius = Math.max(bAxe, aAxe);
            final double smallerRadius = Math.min(bAxe, aAxe);
            final double calculatedArea = Math.PI * longerRadius * smallerRadius;

            if (Math.abs(calculatedArea - area) > MAX_AREA_DIFFERENCE){
                return;
            }

            // if it's more circle than ellipse
            if (longerRadius / smallerRadius < 2) {
                return;
            }

            builder.add(new Ellipse(image.getColoredImage().getColor(), new Point(boundingRect.x + aAxe, boundingRect.y + bAxe), aAxe, bAxe));
        });

        return builder.build();
    }
}
