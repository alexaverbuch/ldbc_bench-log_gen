package com.ldbc.driver.generator.dshini;

import java.io.File;
import java.util.Iterator;
import org.junit.Test;

import com.ldbc.driver.Operation;
import com.ldbc.driver.generator.Generator;
import com.ldbc.driver.util.Bucket.DiscreteBucket;
import com.ldbc.driver.util.Histogram;
import com.ldbc.driver.workloads.dshini.DshiniBatchOperation;
import com.ldbc.driver.workloads.dshini.DshiniCypherOperation;
import com.ldbc.driver.workloads.dshini.DshiniIndexDeleteOperation;
import com.ldbc.driver.workloads.dshini.DshiniIndexGetOperation;
import com.ldbc.driver.workloads.dshini.DshiniIndexPostOperation;
import com.ldbc.driver.workloads.dshini.DshiniNodeDeleteOperation;
import com.ldbc.driver.workloads.dshini.DshiniNodeGetOperation;
import com.ldbc.driver.workloads.dshini.DshiniNodePostOperation;
import com.ldbc.driver.workloads.dshini.DshiniNodePutOperation;
import com.ldbc.driver.workloads.dshini.DshiniRelationshipDeleteOperation;
import com.ldbc.driver.workloads.dshini.DshiniRelationshipGetOperation;

import static org.junit.Assert.assertEquals;

import static com.ldbc.driver.generator.dshini.TestUtils.*;

public class DshiniRequestLogGeneratorTest
{
    @Test
    public void testDshiniRequestLogGenerator()
    {
        // Given
        // String requestLogPath = "/test_request_log.log";
        String requestLogPath = "logs/dshini-request-logs-2013-04-29/request-ip-10-3-55-181.log";
        File requestLogFile = new File( requestLogPath );

        // When
        DshiniRequestLogOperationGenerator requestLogGenerator = new DshiniRequestLogOperationGenerator( requestLogFile );

        Histogram<Class, Long> distribution = new Histogram<Class, Long>( 0l );
        distribution.addBucket( DiscreteBucket.create( (Class) DshiniCypherOperation.class ), 0l );
        distribution.addBucket( DiscreteBucket.create( (Class) DshiniBatchOperation.class ), 0l );
        distribution.addBucket( DiscreteBucket.create( (Class) DshiniIndexGetOperation.class ), 0l );
        distribution.addBucket( DiscreteBucket.create( (Class) DshiniIndexPostOperation.class ), 0l );
        distribution.addBucket( DiscreteBucket.create( (Class) DshiniIndexDeleteOperation.class ), 0l );
        distribution.addBucket( DiscreteBucket.create( (Class) DshiniNodeGetOperation.class ), 0l );
        distribution.addBucket( DiscreteBucket.create( (Class) DshiniNodePostOperation.class ), 0l );
        distribution.addBucket( DiscreteBucket.create( (Class) DshiniNodePostOperation.class ), 0l );
        distribution.addBucket( DiscreteBucket.create( (Class) DshiniNodePutOperation.class ), 0l );

        distribution.importValueSequence( new OperationToClassConvertor( requestLogGenerator ) );

        // When
        // System.out.println( distribution.toPrettyString() );
        assertEquals( new Long( 4296 ), distribution.sumOfAllBucketValues() );
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
        DshiniRequestLogOperationGenerator requestLogGenerator = new DshiniRequestLogOperationGenerator(
                requestLogFile1, requestLogFile2 );

        Histogram<Class, Long> distribution = new Histogram<Class, Long>( 0l );
        distribution.addBucket( DiscreteBucket.create( (Class) DshiniCypherOperation.class ), 0l );
        distribution.addBucket( DiscreteBucket.create( (Class) DshiniBatchOperation.class ), 0l );
        distribution.addBucket( DiscreteBucket.create( (Class) DshiniIndexGetOperation.class ), 0l );
        distribution.addBucket( DiscreteBucket.create( (Class) DshiniIndexPostOperation.class ), 0l );
        distribution.addBucket( DiscreteBucket.create( (Class) DshiniIndexDeleteOperation.class ), 0l );
        distribution.addBucket( DiscreteBucket.create( (Class) DshiniNodeGetOperation.class ), 0l );
        distribution.addBucket( DiscreteBucket.create( (Class) DshiniNodePostOperation.class ), 0l );
        distribution.addBucket( DiscreteBucket.create( (Class) DshiniNodePostOperation.class ), 0l );
        distribution.addBucket( DiscreteBucket.create( (Class) DshiniNodePutOperation.class ), 0l );

        distribution.importValueSequence( new OperationToClassConvertor( requestLogGenerator ) );

        // Then
        // System.out.println( distribution.toPrettyString() );
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
        final DshiniRequestLogOperationGenerator requestLogGenerator = new DshiniRequestLogOperationGenerator(
                requestLogFile1, requestLogFile2, requestLogFile3, requestLogFile4, requestLogFile5, requestLogFile6 );

        Histogram<Class, Long> distribution = new Histogram<Class, Long>( 0l );
        distribution.addBucket( DiscreteBucket.create( (Class) DshiniCypherOperation.class ), 0l );
        distribution.addBucket( DiscreteBucket.create( (Class) DshiniBatchOperation.class ), 0l );
        distribution.addBucket( DiscreteBucket.create( (Class) DshiniIndexGetOperation.class ), 0l );
        distribution.addBucket( DiscreteBucket.create( (Class) DshiniIndexPostOperation.class ), 0l );
        distribution.addBucket( DiscreteBucket.create( (Class) DshiniIndexDeleteOperation.class ), 0l );
        distribution.addBucket( DiscreteBucket.create( (Class) DshiniNodeGetOperation.class ), 0l );
        distribution.addBucket( DiscreteBucket.create( (Class) DshiniNodePostOperation.class ), 0l );
        distribution.addBucket( DiscreteBucket.create( (Class) DshiniNodePutOperation.class ), 0l );
        distribution.addBucket( DiscreteBucket.create( (Class) DshiniNodeDeleteOperation.class ), 0l );
        distribution.addBucket( DiscreteBucket.create( (Class) DshiniRelationshipGetOperation.class ), 0l );
        distribution.addBucket( DiscreteBucket.create( (Class) DshiniRelationshipDeleteOperation.class ), 0l );

        distribution.importValueSequence( new OperationToClassConvertor( requestLogGenerator ) );

        // Then
        // System.out.println( distribution.toPrettyString() );
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
