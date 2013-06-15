package com.ldbc.driver.dshini.db.neo4j.emdedded;

import java.util.Iterator;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.helpers.collection.IteratorUtil;

import com.ldbc.driver.OperationHandler;
import com.ldbc.driver.OperationResult;
import com.ldbc.driver.dshini.operations.IndexQueryGetNodeOperationFactory.IndexQueryGetNodeOperation;

public class EmbeddedIndexQueryGetNodeOperationHandler extends OperationHandler<IndexQueryGetNodeOperation>
{

    @Override
    protected OperationResult executeOperation( IndexQueryGetNodeOperation operation )
    {
        int resultCode;
        int result;

        Neo4jConnectionStateEmbedded connection = (Neo4jConnectionStateEmbedded) getDbConnectionState();
        Transaction tx = connection.getDb().beginTx();
        try
        {
            Iterator<Node> nodes = connection.getDb().index().forNodes( operation.getIndexName() ).query(
                    operation.getIndexQuery() );

            resultCode = 0;
            result = IteratorUtil.count( nodes );
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
