package eti.niwa.wektorianie.util;

import com.google.common.collect.ImmutableList;
import org.javatuples.Pair;
import org.javatuples.Triplet;
import org.opencv.core.Point;

import java.util.List;

public class ImageHelper {

    public static List<Pair<Edge, Edge>> neighbouringEdges(List<Point> points)
    {
        final ImmutableList.Builder<Pair<Edge, Edge>> builder = ImmutableList.builder();
        final List<Edge> edges = edges(points);

        for (int i = 0; i < edges.size(); i ++ ) {
            builder.add(new Pair<>(edges.get(i), edges.get((i + 1) % edges.size())));
        }
        return builder.build();
    }

    public static List<Edge> edges(final List<Point> points)
    {
        final ImmutableList.Builder<Edge> builder = ImmutableList.builder();

        for (int i = 0; i < points.size(); i ++) {
            builder.add(new Edge(points.get(i), points.get((i + 1) % points.size())));
        }
        return builder.build();
    }
}
