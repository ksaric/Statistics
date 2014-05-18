package hr.logos.stat;

import com.google.common.collect.*;

import java.util.Comparator;
import java.util.List;

import static com.google.common.base.Preconditions.*;

/**
 * @author ksaric, pfh (Kristijan Šarić)
 */

public class DefaultNumbersSorter implements NumbersSorter {

    /* sort the numbers */
    private final Comparator<Number> numberComparator;

    public DefaultNumbersSorter( final Comparator<Number> numberComparator ) {
        this.numberComparator = checkNotNull( numberComparator );
    }

    @Override
    public List<? extends Number> sort( final List<? extends Number> numbers ) {
        return sortNumbers( numbers );
    }

    private List<? extends Number> sortNumbers( List<? extends Number> numbers ) {
        final Ordering<Number> ordering = getOrdering();
        return ordering.sortedCopy( numbers );
    }

    Ordering<Number> getOrdering() {
        return Ordering.from( numberComparator );
    }

}
