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
 * <a href=http://en.wikipedia.org/wiki/Quantile>Quantile wiki</a>
 *
 * @author ksaric, pfh (Kristijan Šarić)
 */

public class QuantileTest {

    public static final int NUM_OF_RUNS = NumberOfRuns.XL.getNumOfRuns();

    protected final Log logger = LogFactory.getLog( getClass() );

    // 10×(1/4) = 2.5 => 3, N(3) = 7
    @Test
    public void testSimpleQuantileFirstQuartileEven() throws Exception {
        //Before
        final List<Integer> currentIntegers = Lists.newArrayList( 3, 6, 7, 8, 8, 10, 13, 15, 16, 20 );

        //When
        final Result quantileComputation = ComputationsFactory.newQuantileComputations( 4 ).compute( currentIntegers );

        //Then
        Assert.assertThat( quantileComputation.getAmount(), equalTo( new ResultValue( 7 ).getAmount() ) );
    }

    /*@Test
    public void testListQuantileFirstQuartileEven() throws Exception {
        //Before
        final List<Integer> currentIntegers = Lists.newArrayList( 3, 6, 7, 8, 8, 10, 13, 15, 16, 20 );

        //When
        final List<Result> quantileComputation = ComputationsFactory.newQuantileComputations( 4 ).compute( currentIntegers );

        //Then
        Assert.assertThat( quantileComputation.getAmount(), equalTo( new ResultValue( 7 ).getAmount() ) );
    }*/

    /*@Test
    public void testSimpleQuantileOdd() throws Exception {
        //Before
        final List<Integer> currentIntegers = Lists.newArrayList( 3, 6, 7, 8, 8, 9, 10, 13, 15, 16, 20 );

        //When
        final Result quantileComputation = ComputationsFactory.newQuantileComputations().compute( currentIntegers );

        //Then
        Assert.assertThat( quantileComputation.getAmount(), equalTo( ResultValue.value( 3 ).getAmount() ) );
    }*/

}
