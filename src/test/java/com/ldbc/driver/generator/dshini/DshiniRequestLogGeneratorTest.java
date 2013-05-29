package com.ldbc.driver.generator.dshini;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.neo4j.cypher.IterableRequiredException;

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
    public void testDshiniRequestLogEntryReader() throws IOException
    {
        // String requestLogPath = "/test_request_log.log";
        // File requestLogFile = getResource( requestLogPath );
        String requestLogPath = "logs/dshini-request-logs-2013-04-29/request-ip-10-3-55-181.log";
        File requestLogFile = new File( requestLogPath );
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
        String requestLogPath = "logs/dshini-request-logs-2013-04-29/request-ip-10-3-55-181.log";
        File requestLogFile = new File( requestLogPath );

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
        // System.out.println(
        // distribution.toPercentageValues().toPrettyString() );

        assertEquals( distribution.sumOfAllBucketValues(), new Long( operationClasses.size() ) );
        assertEquals( 4296, operationClasses.size() );
    }

    @Test
    public void testDshiniMultiRequestLogGenerator()
    {
        String requestLogPath1 = "/test_request_log.log";
        String requestLogPath2 = "logs/dshini-request-logs-2013-04-29/request-ip-10-3-55-181.log";
        File requestLogFile1 = getResource( requestLogPath1 );
        File requestLogFile2 = new File( requestLogPath2 );

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
        // System.out.println(
        // distribution.toPercentageValues().toPrettyString() );

        assertEquals( distribution.sumOfAllBucketValues(), new Long( operationClasses.size() ) );
        assertEquals( 4307, operationClasses.size() );
    }

    @Test
    public void testDshiniMultiRequestLogGeneratorAllLogs()
    {
        final File requestLogFile1 = new File( "logs/dshini-request-logs-2013-04-29/request-ip-10-3-55-181.log" );
        final File requestLogFile2 = new File( "logs/dshini-request-logs-2013-04-29/request-ip-10-196-162-95.log" );
        final File requestLogFile3 = new File( "logs/dshini-request-logs-2013-04-29/request-ip-10-76-97-169.log" );
        final File requestLogFile4 = new File( "logs/dshini-request-logs-2013-04-29/request-ip-10-84-146-61.log" );
        final File requestLogFile5 = new File( "logs/dshini-request-logs-2013-04-29/request-ip-10-90-59-251.log" );
        final File requestLogFile6 = new File( "logs/dshini-request-logs-2013-04-29/request-ip-10-98-203-214.log" );

        final DshiniRequestLogOperationGenerator requestLogGenerator = new DshiniRequestLogOperationGenerator(
                requestLogFile1, requestLogFile2, requestLogFile3, requestLogFile4, requestLogFile5, requestLogFile6 );

        final Iterator<Class> operationClassesIterator = new Iterator<Class>()
        {
            @Override
            public void remove()
            {
            }

            @Override
            public Class next()
            {
                return requestLogGenerator.next().getClass();
            }

            @Override
            public boolean hasNext()
            {
                return requestLogGenerator.hasNext();
            }
        };

        Iterable<Class> operationClasses = new Iterable<Class>()
        {
            @Override
            public Iterator<Class> iterator()
            {
                return operationClassesIterator;
            }
        };

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

        distribution.importValueSequence( operationClasses );

        System.out.println( distribution.toPrettyString() );

        System.out.println( distribution.sumOfAllBucketValues() );
        assertEquals( true, true );
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
