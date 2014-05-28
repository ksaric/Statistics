package hr.logos.common;

import java.util.List;

/**
 * @author ksaric, pfh (Kristijan Šarić)
 */

final class MedianStringRepresenetation extends AbstractNumberListStringRepresentation {

    @Override
    protected String getFunctionString() {
        return "MEDIAN";
    }

    @Override
    public String respresentString( final List<? extends Number> representable ) {
        return getRepresentation( representable );
    }
}
