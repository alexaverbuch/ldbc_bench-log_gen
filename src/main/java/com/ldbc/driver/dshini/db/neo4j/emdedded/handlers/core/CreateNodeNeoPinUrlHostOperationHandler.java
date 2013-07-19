package com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.core;

import org.neo4j.helpers.collection.MapUtil;

import com.ldbc.driver.OperationHandler;
import com.ldbc.driver.OperationResult;
import com.ldbc.driver.dshini.db.neo4j.emdedded.Neo4jConnectionStateEmbedded;
import com.ldbc.driver.dshini.operations.core.CreateNodeNeoPinUrlHostOperationFactory.CreateNodeNeoPinUrlHostOperation;

public class CreateNodeNeoPinUrlHostOperationHandler extends OperationHandler<CreateNodeNeoPinUrlHostOperation>
{
    @Override
    protected OperationResult executeOperation( CreateNodeNeoPinUrlHostOperation operation )
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
