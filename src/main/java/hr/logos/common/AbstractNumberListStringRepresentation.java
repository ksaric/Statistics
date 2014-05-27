package hr.logos.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * @author ksaric, pfh (Kristijan Šarić)
 */

public abstract class AbstractNumberListStringRepresentation implements StringRepresentation<List<? extends Number>> {

    protected final Log logger = LogFactory.getLog( getClass() );

    protected String getRepresentation( List<? extends Number> representable ) {

        // MOD (1, 2, 3, 2, 2)
        // AVG (2, 3, 3)
        final String formattedString = String.format(
                "%s %s",
                getFunctionString(),
                representable.toString()
                        .replace( "[", "(" )
                        .replace( "]", ")" )
        );

        logger.debug( formattedString );

        return formattedString;
    }

    protected abstract String getFunctionString();

}
