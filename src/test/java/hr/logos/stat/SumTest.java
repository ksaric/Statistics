package hr.logos.stat;

import com.google.common.collect.*;
import hr.logos.common.StringRepresentation;
import hr.logos.common.StringRepresentationFactory;
import hr.logos.functions.SumFunction;
import hr.logos.stat.generators.IntegerListGenerator;
import hr.logos.stat.generators.NumberOfRuns;
import net.java.quickcheck.generator.iterable.Iterables;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdice.calc.Calculator;
import org.jdice.calc.Num;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

/**
 * @author ksaric, pfh (Kristijan Šarić)
 */

public class SumTest {

    public static final int NUM_OF_RUNS = NumberOfRuns.M.getNumOfRuns();

    protected final Log logger = LogFactory.getLog( getClass() );

    @Test
    public void testSimpleSum() throws Exception {
        //When
        final Num calculatedNumber = Calculator.builder()
                .use( SumFunction.class )
                .expression( "SUM(1, 2, 3, 4)" )
                .calculate();

        //Then
        final BigDecimal resultAmount = calculatedNumber.toBigDecimal();

        Assert.assertThat( 0, equalTo( resultAmount.compareTo( BigDecimal.TEN ) ) );
    }

    @Test
    public void testSimpleSumExpression() throws Exception {
        //Before
        final List<Integer> currentIntegers = Lists.newArrayList( 1, 2, 3, 4 );

        //When
        final StringRepresentation<List<? extends Number>> stringRepresentation =
                StringRepresentationFactory.newSumStringRepresentation();

        final String expression = stringRepresentation.respresentString( currentIntegers );

        final Num calculatedNumber = Calculator.builder()
                .use( SumFunction.class )
                .expression( expression )
                .calculate();

        //Then
        final BigDecimal resultAmount = calculatedNumber.toBigDecimal();

        Assert.assertThat( 0, equalTo( resultAmount.compareTo( BigDecimal.TEN ) ) );
    }

    @Test
    public void testSimpleSumLong() throws Exception {
        //Before
        final List<Long> currentIntegers = Lists.newArrayList( 1l, 2l, 3l, 4l );

        //When
        final StringRepresentation<List<? extends Number>> stringRepresentation =
                StringRepresentationFactory.newSumStringRepresentation();

        final String expression = stringRepresentation.respresentString( currentIntegers );

        final Num calculatedNumber = Calculator.builder()
                .use( SumFunction.class )
                .expression( expression )
                .calculate();

        //Then
        final BigDecimal resultAmount = calculatedNumber.toBigDecimal();

        Assert.assertThat( 0, equalTo( resultAmount.compareTo( BigDecimal.TEN ) ) );
    }

    @Test
    public void testSimpleSumFloat() throws Exception {
        //Before
        final List<Float> currentIntegers = Lists.newArrayList( 1f, 2f, 3f, 4f );

        //When
        final StringRepresentation<List<? extends Number>> stringRepresentation =
                StringRepresentationFactory.newSumStringRepresentation();

        final String expression = stringRepresentation.respresentString( currentIntegers );

        final Num calculatedNumber = Calculator.builder()
                .use( SumFunction.class )
                .expression( expression )
                .calculate();

        //Then
        final BigDecimal resultAmount = calculatedNumber.toBigDecimal();

        Assert.assertThat( 0, equalTo( resultAmount.compareTo( BigDecimal.TEN ) ) );
    }

    @Test
    public void testSimpleSumDouble() throws Exception {
        //Before
        final List<Double> currentIntegers = Lists.newArrayList( 1d, 2d, 3d, 4d );

        //When
        final StringRepresentation<List<? extends Number>> stringRepresentation =
                StringRepresentationFactory.newSumStringRepresentation();

        final String expression = stringRepresentation.respresentString( currentIntegers );

        final Num calculatedNumber = Calculator.builder()
                .use( SumFunction.class )
                .expression( expression )
                .calculate();

        //Then
        final BigDecimal resultAmount = calculatedNumber.toBigDecimal();

        Assert.assertThat( 0, equalTo( resultAmount.compareTo( BigDecimal.TEN ) ) );
    }

    // todo: sum() not working, no support for no-arg. Error message should be more clear than:
    // java.text.ParseException: Exception while parsing 'sum()'. Can't find extension 'sum' or org.jdice.calc.Num with name 'sum'

    @Test
    public void testSimpleSumGenerator() throws Exception {
        //Before
        for ( final List<Integer> currentIntegers : Iterables.toIterable( IntegerListGenerator.POSITIVE_INTEGER_LIST_GENERATOR, NUM_OF_RUNS ) ) {
            //When
            final StringRepresentation<List<? extends Number>> stringRepresentation =
                    StringRepresentationFactory.newSumStringRepresentation();

            final String expression = stringRepresentation.respresentString( currentIntegers );

            final Num calculatedNumber = Calculator.builder()
                    .use( SumFunction.class )
                    .expression( expression )
                    .calculate();

            //Then
            final BigDecimal resultAmount = calculatedNumber.toBigDecimal();

            Assert.assertThat( resultAmount, greaterThanOrEqualTo( BigDecimal.ZERO ) );
        }
    }
}
