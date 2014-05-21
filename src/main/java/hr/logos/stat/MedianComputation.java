package hr.logos.stat;

import com.google.common.annotations.VisibleForTesting;
import hr.logos.common.ResultValue;

import java.util.List;

/**
 * @author ksaric
 */

final class MedianComputation implements Computations {

    private final ResultValueFactory resultValueFactory;
    private final NumbersSorter numbersSorter;

    MedianComputation( final ResultValueFactory resultValueFactory, final NumbersSorter numbersSorter ) {
        this.resultValueFactory = resultValueFactory;
        this.numbersSorter = numbersSorter;
    }

    @Override
    public Result compute( final List<? extends Number> numbers ) {
        ResultValue result = ResultValue.ZERO;

        if ( numbers.isEmpty() ) {
            return result;
        }

        /* sort */
        final List<? extends Number> sortedList = numbersSorter.sort( numbers );

        final int halfLength = sortedList.size() / 2;

        if ( isEvenLength( sortedList ) ) {
            result = getEvenMedian( sortedList, halfLength - 1 );
        } else {
            result = getOddMedian( sortedList, halfLength );
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
        final ResultValue addedResultValues = addResultValues( integers, halfLength );

        return addedResultValues.divide( ResultValue.TWO );
    }

    @VisibleForTesting
    ResultValue addResultValues( List<? extends Number> integers, int halfLength ) {
        final ResultValue firstValue = resultValueFactory.create( integers.get( halfLength ).doubleValue() );
        final ResultValue secondValue = resultValueFactory.create( integers.get( halfLength + 1 ).doubleValue() );

        return firstValue.add( secondValue );
    }

    private boolean isEvenLength( List<? extends Number> integers ) {
        return integers.size() % 2 == 0;
    }


}