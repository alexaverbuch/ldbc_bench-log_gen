package com.ldbc.driver.dshini.db.neo4j.emdedded;

import com.ldbc.driver.OperationHandler;
import com.ldbc.driver.dshini.db.neo4j.emdedded.handlers.batch.BatchOperationHandler;
import com.ldbc.driver.dshini.operations.batch.BatchOperationFactory.BatchOperation;
import com.ldbc.driver.dshini.workloads.DshiniBatchCommands;

public class Neo4jDshiniBatchCommandsEmbedded extends DshiniBatchCommands
{

    @Override
    public Class<? extends OperationHandler<BatchOperation>> getBatchOperationHandler()
    {
        return BatchOperationHandler.class;
    }

}
