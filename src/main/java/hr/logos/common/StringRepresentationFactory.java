package hr.logos.common;

import java.util.List;

/**
 * @author ksaric, pfh (Kristijan Šarić)
 */

public class StringRepresentationFactory {

    private StringRepresentationFactory() {
    }

    public static StringRepresentation<List<? extends Number>> newSumStringRepresentation() {
        return new SumStringRepresenetation();
    }

    public static StringRepresentation<List<? extends Number>> newAvgStringRepresentation() {
        return new AvgStringRepresenetation();
    }

    public static StringRepresentation<List<? extends Number>> newModStringRepresentation() {
        return new ModStringRepresenetation();
    }

}
