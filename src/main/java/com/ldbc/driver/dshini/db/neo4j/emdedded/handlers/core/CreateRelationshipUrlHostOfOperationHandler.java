package com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.core;

import org.neo4j.helpers.collection.MapUtil;

import com.ldbc.driver.OperationHandler;
import com.ldbc.driver.OperationResult;
import com.ldbc.driver.dshini.db.neo4j.emdedded.Neo4jConnectionStateEmbedded;
import com.ldbc.driver.dshini.operations.core.CreateRelationshipUrlHostOfOperationFactory.CreateRelationshipUrlHostOfOperation;

public class CreateRelationshipUrlHostOfOperationHandler extends OperationHandler<CreateRelationshipUrlHostOfOperation>
{
    @Override
    protected OperationResult executeOperation( CreateRelationshipUrlHostOfOperation operation )
    {
        Neo4jConnectionStateEmbedded connection = (Neo4jConnectionStateEmbedded) getDbConnectionState();

        final String queryString = String.format( "START n=node({startId}), m=node({endId}) "
                                                  + "CREATE (n)-[:%s {properties}}]->(m)",
                operation.getRelationshipType() );

        int relationshipsCreated = connection.getExecutionEngine().execute(
                queryString,
                MapUtil.map( "startId", operation.getStartNodeId(), "endId", operation.getEndNodeId(), "properties",
                        operation.getProperties() ) ).getQueryStatistics().getRelationshipsCreated();

        int resultCode = ( 1 == relationshipsCreated ) ? 0 : -1;
        int result = relationshipsCreated;
        return operation.buildResult( resultCode, result );
    }
}
