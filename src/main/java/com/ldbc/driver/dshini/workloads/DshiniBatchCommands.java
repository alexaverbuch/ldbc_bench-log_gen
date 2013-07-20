package com.ldbc.driver.dshini.workloads;

import com.ldbc.driver.Db;
import com.ldbc.driver.DbException;
import com.ldbc.driver.OperationHandler;
import com.ldbc.driver.dshini.operations.batch.BatchOperationFactory.BatchOperation;

public abstract class DshiniBatchCommands implements OperationHandlersRegistrar
{
    @Override
    public void registerHandlersWithDb( Db db ) throws DbException
    {
        db.registerOperationHandler( BatchOperation.class, getBatchOperationHandler() );
    }

    public abstract Class<? extends OperationHandler<BatchOperation>> getBatchOperationHandler();
}
