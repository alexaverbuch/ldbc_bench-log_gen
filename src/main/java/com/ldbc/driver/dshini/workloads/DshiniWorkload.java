package com.ldbc.driver.dshini.workloads;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.google.common.base.Predicate;
import com.ldbc.driver.Operation;
import com.ldbc.driver.Workload;
import com.ldbc.driver.WorkloadException;
import com.ldbc.driver.dshini.generator.RequestLogOperationGenerator;
import com.ldbc.driver.dshini.operations.Dshini;
import com.ldbc.driver.dshini.operations.Dshini.ReadWrite;
import com.ldbc.driver.generator.Generator;
import com.ldbc.driver.generator.GeneratorBuilder;
import com.ldbc.driver.generator.wrapper.FilterGeneratorWrapper;
import com.ldbc.driver.generator.wrapper.FutureTimeShiftGeneratorWrapper;
import com.ldbc.driver.generator.wrapper.OrderedMultiGeneratorWrapper;
import com.ldbc.driver.util.temporal.Duration;
import com.ldbc.driver.util.temporal.Time;

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
    public Generator<Operation<?>> createLoadOperations( GeneratorBuilder generatorBuilder ) throws WorkloadException
    {
        throw new UnsupportedOperationException( "Load phase not implemented for DShini workload" );
    }

    @Override
    public Generator<Operation<?>> createTransactionalOperations( GeneratorBuilder generatorBuilder )
            throws WorkloadException
    {
        // NOTE test with READ operations to avoid mutating database (it's BIG)
        List<Class<? extends Operation<?>>> operations = new ArrayList<Class<? extends Operation<?>>>();
        operations.addAll( Dshini.operations().core( ReadWrite.READ ) );
        operations.addAll( Dshini.operations().index( ReadWrite.READ ) );
        operations.addAll( Dshini.operations().cypher( ReadWrite.READ ) );
        // operations.addAll( Dshini.operations().batch( ReadWrite.READ ) );

        Predicate<Operation<?>> filter = new IncludeOnlyClassesPredicate<Operation<?>>( operations );

        RequestLogOperationGenerator[] requestLogReaderGenerators = new RequestLogOperationGenerator[logFiles.length];
        for ( int i = 0; i < logFiles.length; i++ )
        {
            requestLogReaderGenerators[i] = new RequestLogOperationGenerator( logFiles[i] );
        }
        Generator<Operation<?>> generator = OrderedMultiGeneratorWrapper.operationsByScheduledStartTime( 1,
                requestLogReaderGenerators );

        // NOTE remove wrapper to execute all operation types, e.g.:
        // Generator<Operation<?>> filteredGenerator = generator;
        Generator<Operation<?>> filteredGenerator = new FilterGeneratorWrapper<Operation<?>>( generator, filter );

        Generator<Operation<?>> futureShiftedGenerator = new FutureTimeShiftGeneratorWrapper( filteredGenerator,
                Time.now().plus( Duration.fromSeconds( 2 ) ) );
        return futureShiftedGenerator;
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

class IncludeOnlyClassesPredicate<T> implements Predicate<T>
{
    private final Set<Class<? extends Operation<?>>> includedItems;

    public IncludeOnlyClassesPredicate( Class<? extends Operation<?>>... includedItems )
    {
        this( Arrays.asList( includedItems ) );
    }

    public IncludeOnlyClassesPredicate( List<Class<? extends Operation<?>>> includedItems )
    {
        this.includedItems = new HashSet<Class<? extends Operation<?>>>( includedItems );
    }

    @Override
    public boolean apply( T input )
    {
        return true == includedItems.contains( input.getClass() );
    }
}
