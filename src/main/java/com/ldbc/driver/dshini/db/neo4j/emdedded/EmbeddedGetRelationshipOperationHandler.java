package com.ldbc.driver.dshini.db.neo4j.emdedded;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.neo4j.graphdb.Relationship;
import org.neo4j.helpers.collection.MapUtil;

import com.ldbc.driver.OperationHandler;
import com.ldbc.driver.OperationResult;
import com.ldbc.driver.dshini.operations.GetRelationshipOperationFactory.GetRelationshipOperation;

public class EmbeddedGetRelationshipOperationHandler extends OperationHandler<GetRelationshipOperation>
{

    @Override
    protected OperationResult executeOperation( GetRelationshipOperation operation )
    {
        Neo4jConnectionStateEmbedded connection = (Neo4jConnectionStateEmbedded) getDbConnectionState();
        final String queryString = "START r=rel({relId}) RETURN r";
        Iterator<Relationship> relationships = connection.getExecutionEngine().execute( queryString,
                MapUtil.map( "relId", operation.getRelationshipId() ) ).columnAs( "r" );

        int resultCode;
        long result;
        try
        {
            result = relationships.next().getId();
            resultCode = 0;
        }
        catch ( NoSuchElementException e )
        {
            result = -1;
            resultCode = -1;
        }
        return operation.buildResult( resultCode, result );
    }

}
