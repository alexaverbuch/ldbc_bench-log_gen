package com.ldbc.driver.generator.dshini;

import java.io.File;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.ldbc.driver.Operation;
import com.ldbc.driver.generator.Generator;
import com.ldbc.driver.generator.GeneratorException;
import com.ldbc.driver.workloads.dshini.BatchOperation;
import com.ldbc.driver.workloads.dshini.CypherOperation;
import com.ldbc.driver.workloads.dshini.IndexNodeDeleteOperation;
import com.ldbc.driver.workloads.dshini.IndexNodeQueryGetOperation;
import com.ldbc.driver.workloads.dshini.IndexNodePostOperation;
import com.ldbc.driver.workloads.dshini.NodeDeleteOperation;
import com.ldbc.driver.workloads.dshini.NodeGetOperation;
import com.ldbc.driver.workloads.dshini.NodePostOperation;
import com.ldbc.driver.workloads.dshini.NodePutOperation;
import com.ldbc.driver.workloads.dshini.RelationshipDeleteOperation;
import com.ldbc.driver.workloads.dshini.RelationshipGetOperation;

public class RequestLogOperationGenerator extends Generator<Operation<?>>
{
    private final Pattern CYPHER_PATTERN = Pattern.compile( ".*db/data/cypher.*" );
    private final Pattern BATCH_PATTERN = Pattern.compile( ".*db/data/batch.*" );
    private final Pattern NODE_INDEX_QUERY_PATTERN = Pattern.compile( ".*db/data/index/node/.*\\?query=.*" );
    private final Pattern NODE_INDEX_PATTERN = Pattern.compile( ".*db/data/index/node/.*" );
    private final Pattern NODE_PATTERN = Pattern.compile( ".*db/data/node.*" );
    private final Pattern RELATIONSHIP_PATTERN = Pattern.compile( ".*db/data/relationship.*" );

    private static final Logger logger = Logger.getLogger( RequestLogOperationGenerator.class );

    private final MultiRequestLogEntryReader requestLogReader;

    // TODO make protected?
    public RequestLogOperationGenerator( File... requestLogFiles )
    {
        super( null );
        this.requestLogReader = new MultiRequestLogEntryReader( requestLogFiles );
    }

    @Override
    protected Operation<?> doNext() throws GeneratorException
    {
        if ( false == requestLogReader.hasNext() ) return null;
        RequestLogEntry entry = requestLogReader.next();
        try
        {
            long time = RequestLogParsingUtils.parseTime( entry.getTime() );

            if ( isCypher( entry ) ) return CypherOperation.create( time, entry.getDescription() );
            // TODO
            if ( isBatch( entry ) ) return BatchOperation.create( time, entry.getDescription() );
            // TODO
            if ( isIndexNodeQueryGet( entry ) )
                return IndexNodeQueryGetOperation.create( time, entry.getDescription() );
            // TODO
            if ( isIndexNodePost( entry ) ) return IndexNodePostOperation.create( time, entry.getDescription() );
            // TODO
            if ( isIndexNodeDelete( entry ) ) return IndexNodeDeleteOperation.create( time, entry.getDescription() );
            // TODO
            if ( isNodeGet( entry ) ) return NodeGetOperation.create( time, entry.getDescription() );
            // TODO
            if ( isNodePost( entry ) ) return NodePostOperation.create( time, entry.getDescription() );
            // TODO
            if ( isNodePut( entry ) ) return NodePutOperation.create( time, entry.getDescription() );
            // TODO
            if ( isNodeDelete( entry ) ) return NodeDeleteOperation.create( time, entry.getDescription() );
            // TODO
            if ( isRelationshipGet( entry ) ) return RelationshipGetOperation.create( time, entry.getDescription() );
            // TODO
            if ( isRelationshipDelete( entry ) )
                return RelationshipDeleteOperation.create( time, entry.getDescription() );
        }
        catch ( RequestLogEntryException e )
        {
            String errMsg = String.format( "Error parsing log entry: %s", entry.toString() );
            logger.error( errMsg );
            throw new GeneratorException( errMsg );
        }

        String errMsg = String.format( "No entry matching Dshini log entry: %s", entry.toString() );
        logger.error( errMsg );
        throw new GeneratorException( errMsg );
    }

    /*
     *  DShini Operation Types
     */

    private boolean isCypher( RequestLogEntry requestLogEntry )
    {
        return CYPHER_PATTERN.matcher( requestLogEntry.getUrl() ).matches();
    }

    private boolean isBatch( RequestLogEntry requestLogEntry )
    {
        return BATCH_PATTERN.matcher( requestLogEntry.getUrl() ).matches();
    }

    private boolean isIndexNodeQueryGet( RequestLogEntry requestLogEntry )
    {
        return requestLogEntry.getHttpMethod().equals( "GET" )
               && NODE_INDEX_QUERY_PATTERN.matcher( requestLogEntry.getUrl() ).matches();
    }

    private boolean isIndexNodePost( RequestLogEntry requestLogEntry )
    {
        return requestLogEntry.getHttpMethod().equals( "POST" )
               && NODE_INDEX_PATTERN.matcher( requestLogEntry.getUrl() ).matches();
    }

    private boolean isIndexNodeDelete( RequestLogEntry requestLogEntry )
    {
        return requestLogEntry.getHttpMethod().equals( "DELETE" )
               && NODE_INDEX_PATTERN.matcher( requestLogEntry.getUrl() ).matches();
    }

    private boolean isNodeGet( RequestLogEntry requestLogEntry )
    {
        return requestLogEntry.getHttpMethod().equals( "GET" )
               && NODE_PATTERN.matcher( requestLogEntry.getUrl() ).matches();
    }

    private boolean isNodePost( RequestLogEntry requestLogEntry )
    {
        return requestLogEntry.getHttpMethod().equals( "POST" )
               && NODE_PATTERN.matcher( requestLogEntry.getUrl() ).matches();
    }

    private boolean isNodePut( RequestLogEntry requestLogEntry )
    {
        return requestLogEntry.getHttpMethod().equals( "PUT" )
               && NODE_PATTERN.matcher( requestLogEntry.getUrl() ).matches();
    }

    private boolean isNodeDelete( RequestLogEntry requestLogEntry )
    {
        return requestLogEntry.getHttpMethod().equals( "DELETE" )
               && NODE_PATTERN.matcher( requestLogEntry.getUrl() ).matches();
    }

    private boolean isRelationshipGet( RequestLogEntry requestLogEntry )
    {
        return requestLogEntry.getHttpMethod().equals( "GET" )
               && RELATIONSHIP_PATTERN.matcher( requestLogEntry.getUrl() ).matches();
    }

    private boolean isRelationshipDelete( RequestLogEntry requestLogEntry )
    {
        return requestLogEntry.getHttpMethod().equals( "DELETE" )
               && RELATIONSHIP_PATTERN.matcher( requestLogEntry.getUrl() ).matches();
    }
}
