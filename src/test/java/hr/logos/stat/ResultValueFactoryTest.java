package hr.logos.stat;

import hr.logos.common.ResultValue;
import org.junit.Test;
import org.testng.Assert;

import java.math.BigDecimal;

public class ResultValueFactoryTest {

    @Test
    public void test() throws Exception {
        //Before
        final ResultValueFactory resultValue = new DefaultResultValueFactory();

        //When
        final ResultValue stringResultValue = resultValue.create( "10" );
        final ResultValue doubleResultValue = resultValue.create( (double) 10 );
        final ResultValue longResultValue = resultValue.create( (long) 10 );
        final ResultValue integerResultValue = resultValue.create( 10 );
        final ResultValue bigDecimalResultValue = resultValue.create( new BigDecimal( 10 ) );

        //Then
        Assert.assertEquals( ResultValue.TEN, stringResultValue );
        Assert.assertEquals( ResultValue.TEN, doubleResultValue );
        Assert.assertEquals( ResultValue.TEN, longResultValue );
        Assert.assertEquals( ResultValue.TEN, integerResultValue );
        Assert.assertEquals( ResultValue.TEN, bigDecimalResultValue );
    }


}