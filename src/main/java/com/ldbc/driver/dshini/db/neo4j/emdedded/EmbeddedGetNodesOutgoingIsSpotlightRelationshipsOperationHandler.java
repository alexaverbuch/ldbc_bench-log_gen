package com.ldbc.driver.dshini.db.neo4j.emdedded;

import java.util.Iterator;

import org.neo4j.graphdb.Relationship;
import org.neo4j.helpers.collection.IteratorUtil;
import org.neo4j.helpers.collection.MapUtil;

import com.ldbc.driver.OperationHandler;
import com.ldbc.driver.OperationResult;
import com.ldbc.driver.dshini.operations.core.GetNodesOutgoingIsSpotlightRelationshipsOperationFactory.GetNodesOutgoingIsSpotlightRelationshipsOperation;

public class EmbeddedGetNodesOutgoingIsSpotlightRelationshipsOperationHandler extends
        OperationHandler<GetNodesOutgoingIsSpotlightRelationshipsOperation>
{

    @Override
    protected OperationResult executeOperation( GetNodesOutgoingIsSpotlightRelationshipsOperation operation )
    {
        Neo4jConnectionStateEmbedded connection = (Neo4jConnectionStateEmbedded) getDbConnectionState();
        final String queryString = String.format( "START n=node({nodeId}) " + "MATCH (n)-[r:%s]->() RETURN r",
                operation.getRelationshipType() );
        Iterator<Relationship> relationships = connection.getExecutionEngine().execute( queryString,
                MapUtil.map( "nodeId", operation.getNodeId() ) ).columnAs( "r" );

        int resultCode = 0;
        int result = IteratorUtil.count( relationships );
        return operation.buildResult( resultCode, result );
    }

}
