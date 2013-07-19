package com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.index;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;

import com.ldbc.driver.OperationHandler;
import com.ldbc.driver.OperationResult;
import com.ldbc.driver.dshini.db.neo4j.emdedded.Neo4jConnectionStateEmbedded;
import com.ldbc.driver.dshini.operations.index.AddNodeToNeoProductIndexOperationFactory.AddNodeToNeoProductIndexOperation;

public class AddNodeToNeoProductIndexOperationHandler extends OperationHandler<AddNodeToNeoProductIndexOperation>
{
    @Override
    protected OperationResult executeOperation( AddNodeToNeoProductIndexOperation operation )
    {
        int resultCode;
        int result;

        Neo4jConnectionStateEmbedded connection = (Neo4jConnectionStateEmbedded) getDbConnectionState();
        Transaction tx = connection.getDb().beginTx();
        try
        {
            Node node = connection.getDb().getNodeById( operation.getNodeId() );
            connection.getDb().index().forNodes( operation.getIndexName() ).add( node, operation.getKey(),
                    operation.getValue() );

            resultCode = 0;
            result = 1;
        }
        catch ( Exception e )
        {
            resultCode = -1;
            result = 0;
        }
        finally
        {
            tx.finish();
        }

        return operation.buildResult( resultCode, result );
    }
}
