package hr.logos.stat;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Optional;
import hr.logos.common.ResultValue;

import java.util.List;

/**
 * Whitelist testability problem. Again.
 *
 * @author ksaric, pfh (Kristijan Šarić)
 */

class StandardDeviationComputation implements Computation {

    private final Computation varianceComputation;
    private final ResultValueFactory resultValueFactory;

    StandardDeviationComputation(
            final ResultValueFactory resultValueFactory,
            final Computation varianceComputation
    ) {
        this.resultValueFactory = resultValueFactory;
        this.varianceComputation = varianceComputation;
    }

    @Override
    public Result compute( final List<? extends Number> numbers ) {
        ResultValue result = ResultValue.ZERO;

        if ( numbers.isEmpty() ) {
            return result;
        }

        final ResultValue variance = resultValueFactory.create( varianceComputation.compute( numbers ).getAmount() );

        return Optional
                .fromNullable( resultValueFactory.create( sqrt( variance ) ) )
                .or( result );
    }

    @VisibleForTesting
    Double sqrt( final ResultValue divide ) {
        return Math.sqrt( resultValueToDouble( divide ) );
    }

    @VisibleForTesting
    Double resultValueToDouble( final ResultValue divide ) {
        return divide.getAmount().doubleValue();
    }
}
