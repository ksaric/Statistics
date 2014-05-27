package hr.logos.functions;

import org.jdice.calc.AbstractCalculator;
import org.jdice.calc.Function;
import org.jdice.calc.Num;
import org.jdice.calc.SingletonExtension;

import java.math.BigDecimal;

@SingletonExtension
public class SumFunction implements Function {

    @Override
    public String getSymbol() {
        return "SUM";
    }

    @Override
    public Num calc( final AbstractCalculator abstractCalculator, final Num... numbers ) throws Exception {

        BigDecimal sum = BigDecimal.ZERO;

        for ( Num currentNumber : numbers ) {
            sum = sum.add( currentNumber.toBigDecimal() );
        }

        return new Num( sum );
    }

    @Override
    public int getFunctionAttributes() {
        return 0;
    }
}