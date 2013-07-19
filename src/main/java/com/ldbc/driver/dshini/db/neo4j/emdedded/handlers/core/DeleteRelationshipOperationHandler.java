package com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.core;

import org.neo4j.helpers.collection.MapUtil;

import com.ldbc.driver.OperationHandler;
import com.ldbc.driver.OperationResult;
import com.ldbc.driver.dshini.db.neo4j.emdedded.Neo4jConnectionStateEmbedded;
import com.ldbc.driver.dshini.operations.core.DeleteRelationshipOperationFactory.DeleteRelationshipOperation;

public class DeleteRelationshipOperationHandler extends OperationHandler<DeleteRelationshipOperation>
{
    @Override
    protected OperationResult executeOperation( DeleteRelationshipOperation operation )
    {
        Neo4jConnectionStateEmbedded connection = (Neo4jConnectionStateEmbedded) getDbConnectionState();
        final String queryString = "START r=rel({relId}) DELETE r";
        int deletedRelationships = connection.getExecutionEngine().execute( queryString,
                MapUtil.map( "relId", operation.getRelationshipId() ) ).getQueryStatistics().getDeletedRelationships();

        int resultCode = ( 1 == deletedRelationships ) ? 0 : -1;
        int result = deletedRelationships;
        return operation.buildResult( resultCode, result );
    }
}
