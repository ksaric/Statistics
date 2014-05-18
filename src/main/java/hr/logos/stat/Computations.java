package hr.logos.stat;

import java.util.List;

/**
 * @author ksaric, pfh (Kristijan Šarić)
 */

public interface Computations {

    Result compute( final List<? extends Number> numbers );

}
