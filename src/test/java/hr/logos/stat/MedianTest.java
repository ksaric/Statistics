package hr.logos.stat;

import com.google.common.collect.*;
import hr.logos.common.ResultValue;
import hr.logos.common.StringRepresentation;
import hr.logos.common.StringRepresentationFactory;
import hr.logos.functions.MedianFunction;
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

public class MedianTest {

    public static final int NUM_OF_RUNS = NumberOfRuns.XL.getNumOfRuns();

    protected final Log logger = LogFactory.getLog( getClass() );

    @Test
    public void testSimpleMedianExpression1() throws Exception {
        //When
        final Num calculatedNumber = Calculator.builder()
                .use( MedianFunction.class )
                .expression( "MEDIAN (1, 2, 3, 4, 5)" )
                .calculate();

        //Then
        final BigDecimal resultAmount = calculatedNumber.toBigDecimal();

        Assert.assertThat( resultAmount, equalTo( BigDecimal.valueOf( 3 ) ) );
    }

    @Test
    public void testSimpleMedianExpression2() throws Exception {
        //When
        final Num calculatedNumber = Calculator.builder()
                .use( MedianFunction.class )
                .expression( "MEDIAN (1, 2, 2, 3, 4, 5, 6, 6, 6)" )
                .calculate();

        //Then
        final BigDecimal resultAmount = calculatedNumber.toBigDecimal();

        Assert.assertThat( resultAmount, equalTo( BigDecimal.valueOf( 4 ) ) );

    }

    @Test
    public void testSimpleMedianExpression3() throws Exception {
        //When
        final Num calculatedNumber = Calculator.builder()
                .use( MedianFunction.class )
                .expression( "MEDIAN (1, 2, 3, 4, 5, 5)" )
                .calculate();

        //Then
        final BigDecimal resultAmount = calculatedNumber.toBigDecimal();

        Assert.assertThat( resultAmount, equalTo( BigDecimal.valueOf( 3.5 ) ) );

    }

    @Test
    public void testSimpleMedianExpression4() throws Exception {
        //When
        final Num calculatedNumber = Calculator.builder()
                .use( MedianFunction.class )
                .expression( "MEDIAN (1, 2, 3, 4, 5, 5)" )
                .calculate();

        //Then
        final BigDecimal resultAmount = calculatedNumber.toBigDecimal();

        Assert.assertThat( resultAmount, equalTo( BigDecimal.valueOf( 3.5 ) ) );

    }

    @Test
    public void testSimpleMedianExpression5() throws Exception {
        //When
        final Num calculatedNumber = Calculator.builder()
                .use( MedianFunction.class )
                .expression( "MEDIAN (1, 2, 2, 3, 4, 5, 6, 6, 6, 6)" )
                .calculate();

        //Then
        final BigDecimal resultAmount = calculatedNumber.toBigDecimal();

        Assert.assertThat( resultAmount, equalTo( BigDecimal.valueOf( 4.5 ) ) );

    }

    @Test
    public void testSimpleMedianEmptyList() throws Exception {
        //Before
        final List<Integer> currentIntegers = Lists.newArrayList();

        //When
        final Result medianCompute = ComputationsFactory.newMedianComputation().compute( currentIntegers );

        //Then
        Assert.assertThat( medianCompute.getAmount(), equalTo( ResultValue.ZERO.getAmount() ) );
    }

    @Test
    public void testSimpleMedianOdd1() throws Exception {
        //Before
        final List<Integer> currentIntegers = Lists.newArrayList( 1, 2, 3, 4, 5 );

        //When
        final Result medianCompute = ComputationsFactory.newMedianComputation().compute( currentIntegers );

        //Then
        Assert.assertThat( medianCompute.getAmount(), equalTo( new ResultValue( 3 ).getAmount() ) );
    }

    @Test
    public void testSimpleMedianOdd2() throws Exception {
        //Before
        final List<Integer> currentIntegers = Lists.newArrayList( 1, 2, 2, 3, 4, 5, 6, 6, 6 );

        //When
        final Result medianCompute = ComputationsFactory.newMedianComputation().compute( currentIntegers );

        //Then
        Assert.assertThat( medianCompute.getAmount(), equalTo( new ResultValue( 4 ).getAmount() ) );
    }

    @Test
    public void testSimpleMedianEven1() throws Exception {
        //Before
        final List<Integer> currentIntegers = Lists.newArrayList( 1, 2, 3, 4, 5, 5 );

        //When
        final Result medianCompute = ComputationsFactory.newMedianComputation().compute( currentIntegers );

        //Then
        Assert.assertThat( medianCompute.getAmount(), equalTo( new ResultValue( 3.5 ).getAmount() ) );
    }

    @Test
    public void testSimpleMedianEven2() throws Exception {
        //Before
        final List<Integer> numbers = Lists.newArrayList( 1, 2, 2, 3, 4, 5, 6, 6, 6, 6 );

        //When
        final StringRepresentation<List<? extends Number>> representation =
                StringRepresentationFactory.newMedianStringRepresentation();

        final String expression = representation.respresentString( numbers );

        final Num calculatedNumber = Calculator.builder()
                .use( MedianFunction.class )
                .expression( expression )
                .calculate();

        //Then
        final BigDecimal resultAmount = calculatedNumber.toBigDecimal();

        Assert.assertThat( resultAmount, equalTo( BigDecimal.valueOf( 4.5 ) ) );
    }

}
