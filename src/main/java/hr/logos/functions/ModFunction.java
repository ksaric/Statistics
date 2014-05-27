package hr.logos.functions;

import com.beust.jcommander.internal.Maps;
import org.jdice.calc.AbstractCalculator;
import org.jdice.calc.Function;
import org.jdice.calc.Num;
import org.jdice.calc.SingletonExtension;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author ksaric, pfh (Kristijan Šarić)
 */

@SingletonExtension
public class ModFunction implements Function {

    @Override
    public String getSymbol() {
        return "MOD";
    }

    @Override
    public Num calc( final AbstractCalculator abstractCalculator, final Num... numbers ) throws Exception {

        final Map<Num, Integer> occurenceCountMap = Maps.newHashMap();

        Integer highestOccurence = 0;
        Num result = Num.toNum( BigDecimal.ZERO );

        /* find the number with the largest occurence */
        for ( final Num currentNumber : numbers ) {
            if ( occurenceCountMap.containsKey( currentNumber ) ) {
                /* there is an occurence already */
                final Integer occurenceNumber = occurenceCountMap.get( currentNumber );
                occurenceCountMap.put( currentNumber, occurenceNumber + 1 );
            } else {
                occurenceCountMap.put( currentNumber, 1 );
            }
        }

        /* return the result with the largest occurence */
        for ( final Num resultValue : occurenceCountMap.keySet() ) {
            if ( occurenceCountMap.get( resultValue ) > highestOccurence ) {
                result = resultValue;
                highestOccurence = occurenceCountMap.get( resultValue );
            }
        }

        return result;
    }

    @Override
    public int getFunctionAttributes() {
        return 0;
    }
}
