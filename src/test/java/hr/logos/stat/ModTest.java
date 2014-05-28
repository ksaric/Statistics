package hr.logos.stat;

import com.google.common.collect.*;
import hr.logos.common.ResultValue;
import hr.logos.common.StringRepresentation;
import hr.logos.common.StringRepresentationFactory;
import hr.logos.functions.ModFunction;
import hr.logos.stat.generators.NumberOfRuns;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdice.calc.Calculator;
import org.jdice.calc.Num;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;

/**
 * @author ksaric, pfh (Kristijan Šarić)
 */

public class ModTest {

    public static final int NUM_OF_RUNS = NumberOfRuns.XL.getNumOfRuns();

    protected final Log logger = LogFactory.getLog( getClass() );

    @Test
    public void testModExpression() throws Exception {
        //When
        final Num calculatedNumber = Calculator.builder()
                .use( ModFunction.class )
                .expression( "MOD (9, 9, 9, 10, 11, 11)" )
                .calculate();

        //Then
        final BigDecimal resultAmount = calculatedNumber.toBigDecimal();

        Assert.assertThat( 0, equalTo( resultAmount.compareTo( BigDecimal.TEN.subtract( BigDecimal.ONE ) ) ) );
    }

    @Test
    public void testSimpleModZero() throws Exception {
        //Before
        final List<Integer> currentIntegers = Lists.newArrayList();

        //When
        final Result medianCompute = ComputationsFactory.newModComputation().compute( currentIntegers );

        //Then
        Assert.assertThat( medianCompute.getAmount(), equalTo( new ResultValue( 0 ).getAmount() ) );
    }

    @Test
    public void testSimpleModSmallerOccurrence() throws Exception {
        //Before
        final List<Integer> currentIntegers = Lists.newArrayList( 10, 9 );

        //When
        final Result medianCompute = ComputationsFactory.newModComputation().compute( currentIntegers );

        //Then
        Assert.assertThat( medianCompute.getAmount(), equalTo( new ResultValue( 9 ).getAmount() ) );
    }

    @Test
    public void testSimpleModEven1() throws Exception {
        //Before
        final List<Integer> numbers = Lists.newArrayList( 1, 2, 3, 4, 5, 5 );

        //When
        final StringRepresentation<List<? extends Number>> representation =
                StringRepresentationFactory.newModStringRepresentation();

        final String expression = representation.respresentString( numbers );

        final Num calculatedNumber = Calculator.builder()
                .use( ModFunction.class )
                .expression( expression )
                .calculate();

        //Then
        final BigDecimal resultAmount = calculatedNumber.toBigDecimal();

        Assert.assertThat( BigDecimal.valueOf( 5 ), equalTo( resultAmount ) );
    }

    @Test
    public void testSimpleModEven2() throws Exception {
        //Before
        final List<Integer> currentIntegers = Lists.newArrayList( 1, 2, 2, 2, 3, 4, 5, 5 );

        //When
        final Result medianCompute = ComputationsFactory.newModComputation().compute( currentIntegers );

        //Then
        Assert.assertThat( medianCompute.getAmount(), equalTo( new ResultValue( 2 ).getAmount() ) );
    }

    @Test
    public void testSimpleModEvenDouble1() throws Exception {
        //Before
        final List<Double> currentIntegers = Lists.newArrayList( 1d, 1d, 1d, 1d, 2d, 2d, 2d, 3d, 4d, 5d, 5d );

        //When
        final Result medianCompute = ComputationsFactory.newModComputation().compute( currentIntegers );

        //Then
        Assert.assertThat( medianCompute.getAmount(), equalTo( new ResultValue( 1 ).getAmount() ) );
    }

}
