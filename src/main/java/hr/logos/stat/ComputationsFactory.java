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

    public static Computations newSimpleAverageComputation() {
        final ResultValueFactory resultValueFactory = newResultValueFactory();
        final Computations sumComputation = ComputationsFactory.newSumComputation();

        return new AverageComputation( resultValueFactory, sumComputation );
    }

    public static Computations newSumComputation() {
        final ResultValueFactory resultValueFactory = newResultValueFactory();
        return new SumComputation( resultValueFactory );
    }

    public static Computations newModComputation() {
        final Map<ResultValue, Integer> occurenceCountMap = Maps.newHashMap();
        final ResultValueFactory resultValueFactory = newResultValueFactory();

        return new ModComputation( occurenceCountMap, resultValueFactory );
    }

    public static Computations newMedianComputation() {
        final ResultValueFactory resultValueFactory = newResultValueFactory();
        final NumbersSorter numbersSorter = newDefaultNumbersSorter();

        return new MedianComputation( resultValueFactory, numbersSorter );
    }

    public static Computations newQuantileComputations( final Integer quantile ) {
        /* sort the numbers */
        final ResultValueFactory resultValueFactory = newResultValueFactory();
        final NumbersSorter numbersSorter = newDefaultNumbersSorter();

        return new QuantileComputation( resultValueFactory, numbersSorter, quantile );
    }

    public static Computations newStandardDeviationComputation() {
        final ResultValueFactory resultValueFactory = newResultValueFactory();
        final Computations varianceComputation = ComputationsFactory.newVarianceComputation();

        return new StandardDeviationComputation( resultValueFactory, varianceComputation );
    }

    public static Computations newVarianceComputation() {
        final ResultValueFactory resultValueFactory = newResultValueFactory();
        final Computations averageComputation = ComputationsFactory.newSimpleAverageComputation();

        return new VarianceComputation( resultValueFactory, averageComputation );
    }

    public static Computations newCoefficientOfVariationComputation() {
        final Computations standardDeviationComputation = newStandardDeviationComputation();
        final Computations averageComputation = newSimpleAverageComputation();

        return new CoefficientOfVariationComputation( standardDeviationComputation, averageComputation );
    }

    public static ResultValueFactory newResultValueFactory() {
        return new DefaultResultValueFactory();
    }

    private static DefaultNumbersSorter newDefaultNumbersSorter() {
        final Comparator<Number> numberComparator = new NumberComparator();

        return new DefaultNumbersSorter( numberComparator );
    }
}
