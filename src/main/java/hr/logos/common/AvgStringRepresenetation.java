package hr.logos.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * @author ksaric, pfh (Kristijan Šarić)
 */

final class AvgStringRepresenetation implements StringRepresentation<List<? extends Number>> {

    protected final Log logger = LogFactory.getLog( getClass() );

    @Override
    public String respresentString( final List<? extends Number> representable ) {
        final String formattedString = String.format( "AVG %s", representable.toString()
                        .replace( "[", "(" )
                        .replace( "]", ")" )
        );

        logger.info( formattedString );

        return formattedString;
    }
}
