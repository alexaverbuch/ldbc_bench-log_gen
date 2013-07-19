package com.ldbc.driver.dshini.workloads;

import java.util.List;

import com.ldbc.driver.Operation;
import com.ldbc.driver.OperationHandler;
import com.ldbc.driver.util.Pair;

public interface AllOperationHandlersProvider
{
    public List<Pair<Class<? extends Operation<?>>, Class<? extends OperationHandler<?>>>> allOperationHandlers();
}
