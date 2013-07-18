package com.ldbc.driver.dshini.workloads;

import com.ldbc.driver.OperationHandler;
import com.ldbc.driver.dshini.operations.batch.BatchOperationFactory.BatchOperation;

public interface DshiniBatchCommands
{
    public Class<? extends OperationHandler<BatchOperation>> getBatchOperationHandler();
}
