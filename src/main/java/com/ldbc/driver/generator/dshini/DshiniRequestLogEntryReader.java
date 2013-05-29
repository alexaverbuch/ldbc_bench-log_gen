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

import com.ldbc.driver.generator.GeneratorException;

class DshiniRequestLogEntryReader implements Iterator<DshiniRequestLogEntry>
{
    private static final Logger logger = Logger.getLogger( DshiniRequestLogEntryReader.class );

    private final File requestLogFile;
    private final BufferedReader requestLogReader;
    private final Pattern semiColonPattern = Pattern.compile( ";" );

    private DshiniRequestLogEntry next = null;
    private boolean closed = false;

    public DshiniRequestLogEntryReader( File requestLogFile )
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
            throw new GeneratorException( errMsg, e.getCause() );
        }
    }

    @Override
    public boolean hasNext()
    {
        next = ( next == null ) ? nextDshiniRequestLogEntry() : next;
        if ( ( null == next ) && ( false == closed ) )
        {
            closed = true;
            closeReader();
        }
        return ( null != next );
    }

    @Override
    public DshiniRequestLogEntry next()
    {
        next = ( null == next ) ? nextDshiniRequestLogEntry() : next;
        if ( null == next ) throw new NoSuchElementException( "No more request logs to read" );
        DshiniRequestLogEntry tempNext = next;
        next = null;
        return tempNext;
    }

    @Override
    public void remove()
    {
        throw new UnsupportedOperationException();
    }

    // // Return null if nothing left
    // private DshiniRequestLogEntry nextDshiniRequestLogEntry()
    // {
    // String requestLogLine = null;
    // try
    // {
    // requestLogLine = requestLogReader.readLine();
    // }
    // catch ( IOException e )
    // {
    // String errMsg = String.format(
    // "Error retrieving next request log entry from file [%s]",
    // requestLogReader );
    // logger.error( errMsg, e );
    // throw new GeneratorException( errMsg, e.getCause() );
    // }
    // return ( null == requestLogLine ) ? null : buildDshiniRequestLogEntry(
    // requestLogLine );
    // }

    // Return null if nothing left
    private DshiniRequestLogEntry nextDshiniRequestLogEntry()
    {
        DshiniRequestLogEntry logEntry = null;
        boolean exceptionThrown;
        String requestLogLine = null;
        do
        {
            exceptionThrown = false;
            try
            {
                requestLogLine = requestLogReader.readLine();
                logEntry = ( null == requestLogLine ) ? null : buildDshiniRequestLogEntry( requestLogLine );
            }
            catch ( IOException e )
            {
                String errMsg = String.format( "Error retrieving next request log entry from file [%s]",
                        requestLogReader );
                logger.error( errMsg, e );
                throw new GeneratorException( errMsg, e.getCause() );
            }
            catch ( GeneratorException e )
            {
                exceptionThrown = true;
            }
        }
        while ( exceptionThrown == true );
        return logEntry;
    }

    private DshiniRequestLogEntry buildDshiniRequestLogEntry( String requestLogLine )
    {
        // TODO change to limit = 5 ?
        int limit = 0;
        String[] tokens = semiColonPattern.split( requestLogLine, limit );
        if ( tokens.length != 5 )
        {
            String errMsg = String.format( "Unexpected token count in %s [expected=5, actual=%s]\n%s\n%s",
                    requestLogFile.getName(), tokens.length, Arrays.toString( tokens ), requestLogLine );
            logger.error( errMsg );
            throw new GeneratorException( errMsg );
        }

        String time = tokens[0].replace( "\"", "" );
        String httpMethod = tokens[1];
        String url = tokens[2];
        String cypher = tokens[3];
        String httpHeaders = tokens[4];

        return new DshiniRequestLogEntry( time, httpMethod, url, cypher, httpHeaders );
    }

    private boolean closeReader()
    {
        if ( null != requestLogReader ) try
        {
            requestLogReader.close();
        }
        catch ( IOException e )
        {
            String errMsg = String.format( "Error closing request log file [%s]", requestLogReader );
            logger.error( errMsg, e );
            throw new GeneratorException( errMsg, e.getCause() );
        }
        return true;
    }
}
