package com.ldbc.driver.dshini.generator;

import java.io.File;
import java.util.List;
import java.util.regex.Pattern;

import org.junit.Ignore;
import org.junit.Test;

import com.ldbc.driver.Operation;
import com.ldbc.driver.dshini.generator.MultiRequestLogEntryReader;
import com.ldbc.driver.dshini.generator.RequestLogEntry;
import com.ldbc.driver.dshini.generator.RequestLogEntryException;
import com.ldbc.driver.dshini.generator.RequestLogOperationGenerator;
import com.ldbc.driver.dshini.operations.MatchableException;
import com.ldbc.driver.dshini.operations.MatchableOperationCreator;
import com.ldbc.driver.dshini.operations.OperationMatcher;

import static org.junit.Assert.*;

public class RequestLogReaderTest
{
    @Ignore
    @Test
    public void requestLogEntryReaderAllLogsExactlyOneEntryTest() throws MatchableException, RequestLogEntryException
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

        OperationMatcher matcher = new OperationMatcher();
        MatchableOperationCreator[] operations = RequestLogOperationGenerator.operations( matcher );
        matcher.setOperations( operations );

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

    @Ignore
    @Test
    public void performanceTest() throws MatchableException, RequestLogEntryException
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

        // When
        int entries = 0;
        long startTime = System.nanoTime();
        while ( requestLogReader.hasNext() )
        {
            RequestLogEntry entry = requestLogReader.next();
            entries++;
        }
        long endTime = System.nanoTime();

        long runtime = ( endTime - startTime ) / 1000000000;
        System.out.println( String.format( "Runtime: %s seconds", runtime ) );
        System.out.println( String.format( "Entries: %s ", entries ) );
        System.out.println( String.format( "Throughput: %s (entries/second)", entries / runtime ) );

        // Then
        assertEquals( 13043166l, entries );
    }
}
