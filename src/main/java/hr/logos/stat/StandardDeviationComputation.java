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

class StandardDeviationComputation implements Computations {

    private final Computations varianceComputation;
    private final ResultValueFactory resultValueFactory;

    StandardDeviationComputation(
            final ResultValueFactory resultValueFactory,
            final Computations varianceComputation
    ) {
        this.resultValueFactory = resultValueFactory;
        this.varianceComputation = varianceComputation;
    }

    @Override
    public Result compute( final List<? extends Number> numbers ) {
        if ( numbers.isEmpty() ) {
            return ResultValue.ZERO;
        }

        final ResultValue variance = resultValueFactory.create( varianceComputation.compute( numbers ).getAmount() );

        return Optional
                .fromNullable( resultValueFactory.create( sqrt( variance ) ) )
                .or( ResultValue.ZERO );
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
