package com.ldbc.driver.dshini.generator;

import java.io.File;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.ldbc.driver.Operation;
import com.ldbc.driver.dshini.generator.RequestLogOperationGenerator;
import com.ldbc.driver.dshini.log.RequestLogEntry;
import com.ldbc.driver.dshini.log.RequestLogEntryException;
import com.ldbc.driver.dshini.log.RequestLogEntryReader;

import static com.ldbc.driver.util.TestUtils.getResource;
import static org.junit.Assert.*;

public class RequestLogReaderTest
{
    @Test
    public void shouldNotFailWhenHasNextCalledAfterReturningFalse()
    {
        // Given
        File requestLogFile = getResource( "/test_request_log.log" );

        // When
        RequestLogEntryReader reader = new RequestLogEntryReader( requestLogFile );
        while ( reader.hasNext() )
        {
            reader.next();
        }

        // Then
        reader.hasNext();
        assertEquals( true, true );
    }

    @Ignore
    @Test
    public void requestLogEntryReaderAllLogsExactlyOneEntryTest() throws DshiniLogEntryMatchableException, RequestLogEntryException
    {
        // Given
        final File requestLogFile1 = new File( "logs/dshini-request-logs-2013-04-29/request-ip-10-3-55-181.log" );
        final File requestLogFile2 = new File( "logs/dshini-request-logs-2013-04-29/request-ip-10-196-162-95.log" );
        final File requestLogFile3 = new File( "logs/dshini-request-logs-2013-04-29/request-ip-10-76-97-169.log" );
        final File requestLogFile4 = new File( "logs/dshini-request-logs-2013-04-29/request-ip-10-84-146-61.log" );
        final File requestLogFile5 = new File( "logs/dshini-request-logs-2013-04-29/request-ip-10-90-59-251.log" );
        final File requestLogFile6 = new File( "logs/dshini-request-logs-2013-04-29/request-ip-10-98-203-214.log" );

        RequestLogEntryReader r1 = new RequestLogEntryReader( requestLogFile1 );
        RequestLogEntryReader r2 = new RequestLogEntryReader( requestLogFile2 );
        RequestLogEntryReader r3 = new RequestLogEntryReader( requestLogFile3 );
        RequestLogEntryReader r4 = new RequestLogEntryReader( requestLogFile4 );
        RequestLogEntryReader r5 = new RequestLogEntryReader( requestLogFile5 );
        RequestLogEntryReader r6 = new RequestLogEntryReader( requestLogFile6 );

        RequestLogEntryReader[] readers = new RequestLogEntryReader[] { r1, r2, r3, r4, r5, r6 };

        OperationMatcher matcher = new OperationMatcher();
        DshiniLogEntryMatchable[] operations = RequestLogOperationGenerator.operations( matcher );
        matcher.setOperations( operations );

        // When
        for ( RequestLogEntryReader reader : readers )
        {
            while ( reader.hasNext() )
            {
                RequestLogEntry entry = reader.next();
                try
                {
                    List<Operation<?>> matchedOperations = matcher.getAllMatchingOperations( entry );

                    // Then
                    assertEquals(
                            String.format( "Too many matched operations, expected one: %s", matchedOperations.toArray() ),
                            1, matchedOperations.size() );
                }
                catch ( RequestLogEntryException e )
                {
                    String errMsg = String.format( "Error parsing log entry\n%s", entry.toString() );
                    System.out.println( errMsg );
                }

            }
        }
    }

    @Ignore
    @Test
    public void performanceTest() throws DshiniLogEntryMatchableException, RequestLogEntryException
    {
        // Given
        final File requestLogFile1 = new File( "logs/dshini-request-logs-2013-04-29/request-ip-10-3-55-181.log" );
        final File requestLogFile2 = new File( "logs/dshini-request-logs-2013-04-29/request-ip-10-196-162-95.log" );
        final File requestLogFile3 = new File( "logs/dshini-request-logs-2013-04-29/request-ip-10-76-97-169.log" );
        final File requestLogFile4 = new File( "logs/dshini-request-logs-2013-04-29/request-ip-10-84-146-61.log" );
        final File requestLogFile5 = new File( "logs/dshini-request-logs-2013-04-29/request-ip-10-90-59-251.log" );
        final File requestLogFile6 = new File( "logs/dshini-request-logs-2013-04-29/request-ip-10-98-203-214.log" );

        RequestLogEntryReader r1 = new RequestLogEntryReader( requestLogFile1 );
        RequestLogEntryReader r2 = new RequestLogEntryReader( requestLogFile2 );
        RequestLogEntryReader r3 = new RequestLogEntryReader( requestLogFile3 );
        RequestLogEntryReader r4 = new RequestLogEntryReader( requestLogFile4 );
        RequestLogEntryReader r5 = new RequestLogEntryReader( requestLogFile5 );
        RequestLogEntryReader r6 = new RequestLogEntryReader( requestLogFile6 );

        RequestLogEntryReader[] readers = new RequestLogEntryReader[] { r1, r2, r3, r4, r5, r6 };

        // When
        int entries = 0;
        long startTime = System.nanoTime();

        for ( RequestLogEntryReader reader : readers )
        {
            while ( reader.hasNext() )
            {
                RequestLogEntry entry = reader.next();
                entries++;
            }
        }

        long endTime = System.nanoTime();

        long runtime = ( endTime - startTime ) / 1000000000;
        System.out.println( String.format( "Runtime: %s seconds", runtime ) );
        System.out.println( String.format( "Entries: %s ", entries ) );
        System.out.println( String.format( "Throughput: %s (entries/second)", entries / runtime ) );

        // Then
        assertEquals( 13049991l, entries );
    }
}
