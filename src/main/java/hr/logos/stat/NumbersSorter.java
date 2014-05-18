package hr.logos.stat;

import java.util.List;

/**
 * @author ksaric, pfh (Kristijan Šarić)
 */

public interface NumbersSorter {

    List<? extends Number> sort( List<? extends Number> numbers );
}
