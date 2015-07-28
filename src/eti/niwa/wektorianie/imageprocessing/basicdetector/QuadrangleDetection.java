package eti.niwa.wektorianie.imageprocessing.basicdetector;

import com.google.common.collect.ImmutableList;
import eti.niwa.wektorianie.shape.Diamond;
import eti.niwa.wektorianie.shape.Rectangular;
import eti.niwa.wektorianie.shape.Shape;
import eti.niwa.wektorianie.util.ImageHelper;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.imgproc.Imgproc;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class QuadrangleDetection implements BaseBasicDetector{

    public static final double MIN_AREA = 100;
    public static final double DIAMOND_EDGE_LENGTH_MAX_DIFFERENCE = 10;
    public static final double RECTANGLE_MAX_COS_ANGLE_BETWEEN_EDGES = 0.3;

    // originally comes from http://opencv-code.com/tutorials/detecting-simple-shapes-in-an-image/
    @Override
    @Nonnull
    public List<Shape> processImage(@Nonnull ProcessedImage image) {
        final List<MatOfPoint> contours = new ArrayList<>();
        Imgproc.findContours(image.getColoredImage().getImage(), contours, new Mat(), Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

        final ImmutableList.Builder<Shape> builder = ImmutableList.builder();

        for (final MatOfPoint contour : contours) {
            final MatOfPoint2f contour2f = new MatOfPoint2f( contour.toArray() );
            final MatOfPoint2f approx = new MatOfPoint2f();
            //TODO: wtf is this 0.02?
            Imgproc.approxPolyDP(contour2f, approx, Imgproc.arcLength(contour2f, true) * 0.02, true);

            final List<Point> points = approx.toList();

            if (points.size() != 4) {
                continue;
            }

            if (Imgproc.contourArea(approx) < MIN_AREA) {
                continue;
            }

            if (!Imgproc.isContourConvex(new MatOfPoint(approx.toArray()))) {
                continue;
            }

            // check for rectangular
            final double maxCosAngle = ImageHelper.neighbouringEdges(points).stream().map(x -> x.getValue0().cos(x.getValue1())).max(Comparator.<Double>naturalOrder()).get();
            if (maxCosAngle < RECTANGLE_MAX_COS_ANGLE_BETWEEN_EDGES) {
                builder.add(new Rectangular(image.getColoredImage().getColor(), points));
            } else {
                // check for diamond
                final double firstEdgeSize = ImageHelper.edges(points).stream().findFirst().get().size();

                if (ImageHelper.edges(points).stream().allMatch(edge -> Math.abs(edge.size() - firstEdgeSize) < DIAMOND_EDGE_LENGTH_MAX_DIFFERENCE)) {
                    builder.add(new Diamond(image.getColoredImage().getColor(), points));
                }
            }

        }

        return builder.build();
    }
}
