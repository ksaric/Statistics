package hr.logos.stat;

import hr.logos.common.ResultValue;

import java.math.BigDecimal;

/**
 * Added level of abstraction, so the future substition is easy and flexibile.
 *
 * @author ksaric, pfh (Kristijan Šarić)
 */

public interface ResultValueFactory {

    ResultValue create( final String amount );

    ResultValue create( final BigDecimal amount );

    ResultValue create( final Double amount );

    ResultValue create( final Long amount );

    ResultValue create( final Integer amount );

}
