package com.ldbc.driver.dshini.generator;

import java.io.File;

import org.junit.Ignore;
import org.junit.Test;

import com.ldbc.driver.Operation;
import com.ldbc.driver.dshini.generator.RequestLogOperationGenerator;
import com.ldbc.driver.dshini.operations.AddNodeToIndexOperationFactory.AddNodeToIndexOperation;
import com.ldbc.driver.dshini.operations.BatchOperationFactory.BatchOperation;
import com.ldbc.driver.dshini.operations.CreateNodeOperationFactory.CreateNodeOperation;
import com.ldbc.driver.dshini.operations.CreateRelationshipOperationFactory.CreateRelationshipOperation;
import com.ldbc.driver.dshini.operations.CypherOperationFactory.CypherOperation;
import com.ldbc.driver.dshini.operations.DeleteNodeFromIndexOperationFactory.DeleteNodeFromIndexOperation;
import com.ldbc.driver.dshini.operations.DeleteNodeOperationFactory.DeleteNodeOperation;
import com.ldbc.driver.dshini.operations.DeleteRelationshipOperationFactory.DeleteRelationshipOperation;
import com.ldbc.driver.dshini.operations.GetNodeOperationFactory.GetNodeOperation;
import com.ldbc.driver.dshini.operations.GetNodesOutRelationshipsOperationFactory.GetNodeOutRelationshipsOperation;
import com.ldbc.driver.dshini.operations.GetNodesRelationshipsOperationFactory.GetNodeRelationshipsOperation;
import com.ldbc.driver.dshini.operations.GetNodesTypedInRelationshipsOperationFactory.GetNodeTypedInRelationshipsOperation;
import com.ldbc.driver.dshini.operations.GetNodesTypedOutRelationshipsOperationFactory.GetNodeTypedOutRelationshipsOperation;
import com.ldbc.driver.dshini.operations.GetRelationshipOperationFactory.GetRelationshipOperation;
import com.ldbc.driver.dshini.operations.IndexQueryGetNodeOperationFactory.IndexQueryGetNodeOperation;
import com.ldbc.driver.dshini.operations.UpdateNodePropertiesOperationFactory.UpdateNodePropertiesOperation;
import com.ldbc.driver.generator.Generator;
import com.ldbc.driver.generator.GeneratorException;
import com.ldbc.driver.generator.wrapper.OrderedMultiGeneratorWrapper;
import com.ldbc.driver.util.Bucket.DiscreteBucket;
import com.ldbc.driver.util.Histogram;

