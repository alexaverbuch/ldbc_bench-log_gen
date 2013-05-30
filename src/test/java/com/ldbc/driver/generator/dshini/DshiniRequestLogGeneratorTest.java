package com.ldbc.driver.generator.dshini;

import java.io.File;
import java.util.Iterator;

import org.junit.Test;

import com.ldbc.driver.Operation;
import com.ldbc.driver.generator.Generator;
import com.ldbc.driver.util.Bucket.DiscreteBucket;
import com.ldbc.driver.util.Histogram;
import com.ldbc.driver.workloads.dshini.BatchOperation;
import com.ldbc.driver.workloads.dshini.CypherOperation;
import com.ldbc.driver.workloads.dshini.IndexDeleteNodeOperation;
import com.ldbc.driver.workloads.dshini.IndexQueryGetNodeOperation;
import com.ldbc.driver.workloads.dshini.AddNodeToIndexOperation;
import com.ldbc.driver.workloads.dshini.DeleteNodeOperation;
import com.ldbc.driver.workloads.dshini.GetNodeOperation;
import com.ldbc.driver.workloads.dshini.PostNodeOperation;
import com.ldbc.driver.workloads.dshini.NodePutOperation;
import com.ldbc.driver.workloads.dshini.DeleteRelationshipOperation;
import com.ldbc.driver.workloads.dshini.GetRelationshipOperation;

import static org.junit.Assert.assertEquals;

import static com.ldbc.driver.generator.dshini.TestUtils.*;

public class DshiniRequestLogGeneratorTest
{
    @Test
    public void testDshiniRequestLogGenerator()
    {
        // Given
        String requestLogPath = "/test_request_log.log";
        File requestLogFile = getResource( requestLogPath );

        // When
        RequestLogOperationGenerator requestLogGenerator = new RequestLogOperationGenerator( requestLogFile );

        Histogram<Class, Long> distribution = new Histogram<Class, Long>( 0l );
        distribution.addBucket( DiscreteBucket.create( (Class) CypherOperation.class ), 0l );
        distribution.addBucket( DiscreteBucket.create( (Class) BatchOperation.class ), 0l );
        distribution.addBucket( DiscreteBucket.create( (Class) IndexQueryGetNodeOperation.class ), 0l );
        distribution.addBucket( DiscreteBucket.create( (Class) AddNodeToIndexOperation.class ), 0l );
        distribution.addBucket( DiscreteBucket.create( (Class) IndexDeleteNodeOperation.class ), 0l );
        distribution.addBucket( DiscreteBucket.create( (Class) GetNodeOperation.class ), 0l );
        distribution.addBucket( DiscreteBucket.create( (Class) PostNodeOperation.class ), 0l );
        distribution.addBucket( DiscreteBucket.create( (Class) PostNodeOperation.class ), 0l );
        distribution.addBucket( DiscreteBucket.create( (Class) NodePutOperation.class ), 0l );

        distribution.importValueSequence( new OperationToClassConvertor( requestLogGenerator ) );

        // Then
        assertEquals( new Long( 11 ), distribution.sumOfAllBucketValues() );
    }

