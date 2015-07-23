package eti.niwa.wektorianie.imageprocessing.color;

import org.opencv.core.Mat;
import org.opencv.core.Scalar;

import java.util.function.Function;

public class ColoredImage {

    private final Mat image;
    private final Color color;

    public ColoredImage(Mat image, Color color) {
        this.image = image;
        this.color = color;
    }

    public Mat getImage() {
        return image;
    }

    public Color getColor() {
        return color;
    }

    public enum Color {
        BLACK(0, 0, 0),
        RED(255, 0, 0),
        GREEN(0, 255, 0),
        BLUE(0, 0, 255),
        YELLOW(255, 255, 0),
        CYAN(0, 255, 255),
        PINK(255, 0, 255);

        private final int R;
        private final int G;
        private final int B;

        private static final int COLOR_THRESHOLD = 100;

        Color(int b, int g, int r) {
            R = r;
            G = g;
            B = b;
        }

        public int getR() {
            return R;
        }

        public int getG() {
            return G;
        }

        public int getB() {
            return B;
        }

        public Scalar getFirstScalar() {
            return getScalar(value -> Math.min(value, getScalarValue(value)));
        }

        public Scalar getSecondScalar() {
            return getScalar(value -> Math.max(value, getScalarValue(value)));
        }

        private Scalar getScalar(Function<Integer, Integer> func) {
            return new Scalar(func.apply(R),func.apply(G), func.apply(B));
        }

        private int getScalarValue(int value) {
            if (value > 125) {
                return value - COLOR_THRESHOLD;
            }
            return value + COLOR_THRESHOLD;
        }
    }
}
