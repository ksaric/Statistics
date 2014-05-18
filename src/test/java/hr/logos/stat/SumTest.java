package hr.logos.stat;

import com.google.common.collect.*;
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
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

/**
 * @author ksaric, pfh (Kristijan Šarić)
 */

public class SumTest {

    public static final int NUM_OF_RUNS = NumberOfRuns.XL.getNumOfRuns();

    protected final Log logger = LogFactory.getLog( getClass() );


    @Test
    public void testSimpleSum() throws Exception {
        //Before
        final List<Integer> currentIntegers = Lists.newArrayList( 1, 2, 3, 4 );

        //When
        final Result sumCompute = ComputationsFactory.newSumComputation().compute( currentIntegers );

        //Then
        final BigDecimal resultAmount = sumCompute.getAmount();

        Assert.assertThat( 0, equalTo( resultAmount.compareTo( BigDecimal.TEN ) ) );
    }

    @Test
    public void testSimpleSumLong() throws Exception {
        //Before
        final List<Long> currentIntegers = Lists.newArrayList( 1l, 2l, 3l, 4l );

        //When
        final Result sumCompute = ComputationsFactory.newSumComputation().compute( currentIntegers );

        //Then
        final BigDecimal resultAmount = sumCompute.getAmount();

        Assert.assertThat( 0, equalTo( resultAmount.compareTo( BigDecimal.TEN ) ) );
    }

    @Test
    public void testSimpleSumFloat() throws Exception {
        //Before
        final List<Float> currentIntegers = Lists.newArrayList( 1f, 2f, 3f, 4f );

        //When
        final Result sumCompute = ComputationsFactory.newSumComputation().compute( currentIntegers );

        //Then
        final BigDecimal resultAmount = sumCompute.getAmount();

        Assert.assertThat( 0, equalTo( resultAmount.compareTo( BigDecimal.TEN ) ) );
    }

    @Test
    public void testSimpleSumDouble() throws Exception {
        //Before
        final List<Double> currentIntegers = Lists.newArrayList( 1d, 2d, 3d, 4d );

        //When
        final Result sumCompute = ComputationsFactory.newSumComputation().compute( currentIntegers );

        //Then
        final BigDecimal resultAmount = sumCompute.getAmount();

        Assert.assertThat( 0, equalTo( resultAmount.compareTo( BigDecimal.TEN ) ) );
    }


    @Test
    public void testSimpleSumGenerator() throws Exception {
        //Before
        for ( final List<Integer> currentIntegers : Iterables.toIterable( IntegerListGenerator.POSITIVE_INTEGER_LIST_GENERATOR, NUM_OF_RUNS ) ) {
            //When
            final Result sumCompute = ComputationsFactory.newSumComputation().compute( currentIntegers );

            //Then
            final BigDecimal result = sumCompute.getAmount();

            Assert.assertThat( result, greaterThanOrEqualTo( BigDecimal.ZERO ) );
        }
    }

}