import static org.junit.Assert.assertEquals;

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
            assertEquals( true, reorderedOperationsGenerator.hasNext() );
            assertEquals( originalOrderOperationsGenerator.next(), reorderedOperationsGenerator.next() );
        }
        assertEquals( false, reorderedOperationsGenerator.hasNext() );
    }

    @Ignore
    @Test
    public void shouldRecognizeAllOperations()
    {
        // Given
        final File requestLogFile1 = new File( "logs/dshini-request-logs-2013-04-29/request-ip-10-3-55-181.log" );
        final File requestLogFile2 = new File( "logs/dshini-request-logs-2013-04-29/request-ip-10-196-162-95.log" );
        final File requestLogFile3 = new File( "logs/dshini-request-logs-2013-04-29/request-ip-10-76-97-169.log" );
        final File requestLogFile4 = new File( "logs/dshini-request-logs-2013-04-29/request-ip-10-84-146-61.log" );
        final File requestLogFile5 = new File( "logs/dshini-request-logs-2013-04-29/request-ip-10-90-59-251.log" );
        final File requestLogFile6 = new File( "logs/dshini-request-logs-2013-04-29/request-ip-10-98-203-214.log" );

        // When
        RequestLogOperationGenerator g1 = new RequestLogOperationGenerator( requestLogFile1 );
        RequestLogOperationGenerator g2 = new RequestLogOperationGenerator( requestLogFile2 );
        RequestLogOperationGenerator g3 = new RequestLogOperationGenerator( requestLogFile3 );
        RequestLogOperationGenerator g4 = new RequestLogOperationGenerator( requestLogFile4 );
        RequestLogOperationGenerator g5 = new RequestLogOperationGenerator( requestLogFile5 );
        RequestLogOperationGenerator g6 = new RequestLogOperationGenerator( requestLogFile6 );

        OrderedMultiGeneratorWrapper<Operation<?>> requestLogGenerator = OrderedMultiGeneratorWrapper.operationsByScheduledStartTime(
                1, g1, g2, g3, g4, g5, g6 );

        Histogram<String, Long> distribution = initDistribution();

        distribution.importValueSequence( new ClassNameGeneratorWrapper( requestLogGenerator ) );

        System.out.println( distribution.toPrettyString() );

        // Then
        assertEquals( new Long( 13049989 ), distribution.sumOfAllBucketValues() );
    }

    @Ignore
    @Test
    public void shouldReturnAllDshiniOperationsOrderedByScheduledStartTime()
    {
        // Given
        final File requestLogFile1 = new File( "logs/dshini-request-logs-2013-04-29/request-ip-10-3-55-181.log" );
        final File requestLogFile2 = new File( "logs/dshini-request-logs-2013-04-29/request-ip-10-196-162-95.log" );
        final File requestLogFile3 = new File( "logs/dshini-request-logs-2013-04-29/request-ip-10-76-97-169.log" );
        final File requestLogFile4 = new File( "logs/dshini-request-logs-2013-04-29/request-ip-10-84-146-61.log" );
        final File requestLogFile5 = new File( "logs/dshini-request-logs-2013-04-29/request-ip-10-90-59-251.log" );
        final File requestLogFile6 = new File( "logs/dshini-request-logs-2013-04-29/request-ip-10-98-203-214.log" );

        // When
        RequestLogOperationGenerator g1 = new RequestLogOperationGenerator( requestLogFile1 );
        RequestLogOperationGenerator g2 = new RequestLogOperationGenerator( requestLogFile2 );
        RequestLogOperationGenerator g3 = new RequestLogOperationGenerator( requestLogFile3 );
        RequestLogOperationGenerator g4 = new RequestLogOperationGenerator( requestLogFile4 );
        RequestLogOperationGenerator g5 = new RequestLogOperationGenerator( requestLogFile5 );
        RequestLogOperationGenerator g6 = new RequestLogOperationGenerator( requestLogFile6 );

        OrderedMultiGeneratorWrapper<Operation<?>> requestLogGenerator = OrderedMultiGeneratorWrapper.operationsByScheduledStartTime(
                3, g1, g2, g3, g4, g5, g6 );

        // Then
        // TODO temp
        int badTimeOrderingCount = 0;
        long operations = 0;
        long lastScheduledStartTime = Long.MIN_VALUE;
        while ( requestLogGenerator.hasNext() )
        {
            Operation<?> operation = requestLogGenerator.next();
            // TODO remove
            if ( -1 == operation.getScheduledStartTimeNanoSeconds() )
            {
                System.out.println( operation.getClass().getName() );
                assertEquals( true, false );
            }

            if ( false == lastScheduledStartTime <= operation.getScheduledStartTimeNanoSeconds() )
            {
                // System.out.println( operation.getClass().getName() );
                // System.out.println( String.format( "%s\n%s\n--",
                // lastScheduledStartTime,
                // operation.getScheduledStartTimeNanoSeconds() ) );
                badTimeOrderingCount++;
            }
            // TODO uncomment
            // assertEquals( true, lastScheduledStartTime <=
            // operation.getScheduledStartTimeNanoSeconds() );
            lastScheduledStartTime = operation.getScheduledStartTimeNanoSeconds();
            operations++;
        }

        assertEquals( 13049989, operations );
        System.out.println( String.format( "---\nbadTimeOrderingCount = %s\n---", badTimeOrderingCount ) );
        // badTimeOrderingCount = 89
    }

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
        final File requestLogFile1 = new File( "logs/dshini-request-logs-2013-04-29/request-ip-10-3-55-181.log" );
        final File requestLogFile2 = new File( "logs/dshini-request-logs-2013-04-29/request-ip-10-196-162-95.log" );
        final File requestLogFile3 = new File( "logs/dshini-request-logs-2013-04-29/request-ip-10-76-97-169.log" );
        final File requestLogFile4 = new File( "logs/dshini-request-logs-2013-04-29/request-ip-10-84-146-61.log" );
        final File requestLogFile5 = new File( "logs/dshini-request-logs-2013-04-29/request-ip-10-90-59-251.log" );
        final File requestLogFile6 = new File( "logs/dshini-request-logs-2013-04-29/request-ip-10-98-203-214.log" );

        // When
        RequestLogOperationGenerator g1 = new RequestLogOperationGenerator( requestLogFile1 );
        RequestLogOperationGenerator g2 = new RequestLogOperationGenerator( requestLogFile2 );
        RequestLogOperationGenerator g3 = new RequestLogOperationGenerator( requestLogFile3 );
        RequestLogOperationGenerator g4 = new RequestLogOperationGenerator( requestLogFile4 );
        RequestLogOperationGenerator g5 = new RequestLogOperationGenerator( requestLogFile5 );
        RequestLogOperationGenerator g6 = new RequestLogOperationGenerator( requestLogFile6 );

        OrderedMultiGeneratorWrapper<Operation<?>> requestLogGenerator = OrderedMultiGeneratorWrapper.operationsByScheduledStartTime(
                lookaheadDistance, g1, g2, g3, g4, g5, g6 );

        long operations = 0;
        long startTime = System.nanoTime();
        while ( requestLogGenerator.hasNext() )
        {
            Operation<?> operation = requestLogGenerator.next();
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
        distribution.addBucket( DiscreteBucket.create( CypherOperation.class.getSimpleName() ), 0l );
        distribution.addBucket( DiscreteBucket.create( BatchOperation.class.getSimpleName() ), 0l );
        distribution.addBucket( DiscreteBucket.create( IndexQueryGetNodeOperation.class.getSimpleName() ), 0l );
        distribution.addBucket( DiscreteBucket.create( AddNodeToIndexOperation.class.getSimpleName() ), 0l );
        distribution.addBucket( DiscreteBucket.create( DeleteNodeFromIndexOperation.class.getSimpleName() ), 0l );
        distribution.addBucket( DiscreteBucket.create( GetNodeOperation.class.getSimpleName() ), 0l );
        distribution.addBucket( DiscreteBucket.create( CreateNodeOperation.class.getSimpleName() ), 0l );
        distribution.addBucket( DiscreteBucket.create( UpdateNodePropertiesOperation.class.getSimpleName() ), 0l );
        distribution.addBucket( DiscreteBucket.create( DeleteNodeOperation.class.getSimpleName() ), 0l );
        distribution.addBucket( DiscreteBucket.create( GetRelationshipOperation.class.getSimpleName() ), 0l );
        distribution.addBucket( DiscreteBucket.create( DeleteRelationshipOperation.class.getSimpleName() ), 0l );
        distribution.addBucket( DiscreteBucket.create( GetNodeOutRelationshipsOperation.class.getSimpleName() ), 0l );
        distribution.addBucket( DiscreteBucket.create( CreateRelationshipOperation.class.getSimpleName() ), 0l );
        distribution.addBucket( DiscreteBucket.create( GetNodeTypedOutRelationshipsOperation.class.getSimpleName() ),
                0l );
        distribution.addBucket( DiscreteBucket.create( GetNodeRelationshipsOperation.class.getSimpleName() ), 0l );
        distribution.addBucket( DiscreteBucket.create( GetNodeTypedInRelationshipsOperation.class.getSimpleName() ), 0l );
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
