package com.ldbc.driver.dshini.log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;


public class RequestLogEntryReader implements Iterator<RequestLogEntry>
{
    private static final Logger logger = Logger.getLogger( RequestLogEntryReader.class );

    private final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final Pattern SEMICOLON_PATTERN = Pattern.compile( ";" );

    private final String NORMAL_START_BRACKET = "\"\\[\"\"";
    private final String WEIRD_START_BRACKET_1 = "\"a:4:\\{i:0;s:24:\"";
    private final String WEIRD_START_BRACKET_2 = "\"a:2:\\{i:0;s:8:\"";
    private final Pattern HEADERS_START_PATTERN = Pattern.compile( String.format( "(%s)|(%s)|(%s)",
            WEIRD_START_BRACKET_1, WEIRD_START_BRACKET_2, NORMAL_START_BRACKET ) );

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
        if ( true == closed ) return false;
        next = ( next == null ) ? nextDshiniRequestLogEntry() : next;
        if ( null == next )
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
                return ( null == requestLogLine ) ? null : parseDshiniRequestLogEntry( requestLogLine );
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
        return null;
    }

    private RequestLogEntry parseDshiniRequestLogEntry( String requestLogLine ) throws RequestLogEntryException
    {
        // parse first 3 columns (time, httpMethod, url)
        int limit = 4;
        String[] tokens = SEMICOLON_PATTERN.split( requestLogLine, limit );
        if ( tokens.length != limit )
        {
            String errMsg = String.format(
                    "File [%s] Line [%s] - unexpected token count [expected=%s, actual=%s]\nTokens: %s\n%s",
                    requestLogFile.getName(), lineNumber, limit, tokens.length, Arrays.toString( tokens ),
                    requestLogLine );
            badLineCount++;
            throw new RequestLogEntryException( errMsg );
        }

        // parse last 2 columns (description, httpHeaders)
        Matcher matcher = HEADERS_START_PATTERN.matcher( tokens[3] );
        if ( false == matcher.find() )
        {
            String errMsg = String.format( "File [%s] Line [%s] - HTTP Headers column not found in end of line\n%s",
                    requestLogFile.getName(), lineNumber, requestLogLine );
            badLineCount++;
            throw new RequestLogEntryException( errMsg );
        }

        String time = tokens[0];
        String httpMethod = tokens[1];
        String url = tokens[2];
        String description = tokens[3].substring( 0, matcher.start() - 1 );
        String httpHeaders = tokens[3].substring( matcher.start() );

        try
        {
            return new RequestLogEntry( OBJECT_MAPPER, time, httpMethod, url, description, httpHeaders );
        }
        catch ( RequestLogEntryException e )
        {
            String errMsg = String.format( "File [%s] Line [%s] - Error creating RequestLogEntry from line\n%s",
                    requestLogFile.getName(), lineNumber, requestLogLine );
            badLineCount++;
            logger.error( errMsg );
            throw new RequestLogEntryException( errMsg );
        }
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
