package hr.logos.stat;

import com.google.common.collect.*;
import hr.logos.common.ResultValue;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;

public class CoefficientOfVariationComputationTest {

    protected final Log logger = LogFactory.getLog( getClass() );

    @Test
    public void testSimpleCoefficientOfVariationEmptyList() throws Exception {
        //Before
        final List<Integer> numbers = Lists.newArrayList();

        //When
        final Result medianCompute = ComputationsFactory.newCoefficientOfVariationComputation()
                .compute( numbers );

        //Then
        Assert.assertThat( medianCompute.getAmount(), equalTo( ResultValue.ZERO.getAmount() ) );
    }

    @Test
    public void testSimpleCoefficientOfVariationList() throws Exception {
        //Before
        final List<Integer> numbers = Lists.newArrayList( 87, 90, 92, 90, 86, 91, 91, 89, 94, 90 );

        //When
        final Result medianCompute = ComputationsFactory.newCoefficientOfVariationComputation()
                .compute( numbers );

        //Then
        Assert.assertThat( medianCompute.getAmount(), equalTo( new ResultValue( 0.0257 ).getAmount() ) );
    }

}