package com.ldbc.driver.dshini.db.neo4j.handlers.emdedded;

import org.apache.log4j.Logger;

import com.ldbc.driver.OperationHandler;
import com.ldbc.driver.OperationResult;
import com.ldbc.driver.dshini.db.neo4j.Neo4jDb;
import com.ldbc.driver.dshini.operations.CypherOperationFactory.CypherOperation;

public class EmbeddedCypherOperationHandler extends OperationHandler<CypherOperation>
{
    private static Logger logger = Logger.getLogger( EmbeddedCypherOperationHandler.class );

    @Override
    protected OperationResult executeOperation( CypherOperation operation )
    {
//        logger.info( operation.getScheduledStartTime() );
        Neo4jDb db = ( (Neo4jDb) getDb() );
        int resultCode = -1;
        Object result = null;
        return operation.buildResult( resultCode, result );
    }

}
