package com.ldbc.driver.dshini;

import java.io.File;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.ldbc.driver.Operation;
import com.ldbc.driver.dshini.generator.Matchable;
import com.ldbc.driver.dshini.generator.MatchableException;
import com.ldbc.driver.dshini.generator.OperationMatcher;
import com.ldbc.driver.dshini.generator.RequestLogOperationGenerator;
import com.ldbc.driver.dshini.log.RequestLogEntry;
import com.ldbc.driver.dshini.log.RequestLogEntryException;
import com.ldbc.driver.dshini.log.RequestLogEntryReader;
import com.ldbc.driver.dshini.operations.Dshini;

import static com.ldbc.driver.util.TestUtils.getResource;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.matchers.JUnitMatchers.*;

public class RequestLogReaderTest
{
    @Test
    public void repairedLogFilesShouldContainExpectedEntries()
    {
        // Given
        final File repairedRequestLogFile1 = new File(
                "logs/dshini-request-logs-2013-04-29/request-ip-10-196-162-95-repaired entries.log" );
        final File repairedRequestLogFile2 = new File(
                "logs/dshini-request-logs-2013-04-29/request-ip-10-84-146-61-repaired entries.log" );

        // When
        int entryCount1 = 0;
        RequestLogEntryReader reader1 = new RequestLogEntryReader( repairedRequestLogFile1 );
        while ( reader1.hasNext() )
        {
            reader1.next();
            entryCount1++;
        }
        int entryCount2 = 0;
        RequestLogEntryReader reader2 = new RequestLogEntryReader( repairedRequestLogFile2 );
        while ( reader2.hasNext() )
        {
            reader2.next();
            entryCount2++;
        }

        // Then
        assertThat( entryCount1, is( 2 ) );
        assertThat( entryCount2, is( 2 ) );
    }

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
        assertThat( reader.hasNext(), is( false ) );
        assertThat( reader.hasNext(), is( false ) );
    }

    // TODO
    // @Ignore
    @Test
    public void everyEntryShouldMapToOnlyOneOperation() throws MatchableException, RequestLogEntryException
    {
        // Given
        RequestLogEntryReader[] readers = TestUtils.allLogReaders();

        OperationMatcher matcher = new OperationMatcher();
        Iterable<Matchable<RequestLogEntry>> operations = RequestLogOperationGenerator.operations();
        matcher.setMatchables( operations );

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
                    assertEquals( String.format( "Incorrect number of matched operations, expected 1, got %s: %s\n%s",
                            matchedOperations.size(), matchedOperations.toString(), entry.toString() ), 1,
                            matchedOperations.size() );
                }
                catch ( RequestLogEntryException e )
                {
                    String errMsg = String.format( "Error parsing log entry\n%s", entry.toString() );
                    System.out.println( errMsg );
                }

            }
        }
    }
}
