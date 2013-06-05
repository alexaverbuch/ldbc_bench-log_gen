package com.ldbc.driver.dshini.generator;

import java.io.File;
import java.util.Iterator;

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
import com.ldbc.driver.util.Bucket.DiscreteBucket;
import com.ldbc.driver.util.Histogram;

import static org.junit.Assert.assertEquals;

import static com.ldbc.driver.util.TestUtils.*;

public class RequestLogGeneratorTest
{
    @Test
    public void basicTest()
    {
        // Given
        String requestLogPath = "/test_request_log.log";
        File requestLogFile = getResource( requestLogPath );

        // When
        RequestLogOperationGenerator requestLogGenerator = new RequestLogOperationGenerator( requestLogFile );

        Histogram<String, Long> distribution = initDistribution();

        distribution.importValueSequence( new OperationToClassConvertor( requestLogGenerator ) );

        // Then
        assertEquals( new Long( 11 ), distribution.sumOfAllBucketValues() );
    }

    @Ignore
    @Test
    public void checkAllOperationsRecognizedTest()
    {
        // Given
        final File requestLogFile1 = new File( "logs/dshini-request-logs-2013-04-29/request-ip-10-3-55-181.log" );
        final File requestLogFile2 = new File( "logs/dshini-request-logs-2013-04-29/request-ip-10-196-162-95.log" );
        final File requestLogFile3 = new File( "logs/dshini-request-logs-2013-04-29/request-ip-10-76-97-169.log" );
        final File requestLogFile4 = new File( "logs/dshini-request-logs-2013-04-29/request-ip-10-84-146-61.log" );
        final File requestLogFile5 = new File( "logs/dshini-request-logs-2013-04-29/request-ip-10-90-59-251.log" );
        final File requestLogFile6 = new File( "logs/dshini-request-logs-2013-04-29/request-ip-10-98-203-214.log" );

        // When
        final RequestLogOperationGenerator requestLogGenerator = new RequestLogOperationGenerator( requestLogFile1,
                requestLogFile2, requestLogFile3, requestLogFile4, requestLogFile5, requestLogFile6 );

        Histogram<String, Long> distribution = initDistribution();

        distribution.importValueSequence( new OperationToClassConvertor( requestLogGenerator ) );

        System.out.println( distribution.toPrettyString() );

        // Then
        assertEquals( new Long( 13049989 ), distribution.sumOfAllBucketValues() );
    }

    @Ignore
    @Test
    public void performanceTest()
    {
        // Given
        final File requestLogFile1 = new File( "logs/dshini-request-logs-2013-04-29/request-ip-10-3-55-181.log" );
        final File requestLogFile2 = new File( "logs/dshini-request-logs-2013-04-29/request-ip-10-196-162-95.log" );
        final File requestLogFile3 = new File( "logs/dshini-request-logs-2013-04-29/request-ip-10-76-97-169.log" );
        final File requestLogFile4 = new File( "logs/dshini-request-logs-2013-04-29/request-ip-10-84-146-61.log" );
        final File requestLogFile5 = new File( "logs/dshini-request-logs-2013-04-29/request-ip-10-90-59-251.log" );
        final File requestLogFile6 = new File( "logs/dshini-request-logs-2013-04-29/request-ip-10-98-203-214.log" );

        // When
        final RequestLogOperationGenerator requestLogGenerator = new RequestLogOperationGenerator( requestLogFile1,
                requestLogFile2, requestLogFile3, requestLogFile4, requestLogFile5, requestLogFile6 );

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

class OperationToClassConvertor implements Iterator<String>
{
    final Generator<Operation<?>> operationsGenerator;

    public OperationToClassConvertor( Generator<Operation<?>> operationsGenerator )
    {
        this.operationsGenerator = operationsGenerator;
    }

    @Override
    public void remove()
    {
    }

    @Override
    public String next()
    {
        return operationsGenerator.next().getClass().getSimpleName();
    }

    @Override
    public boolean hasNext()
    {
        return operationsGenerator.hasNext();
    }
};
