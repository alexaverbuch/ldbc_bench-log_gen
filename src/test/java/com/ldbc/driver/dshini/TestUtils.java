package com.ldbc.driver.dshini;

import java.io.File;

import com.ldbc.driver.dshini.generator.RequestLogOperationGenerator;
import com.ldbc.driver.dshini.log.RequestLogEntryReader;

public class TestUtils
{
    public static RequestLogOperationGenerator[] allLogGenerators()
    {
        File[] logFiles = allLogFiles();
        RequestLogOperationGenerator[] logGenerators = new RequestLogOperationGenerator[logFiles.length];
        for ( int i = 0; i < logFiles.length; i++ )
        {
            logGenerators[i] = new RequestLogOperationGenerator( logFiles[i] );
        }
        return logGenerators;
    }

    public static RequestLogEntryReader[] allLogReaders()
    {
        File[] logFiles = allLogFiles();
        RequestLogEntryReader[] logReaders = new RequestLogEntryReader[logFiles.length];
        for ( int i = 0; i < logFiles.length; i++ )
        {
            logReaders[i] = new RequestLogEntryReader( logFiles[i] );
        }
        return logReaders;
    }

    public static File[] allLogFiles()
    {
        File f1 = new File( "logs/dshini-request-logs-2013-04-29/request-ip-10-3-55-181.log" );
        File f2 = new File( "logs/dshini-request-logs-2013-04-29/request-ip-10-196-162-95.log" );
        File f2repairedEntries = new File(
                "logs/dshini-request-logs-2013-04-29/request-ip-10-196-162-95-repaired entries.log" );
        File f3 = new File( "logs/dshini-request-logs-2013-04-29/request-ip-10-76-97-169.log" );
        File f4 = new File( "logs/dshini-request-logs-2013-04-29/request-ip-10-84-146-61.log" );
        File f4repairedEntries = new File(
                "logs/dshini-request-logs-2013-04-29/request-ip-10-84-146-61-repaired entries.log" );
        File f5 = new File( "logs/dshini-request-logs-2013-04-29/request-ip-10-90-59-251.log" );
        File f6 = new File( "logs/dshini-request-logs-2013-04-29/request-ip-10-98-203-214.log" );

        // request-domU-12-31-39-0A-B5-D1 (binary?)
        // request-domU-12-31-39-0B-D0-81
        // request-domU-12-31-39-0C-90-81

        return new File[] { f1, f2, f2repairedEntries, f3, f4, f4repairedEntries, f5, f6 };
    }
}
