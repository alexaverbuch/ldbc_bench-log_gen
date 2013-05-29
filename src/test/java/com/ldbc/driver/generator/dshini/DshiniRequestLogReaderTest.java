package com.ldbc.driver.generator.dshini;

import java.io.File;
import java.io.IOException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DshiniRequestLogReaderTest
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
}
