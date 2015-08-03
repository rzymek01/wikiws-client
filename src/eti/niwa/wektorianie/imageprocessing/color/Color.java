package eti.niwa.wektorianie.imageprocessing.color;

import org.opencv.core.Scalar;

public enum Color {
    WHITE(0, 255, 250, 255, 250, 255),
    BLACK(0, 255, 0, 60, 0, 60),
    RED1(0, 10, 20, 250, 20, 250),
    RED2(160, 179, 20, 250, 20, 250),
    GREEN(38, 100, 20, 250, 20, 250),
    BLUE(100, 140, 20, 250, 20, 250);

    private final int minH;
    private final int maxH;
    private final int minS;
    private final int maxS;
    private final int minV;
    private final int maxV;

    Color(int minh, int maxh, int mins, int maxs, int minv, int maxv) {
        minH = minh;
        maxH = maxh;
        minS = mins;
        maxS = maxs;
        minV = minv;
        maxV = maxv;
    }

    public Scalar getColor() {
        switch (name()) {
            case "WHITE":
                return new Scalar(255, 255, 255);
            case "RED1":
            case "RED2":
                return new Scalar(255, 0, 0);
            case "GREEN":
                return new Scalar(0, 255, 0);
            case "BLUE":
                return new Scalar(0, 0, 255);
            default:
                return new Scalar(0, 0, 0);
        }
    }

    public Scalar getFirstScalar() {
        return new Scalar(minH, minS, minV);
    }

    public Scalar getSecondScalar() {
        return new Scalar(maxH, maxS, maxV);
    }
}