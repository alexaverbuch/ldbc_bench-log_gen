package com.ldbc.driver.generator.dshini;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.Range;
import com.ldbc.driver.Operation;
import com.ldbc.driver.generator.Generator;
import com.ldbc.driver.util.Bucket.DiscreteBucket;
import com.ldbc.driver.util.Histogram;
import com.ldbc.driver.util.Bucket.NumberRangeBucket;
import com.ldbc.driver.workloads.dshini.DshiniBatchOperation;
import com.ldbc.driver.workloads.dshini.DshiniCypherOperation;
import com.ldbc.driver.workloads.dshini.DshiniIndexDeleteOperation;
import com.ldbc.driver.workloads.dshini.DshiniIndexGetOperation;
import com.ldbc.driver.workloads.dshini.DshiniIndexPostOperation;
import com.ldbc.driver.workloads.dshini.DshiniNodeGetOperation;
import com.ldbc.driver.workloads.dshini.DshiniNodePostOperation;
import com.ldbc.driver.workloads.dshini.DshiniNodePutOperation;

import static org.junit.Assert.assertEquals;

import static com.ldbc.driver.generator.dshini.TestUtils.*;

public class DshiniRequestLogGeneratorTest
{
    @Test
    public void testDshiniRequestLogEntryReader()
    {
        // String requestLogPath = "/test_request_log.log";
        String requestLogPath = "/request-ip-10-3-55-181.log";
        File requestLogFile = getResource( requestLogPath );
        DshiniRequestLogEntryReader requestLogReader = new DshiniRequestLogEntryReader( requestLogFile );
        boolean unexpectedEntriesEncountered = false;
        int requestLogEntryCount = 0;
        while ( requestLogReader.hasNext() )
        {
            DshiniRequestLogEntry entry = requestLogReader.next();

            if ( entry.getHttpMethod().equals( "POST" ) && entry.getUrl().endsWith( "db/data/batch" ) )
            {
                // Batch
            }
            else if ( entry.getHttpMethod().equals( "POST" ) && entry.getUrl().endsWith( "db/data/cypher" ) )
            {
                // Cypher
            }
            else if ( entry.getHttpMethod().equals( "GET" ) && entry.getUrl().contains( "db/data/index" ) )
            {
                // Index Get
            }
            else if ( entry.getHttpMethod().equals( "POST" ) && entry.getUrl().contains( "db/data/index" ) )
            {
                // Post Index
            }
            else if ( entry.getHttpMethod().equals( "DELETE" ) && entry.getUrl().contains( "db/data/index" ) )
            {
                // Delete Index
            }
            else if ( entry.getHttpMethod().equals( "GET" ) && entry.getUrl().contains( "db/data/node" ) )
            {
                // Node Get
            }
            else if ( entry.getHttpMethod().equals( "POST" ) && entry.getUrl().contains( "db/data/node" ) )
            {
                // Node Post
            }
            else if ( entry.getHttpMethod().equals( "PUT" ) && entry.getUrl().contains( "db/data/node" ) )
            {
                // Node Put
            }
            else
            {
                System.out.println( entry.getHttpMethod() + "  " + entry.getUrl() );
                unexpectedEntriesEncountered = true;

            }
            requestLogEntryCount++;
        }
        assertEquals( 4296, requestLogEntryCount );
        assertEquals( unexpectedEntriesEncountered, false );
    }

    @Test
    public void testDshiniRequestLogGenerator()
    {
        // String requestLogPath = "/test_request_log.log";
        String requestLogPath = "/request-ip-10-3-55-181.log";
        File requestLogFile = getResource( requestLogPath );

        DshiniRequestLogOperationGenerator requestLogGenerator = new DshiniRequestLogOperationGenerator( requestLogFile );
        List<Class> operationClasses = generateClassSequence( requestLogGenerator );

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

        distribution.importValueSequence( operationClasses );

        System.out.println( distribution.toPrettyString() );
        System.out.println( distribution.toPercentageValues().toPrettyString() );

        assertEquals( 4296, operationClasses.size() );
    }

    @Test
    public void testDshiniMultiRequestLogGenerator()
    {
        String requestLogPath1 = "/test_request_log.log";
        String requestLogPath2 = "/request-ip-10-3-55-181.log";
        File requestLogFile1 = getResource( requestLogPath1 );
        File requestLogFile2 = getResource( requestLogPath2 );

        DshiniRequestLogOperationGenerator requestLogGenerator = new DshiniRequestLogOperationGenerator(
                requestLogFile1, requestLogFile2 );
        List<Class> operationClasses = generateClassSequence( requestLogGenerator );

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

        distribution.importValueSequence( operationClasses );

        System.out.println( distribution.toPrettyString() );
        System.out.println( distribution.toPercentageValues().toPrettyString() );

        assertEquals( 4307, operationClasses.size() );
    }

    public final List<Class> generateClassSequence( Generator<Operation<?>> generator )
    {
        return generateClassSequence( generator, -1 );
    }

    public final List<Class> generateClassSequence( Generator<Operation<?>> generator, int size )
    {
        List<Class> generatedSequence = new ArrayList<Class>();
        int operationCount = 0;
        while ( generator.hasNext() && ( operationCount < size || size == -1 ) )
        {
            generatedSequence.add( generator.next().getClass() );
        }
        return generatedSequence;
    }

}
