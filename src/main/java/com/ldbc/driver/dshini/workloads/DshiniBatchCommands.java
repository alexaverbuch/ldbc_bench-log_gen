package com.ldbc.driver.dshini.workloads;

import java.util.ArrayList;
import java.util.List;

import com.ldbc.driver.Operation;
import com.ldbc.driver.OperationHandler;
import com.ldbc.driver.dshini.operations.batch.BatchOperationFactory.BatchOperation;
import com.ldbc.driver.util.Pair;

public abstract class DshiniBatchCommands implements AllOperationHandlersProvider
{
    @Override
    public final List<Pair<Class<? extends Operation<?>>, Class<? extends OperationHandler<?>>>> allOperationHandlers()
    {
        List<Pair<Class<? extends Operation<?>>, Class<? extends OperationHandler<?>>>> handlers = new ArrayList<Pair<Class<? extends Operation<?>>, Class<? extends OperationHandler<?>>>>();
        handlers.add( new Pair( BatchOperation.class, getBatchOperationHandler() ) );
        return handlers;
    }

    public abstract Class<? extends OperationHandler<BatchOperation>> getBatchOperationHandler();
}
