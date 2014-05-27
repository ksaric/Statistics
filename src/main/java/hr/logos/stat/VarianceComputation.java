package hr.logos.stat;

import hr.logos.common.ResultValue;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author ksaric, pfh (Kristijan Šarić)
 */

public class VarianceComputation implements Computation {

    private final Computation averageComputation;
    private final ResultValueFactory resultValueFactory;

    VarianceComputation( final ResultValueFactory resultValueFactory, Computation averageComputation ) {
        this.resultValueFactory = resultValueFactory;
        this.averageComputation = averageComputation;
    }

    @Override
    public Result compute( final List<? extends Number> numbers ) {
        ResultValue result;

        if ( numbers.isEmpty() ) {
            return ResultValue.ZERO;
        }

        final Result avg = averageComputation.compute( numbers );

        ResultValue deltaSum = ResultValue.ZERO;
        deltaSum = calculateDeltaSum( numbers, deltaSum, avg );

        /* sum divide */
        result = deltaSum.divide( resultValueFactory.create( numbers.size() - 1 ) );
        return result;
    }

    private <T> ResultValue calculateDeltaSum( List<? extends Number> numbers, ResultValue deltaSum, Result avg ) {
        for ( final Number number : numbers ) {
            final ResultValue currentNumber = resultValueFactory.create( number.doubleValue() );

            final BigDecimal amount = avg.getAmount();
            final ResultValue average = resultValueFactory.create( amount );

            final ResultValue delta = currentNumber.subtract( average );

            deltaSum = deltaSum.add( delta.multiply( delta ) );
        }

        return deltaSum;
    }
}
