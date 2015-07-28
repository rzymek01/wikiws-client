package eti.niwa.wektorianie.util;

import eti.niwa.wektorianie.imageprocessing.color.Color;
import org.javatuples.Pair;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;

public class Edge {

    private final Point begin;
    private final Point end;

    public Edge(Point one, Point two) {
        begin = one;
        end = two;
    }

    public Point getBegin() {
        return begin;
    }

    public Point getEnd() {
        return end;
    }

    public Pair<Double, Double> vector() {
        return new Pair<>(end.x - begin.x, end.y - begin.y);
    }

    public double cos(final Edge edge) {
        final Pair<Double, Double> myVector = vector(), itsVector = edge.vector();
        return (myVector.getValue0() * itsVector.getValue0() + myVector.getValue1() * itsVector.getValue1()) / (size() * edge.size());
    }

    public double size() {
        final Pair<Double, Double> vector = vector();
        return Math.sqrt(vector.getValue0() * vector.getValue0() + vector.getValue1() * vector.getValue1());
    }

    public void drawOnImage(Mat mat, Color color) {
        Core.line(mat, getBegin(), getEnd(), color.getColor(), 1);
    }
}
