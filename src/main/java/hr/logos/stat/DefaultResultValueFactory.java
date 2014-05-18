package hr.logos.stat;

import hr.logos.common.ResultValue;

import java.math.BigDecimal;

/**
 * @author ksaric, pfh (Kristijan Šarić)
 */

public class DefaultResultValueFactory implements ResultValueFactory {

    @Override
    public ResultValue create( final String amount ) {
        return new ResultValue( amount );
    }

    @Override
    public ResultValue create( final BigDecimal amount ) {
        return new ResultValue( amount );
    }

    @Override
    public ResultValue create( final Double amount ) {
        return new ResultValue( amount );
    }

    @Override
    public ResultValue create( final Long amount ) {
        return new ResultValue( amount );
    }

    @Override
    public ResultValue create( final Integer amount ) {
        return new ResultValue( amount );
    }
}
