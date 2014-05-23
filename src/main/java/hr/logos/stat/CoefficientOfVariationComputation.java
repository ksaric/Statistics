package hr.logos.stat;

import hr.logos.common.ResultValue;

import javax.inject.Inject;
import java.util.List;

/**
 * <a href="http://en.wikipedia.org/wiki/">Wiki</a>
 *
 * @author ksaric, pfh (Kristijan Šarić)
 */

public class CoefficientOfVariationComputation implements Computations {

    private final Computations standardDeviationComputation;
    private final Computations averageComputation;

    @Inject
    public CoefficientOfVariationComputation(
            Computations standardDeviationComputation,
            Computations averageComputation
    ) {
        this.standardDeviationComputation = standardDeviationComputation;
        this.averageComputation = averageComputation;
    }

    @Override
    public Result compute( final List<? extends Number> numbers ) {
        if ( numbers.isEmpty() ) {
            return ResultValue.ZERO;
        }

        final Result sdResult = standardDeviationComputation.compute( numbers );
        final Result averageResult = averageComputation.compute( numbers );

        return new ResultValue( sdResult.getAmount() ).divide( new ResultValue( averageResult.getAmount() ) );
    }
}
