package hr.logos.stat;

import hr.logos.common.ResultValue;

import java.util.List;

/**
 * @author ksaric, pfh (Kristijan Šarić)
 */

public class VarianceComputation implements Computations {

    private final Computations averageComputations;
    private final ResultValueFactory resultValueFactory;

    VarianceComputation( final ResultValueFactory resultValueFactory, Computations averageComputations ) {
        this.resultValueFactory = resultValueFactory;
        this.averageComputations = averageComputations;
    }

    @Override
    public Result compute( final List<? extends Number> numbers ) {
        ResultValue result;

        if ( numbers.isEmpty() ) {
            return ResultValue.ZERO;
        }

        final Result avg = averageComputations.compute( numbers );

        ResultValue deltaSum = ResultValue.ZERO;
        deltaSum = calculateDeltaSum( numbers, deltaSum, avg );

        /* sum divide */
        result = deltaSum.divide( resultValueFactory.create( numbers.size() ) );
        return result;
    }

    private ResultValue calculateDeltaSum( List<? extends Number> numbers, ResultValue deltaSum, Result avg ) {
        for ( final Number number : numbers ) {
            final ResultValue currentNumber = resultValueFactory.create( number.doubleValue() );
            final ResultValue average = resultValueFactory.create( avg.getAmount() );

            final ResultValue delta = currentNumber.subtract( average );

            deltaSum = deltaSum.add( delta.multiply( delta ) );
        }

        return deltaSum;
    }
}
