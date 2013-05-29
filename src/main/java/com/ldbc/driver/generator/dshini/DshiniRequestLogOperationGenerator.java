package com.ldbc.driver.generator.dshini;

import java.io.File;

import org.apache.log4j.Logger;

import com.ldbc.driver.Operation;
import com.ldbc.driver.generator.Generator;
import com.ldbc.driver.generator.GeneratorException;
import com.ldbc.driver.workloads.dshini.DshiniBatchOperation;
import com.ldbc.driver.workloads.dshini.DshiniCypherOperation;
import com.ldbc.driver.workloads.dshini.DshiniIndexDeleteOperation;
import com.ldbc.driver.workloads.dshini.DshiniIndexGetOperation;
import com.ldbc.driver.workloads.dshini.DshiniIndexPostOperation;
import com.ldbc.driver.workloads.dshini.DshiniNodeDeleteOperation;
import com.ldbc.driver.workloads.dshini.DshiniNodeGetOperation;
import com.ldbc.driver.workloads.dshini.DshiniNodePostOperation;
import com.ldbc.driver.workloads.dshini.DshiniNodePutOperation;
import com.ldbc.driver.workloads.dshini.DshiniRelationshipDeleteOperation;
import com.ldbc.driver.workloads.dshini.DshiniRelationshipGetOperation;

public class DshiniRequestLogOperationGenerator extends Generator<Operation<?>>
{
    private static final Logger logger = Logger.getLogger( DshiniRequestLogOperationGenerator.class );

    private final DshiniMultiRequestLogEntryReader requestLogReader;

    // TODO make protected?
    public DshiniRequestLogOperationGenerator( File... requestLogFiles )
    {
        super( null );
        this.requestLogReader = new DshiniMultiRequestLogEntryReader( requestLogFiles );
    }

    @Override
    protected Operation<?> doNext() throws GeneratorException
    {
        DshiniRequestLogEntry requestLogEntry = ( requestLogReader.hasNext() ) ? requestLogReader.next() : null;
        if ( null == requestLogEntry ) return null;
        if ( isCypher( requestLogEntry ) ) return new DshiniCypherOperation();
        if ( isBatch( requestLogEntry ) ) return new DshiniBatchOperation();
        if ( isIndexGet( requestLogEntry ) ) return new DshiniIndexGetOperation();
        if ( isIndexPost( requestLogEntry ) ) return new DshiniIndexPostOperation();
        if ( isIndexDelete( requestLogEntry ) ) return new DshiniIndexDeleteOperation();
        if ( isNodeGet( requestLogEntry ) ) return new DshiniNodeGetOperation();
        if ( isNodePost( requestLogEntry ) ) return new DshiniNodePostOperation();
        if ( isNodePut( requestLogEntry ) ) return new DshiniNodePutOperation();
        if ( isNodeDelete( requestLogEntry ) ) return new DshiniNodeDeleteOperation();
        if ( isRelationshipGet( requestLogEntry ) ) return new DshiniRelationshipGetOperation();
        if ( isRelationshipDelete( requestLogEntry ) ) return new DshiniRelationshipDeleteOperation();

        String errMsg = String.format( "No entry matching Dshini log entry: %s", requestLogEntry.toString() );
        logger.error( errMsg );
        throw new GeneratorException( errMsg );
    }

    /*
     *  DShini Operation Types
     */

    private boolean isCypher( DshiniRequestLogEntry requestLogEntry )
    {
        return requestLogEntry.getHttpMethod().equals( "POST" ) && requestLogEntry.getUrl().endsWith( "db/data/cypher" );
    }

    private boolean isBatch( DshiniRequestLogEntry requestLogEntry )
    {
        return requestLogEntry.getHttpMethod().equals( "POST" ) && requestLogEntry.getUrl().endsWith( "db/data/batch" );
    }

    private boolean isIndexGet( DshiniRequestLogEntry requestLogEntry )
    {
        return requestLogEntry.getHttpMethod().equals( "GET" ) && requestLogEntry.getUrl().contains( "db/data/index" );
    }

    private boolean isIndexPost( DshiniRequestLogEntry requestLogEntry )
    {
        return requestLogEntry.getHttpMethod().equals( "POST" ) && requestLogEntry.getUrl().contains( "db/data/index" );
    }

    private boolean isIndexDelete( DshiniRequestLogEntry requestLogEntry )
    {
        return requestLogEntry.getHttpMethod().equals( "DELETE" )
               && requestLogEntry.getUrl().contains( "db/data/index" );
    }

    private boolean isNodeGet( DshiniRequestLogEntry requestLogEntry )
    {
        return requestLogEntry.getHttpMethod().equals( "GET" ) && requestLogEntry.getUrl().contains( "db/data/node" );
    }

    private boolean isNodePost( DshiniRequestLogEntry requestLogEntry )
    {
        return requestLogEntry.getHttpMethod().equals( "POST" ) && requestLogEntry.getUrl().contains( "db/data/node" );
    }

    private boolean isNodePut( DshiniRequestLogEntry requestLogEntry )
    {
        return requestLogEntry.getHttpMethod().equals( "PUT" ) && requestLogEntry.getUrl().contains( "db/data/node" );
    }

    private boolean isNodeDelete( DshiniRequestLogEntry requestLogEntry )
    {
        return requestLogEntry.getHttpMethod().equals( "DELETE" ) && requestLogEntry.getUrl().contains( "db/data/node" );
    }

    private boolean isRelationshipGet( DshiniRequestLogEntry requestLogEntry )
    {
        return requestLogEntry.getHttpMethod().equals( "GET" )
               && requestLogEntry.getUrl().contains( "db/data/relationship" );
    }

    private boolean isRelationshipDelete( DshiniRequestLogEntry requestLogEntry )
    {
        return requestLogEntry.getHttpMethod().equals( "DELETE" )
               && requestLogEntry.getUrl().contains( "db/data/relationship" );
    }
}
