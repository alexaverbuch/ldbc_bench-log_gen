package com.ldbc.driver.dshini.log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DshiniOperationWriter
{
    private final String LOG_DELIMITER = ";";
    private BufferedWriter bufferedLogWriter = null;

    public DshiniOperationWriter( File logFile ) throws RequestLogOperationWriterException
    {
        try
        {
            if ( logFile.exists() )
            {
                logFile.delete();
            }
            logFile.createNewFile();

            bufferedLogWriter = new BufferedWriter( new FileWriter( logFile ) );
            writeHeaders();
        }
        catch ( IOException e )
        {
            throw new RequestLogOperationWriterException( "Error instantiating log writer", e.getCause() );
        }
    }

    // Write .csv log column headers
    private void writeHeaders() throws RequestLogOperationWriterException
    {
        try
        {
            // TODO
            bufferedLogWriter.write( "column1..." );
            bufferedLogWriter.write( LOG_DELIMITER );

            // TODO
            bufferedLogWriter.write( "column2..." );
            bufferedLogWriter.write( LOG_DELIMITER );

            bufferedLogWriter.newLine();
        }
        catch ( IOException e )
        {
            throw new RequestLogOperationWriterException( "Error writing operation log headers", e.getCause() );
        }
    }

    // Write a .csv log data row
    public void logOperation( OperationLogEntry entry ) throws RequestLogOperationWriterException
    {
        try
        {
            // TODO
            bufferedLogWriter.write( "entry.something1" );
            bufferedLogWriter.write( LOG_DELIMITER );

            // TODO
            bufferedLogWriter.write( "entry.something2" );
            bufferedLogWriter.write( LOG_DELIMITER );

            bufferedLogWriter.newLine();
        }
        catch ( IOException e )
        {
            throw new RequestLogOperationWriterException( String.format( "Error writing operation %s to log",
                    entry.toString() ), e.getCause() );
        }
    }

    public void close() throws RequestLogOperationWriterException
    {
        try
        {
            bufferedLogWriter.flush();
            bufferedLogWriter.close();
        }
        catch ( IOException e )
        {
            throw new RequestLogOperationWriterException( "Error closing operation log", e.getCause() );
        }
    }
}
