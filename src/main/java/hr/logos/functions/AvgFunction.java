package hr.logos.functions;

import org.jdice.calc.AbstractCalculator;
import org.jdice.calc.Function;
import org.jdice.calc.Num;
import org.jdice.calc.SingletonExtension;

import java.math.BigDecimal;

/**
 * todo: Function composition? Problems with <Num> <-> <? extends Number> !?!
 *
 * @author ksaric, pfh (Kristijan Šarić)
 */

@SingletonExtension
public class AvgFunction implements Function {

    @Override
    public String getSymbol() {
        return "AVG";
    }

    @Override
    public Num calc( final AbstractCalculator abstractCalculator, final Num... numbers ) throws Exception {

        BigDecimal sum = BigDecimal.ZERO;

        for ( Num currentNumber : numbers ) {
            sum = sum.add( currentNumber.toBigDecimal() );
        }

        return new Num( sum.divide( BigDecimal.valueOf( numbers.length ) ) );
    }

    @Override
    public int getFunctionAttributes() {
        return 0;
    }
}
