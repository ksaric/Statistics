package hr.logos.stat;

import com.google.common.collect.*;
import hr.logos.functions.StandardDeviation;
import org.hamcrest.Matchers;
import org.jdice.calc.Calculator;
import org.jdice.calc.Num;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;

public class VarianceComputationTest {

    @Test
    public void testSimpleStandardDeviationExpression() throws Exception {
        //When
        final Num calculatedNumber = Calculator.builder()
                .use( StandardDeviation.class )
                .expression( "StdDev (87, 90, 92, 90, 86, 91, 91, 89, 94, 90)" )
                .calculate();

        //Then
        final BigDecimal resultAmount = calculatedNumber.toBigDecimal();

        Assert.assertThat( resultAmount, equalTo( BigDecimal.valueOf( 5.333 ) ) );
    }

    @Test
    public void testSimpleStandardDeviation() throws Exception {
        //Before
        final List<Integer> currentIntegers = Lists.newArrayList( 87, 90, 92, 90, 86, 91, 91, 89, 94, 90 );

        //When
        final Result sumCompute = ComputationsFactory.newVarianceComputation().compute( currentIntegers );

        //Then
        final BigDecimal resultAmount = sumCompute.getAmount();
        Assert.assertThat( scaleBD( BigDecimal.valueOf( 5.333 ) ), Matchers.equalTo( scaleBD( resultAmount ) ) );
    }

    private static BigDecimal scaleBD( final BigDecimal bigDecimal ) {
        return bigDecimal.setScale( 3, BigDecimal.ROUND_HALF_UP ).stripTrailingZeros();
    }

}