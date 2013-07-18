package com.ldbc.driver.dshini;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.matchers.JUnitMatchers.*;

import com.ldbc.driver.Operation;
import com.ldbc.driver.dshini.operations.Dshini;
import com.ldbc.driver.dshini.operations.Dshini.ReadWrite;
import com.ldbc.driver.generator.Generator;
import com.ldbc.driver.generator.GeneratorException;
import com.ldbc.driver.generator.wrapper.OrderedMultiGeneratorWrapper;
import com.ldbc.driver.util.Bucket.DiscreteBucket;
import com.ldbc.driver.util.temporal.Time;
import com.ldbc.driver.util.Histogram;

// TODO uncomment to run full tests
//@Ignore
public class RequestLogGeneratorSlowTest
{
    @Test
    public void shouldRecognizeAllOperations()
    {
        // Given
        OrderedMultiGeneratorWrapper<Operation<?>> requestLogGenerator = OrderedMultiGeneratorWrapper.operationsByScheduledStartTime(
                1, TestUtils.allLogGenerators() );

        // When
        Histogram<String, Long> distribution = initDistribution();

        // Will error if Operation encountered that is not in initDistribution
        distribution.importValueSequence( new ClassNameGeneratorWrapper( requestLogGenerator ) );

        System.out.println( distribution.toPrettyString() );

        // Then
    }

    @Test
    public void shouldReturnAllDshiniOperationsOrderedByScheduledStartTime()
    {
        // Given
        OrderedMultiGeneratorWrapper<Operation<?>> requestLogGenerator = OrderedMultiGeneratorWrapper.operationsByScheduledStartTime(
                3, TestUtils.allLogGenerators() );

        // When

        int badTimeOrderingCount = 0;
        Time lastScheduledStartTime = Time.fromNano( 0 );
        while ( requestLogGenerator.hasNext() )
        {
            Operation<?> operation = requestLogGenerator.next();

            if ( false == lastScheduledStartTime.asNano() <= operation.getScheduledStartTime().asNano() )
            {
                badTimeOrderingCount++;
            }
            assertEquals( true, lastScheduledStartTime.asNano() <= operation.getScheduledStartTime().asNano() );
            lastScheduledStartTime = operation.getScheduledStartTime();
        }

        // Then
        assertThat( badTimeOrderingCount, is( 0 ) );
    }

    /*
     * The following are not tests, they are development aids
     */

    private Histogram<String, Long> initDistribution()
    {
        Histogram<String, Long> distribution = new Histogram<String, Long>( 0l );
        for ( Class<? extends Operation<?>> operationType : Dshini.operations().all( ReadWrite.READWRITE ) )
        {
            distribution.addBucket( DiscreteBucket.create( operationType.getSimpleName() ) );
        }
        return distribution;
    }

    static class ClassNameGeneratorWrapper extends Generator<String>
    {
        final Generator<?> operationsGenerator;

        public ClassNameGeneratorWrapper( Generator<?> operationsGenerator )
        {
            super( null );
            this.operationsGenerator = operationsGenerator;
        }

        @Override
        protected String doNext() throws GeneratorException
        {
            if ( false == operationsGenerator.hasNext() ) return null;
            return operationsGenerator.next().getClass().getSimpleName();
        }
    };

}
