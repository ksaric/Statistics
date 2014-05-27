package hr.logos.stat;

import hr.logos.common.ResultValue;

import java.util.List;
import java.util.Map;

/**
 * @author ksaric
 */

final class ModComputation implements Computation {

    private final Map<ResultValue, Integer> occurenceCountMap;
    private final ResultValueFactory resultValueFactory;

    ModComputation( final Map<ResultValue, Integer> occurenceCountMap, final ResultValueFactory resultValueFactory ) {
        this.occurenceCountMap = occurenceCountMap;
        this.resultValueFactory = resultValueFactory;
    }

    @Override
    public Result compute( final List<? extends Number> number ) {
        Integer highestOccurence = 0;
        ResultValue result = ResultValue.ZERO;

        /* find the number with the largest occurence */
        for ( final Number currentNumber : number ) {
            final ResultValue currentResultValue = resultValueFactory.create( currentNumber.doubleValue() );

            if ( occurenceCountMap.containsKey( currentResultValue ) ) {
                /* there is an occurence already */
                final Integer occurenceNumber = occurenceCountMap.get( currentResultValue );
                occurenceCountMap.put( currentResultValue, occurenceNumber + 1 );
            } else {
                occurenceCountMap.put( currentResultValue, 1 );
            }
        }

        /* return the result with the largest occurence */
        for ( final ResultValue resultValue : occurenceCountMap.keySet() ) {
            if ( occurenceCountMap.get( resultValue ) > highestOccurence ) {
                result = resultValue;
                highestOccurence = occurenceCountMap.get( resultValue );
            }
        }

        return result;
    }
}
