package com.ldbc.driver.dshini.db.neo4j.handlers.emdedded;

import com.ldbc.driver.OperationHandler;
import com.ldbc.driver.OperationResult;
import com.ldbc.driver.dshini.db.neo4j.Neo4jDb;
import com.ldbc.driver.dshini.operations.CreateNodeOperationFactory.CreateNodeOperation;

public class EmbeddedCreateNodeOperationHandler extends OperationHandler<CreateNodeOperation>
{

    @Override
    protected OperationResult executeOperation( CreateNodeOperation operation )
    {
        Neo4jDb db = ( (Neo4jDb) getDb() );
        int resultCode = -1;
        Long result = null;
        return operation.buildResult( resultCode, result );
    }

}