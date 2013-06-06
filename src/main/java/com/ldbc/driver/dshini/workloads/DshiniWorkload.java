package com.ldbc.driver.dshini.workloads;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ldbc.driver.Operation;
import com.ldbc.driver.Workload;
import com.ldbc.driver.WorkloadException;
import com.ldbc.driver.dshini.generator.RequestLogOperationGenerator;
import com.ldbc.driver.generator.Generator;
import com.ldbc.driver.generator.GeneratorBuilder;

public class DshiniWorkload extends Workload
{
    private static final Logger logger = Logger.getLogger( DshiniWorkload.class );

    private final String LOG_FILE_PATHS_ARG = "logfiles";
    private final String LOG_FILE_PATHS_SEPARATOR = ",";
    private File[] logFiles = null;

    @Override
    public void onInit( Map<String, String> properties ) throws WorkloadException
    {
        if ( false == properties.containsKey( LOG_FILE_PATHS_ARG ) )
        {
            throw new WorkloadException( String.format( "Parameters [%s] not found", LOG_FILE_PATHS_ARG ) );
        }
        String[] logFilePaths = parsePaths( properties.get( LOG_FILE_PATHS_ARG ) );
        logFiles = pathsToFiles( logFilePaths );

        logger.info( String.format( "Log files: %s", Arrays.toString( logFilePaths ) ) );
    }

    @Override
    public Generator<Operation<?>> getLoadOperations( GeneratorBuilder generatorBuilder ) throws WorkloadException
    {
        throw new UnsupportedOperationException( "Load phase not implemented for DShini workload" );
    }

    @Override
    public Generator<Operation<?>> getTransactionalOperations( GeneratorBuilder generatorBuilder )
            throws WorkloadException
    {
        return new RequestLogOperationGenerator( logFiles );
    }

    @Override
    protected void onCleanup() throws WorkloadException
    {
    }

    private File[] pathsToFiles( String[] paths )
    {
        List<File> files = new ArrayList<File>();
        for ( String path : paths )
        {
            files.add( new File( path ) );
        }
        return files.toArray( new File[files.size()] );
    }

    private String[] parsePaths( String pathsString )
    {
        List<String> paths = new ArrayList<String>();
        for ( String path : pathsString.split( LOG_FILE_PATHS_SEPARATOR ) )
        {
            paths.add( path.trim() );
        }
        return paths.toArray( new String[paths.size()] );
    }
}
