package com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.index;

import java.util.Iterator;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.helpers.collection.IteratorUtil;

import com.ldbc.driver.OperationHandler;
import com.ldbc.driver.OperationResult;
import com.ldbc.driver.dshini.db.neo4j.emdedded.Neo4jConnectionStateEmbedded;
import com.ldbc.driver.dshini.operations.index.IndexQueryNodeOnNeoPinUrlHostIndexOperationFactory.IndexQueryNodeOnNeoPinUrlHostIndexOperation;

public class IndexQueryNodeOnNeoPinUrlHostIndexOperationHandler extends
        OperationHandler<IndexQueryNodeOnNeoPinUrlHostIndexOperation>
{

    @Override
    protected OperationResult executeOperation( IndexQueryNodeOnNeoPinUrlHostIndexOperation operation )
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
