package hr.logos.stat;

import com.google.common.annotations.VisibleForTesting;
import hr.logos.common.ResultValue;

import java.util.List;

/**
 * @author ksaric
 */

final class QuantileComputation implements Computation {

    private final NumbersSorter numbersSorter;
    private final ResultValueFactory resultValueFactory;
    private final Integer quantile;

    QuantileComputation(
            final ResultValueFactory resultValueFactory,
            final NumbersSorter numbersSorter,
            final Integer quantile
    ) {
        this.resultValueFactory = resultValueFactory;
        this.numbersSorter = numbersSorter;
        this.quantile = quantile;
    }

    @Override
    public Result compute( final List<? extends Number> numbers ) {
        ResultValue result = ResultValue.ZERO;

        if ( numbers.isEmpty() ) {
            return result;
        }

        /* sort the numbers */
        final List<? extends Number> sortedList = numbersSorter.sort( numbers );

        if ( isEvenLength( sortedList ) ) {
            result = getEvenMedian( sortedList, getSplitIndex( sortedList ) );
        } else {
            result = getOddMedian( sortedList, getSplitIndex( sortedList ) );
        }

        return result;
    }

    private ResultValue getOddMedian( final List<? extends Number> integers, int halfLength ) {
        return resultValueFactory.create( integers.get( halfLength ).doubleValue() );
    }

    private ResultValue getEvenMedian( final List<? extends Number> integers, int halfLength ) {
        return divideResultValues( integers, halfLength );
    }

    @VisibleForTesting
    ResultValue divideResultValues( List<? extends Number> integers, int halfLength ) {
        final ResultValue firstValue = resultValueFactory.create( integers.get( halfLength ).doubleValue() );
        final ResultValue secondValue = resultValueFactory.create( integers.get( halfLength + 1 ).doubleValue() );

        return addResultValues( firstValue, secondValue ).divide( ResultValue.TWO );
    }

    @VisibleForTesting
    ResultValue addResultValues( final ResultValue firstValue, final ResultValue secondValue ) {
        return firstValue.add( secondValue );
    }

    private int getSplitIndex( final List<? extends Number> number ) {
        final int index = (int) ( ( (double) number.size() / (double) quantile ) + 0.5 );
        return index - 1;
    }

    private boolean isEvenLength( List<? extends Number> integers ) {
        return integers.size() % 2 == 0;
    }

}