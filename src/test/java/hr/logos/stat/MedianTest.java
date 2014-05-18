package hr.logos.stat;

import com.google.common.collect.*;
import hr.logos.common.ResultValue;
import hr.logos.stat.generators.NumberOfRuns;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;

/**
 * @author ksaric, pfh (Kristijan Šarić)
 */

public class MedianTest {

    public static final int NUM_OF_RUNS = NumberOfRuns.XL.getNumOfRuns();

    protected final Log logger = LogFactory.getLog( getClass() );

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
        final List<Integer> currentIntegers = Lists.newArrayList( 1, 2, 2, 3, 4, 5, 6, 6, 6, 6 );

        //When
        final Result medianCompute = ComputationsFactory.newMedianComputation().compute( currentIntegers );

        //Then
        Assert.assertThat( medianCompute.getAmount(), equalTo( new ResultValue( 4.5 ).getAmount() ) );
    }

}
