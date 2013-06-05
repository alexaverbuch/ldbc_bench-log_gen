package com.ldbc.driver.dshini.db.neo4j.handlers.emdedded;

import com.ldbc.driver.OperationHandler;
import com.ldbc.driver.OperationResult;
import com.ldbc.driver.dshini.db.neo4j.Neo4jDb;
import com.ldbc.driver.dshini.operations.IndexQueryGetNodeOperationFactory.IndexQueryGetNodeOperation;

public class EmbeddedIndexQueryGetNodeOperationHandler extends OperationHandler<IndexQueryGetNodeOperation>
{

    @Override
    protected OperationResult executeOperation( IndexQueryGetNodeOperation operation )
    {
        Neo4jDb db = ( (Neo4jDb) getDb() );
        int resultCode = -1;
        Object result = null;
        return operation.buildResult( resultCode, result );
    }

}
