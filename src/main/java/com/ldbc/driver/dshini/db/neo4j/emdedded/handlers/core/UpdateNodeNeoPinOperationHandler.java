package com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.core;

import org.neo4j.helpers.collection.MapUtil;

import com.ldbc.driver.OperationHandler;
import com.ldbc.driver.OperationResult;
import com.ldbc.driver.dshini.db.neo4j.emdedded.Neo4jConnectionStateEmbedded;
import com.ldbc.driver.dshini.operations.core.UpdateNodeNeoPinOperationFactory.UpdateNodeNeoPinOperation;

public class UpdateNodeNeoPinOperationHandler extends OperationHandler<UpdateNodeNeoPinOperation>
{
    @Override
    protected OperationResult executeOperation( UpdateNodeNeoPinOperation operation )
    {
        Neo4jConnectionStateEmbedded connection = (Neo4jConnectionStateEmbedded) getDbConnectionState();
        final String queryString = "START n=node({nodeId}) SET n={properties}";
        int propertiesSet = connection.getExecutionEngine().execute( queryString,
                MapUtil.map( "nodeId", operation.getNodeId(), "properties", operation.getProperties() ) ).getQueryStatistics().getPropertiesSet();

        int resultCode = ( propertiesSet == operation.getProperties().size() ) ? 0 : -1;
        int result = propertiesSet;
        return operation.buildResult( resultCode, result );
    }
}
