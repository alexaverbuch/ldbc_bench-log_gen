package com.ldbc.driver.generator.dshini;

import java.io.File;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.ldbc.driver.Operation;
import com.ldbc.driver.generator.Generator;
import com.ldbc.driver.generator.GeneratorException;
import com.ldbc.driver.workloads.dshini.BatchOperation;
import com.ldbc.driver.workloads.dshini.CypherOperation;
import com.ldbc.driver.workloads.dshini.IndexDeleteNodeOperation;
import com.ldbc.driver.workloads.dshini.IndexQueryGetNodeOperation;
import com.ldbc.driver.workloads.dshini.AddNodeToIndexOperation;
import com.ldbc.driver.workloads.dshini.DeleteNodeOperation;
import com.ldbc.driver.workloads.dshini.GetNodeOperation;
import com.ldbc.driver.workloads.dshini.PostNodeOperation;
import com.ldbc.driver.workloads.dshini.NodePutOperation;
import com.ldbc.driver.workloads.dshini.DeleteRelationshipOperation;
import com.ldbc.driver.workloads.dshini.GetRelationshipOperation;

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
            if ( isIndexQueryGetNode( entry ) ) return IndexQueryGetNodeOperation.create( time, entry.getUrl() );
            // TODO
            if ( isAddNodeToIndex( entry ) )
                return AddNodeToIndexOperation.create( time, entry.getUrl(), entry.getDescription() );
            // TODO
            if ( isIndexDeleteNode( entry ) ) return IndexDeleteNodeOperation.create( time, entry.getDescription() );
            // TODO
            if ( isGetNode( entry ) ) return GetNodeOperation.create( time, entry.getDescription() );
            // TODO
            if ( isPostNode( entry ) ) return PostNodeOperation.create( time, entry.getDescription() );
            // TODO
            if ( isPutNode( entry ) ) return NodePutOperation.create( time, entry.getDescription() );
            // TODO
            if ( isDeleteNode( entry ) ) return DeleteNodeOperation.create( time, entry.getDescription() );
            // TODO
            if ( isGetRelationship( entry ) ) return GetRelationshipOperation.create( time, entry.getDescription() );
            // TODO
            if ( isDeleteRelationship( entry ) )
                return DeleteRelationshipOperation.create( time, entry.getDescription() );
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

    private boolean isIndexQueryGetNode( RequestLogEntry requestLogEntry )
    {
        return requestLogEntry.getHttpMethod().equals( "GET" )
               && NODE_INDEX_QUERY_PATTERN.matcher( requestLogEntry.getUrl() ).matches();
    }

    private boolean isAddNodeToIndex( RequestLogEntry requestLogEntry )
    {
        return requestLogEntry.getHttpMethod().equals( "POST" )
               && NODE_INDEX_PATTERN.matcher( requestLogEntry.getUrl() ).matches();
    }

    private boolean isIndexDeleteNode( RequestLogEntry requestLogEntry )
    {
        return requestLogEntry.getHttpMethod().equals( "DELETE" )
               && NODE_INDEX_PATTERN.matcher( requestLogEntry.getUrl() ).matches();
    }

    private boolean isGetNode( RequestLogEntry requestLogEntry )
    {
        return requestLogEntry.getHttpMethod().equals( "GET" )
               && NODE_PATTERN.matcher( requestLogEntry.getUrl() ).matches();
    }

    private boolean isPostNode( RequestLogEntry requestLogEntry )
    {
        return requestLogEntry.getHttpMethod().equals( "POST" )
               && NODE_PATTERN.matcher( requestLogEntry.getUrl() ).matches();
    }

    private boolean isPutNode( RequestLogEntry requestLogEntry )
    {
        return requestLogEntry.getHttpMethod().equals( "PUT" )
               && NODE_PATTERN.matcher( requestLogEntry.getUrl() ).matches();
    }

    private boolean isDeleteNode( RequestLogEntry requestLogEntry )
    {
        return requestLogEntry.getHttpMethod().equals( "DELETE" )
               && NODE_PATTERN.matcher( requestLogEntry.getUrl() ).matches();
    }

    private boolean isGetRelationship( RequestLogEntry requestLogEntry )
    {
        return requestLogEntry.getHttpMethod().equals( "GET" )
               && RELATIONSHIP_PATTERN.matcher( requestLogEntry.getUrl() ).matches();
    }

    private boolean isDeleteRelationship( RequestLogEntry requestLogEntry )
    {
        return requestLogEntry.getHttpMethod().equals( "DELETE" )
               && RELATIONSHIP_PATTERN.matcher( requestLogEntry.getUrl() ).matches();
    }
}
