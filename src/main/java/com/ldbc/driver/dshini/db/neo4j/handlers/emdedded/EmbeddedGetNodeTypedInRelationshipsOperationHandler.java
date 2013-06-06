package com.ldbc.driver.dshini.db.neo4j.handlers.emdedded;

import org.apache.log4j.Logger;

import com.ldbc.driver.OperationHandler;
import com.ldbc.driver.OperationResult;
import com.ldbc.driver.dshini.db.neo4j.Neo4jDb;
import com.ldbc.driver.dshini.operations.GetNodesTypedInRelationshipsOperationFactory.GetNodeTypedInRelationshipsOperation;

public class EmbeddedGetNodeTypedInRelationshipsOperationHandler extends
        OperationHandler<GetNodeTypedInRelationshipsOperation>
{
    private static final Logger logger = Logger.getLogger( EmbeddedGetNodeTypedInRelationshipsOperationHandler.class );

    @Override
    protected OperationResult executeOperation( GetNodeTypedInRelationshipsOperation operation )
    {
        Neo4jDb db = ( (Neo4jDb) getDb() );
        int resultCode = -1;
        Object result = null;
        // logger.info( String.format( "Executing handler for %s",
        // getClass().getSimpleName() ) );
        return operation.buildResult( resultCode, result );
    }

}
