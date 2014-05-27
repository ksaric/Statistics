package hr.logos.stat;

import com.google.common.collect.*;
import hr.logos.common.ResultValue;
import hr.logos.common.StringRepresentation;
import hr.logos.common.StringRepresentationFactory;
import hr.logos.functions.AvgFunction;
import hr.logos.stat.generators.IntegerListGenerator;
import hr.logos.stat.generators.NumberOfRuns;
import net.java.quickcheck.generator.iterable.Iterables;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdice.calc.Calculator;
import org.jdice.calc.Num;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;

/**
 * @author ksaric, pfh (Kristijan Šarić)
 */

public class AverageTest {

    public static final int NUM_OF_RUNS = NumberOfRuns.XL.getNumOfRuns();

    protected final Log logger = LogFactory.getLog( getClass() );

    @Test
    public void testSimpleAverageIntegerExpression() throws Exception {
        //When
        final Num calculatedNumber = Calculator.builder()
                .use( AvgFunction.class )
                .expression( "AVG (9, 10, 11)" )
                .calculate();

        //Then
        final BigDecimal resultAmount = calculatedNumber.toBigDecimal();

        Assert.assertThat( 0, equalTo( resultAmount.compareTo( BigDecimal.TEN ) ) );
    }

    @Test
    public void testSimpleAverageInteger() throws Exception {
        //Before
        final List<Integer> numbers = Lists.newArrayList( 3, 5, 8, 9, 4 );

        final StringRepresentation<List<? extends Number>> representation =
                StringRepresentationFactory.newAvgStringRepresentation();

        final String expression = representation.respresentString( numbers );

        final Num calculatedNumber = Calculator.builder()
                .use( AvgFunction.class )
                .expression( expression )
                .calculate();

        //Then
        final BigDecimal resultAmount = calculatedNumber.toBigDecimal();

        Assert.assertThat( 0, equalTo( resultAmount.compareTo( BigDecimal.valueOf( 5.8 ) ) ) );
    }

    @Ignore
    @Test
    public void testSimpleAverageIntegerZeroNumbers() throws Exception {
        //Before
        final List<Integer> integers = Lists.newArrayList();

        Computation computation = ComputationsFactory.newSimpleAverageComputation();

        //When
        final Result result = computation.compute( Lists.newArrayList( integers ) );

        //Then
        Assert.assertThat( new ResultValue( 0 ).getAmount(), equalTo( result.getAmount() ) );
    }

    @Ignore
    @Test
    public void testSimpleAverageIntegerPropertyNumbers() throws Exception {
        for ( final List<Integer> currentIntegers : Iterables.toIterable( IntegerListGenerator.INTEGER_LIST_GENERATOR, NUM_OF_RUNS ) ) {

            //Before
            final StringRepresentation<List<? extends Number>> representation =
                    StringRepresentationFactory.newAvgStringRepresentation();

            final String expression = representation.respresentString( currentIntegers );

            final Num calculatedNumber = Calculator.builder()
                    .use( AvgFunction.class )
                    .expression( expression )
                    .calculate();

            //Then
            final BigDecimal resultAmount = calculatedNumber.toBigDecimal();

            Assert.assertThat( 0, equalTo( scaleBD( resultAmount ).compareTo(
                            scaleBD( calculatedNumber.toBigDecimal().multiply( BigDecimal.valueOf( currentIntegers.size() ) ) ) ) )
            );
        }
    }

    private static BigDecimal scaleBD( final BigDecimal bigDecimal ) {
        return bigDecimal.setScale( 3, BigDecimal.ROUND_HALF_UP ).stripTrailingZeros();
    }

}
