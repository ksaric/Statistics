package hr.logos.stat;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.Comparator;

public class NumberComparatorTest {

    @Test
    public void testCompareLess() throws Exception {
        //Before
        final Comparator<Number> numberComparator = new NumberComparator();

        //When
        final int compare = numberComparator.compare( 10, 9 );

        //Then
        Assert.assertThat( 1, Matchers.equalTo( compare ) );
    }

    @Test
    public void testCompareEquals() throws Exception {
        //Before
        final Comparator<Number> numberComparator = new NumberComparator();

        //When
        final int compare = numberComparator.compare( 9, 9 );

        //Then
        Assert.assertThat( 0, Matchers.equalTo( compare ) );
    }

    @Test
    public void testCompareMore() throws Exception {
        //Before
        final Comparator<Number> numberComparator = new NumberComparator();

        //When
        final int compare = numberComparator.compare( 9, 10 );

        //Then
        Assert.assertThat( -1, Matchers.equalTo( compare ) );
    }
}