package com.ldbc.driver.dshini;

import java.io.File;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.matchers.JUnitMatchers.*;

import com.ldbc.driver.Operation;
import com.ldbc.driver.dshini.generator.RequestLogOperationGenerator;
import com.ldbc.driver.dshini.operations.Dshini;
import com.ldbc.driver.dshini.operations.Dshini.ReadWrite;
import com.ldbc.driver.generator.Generator;
import com.ldbc.driver.generator.GeneratorException;
import com.ldbc.driver.generator.wrapper.FutureTimeShiftGeneratorWrapper;
import com.ldbc.driver.generator.wrapper.OrderedMultiGeneratorWrapper;
import com.ldbc.driver.util.Bucket.DiscreteBucket;
import com.ldbc.driver.util.temporal.Duration;
import com.ldbc.driver.util.temporal.Time;
import com.ldbc.driver.util.Histogram;

import static com.ldbc.driver.util.TestUtils.*;

public class RequestLogGeneratorTest
{
    @Test
    public void shouldReadAllOperations()
    {
        // Given
        File requestLogFile = getResource( "/test_request_log.log" );

        // When
        RequestLogOperationGenerator requestLogGenerator = new RequestLogOperationGenerator( requestLogFile );

        Histogram<String, Long> distribution = initDistribution();

        distribution.importValueSequence( new ClassNameGeneratorWrapper( requestLogGenerator ) );

        // Then
        assertEquals( new Long( 11 ), distribution.sumOfAllBucketValues() );
    }

    @Test
    public void shouldAssignNewStartTimeToAllOperationsThatIsOffsetByTheCorrectDuration()
    {
        // Given
        File requestLogFile = getResource( "/test_request_log.log" );

        // When
        RequestLogOperationGenerator requestLogGenerator = new RequestLogOperationGenerator( requestLogFile );
        Generator<Operation<?>> operationGenerator = new FutureTimeShiftGeneratorWrapper(
                new RequestLogOperationGenerator( requestLogFile ), Time.now().plus( Duration.fromSeconds( 1 ) ) );

        // Then
        Operation<?> originalOperation = requestLogGenerator.next();
        Operation<?> shiftedOperation = operationGenerator.next();
        Duration difference = Duration.durationBetween( originalOperation.getScheduledStartTime(),
                shiftedOperation.getScheduledStartTime() );
        int count = 1;
        while ( operationGenerator.hasNext() )
        {
            originalOperation = requestLogGenerator.next();
            shiftedOperation = operationGenerator.next();
            count++;
            assertThat(
                    Duration.durationBetween( originalOperation.getScheduledStartTime(),
                            shiftedOperation.getScheduledStartTime() ), is( difference ) );
        }
        assertThat( count, is( 11 ) );
        assertThat( requestLogGenerator.hasNext(), is( false ) );
    }

    @Test
    public void shouldReturnOperationsOrderedByScheduledStartTimeWhenIndividualLogsAreOrdered()
    {
        // Given
        final File partialAndReorderedRequestLogFile1 = getResource( "/partial_test_request_log1.log" );
        final File partialAndReorderedRequestLogFile2 = getResource( "/partial_test_request_log2.log" );
        final File partialAndReorderedRequestLogFile3 = getResource( "/partial_test_request_log3.log" );
        final File fullRequestLogFile = getResource( "/test_request_log.log" );

        // When
        RequestLogOperationGenerator g1 = new RequestLogOperationGenerator( partialAndReorderedRequestLogFile1 );
        RequestLogOperationGenerator g2 = new RequestLogOperationGenerator( partialAndReorderedRequestLogFile2 );
        RequestLogOperationGenerator g3 = new RequestLogOperationGenerator( partialAndReorderedRequestLogFile3 );
        Generator<Operation<?>> reorderedOperationsGenerator = OrderedMultiGeneratorWrapper.operationsByScheduledStartTime(
                1, g1, g2, g3 );
        Generator<Operation<?>> originalOrderOperationsGenerator = new RequestLogOperationGenerator( fullRequestLogFile );

        // Then
        while ( originalOrderOperationsGenerator.hasNext() )
        {
            assertThat( reorderedOperationsGenerator.hasNext(), is( true ) );
            assertEquals( originalOrderOperationsGenerator.next(), reorderedOperationsGenerator.next() );
        }
        assertThat( reorderedOperationsGenerator.hasNext(), is( false ) );
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
