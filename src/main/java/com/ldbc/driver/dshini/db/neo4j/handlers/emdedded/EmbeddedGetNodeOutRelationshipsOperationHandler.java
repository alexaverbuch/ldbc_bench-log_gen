package com.ldbc.driver.dshini.db.neo4j.handlers.emdedded;

import com.ldbc.driver.OperationHandler;
import com.ldbc.driver.OperationResult;
import com.ldbc.driver.dshini.db.neo4j.Neo4jDb;
import com.ldbc.driver.dshini.operations.GetNodesOutRelationshipsOperationFactory.GetNodeOutRelationshipsOperation;

public class EmbeddedGetNodeOutRelationshipsOperationHandler extends OperationHandler<GetNodeOutRelationshipsOperation>
{

    @Override
    protected OperationResult executeOperation( GetNodeOutRelationshipsOperation operation )
    {
        Neo4jDb db = ( (Neo4jDb) getDb() );
        int resultCode = -1;
        Object result = null;
        return operation.buildResult( resultCode, result );
    }

}
