package com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.core;

import org.neo4j.helpers.collection.MapUtil;

import com.ldbc.driver.OperationHandler;
import com.ldbc.driver.OperationResult;
import com.ldbc.driver.dshini.db.neo4j.emdedded.Neo4jConnectionStateEmbedded;
import com.ldbc.driver.dshini.operations.core.DeleteNodeOperationFactory.DeleteNodeOperation;

public class DeleteNodeOperationHandler extends OperationHandler<DeleteNodeOperation>
{

    @Override
    protected OperationResult executeOperation( DeleteNodeOperation operation )
    {
        Neo4jConnectionStateEmbedded connection = (Neo4jConnectionStateEmbedded) getDbConnectionState();
        final String queryString = "START n=node({nodeId}) DELETE n";
        int deletedNodes = connection.getExecutionEngine().execute( queryString,
                MapUtil.map( "nodeId", operation.getNodeId() ) ).getQueryStatistics().getDeletedNodes();

        int resultCode = ( 1 == deletedNodes ) ? 0 : -1;
        int result = deletedNodes;
        return operation.buildResult( resultCode, result );
    }
}
