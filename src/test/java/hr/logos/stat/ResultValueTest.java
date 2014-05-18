package hr.logos.stat;

import hr.logos.common.ResultValue;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author ksaric
 */

public class ResultValueTest {

    @Test
    public void testAbs() throws Exception {
        //Before
        final ResultValue resultValue = new ResultValue( -10 );

        //When
        final ResultValue abs = resultValue.abs();

        //Then
        Assert.assertThat( ResultValue.TEN, Matchers.equalTo( abs ) );
    }

    @Test
    public void testNegate() throws Exception {
        //Before
        final ResultValue resultValue = ResultValue.TEN;

        //When
        final ResultValue negate = resultValue.negate();

        //Then
        Assert.assertThat( new ResultValue( -10 ), Matchers.equalTo( negate ) );
    }

    @Test
    public void testAdd() throws Exception {
        //Before
        final ResultValue resultValue = ResultValue.TEN;

        //When
        final ResultValue addedValue = resultValue.add( ResultValue.ONE );

        //Then
        Assert.assertThat( new ResultValue( 11 ), Matchers.equalTo( addedValue ) );
    }


    @Test
    public void testSubtract() throws Exception {
        //Before
        final ResultValue resultValue = ResultValue.TEN;

        //When
        final ResultValue subtractedValue = resultValue.subtract( ResultValue.ONE );

        //Then
        Assert.assertThat( new ResultValue( 9 ), Matchers.equalTo( subtractedValue ) );
    }

    @Test
    public void testEqualsTrue() throws Exception {
        //Before
        final ResultValue resultValue = ResultValue.TEN;

        //When
        final boolean equals = resultValue.equals( ResultValue.TEN );

        //Then
        Assert.assertThat( equals, Matchers.equalTo( true ) );
    }

    @Test
    public void testCompareToTrue() throws Exception {
        //Before
        final ResultValue resultValue = ResultValue.TEN;

        //When
        final int compareTo = resultValue.compareTo( ResultValue.TEN );

        //Then
        Assert.assertThat( compareTo, Matchers.equalTo( 0 ) );
    }

    @Test
    public void testEqualsFalse() throws Exception {
        //Before
        final ResultValue resultValue = ResultValue.TEN;

        //When
        final boolean equals = resultValue.equals( ResultValue.ONE );

        //Then
        Assert.assertThat( equals, Matchers.equalTo( false ) );
    }

    @Test
    public void testCompareToFalse() throws Exception {
        //Before
        final ResultValue resultValue = ResultValue.TEN;

        //When
        final int compareTo = resultValue.compareTo( ResultValue.ONE );

        //Then
        Assert.assertThat( compareTo, Matchers.equalTo( 1 ) );
    }

    @Test
    public void testEqualsNull() throws Exception {
        //Before
        final ResultValue resultValue = ResultValue.TEN;

        //When
        final boolean equals = resultValue.equals( null );

        //Then
        Assert.assertThat( equals, Matchers.equalTo( false ) );
    }

    @Test
    public void testEqualsHashCodeTrue() throws Exception {
        //Before
        final ResultValue resultValue = ResultValue.TEN;

        //When
        final int hashCode = resultValue.hashCode();

        //Then
        Assert.assertThat( hashCode, Matchers.equalTo( new ResultValue( 10 ).hashCode() ) );
    }

    @Test
    public void testEqualsHashCodeFalse() throws Exception {
        //Before
        final ResultValue resultValue = ResultValue.TEN;

        //When
        final int hashCode = resultValue.hashCode();

        //Then
        Assert.assertThat( hashCode, Matchers.not( new ResultValue( 9 ).hashCode() ) );
    }


    @Test
    public void testToString() throws Exception {
        //Before
        final ResultValue resultValue = ResultValue.TEN;

        //When
        final String toString = resultValue.toString();

        //Then
        Assert.assertThat( toString, Matchers.equalTo( "10.0000000000" ) );
    }
}