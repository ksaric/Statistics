package hr.logos.functions;

import org.jdice.calc.AbstractCalculator;
import org.jdice.calc.Function;
import org.jdice.calc.Num;

/**
 * @author ksaric, pfh (Kristijan Šarić)
 */

public class StandardDeviation implements Function {

    @Override
    public String getSymbol() {
        return "StdDev";
    }

    @Override
    public Num calc( final AbstractCalculator abstractCalculator, final Num... numbers ) throws Exception {
        return new Num( 0 );
    }

    @Override
    public int getFunctionAttributes() {
        return 0;
    }

}
