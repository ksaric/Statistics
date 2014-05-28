package hr.logos.functions;

import com.beust.jcommander.internal.Lists;
import org.jdice.calc.AbstractCalculator;
import org.jdice.calc.Function;
import org.jdice.calc.Num;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

/**
 * @author ksaric, pfh (Kristijan Šarić)
 */

public class MedianFunction implements Function {

    @Override
    public String getSymbol() {
        return "MEDIAN";
    }

    @Override
    public Num calc( final AbstractCalculator abstractCalculator, final Num... numbers ) throws Exception {

        final List<Num> sortedNumbers = Lists.newArrayList();

        Collections.addAll( sortedNumbers, numbers );
        Collections.sort( sortedNumbers );

        final int indexHalfSize = sortedNumbers.size() / 2;

        if ( evenSize( sortedNumbers ) ) {
            final Num firstHalfValue = sortedNumbers.get( indexHalfSize - 1 );
            final Num secondHalfValue = sortedNumbers.get( indexHalfSize );

            final BigDecimal result = (
                    firstHalfValue.toBigDecimal()
                            .add( secondHalfValue.toBigDecimal() )
            )
                    .divide( BigDecimal.valueOf( 2 ) );

            return new Num( result );
        } else {
            return new Num( sortedNumbers.get( indexHalfSize ) );
        }
    }

    private boolean evenSize( final List<Num> sortedNumbers ) {
        return sortedNumbers.size() % 2 == 0;
    }

    @Override
    public int getFunctionAttributes() {
        return 0;
    }

}
