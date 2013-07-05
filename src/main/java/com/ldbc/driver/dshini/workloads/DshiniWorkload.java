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
import com.ldbc.driver.dshini.operations.CypherOperationFactory.CypherOperation;
import com.ldbc.driver.dshini.operations.GetNodeOperationFactory.GetNodeOperation;
import com.ldbc.driver.dshini.operations.GetNodesOutRelationshipsOperationFactory.GetNodeOutRelationshipsOperation;
import com.ldbc.driver.dshini.operations.GetNodesRelationshipsOperationFactory.GetNodeRelationshipsOperation;
import com.ldbc.driver.dshini.operations.GetNodesTypedInRelationshipsOperationFactory.GetNodeTypedInRelationshipsOperation;
import com.ldbc.driver.dshini.operations.GetNodesTypedOutRelationshipsOperationFactory.GetNodeTypedOutRelationshipsOperation;
import com.ldbc.driver.dshini.operations.GetRelationshipOperationFactory.GetRelationshipOperation;
import com.ldbc.driver.dshini.operations.IndexQueryGetNodeOperationFactory.IndexQueryGetNodeOperation;
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
    public Generator<Operation<?>> getLoadOperations( GeneratorBuilder generatorBuilder ) throws WorkloadException
    {
        throw new UnsupportedOperationException( "Load phase not implemented for DShini workload" );
    }

    @Override
    public Generator<Operation<?>> getTransactionalOperations( GeneratorBuilder generatorBuilder )
            throws WorkloadException
    {
        // There are the read-only DShini operations
        // Test with these to avoid mutating the database (it's BIG)
        //
        // GetNodeOperation.class
        // GetNodeOutRelationshipsOperation.class
        // GetNodeRelationshipsOperation.class
        // GetNodeTypedInRelationshipsOperation.class
        // GetNodeTypedOutRelationshipsOperation.class
        // GetRelationshipOperation.class
        // IndexQueryGetNodeOperation.class

        // TODO: Will only execute operations specifies in here
        // Predicate<Operation<?>> filter = new
        // IncludeOnlyClassesPredicate<Operation<?>>( GetNodeOperation.class );
        Predicate<Operation<?>> filter = new IncludeOnlyClassesPredicate<Operation<?>>( GetNodeOperation.class,
                GetNodeOutRelationshipsOperation.class, GetNodeRelationshipsOperation.class,
                GetNodeTypedInRelationshipsOperation.class, GetNodeTypedOutRelationshipsOperation.class,
                GetRelationshipOperation.class, IndexQueryGetNodeOperation.class );

        RequestLogOperationGenerator[] requestLogReaderGenerators = new RequestLogOperationGenerator[logFiles.length];
        for ( int i = 0; i < logFiles.length; i++ )
        {
            requestLogReaderGenerators[i] = new RequestLogOperationGenerator( logFiles[i] );
        }
        Generator<Operation<?>> generator = OrderedMultiGeneratorWrapper.operationsByScheduledStartTime( 1,
                requestLogReaderGenerators );

        // TODO: remove wrapper to execute all operation types, e.g.:
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
    private final Set<Class<?>> includedItems;

    public IncludeOnlyClassesPredicate( Class<?>... includedItems )
    {
        this.includedItems = new HashSet<Class<?>>( Arrays.asList( includedItems ) );
    }

    @Override
    public boolean apply( T input )
    {
        return true == includedItems.contains( input.getClass() );
    }
}
