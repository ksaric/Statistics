package hr.logos.stat;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Throwables;
import hr.logos.common.ResultValue;
import hr.logos.common.StringRepresentation;
import hr.logos.functions.SumFunction;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdice.calc.Calculator;
import org.jdice.calc.Num;

import java.text.ParseException;
import java.util.List;

/**
 * @author ksaric
 */

final class AverageComputation implements Computation {

    protected final Log logger = LogFactory.getLog( getClass() );

    private final Computation sumComputation;
    private final ResultValueFactory resultValueFactory;
    private final StringRepresentation<List<? extends Number>> stringRepresentation;

    AverageComputation(
            final ResultValueFactory resultValueFactory,
            final Computation sumComputation,
            final StringRepresentation<List<? extends Number>> stringRepresentation
    ) {
        this.resultValueFactory = resultValueFactory;
        this.sumComputation = sumComputation;
        this.stringRepresentation = stringRepresentation;
    }

    @Override
    public Result compute( final List<? extends Number> number ) {
        if ( number.isEmpty() ) {
            return ResultValue.ZERO;
        }

        // sum the numbers
        final Calculator calculator = new Calculator();
        calculator.use( SumFunction.class );

        // represent the operation on Strings
        try {
            calculator.expression( stringRepresentation.respresentString( number ) );
        } catch ( ParseException e ) {
            logger.fatal( e.getMessage() );
            Throwables.propagate( e );
        }

        // calculate the result
        final Num result = calculator.setTracingSteps( true ).calculate();

        return resultValueFactory.create( result.doubleValue() );
    }

    @VisibleForTesting
    ResultValue divideResultValue( final ResultValue result, final ResultValue numbersSize ) {
        return result.divide( numbersSize );
    }
}