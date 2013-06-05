package com.ldbc.driver.dshini.db.neo4j.handlers.emdedded;

import com.ldbc.driver.OperationHandler;
import com.ldbc.driver.OperationResult;
import com.ldbc.driver.dshini.db.neo4j.Neo4jDb;
import com.ldbc.driver.dshini.operations.CreateRelationshipOperationFactory.CreateRelationshipOperation;

public class EmbeddedCreateRelationshipOperationHandler extends OperationHandler<CreateRelationshipOperation>
{

    @Override
    protected OperationResult executeOperation( CreateRelationshipOperation operation )
    {
        Neo4jDb db = ( (Neo4jDb) getDb() );
        int resultCode = -1;
        Long result = null;
        return operation.buildResult( resultCode, result );
    }

}
