package hr.logos.common;

import hr.logos.stat.Result;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class ResultValue implements Comparable<ResultValue>, Serializable, Result {

    private static final RoundingMode DEFAULT_ROUNDING = RoundingMode.HALF_UP;
    private static final MathContext DEFAULT_MATH_CONTEXT = MathContext.DECIMAL128;

    public final int DEFAULT_SCALE = 10;

    public static final ResultValue ZERO = new ResultValue( 0 );
    public static final ResultValue ONE = new ResultValue( 1 );
    public static final ResultValue TWO = new ResultValue( 2 );
    public static final ResultValue TEN = new ResultValue( 10 );

    private BigDecimal amount;

    public ResultValue( final BigDecimal amount ) {
        this( amount, DEFAULT_ROUNDING );
    }

    public ResultValue( final String amount ) {
        this( new BigDecimal( amount ), DEFAULT_ROUNDING );
    }

    public ResultValue( final Double amount ) {
        this( new BigDecimal( amount ), DEFAULT_ROUNDING );
    }

    public ResultValue( final Long amount ) {
        this( new BigDecimal( amount ), DEFAULT_ROUNDING );
    }

    public ResultValue( final Integer amount ) {
        this( new BigDecimal( amount ), DEFAULT_ROUNDING );
    }

    ResultValue( final BigDecimal amount, final RoundingMode rounding ) {
        this.amount = amount;
        this.amount = amount.setScale( DEFAULT_SCALE, rounding );
    }

    public ResultValue abs() {
        return new ResultValue( this.getAmount().abs( DEFAULT_MATH_CONTEXT ) );
    }

    public ResultValue negate() {
        return new ResultValue( this.getAmount().negate( DEFAULT_MATH_CONTEXT ) );
    }

    public ResultValue add( final ResultValue resultValue ) {
        return new ResultValue( this.getAmount().add( resultValue.getAmount(), DEFAULT_MATH_CONTEXT ) );
    }

    public ResultValue subtract( final ResultValue resultValue ) {
        return new ResultValue( this.getAmount().subtract( resultValue.getAmount(), DEFAULT_MATH_CONTEXT ) );
    }

    public ResultValue divide( final ResultValue resultValue ) {
        return new ResultValue( this.getAmount().divide( resultValue.getAmount(), DEFAULT_MATH_CONTEXT ) );
    }

    public ResultValue multiply( final ResultValue resultValue ) {
        return new ResultValue( this.getAmount().multiply( resultValue.getAmount(), DEFAULT_MATH_CONTEXT ) );
    }

    @Override
    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return getAmount().toString();
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;

        ResultValue resultValue = (ResultValue) o;

        if ( !amount.equals( resultValue.amount ) ) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return amount.hashCode();
    }

    /**
     * @param resultValue
     * @return
     */

    @Override
    public int compareTo( final ResultValue resultValue ) {
        return this.getAmount().compareTo( resultValue.getAmount() );
    }
}