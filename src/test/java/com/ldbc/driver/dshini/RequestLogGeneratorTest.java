package com.ldbc.driver.dshini;

import java.io.File;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.matchers.JUnitMatchers.*;

import com.ldbc.driver.Operation;
import com.ldbc.driver.dshini.generator.RequestLogOperationGenerator;
import com.ldbc.driver.dshini.utils.DshiniLogs;
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

    // TODO
    // @Ignore
    @Test
    public void shouldRecognizeAllOperations()
    {
        // Given
        OrderedMultiGeneratorWrapper<Operation<?>> requestLogGenerator = OrderedMultiGeneratorWrapper.operationsByScheduledStartTime(
                1, DshiniLogs.allLogGenerators() );

        // When
        Histogram<String, Long> distribution = initDistribution();

        // Will error if Operation encountered that is not in initDistribution
        distribution.importValueSequence( new ClassNameGeneratorWrapper( requestLogGenerator ) );

        System.out.println( distribution.toPrettyString() );

        // Then
    }

    // TODO
    // @Ignore
    @Test
    public void shouldReturnAllDshiniOperationsOrderedByScheduledStartTime()
    {
        // Given
        OrderedMultiGeneratorWrapper<Operation<?>> requestLogGenerator = OrderedMultiGeneratorWrapper.operationsByScheduledStartTime(
                3, DshiniLogs.allLogGenerators() );

        // When

        int badTimeOrderingCount = 0;
        int operations = 0;
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
            operations++;
        }

        // Then
        assertThat( operations, is( 13049989 ) );
        assertThat( badTimeOrderingCount, is( 0 ) );
        System.out.println( String.format( "---\nbadTimeOrderingCount = %s\n---", badTimeOrderingCount ) );
        // badTimeOrderingCount = 89
    }

    /*
     * The following are not tests, they are development aids
     */

    @Ignore
    @Test
    public void performanceTestNoLookahead()
    {
        doPerformanceTest( 1 );
    }

    @Ignore
    @Test
    public void performanceTestLookahead()
    {
        doPerformanceTest( 3 );
    }

    public void doPerformanceTest( int lookaheadDistance )
    {
        System.out.println( "Lookahead = " + lookaheadDistance );

        // Given
        OrderedMultiGeneratorWrapper<Operation<?>> requestLogGenerator = OrderedMultiGeneratorWrapper.operationsByScheduledStartTime(
                lookaheadDistance, DshiniLogs.allLogGenerators() );

        // When
        long operations = 0;
        long startTime = System.nanoTime();
        while ( requestLogGenerator.hasNext() )
        {
            requestLogGenerator.next();
            operations++;
        }
        long endTime = System.nanoTime();

        long runtime = ( endTime - startTime ) / 1000000000;
        System.out.println( String.format( "Runtime: %s seconds", runtime ) );
        System.out.println( String.format( "Operations: %s ", operations ) );
        System.out.println( String.format( "Throughput: %s (operations/second)", operations / runtime ) );

        // Then
        assertEquals( 13049989, operations );
    }

    private Histogram<String, Long> initDistribution()
    {
        Histogram<String, Long> distribution = new Histogram<String, Long>( 0l );
        for ( Class<? extends Operation<?>> operationType : DshiniLogs.allDshiniOperationTypes() )
        {
            distribution.addBucket( DiscreteBucket.create( operationType.getSimpleName() ) );
        }
        return distribution;
    }

}

class ClassNameGeneratorWrapper extends Generator<String>
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
