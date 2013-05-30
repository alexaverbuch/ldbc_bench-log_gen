package com.ldbc.driver.generator.dshini;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

class RequestLogEntryReader implements Iterator<RequestLogEntry>
{
    private static final Logger logger = Logger.getLogger( RequestLogEntryReader.class );

    private final int EXPECTED_TOKEN_COUNT = 5;
    private final Pattern TOKEN_SEPARATOR_PATTERN = Pattern.compile( ";" );

    private final File requestLogFile;
    private final BufferedReader requestLogReader;

    private RequestLogEntry next = null;
    private boolean closed = false;

    private int badLineCount = 0;
    private int lineNumber = -1;

    public RequestLogEntryReader( File requestLogFile )
    {
        this.requestLogFile = requestLogFile;
        try
        {
            requestLogReader = new BufferedReader( new FileReader( requestLogFile ) );
        }
        catch ( FileNotFoundException e )
        {
            String errMsg = String.format( "Error opening request log file [%s]", requestLogFile );
            logger.error( errMsg, e );
            throw new RequestLogEntryReaderException( errMsg, e.getCause() );
        }
    }

    @Override
    public boolean hasNext()
    {
        next = ( next == null ) ? nextDshiniRequestLogEntry() : next;
        if ( ( null == next ) && ( false == closed ) )
        {
            try
            {
                closed = closeReader();
            }
            catch ( RequestLogEntryReaderException e )
            {
                String errMsg = String.format( "Error encountered while closing file [%s]", requestLogFile.getName() );
                logger.error( errMsg, e );
                throw new RequestLogEntryReaderException( errMsg, e );
            }
        }
        return ( null != next );
    }

    @Override
    public RequestLogEntry next()
    {
        next = ( null == next ) ? nextDshiniRequestLogEntry() : next;
        if ( null == next ) throw new NoSuchElementException( "No more request logs to read" );
        RequestLogEntry tempNext = next;
        next = null;
        return tempNext;
    }

    @Override
    public void remove()
    {
        throw new UnsupportedOperationException();
    }

    // Return null if nothing left
    private RequestLogEntry nextDshiniRequestLogEntry()
    {
        RequestLogEntry logEntry = null;
        boolean exceptionThrown;
        String requestLogLine = null;
        // try until non-"corrupted" request log entry found
        do
        {
            exceptionThrown = false;
            try
            {
                lineNumber++;
                requestLogLine = requestLogReader.readLine();
                logEntry = ( null == requestLogLine ) ? null : parseDshiniRequestLogEntry( requestLogLine );
            }
            catch ( IOException e )
            {
                String errMsg = String.format( "Error retrieving next request log entry from file [%s]",
                        requestLogReader );
                logger.error( errMsg, e );
                throw new RequestLogEntryReaderException( errMsg, e.getCause() );
            }
            catch ( RequestLogEntryException e )
            {
                exceptionThrown = true;
            }
        }
        while ( exceptionThrown == true );
        return logEntry;
    }

    private RequestLogEntry parseDshiniRequestLogEntry( String requestLogLine ) throws RequestLogEntryException
    {
        int limit = 0;
        String[] tokens = TOKEN_SEPARATOR_PATTERN.split( requestLogLine, limit );
        if ( tokens.length != EXPECTED_TOKEN_COUNT )
        {
            String errMsg = String.format( "File [%s] Line [%s] - unexpected token count [expected=%s, actual=%s]\n"
                                           + "Tokens: %s\n" + "Line: %s", requestLogFile.getName(), lineNumber,
                    EXPECTED_TOKEN_COUNT, tokens.length, Arrays.toString( tokens ), requestLogLine );
            // TODO uncomment? output gets messy with 100s of bad records
            // logger.error( errMsg );
            badLineCount++;
            throw new RequestLogEntryException( errMsg );
        }

        String time = tokens[0];
        String httpMethod = tokens[1];
        String url = tokens[2];
        String cypher = tokens[3];
        String httpHeaders = tokens[4];
        return new RequestLogEntry( time, httpMethod, url, cypher, httpHeaders );
    }

    private boolean closeReader()
    {
        logger.info( String.format( "%s read - contained [%s/%s] bad lines", requestLogFile.getName(), badLineCount,
                lineNumber ) );
        if ( true == closed )
        {
            String errMsg = "Can not close log file multiple times";
            logger.error( errMsg );
            throw new RequestLogEntryReaderException( errMsg );
        }
        if ( null == requestLogReader )
        {
            String errMsg = "Can not close log file - reader is null";
            logger.error( errMsg );
            throw new RequestLogEntryReaderException( errMsg );
        }
        try
        {
            requestLogReader.close();
        }
        catch ( IOException e )
        {
            String errMsg = String.format( "Error closing request log file [%s]", requestLogReader );
            logger.error( errMsg, e );
            throw new RequestLogEntryReaderException( errMsg, e.getCause() );
        }
        return true;
    }
}
