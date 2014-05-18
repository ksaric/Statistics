package hr.logos.stat;

import com.google.common.annotations.VisibleForTesting;
import hr.logos.common.ResultValue;

import java.util.List;

/**
 * @author ksaric
 */

final class AverageComputation implements Computations {

    private final Computations sumComputations;
    private final ResultValueFactory resultValueFactory;

    AverageComputation( final ResultValueFactory resultValueFactory, final Computations sumComputations ) {
        this.resultValueFactory = resultValueFactory;
        this.sumComputations = sumComputations;
    }

    @Override
    public Result compute( final List<? extends Number> number ) {
        if ( number.isEmpty() ) return ResultValue.ZERO;

        /* sum the numbers */
        ResultValue result = resultValueFactory.create( sumComputations.compute( number ).getAmount() );

        /* divide the sum with the size/length of the elements */
        final ResultValue numbersSize = resultValueFactory.create( number.size() );

        return divideResultValue( result, numbersSize );
    }

    @VisibleForTesting
    ResultValue divideResultValue( final ResultValue result, final ResultValue numbersSize ) {
        return result.divide( numbersSize );
    }
}