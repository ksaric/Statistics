package hr.logos.stat;

import java.util.List;

/**
 * @author ksaric, pfh (Kristijan Šarić)
 */

public interface Computation {

    Result compute( final List<? extends Number> numbers );
//    <T extends Number & List<Number>> Result<T> compute( final List<? extends Number> numbers );

}
