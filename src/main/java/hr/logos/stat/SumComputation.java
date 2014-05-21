package hr.logos.stat;

import hr.logos.common.ResultValue;

import java.util.List;

/**
 * @author ksaric
 */

final class SumComputation implements Computations {

    private final ResultValueFactory resultValueFactory;

    SumComputation( final ResultValueFactory resultValueFactory ) {
        this.resultValueFactory = resultValueFactory;
    }

    @Override
    public Result compute( final List<? extends Number> number ) {
        ResultValue resultValue = ResultValue.ZERO;

        /* add the numbers together, sum them */
        for ( final Number integer : number ) {
            final ResultValue currentResultValue = resultValueFactory.create( integer.doubleValue() );
            resultValue = resultValue.add( currentResultValue );
        }

        return resultValue;
    }

}