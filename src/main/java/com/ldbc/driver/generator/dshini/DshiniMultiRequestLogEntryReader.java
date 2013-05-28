package com.ldbc.driver.generator.dshini;

import java.io.File;
import java.util.Iterator;

import org.apache.log4j.Logger;

import com.ldbc.driver.generator.GeneratorException;

class DshiniMultiRequestLogEntryReader implements Iterator<DshiniRequestLogEntry>
{
    private static final Logger logger = Logger.getLogger( DshiniMultiRequestLogEntryReader.class );

    private final DshiniRequestLogEntryReader[] requesLogReaders;

    private int currentRequestLogReaderIndex = 0;

    public DshiniMultiRequestLogEntryReader( File... requestLogFiles )
    {
        if ( requestLogFiles.length <= 0 )
        {
            String errMsg = "Expected at least 1 request log file input parameter";
            logger.error( errMsg );
            throw new GeneratorException( errMsg );
        }

        requesLogReaders = new DshiniRequestLogEntryReader[requestLogFiles.length];
        for ( int i = 0; i < requesLogReaders.length; i++ )
        {
            requesLogReaders[i] = new DshiniRequestLogEntryReader( requestLogFiles[i] );
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
    public DshiniRequestLogEntry next()
    {
        return requesLogReaders[currentRequestLogReaderIndex].next();
    }

    @Override
    public void remove()
    {
        throw new UnsupportedOperationException();
    }
}
