package com.ldbc.driver.dshini.db.neo4j.emdedded;

import org.neo4j.helpers.collection.MapUtil;

import com.ldbc.driver.OperationHandler;
import com.ldbc.driver.OperationResult;
import com.ldbc.driver.dshini.operations.CreateNodeOperationFactory.CreateNodeOperation;

public class EmbeddedCreateNodeOperationHandler extends OperationHandler<CreateNodeOperation>
{
    @Override
    protected OperationResult executeOperation( CreateNodeOperation operation )
    {
        Neo4jConnectionStateEmbedded connection = (Neo4jConnectionStateEmbedded) getDbConnectionState();
        final String queryString = "CREATE n = {properties}";
        int nodesCreated = connection.getExecutionEngine().execute( queryString,
                MapUtil.map( "properties", operation.getProperties() ) ).getQueryStatistics().getNodesCreated();

        int resultCode = ( 1 == nodesCreated ) ? 0 : -1;
        int result = nodesCreated;
        return operation.buildResult( resultCode, result );
    }
}
