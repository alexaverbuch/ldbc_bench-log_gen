package com.ldbc.driver.dshini.db.neo4j.emdedded.unimplemented;

import com.ldbc.driver.OperationHandler;
import com.ldbc.driver.OperationResult;
import com.ldbc.driver.dshini.db.neo4j.emdedded.Neo4jConnectionStateEmbedded;
import com.ldbc.driver.dshini.operations.CypherOperationFactory.CypherOperation;

public class EmbeddedCypherOperationHandler extends OperationHandler<CypherOperation>
{
    @Override
    protected OperationResult executeOperation( CypherOperation operation )
    {
        Neo4jConnectionStateEmbedded connection = (Neo4jConnectionStateEmbedded) getDbConnectionState();
        connection.getDb();
        connection.getExecutionEngine();
        int resultCode = -1;
        Object result = null;
        return operation.buildResult( resultCode, result );
    }

}