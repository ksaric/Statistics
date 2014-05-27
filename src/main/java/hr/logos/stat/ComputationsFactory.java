package hr.logos.stat;

import com.google.common.collect.*;
import hr.logos.common.ResultValue;
import net.jcip.annotations.Immutable;

import java.util.Comparator;
import java.util.Map;

/**
 * @author ksaric, pfh (Kristijan Šarić)
 */

@Immutable
public final class ComputationsFactory {

    private ComputationsFactory() {
    }

    public static Computation newSimpleAverageComputation() {
        final ResultValueFactory resultValueFactory = newResultValueFactory();
        final Computation sumComputation = ComputationsFactory.newSumComputation();
        /*final StringRepresentation<List<? extends Number>> stringRepresentation =
                StringRepresentationFactory.newSumStringRepresentation();*/

        return new AverageComputation( resultValueFactory, sumComputation, /*stringRepresentation*/null );
    }

    public static Computation newSumComputation() {
        final ResultValueFactory resultValueFactory = newResultValueFactory();
        return new SumComputation( resultValueFactory );
    }

    public static Computation newModComputation() {
        final Map<ResultValue, Integer> occurenceCountMap = Maps.newHashMap();
        final ResultValueFactory resultValueFactory = newResultValueFactory();

        return new ModComputation( occurenceCountMap, resultValueFactory );
    }

    public static Computation newMedianComputation() {
        final ResultValueFactory resultValueFactory = newResultValueFactory();
        final NumbersSorter numbersSorter = newDefaultNumbersSorter();

        return new MedianComputation( resultValueFactory, numbersSorter );
    }

    public static Computation newQuantileComputations( final Integer quantile ) {
        /* sort the numbers */
        final ResultValueFactory resultValueFactory = newResultValueFactory();
        final NumbersSorter numbersSorter = newDefaultNumbersSorter();

        return new QuantileComputation( resultValueFactory, numbersSorter, quantile );
    }

    public static Computation newStandardDeviationComputation() {
        final ResultValueFactory resultValueFactory = newResultValueFactory();
        final Computation varianceComputation = ComputationsFactory.newVarianceComputation();

        return new StandardDeviationComputation( resultValueFactory, varianceComputation );
    }

    public static Computation newVarianceComputation() {
        final ResultValueFactory resultValueFactory = newResultValueFactory();
        final Computation averageComputation = ComputationsFactory.newSimpleAverageComputation();

        return new VarianceComputation( resultValueFactory, averageComputation );
    }

    public static Computation newCoefficientOfVariationComputation() {
        final Computation standardDeviationComputation = newStandardDeviationComputation();
        final Computation averageComputation = newSimpleAverageComputation();

        return new CoefficientOfVariationComputation( standardDeviationComputation, averageComputation );
    }

    public static Transformation newLinearTransformation() {
        return new LinearTransformation();
    }

    public static ResultValueFactory newResultValueFactory() {
        return new DefaultResultValueFactory();
    }

    private static DefaultNumbersSorter newDefaultNumbersSorter() {
        final Comparator<Number> numberComparator = new NumberComparator();

        return new DefaultNumbersSorter( numberComparator );
    }
}
