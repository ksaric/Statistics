package hr.logos.stat.generators;

/**
 * @author ksaric, pfh (Kristijan Šarić)
 */

public enum NumberOfRuns {

    S( 20 ),

    M( 200 ),

    L( 2000 ),

    XL( 20000 ),

    XXL( 200000 ),

    XXXL( 2000000 ),

    EXHAUSTIVE( 2000000000 );

    private final int numOfRuns;

    NumberOfRuns( int numOfRuns ) {
        this.numOfRuns = numOfRuns;
    }

    public int getNumOfRuns() {
        return numOfRuns;
    }
}
