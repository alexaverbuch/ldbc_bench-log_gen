package com.ldbc.driver.dshini.db.neo4j.emdedded;

import java.util.Iterator;

import org.neo4j.graphdb.Relationship;
import org.neo4j.helpers.collection.IteratorUtil;
import org.neo4j.helpers.collection.MapUtil;

import com.ldbc.driver.OperationHandler;
import com.ldbc.driver.OperationResult;
import com.ldbc.driver.dshini.operations.core.GetNodesOutRelationshipsOperationFactory.GetNodeOutRelationshipsOperation;

public class EmbeddedGetNodeOutRelationshipsOperationHandler extends OperationHandler<GetNodeOutRelationshipsOperation>
{

    @Override
    protected OperationResult executeOperation( GetNodeOutRelationshipsOperation operation )
    {
        Neo4jConnectionStateEmbedded connection = (Neo4jConnectionStateEmbedded) getDbConnectionState();
        final String queryString = "START n=node({nodeId}) " + "MATCH (n)-[r]->() RETURN r";

        Iterator<Relationship> outRelationships = connection.getExecutionEngine().execute( queryString,
                MapUtil.map( "nodeId", operation.getNodeId() ) ).columnAs( "r" );

        int resultCode = 0;
        int result = IteratorUtil.count( outRelationships );
        return operation.buildResult( resultCode, result );
    }
}
