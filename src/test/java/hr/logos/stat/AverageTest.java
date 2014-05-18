package hr.logos.stat;

import com.google.common.collect.*;
import hr.logos.common.ResultValue;
import hr.logos.stat.generators.IntegerListGenerator;
import hr.logos.stat.generators.NumberOfRuns;
import net.java.quickcheck.generator.iterable.Iterables;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
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
    public void testSimpleAverageInteger() throws Exception {
        //Before
        final List<Integer> integers = Lists.newArrayList( 3, 5, 8, 9, 4 );

        Computations computations = ComputationsFactory.newSimpleAverageComputation();

        //When
        final Result result = computations.compute( Lists.newArrayList( integers ) );

        //Then
        Assert.assertThat(new ResultValue( 5.8 ).getAmount(), equalTo( result.getAmount() ) );
    }

    @Test
    public void testSimpleAverageIntegerZeroNumbers() throws Exception {
        //Before
        final List<Integer> integers = Lists.newArrayList();

        Computations computations = ComputationsFactory.newSimpleAverageComputation();

        //When
        final Result result = computations.compute( Lists.newArrayList( integers ) );

        //Then
        Assert.assertThat( new ResultValue( 0 ).getAmount(), equalTo( result.getAmount() ) );
    }

    @Test
    public void testSimpleAverageIntegerPropertyNumbers() throws Exception {
        for ( final List<Integer> currentIntegers : Iterables.toIterable( IntegerListGenerator.INTEGER_LIST_GENERATOR, NUM_OF_RUNS ) ) {

            //When
            final Result result = ComputationsFactory.newSimpleAverageComputation().compute( currentIntegers );
            final Result sumCompute = ComputationsFactory.newSumComputation().compute( currentIntegers );

            final ResultValue originalNumber = new ResultValue( result.getAmount() )
                    .multiply( new ResultValue( currentIntegers.size() ) );

            //Then
            Assert.assertNotNull( result.getAmount() );
            Assert.assertThat( scaleBD( sumCompute.getAmount() ), equalTo( scaleBD( originalNumber.getAmount() ) ) );
        }
    }

    private static BigDecimal scaleBD( final BigDecimal bigDecimal ) {
        return bigDecimal.setScale( 8, BigDecimal.ROUND_HALF_UP ).stripTrailingZeros();
    }

}