    @Test
    public void testDshiniMultiRequestLogGenerator()
    {
        // Given
        String requestLogPath1 = "/test_request_log.log";
        String requestLogPath2 = "logs/dshini-request-logs-2013-04-29/request-ip-10-3-55-181.log";
        File requestLogFile1 = getResource( requestLogPath1 );
        File requestLogFile2 = new File( requestLogPath2 );

        // When
        RequestLogOperationGenerator requestLogGenerator = new RequestLogOperationGenerator( requestLogFile1,
                requestLogFile2 );

        Histogram<Class, Long> distribution = new Histogram<Class, Long>( 0l );
        distribution.addBucket( DiscreteBucket.create( (Class) CypherOperation.class ), 0l );
        distribution.addBucket( DiscreteBucket.create( (Class) BatchOperation.class ), 0l );
        distribution.addBucket( DiscreteBucket.create( (Class) IndexQueryGetNodeOperation.class ), 0l );
        distribution.addBucket( DiscreteBucket.create( (Class) AddNodeToIndexOperation.class ), 0l );
        distribution.addBucket( DiscreteBucket.create( (Class) IndexDeleteNodeOperation.class ), 0l );
        distribution.addBucket( DiscreteBucket.create( (Class) GetNodeOperation.class ), 0l );
        distribution.addBucket( DiscreteBucket.create( (Class) PostNodeOperation.class ), 0l );
        distribution.addBucket( DiscreteBucket.create( (Class) PostNodeOperation.class ), 0l );
        distribution.addBucket( DiscreteBucket.create( (Class) NodePutOperation.class ), 0l );

        distribution.importValueSequence( new OperationToClassConvertor( requestLogGenerator ) );

        // Then
        assertEquals( new Long( 4307 ), distribution.sumOfAllBucketValues() );
    }

    @Test
    public void testDshiniMultiRequestLogGeneratorAllLogs()
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

        Histogram<Class, Long> distribution = new Histogram<Class, Long>( 0l );
        distribution.addBucket( DiscreteBucket.create( (Class) CypherOperation.class ), 0l );
        distribution.addBucket( DiscreteBucket.create( (Class) BatchOperation.class ), 0l );
        distribution.addBucket( DiscreteBucket.create( (Class) IndexQueryGetNodeOperation.class ), 0l );
        distribution.addBucket( DiscreteBucket.create( (Class) AddNodeToIndexOperation.class ), 0l );
        distribution.addBucket( DiscreteBucket.create( (Class) IndexDeleteNodeOperation.class ), 0l );
        distribution.addBucket( DiscreteBucket.create( (Class) GetNodeOperation.class ), 0l );
        distribution.addBucket( DiscreteBucket.create( (Class) PostNodeOperation.class ), 0l );
        distribution.addBucket( DiscreteBucket.create( (Class) NodePutOperation.class ), 0l );
        distribution.addBucket( DiscreteBucket.create( (Class) DeleteNodeOperation.class ), 0l );
        distribution.addBucket( DiscreteBucket.create( (Class) GetRelationshipOperation.class ), 0l );
        distribution.addBucket( DiscreteBucket.create( (Class) DeleteRelationshipOperation.class ), 0l );

        long startTime = System.nanoTime();

        distribution.importValueSequence( new OperationToClassConvertor( requestLogGenerator ) );

        long endTime = System.nanoTime();

        long runtime = ( endTime - startTime ) / 1000000000;
        long operations = distribution.sumOfAllBucketValues();
        System.out.println( String.format( "Runtime: %s seconds", runtime ) );
        System.out.println( String.format( "Operations: %s ", operations ) );
        System.out.println( String.format( "Throughput: %s (operations/second)", operations / runtime ) );

        System.out.println( distribution.toPrettyString() );

        // CYPHER PARSING - STRING:No, JSON:NO
        // Runtime: 106 seconds
        // Operations: 13,043,166
        // Throughput: 123,048 (operations/second)

        // CYPHER PARSING - STRING:Pattern, JSON:NO
        // Runtime: 158 seconds
        // Operations: 13,043,166
        // Throughput: 82,551 (operations/second)

        // CYPHER PARSING - STRING:String, JSON:NO
        // Runtime: 167 seconds
        // Operations: 13,043,166
        // Throughput: 78,102 (operations/second)

        // CYPHER PARSING - STRING:Pattern, JSON:YES
        // Runtime: 200 seconds
        // Operations: 13,043,166
        // Throughput: 65,215 (operations/second)

        // Then
        assertEquals( new Long( 13043166 ), distribution.sumOfAllBucketValues() );
    }
}

class OperationToClassConvertor implements Iterator<Class>
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
    public Class next()
    {
        return operationsGenerator.next().getClass();
    }

    @Override
    public boolean hasNext()
    {
        return operationsGenerator.hasNext();
    }
};
