package com.ldbc.driver.generator.dshini;

import java.io.File;
import java.util.Iterator;

import org.apache.log4j.Logger;

import com.ldbc.driver.generator.GeneratorException;

// TODO add support for ordering by time
// TODO instead of MultiEntryReader do MultiGenerator?
// TODO with MultiGenerator ordering would benefit ALL Generators
class MultiRequestLogEntryReader implements Iterator<RequestLogEntry>
{
    private static final Logger logger = Logger.getLogger( MultiRequestLogEntryReader.class );

    private final RequestLogEntryReader[] requesLogReaders;

    private int currentRequestLogReaderIndex = 0;

    public MultiRequestLogEntryReader( File... requestLogFiles )
    {
        if ( requestLogFiles.length <= 0 )
        {
            String errMsg = "Expected at least 1 request log file input parameter";
            logger.error( errMsg );
            throw new GeneratorException( errMsg );
        }

        requesLogReaders = new RequestLogEntryReader[requestLogFiles.length];
        for ( int i = 0; i < requesLogReaders.length; i++ )
        {
            requesLogReaders[i] = new RequestLogEntryReader( requestLogFiles[i] );
        }
    }

    @Override
    public boolean hasNext()
    {
        if ( requesLogReaders[currentRequestLogReaderIndex].hasNext() ) return true;
        currentRequestLogReaderIndex++;
        if ( currentRequestLogReaderIndex < requesLogReaders.length )
        {
            return requesLogReaders[currentRequestLogReaderIndex].hasNext();
        }
        return false;
    }

    @Override
    public RequestLogEntry next()
    {
        return requesLogReaders[currentRequestLogReaderIndex].next();
    }

    @Override
    public void remove()
    {
        throw new UnsupportedOperationException();
    }
}
