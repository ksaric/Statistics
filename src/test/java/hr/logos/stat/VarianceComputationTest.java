package hr.logos.stat;

import com.google.common.collect.*;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class VarianceComputationTest {

    @Test
    public void testSimpleStandardDeviation() throws Exception {
        //Before
        final List<Integer> currentIntegers = Lists.newArrayList( 2, 4, 4, 4, 5, 5, 7, 9 );

        //When
        final Result sumCompute = ComputationsFactory.newVarianceComputation().compute( currentIntegers );

        //Then
        final BigDecimal resultAmount = sumCompute.getAmount();
        Assert.assertThat( scaleBD( BigDecimal.valueOf( 4 ) ), Matchers.equalTo( scaleBD( resultAmount ) ) );
    }

    private static BigDecimal scaleBD( final BigDecimal bigDecimal ) {
        return bigDecimal.setScale( 8, BigDecimal.ROUND_HALF_UP ).stripTrailingZeros();
    }

}