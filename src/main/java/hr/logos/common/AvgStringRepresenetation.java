package hr.logos.common;

import java.util.List;

/**
 * @author ksaric, pfh (Kristijan Šarić)
 */

final class AvgStringRepresenetation extends AbstractNumberListStringRepresentation implements StringRepresentation<List<? extends Number>> {

    public static final String FUNCTION_NAME = "AVG";

    @Override
    public String respresentString( final List<? extends Number> representable ) {
        return getRepresentation( representable );
    }

    @Override
    protected String getFunctionString() {
        return FUNCTION_NAME;
    }
}
