package com.ldbc.driver.dshini.db.neo4j.emdedded;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;

import com.ldbc.driver.OperationHandler;
import com.ldbc.driver.OperationResult;
import com.ldbc.driver.dshini.operations.AddNodeToIndexOperationFactory.AddNodeToIndexOperation;

public class EmbeddedAddNodeToIndexOperationHandler extends OperationHandler<AddNodeToIndexOperation>
{
    @Override
    protected OperationResult executeOperation( AddNodeToIndexOperation operation )
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
