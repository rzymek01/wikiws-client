package eti.niwa.wektorianie.imageprocessing.basicdetector;

import com.google.common.collect.ImmutableList;
import eti.niwa.wektorianie.imageprocessing.color.ColoredImage;
import eti.niwa.wektorianie.shape.Shape;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class BasicDetectors {

    private final List<BaseBasicDetector> basicDetectors;

    public BasicDetectors() {
        basicDetectors = ImmutableList.of(new CircleDetector(), new QuadrangleDetection());
    }

    @Nonnull
    public List<Shape> detectBasicShapes(@Nonnull ColoredImage coloredImage) {
        return basicDetectors.stream()
                .map(detector -> detector.processImage(new BaseBasicDetector.ProcessedImage(coloredImage)))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}
