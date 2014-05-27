package hr.logos.stat;

import com.google.common.collect.*;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class StandardDeviationComputationTest {

    @Test
    public void testSimpleStandardDeviation() throws Exception {
        //Before
        final List<Integer> currentIntegers = Lists.newArrayList( 87, 90, 92, 90, 86, 91, 91, 89, 94, 90 );

        //When
        final Result sumCompute = ComputationsFactory.newStandardDeviationComputation().compute( currentIntegers );

        //Then
        final BigDecimal resultAmount = sumCompute.getAmount();
        Assert.assertThat( scaleBD( BigDecimal.valueOf( 2.309 ) ), Matchers.equalTo( scaleBD( resultAmount ) ) );
    }

    private static BigDecimal scaleBD( final BigDecimal bigDecimal ) {
        return bigDecimal.setScale( 3, BigDecimal.ROUND_HALF_UP ).stripTrailingZeros();
    }
}