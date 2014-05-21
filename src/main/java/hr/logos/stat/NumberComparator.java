package hr.logos.stat;

import java.util.Comparator;

/**
 * @author ksaric, pfh (Kristijan Šarić)
 */

final class NumberComparator implements Comparator<Number> {

    @Override
    public int compare( final Number number1, final Number number2 ) {
        // for all different Number types, let's check there double values
        if ( number1.doubleValue() < number2.doubleValue() ) {
            return -1;
        }

        if ( number1.doubleValue() > number2.doubleValue() ) {
            return 1;
        }

        return 0;
    }
}
