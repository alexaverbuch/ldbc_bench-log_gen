package com.ldbc.driver.dshini.generator;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import org.junit.Ignore;
import org.junit.Test;

import com.ldbc.driver.Operation;
import com.ldbc.driver.dshini.generator.MultiRequestLogEntryReader;
import com.ldbc.driver.dshini.generator.RequestLogEntry;
import com.ldbc.driver.dshini.generator.RequestLogEntryException;
import com.ldbc.driver.dshini.generator.RequestLogEntryReader;
import com.ldbc.driver.dshini.generator.RequestLogOperationGenerator;
import com.ldbc.driver.dshini.operations.MatchableException;
import com.ldbc.driver.dshini.operations.OperationMatcher;

import static org.junit.Assert.assertEquals;

public class RequestLogReaderTest
{
    @Test
    public void testDshiniRequestLogEntryReader() throws IOException
    {
        // String requestLogPath = "/test_request_log.log";
        // File requestLogFile = getResource( requestLogPath );
        String requestLogPath = "logs/dshini-request-logs-2013-04-29/request-ip-10-3-55-181.log";
        File requestLogFile = new File( requestLogPath );
        RequestLogEntryReader requestLogReader = new RequestLogEntryReader( requestLogFile );
        boolean unexpectedEntriesEncountered = false;
        int requestLogEntryCount = 0;
        while ( requestLogReader.hasNext() )
        {
            RequestLogEntry entry = requestLogReader.next();

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

    @Ignore
    @Test
    public void testDshiniRequestLogEntryReaderAllLogs() throws MatchableException, RequestLogEntryException
    {
        // Given
        final File requestLogFile1 = new File( "logs/dshini-request-logs-2013-04-29/request-ip-10-3-55-181.log" );
        final File requestLogFile2 = new File( "logs/dshini-request-logs-2013-04-29/request-ip-10-196-162-95.log" );
        final File requestLogFile3 = new File( "logs/dshini-request-logs-2013-04-29/request-ip-10-76-97-169.log" );
        final File requestLogFile4 = new File( "logs/dshini-request-logs-2013-04-29/request-ip-10-84-146-61.log" );
        final File requestLogFile5 = new File( "logs/dshini-request-logs-2013-04-29/request-ip-10-90-59-251.log" );
        final File requestLogFile6 = new File( "logs/dshini-request-logs-2013-04-29/request-ip-10-98-203-214.log" );

        MultiRequestLogEntryReader requestLogReader = new MultiRequestLogEntryReader( requestLogFile1, requestLogFile2,
                requestLogFile3, requestLogFile4, requestLogFile5, requestLogFile6 );

        OperationMatcher matcher = new OperationMatcher( RequestLogOperationGenerator.operations() );
        // When
        while ( requestLogReader.hasNext() )
        {
            RequestLogEntry entry = requestLogReader.next();
            List<Operation<?>> matchedOperations = matcher.getAllMatchingOperations( entry );

            // Then
            assertEquals( String.format( "Too many matched operations, expected one: ", matchedOperations.toArray() ),
                    1, matchedOperations.size() );
        }
    }
}
