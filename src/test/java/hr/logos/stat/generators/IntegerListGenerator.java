package hr.logos.stat.generators;

import com.google.common.collect.*;
import net.java.quickcheck.Generator;
import net.java.quickcheck.generator.PrimitiveGenerators;

import java.util.List;
import java.util.Random;

/**
 * @author ksaric, pfh (Kristijan Šarić)
 */

public enum IntegerListGenerator implements Generator<List<Integer>> {
    INTEGER_LIST_GENERATOR {

        private Generator<Integer> generatedIntegers = PrimitiveGenerators.integers();

        @Override
        public List<Integer> next() {
            final Random random = new Random( System.currentTimeMillis() );

            final List<Integer> integers = Lists.newLinkedList();

            for ( int counter = 0; counter < random.nextInt( 50 ); counter++ ) {
                integers.add( generatedIntegers.next() );
            }

            return integers;
        }
    },
    POSITIVE_INTEGER_LIST_GENERATOR {

        private Generator<Integer> generatedPositiveIntegers = PrimitiveGenerators.positiveIntegers();

        @Override
        public List<Integer> next() {
            final Random random = new Random( System.currentTimeMillis() );

            final List<Integer> integers = Lists.newLinkedList();

            for ( int counter = 0; counter < random.nextInt( 50 ); counter++ ) {
                integers.add( generatedPositiveIntegers.next() );
            }

            return integers;
        }
    }

}
