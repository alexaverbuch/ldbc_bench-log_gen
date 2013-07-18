package com.ldbc.driver.dshini.db.neo4j.emdedded;

import java.util.NoSuchElementException;

import org.neo4j.graphdb.Node;
import org.neo4j.helpers.collection.MapUtil;

import com.ldbc.driver.DbException;
import com.ldbc.driver.OperationHandler;
import com.ldbc.driver.OperationResult;
import com.ldbc.driver.dshini.operations.core.GetNodeOperationFactory.GetNodeOperation;

public class EmbeddedGetNodeOperationHandler extends OperationHandler<GetNodeOperation>
{

    @Override
    protected OperationResult executeOperation( GetNodeOperation operation ) throws DbException
    {
        int resultCode;
        long result;

        Neo4jConnectionStateEmbedded connection = (Neo4jConnectionStateEmbedded) getDbConnectionState();
        final String queryString = "START n=node({nodeId}) RETURN n";

        try
        {
            Node node = (Node) connection.getExecutionEngine().execute( queryString,
                    MapUtil.map( "nodeId", operation.getNodeId() ) ).columnAs( "n" ).next();

            resultCode = 0;
            result = node.getId();
        }
        catch ( NoSuchElementException nsee )
        {
            // throw new DbException( "Key not found", nsee.getCause() );
            resultCode = -1;
            result = -1;
        }

        return operation.buildResult( resultCode, result );
    }

}
